/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E7GroupEx_assignment;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author polcorresa
 */
public class GroupEx {

    protected int inside;
    protected int maxThreads;
    protected int esperantMin, esperantSortida, passando;
    protected Lock mon;
    protected Condition hanSortit, sonMin, hanPassat;
    protected boolean entrant, passant;
    protected int contador;

    //n és el num de Threads accedint al recurs
    public GroupEx(int n) {
        mon = new ReentrantLock();
        hanSortit = mon.newCondition();
        sonMin = mon.newCondition();
        hanPassat = mon.newCondition();
        maxThreads = n;
        entrant = true;

    }

    //Mentre que no hi hagi N threads que vulguin accedir s'espera, si està ocupat s'espera
    public void enter() {
        mon.lock();
        passando += 1;
        while (passant) {
            hanPassat.awaitUninterruptibly();
        }
        passando -= 1;

        esperantMin += 1;
        while (esperantMin < maxThreads && !passant) {
            sonMin.awaitUninterruptibly();
        }
        if (esperantMin == maxThreads) {
            passant = true;
            for (int i = 1; i < maxThreads; i++) {
                sonMin.signal();
            }
        }
        esperantMin -= 1;
        if (esperantMin == 0) {
            passant = false;
            for (int i = 0; i < passando; i++) {
                hanPassat.signal();
            }
        }

        esperantSortida += 1;
        while (!entrant) {

            hanSortit.awaitUninterruptibly();
        }
        esperantSortida -= 1;

        inside += 1;
        contador += 1;
        if (contador % maxThreads == 0) {
            entrant = false;
        }
        mon.unlock();

    }

    //Deixa d'usar el recurs
    public void exit() {
        mon.lock();
        inside -= 1;

        if (inside == 0) {
            entrant = true;
            for (int i = 0; i < maxThreads; i++) {
                hanSortit.signal();
            }
        }
        mon.unlock();

    }

}
