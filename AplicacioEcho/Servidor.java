/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package AplicacioEcho;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;

/**
 *
 * @author usuari.aula
 */

public class Servidor {

    public static void main (String [] args){
        ServidorEcho  echo = new ServidorEcho(Comms.PORT_SERVIDOR);
        System.out.println("Servidor Eco Escoltant ... Port : " + Comms.PORT_SERVIDOR + "\n");
        new Thread(echo).start();
    }
}
class OneLaneBridgeV2 {

    protected Lock mon = new ReentrantLock();
    protected Condition podenPassar = mon.newCondition();
    protected Condition bloquejats = mon.newCondition();
    protected int inside, waiting;
    protected boolean sentit, entrantEsperats, bloqueig;

    public void entrar(boolean sentitMeu) {
        try {
            mon.lock();
            while (bloqueig) {
                bloquejats.awaitUninterruptibly();
            }

            if (inside == 0) {        //Ha entrat el primer sense colarse abans d'un canvi de sentit.
                sentit = sentitMeu;
            }

            if (!bloqueig) {
                bloqueig = (sentit != sentitMeu);
            }

            waiting++;
            while (sentit != sentitMeu) {
                podenPassar.awaitUninterruptibly();
            }
            waiting--;
            //El sentit és el que toca
            inside++;
            //System.out.println("He entrado!" + sentitMeu);
        } finally {
            mon.unlock();
        }
    }

    public void sortir(boolean sentitMeu) {
        try {
            mon.lock();
            inside--;
            if (inside == 0 && waiting > 0) {
                podenPassar.signalAll();
                sentit = !sentit;
            } else if (inside == 0 && waiting == 0) {
                bloqueig = false;
                bloquejats.signalAll();
            }
            //System.out.println("He salido!" + sentitMeu);
        } finally {
            mon.unlock();
        }

    }
}

class Treballador implements Runnable {

    OneLaneBridgeV2 bridge;
    AstSocket sc;

    public Treballador(OneLaneBridgeV2 bridge, AstSocket sc) {
        this.bridge = bridge;
        this.sc = sc;
    }

    public void run() {
       
        while(true){
            String accio = sc.rebre();
            boolean sentitB = sc.rebre().equals("t");
            if (accio.equals("e")) {
                bridge.entrar(sentitB);
                System.out.println("Server: cotxe entrat" + sentitB);
                sc.enviar("Has entrat.");
            } else {
                bridge.sortir(sentitB);
                System.out.println("Server: cotxe sortit" + sentitB);
                sc.enviar("Has sortit");
            }
        }
    }

}

class ServidorEcho implements Runnable {

    protected ServerSocket ss;
    protected OneLaneBridgeV2 bridge;

    public ServidorEcho(int port) {
        this.bridge= new OneLaneBridgeV2();
        try {

            ss = new ServerSocket(port);

        } catch (IOException ex) {
            Logger.getLogger(ServidorEcho.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while (true) {
            while (true) {
                try {

                    AstSocket s = new AstSocket(ss.accept()); //accept es bloquejant, espera a que vingui una connexió i assigna un socket

                    new Thread(new Treballador(this.bridge, s)).start(); //inicia un nou fil que manté la connexió

                } catch (IOException ex) {
                    Logger.getLogger(ServidorEcho.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
