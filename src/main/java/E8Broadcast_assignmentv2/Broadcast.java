/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E8Broadcast_assignmentv2;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author polcorresa
 */
public class Broadcast {

    protected Object espai;

    protected boolean[] disponible;
    protected int agafats;
    protected Lock mon = new ReentrantLock();
    protected Condition potTransmetre = mon.newCondition();
    protected Condition potRebre = mon.newCondition();

    public Broadcast(int N) {
        agafats = N;
        disponible = new boolean[N];
        for (int i = 0; i < N; i++) {
            disponible[i] = false;
        }

    }

    public void putValue(Object objecte) {
        try {
            mon.lock();
            while (agafats < disponible.length) {
                potTransmetre.awaitUninterruptibly();
            }
            //Pot transmetre
            agafats = 0;
            for (int i = 0; i < disponible.length; i++) {
                disponible[i] = true;
            }
            espai = objecte;
            potRebre.signalAll();

        } finally {
            mon.unlock();
        }

    }

    public Object getValue(int id) {
        try {
            mon.lock();
            while (!disponible[id]) {
                potRebre.awaitUninterruptibly();
            }
            //puc rebre
            agafats++;
            Object elemNou = espai;
            disponible[id] = false;
            potTransmetre.signal();
            return elemNou;

        } finally {
            mon.unlock();
        }

    }
}
