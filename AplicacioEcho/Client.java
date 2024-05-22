/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AplicacioEcho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pol.corresa
 */
public class Client {

    public static Random rand = new Random();
    public static void main(String[] args){
        String sentit;
        
        if(rand.nextInt()%2 == 0){
            sentit = "t";
        }else{
            sentit = "f";
        }
       
        new Thread(new Cotxe(sentit)).start();
        
    }
    
    
}

class Cotxe implements Runnable {

    protected String sentit;
    protected AstSocket sc;
    protected RepresentantBridge br;
    
    public Cotxe (String sentit){
                
        this.sentit = sentit;
        br = new RepresentantBridge(Comms.IP_SERVIDOR, Comms.PORT_SERVIDOR);
    }
    
    public void run() { 
            Random rand = new Random();
            while(true){
        try {
            Thread.sleep(rand.nextInt(10000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Cotxe.class.getName()).log(Level.SEVERE, null, ex);
        }
           br.entrar(sentit);
           try {
            Thread.sleep(rand.nextInt(10000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Cotxe.class.getName()).log(Level.SEVERE, null, ex);
        }
           br.sortir(sentit);
            }
    }
}
    
class RepresentantBridge{
    protected AstSocket sc;
    
    public RepresentantBridge(String ip, int port){
            sc = new AstSocket(ip, port);    
    }
    
    public void entrar(String sentit){
        sc.enviar("e");
        sc.enviar(sentit);
        String msg = sc.rebre();
        System.out.println(msg + sentit);
    }
    public void sortir(String sentit){
        sc.enviar("s");
        sc.enviar(sentit);
        String msg = sc.rebre();
        System.out.println(msg + sentit);
    }
}


