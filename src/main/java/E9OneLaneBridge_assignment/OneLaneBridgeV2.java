/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E9OneLaneBridge_assignment;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author polcorresa
 */
public class OneLaneBridgeV2 {

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
            System.out.println("He entrado!"+sentitMeu);
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
            System.out.println("He salido!"+sentitMeu);
        } finally {
            mon.unlock();
        }

    }
}
