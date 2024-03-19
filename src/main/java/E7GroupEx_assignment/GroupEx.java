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
    protected int esperantMin, esperantSortida, waitingPassant;
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
        //--------------- SALA ESPERA 1 --------------------------
        //Deixa en espera els Threads mentre estàn passant de la sala d'espera 2
        waitingPassant += 1;
        while (passant) {
            hanPassat.awaitUninterruptibly();
        }
        waitingPassant -= 1;
        //-----------------SALA ESPERA 2--------------------------
        //Deixa en espera els Threads mentre siguin menys que els requerits per passar
        esperantMin += 1;
        while (esperantMin < maxThreads && !passant) {
            sonMin.awaitUninterruptibly();
        }
        //Activa quan han de passar els Threads i bloqueja que no n'entrin més.
        if (esperantMin == maxThreads) {
            passant = true;
            for (int i = 1; i < maxThreads; i++) {
                sonMin.signal();
            }
        }
        esperantMin -= 1;
        //Fa resset de passant i fa signal a tots els que s'havien quedat a la sala 1
        if (esperantMin == 0) {
            passant = false;
            for (int i = 0; i < waitingPassant; i++) {
                hanPassat.signal();
            }
        }
        
        //--------------- SALA ESPERA 3 -------------------------
        //Espera a que quede vacia la sala y ya hayan entrado y salido todos los Threads del bloque

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

        if (inside == 0 && contador%maxThreads == 0) {
            entrant = true;
            for (int i = 0; i < maxThreads; i++) {
                hanSortit.signal();
            }
        }
        mon.unlock();

    }

}
