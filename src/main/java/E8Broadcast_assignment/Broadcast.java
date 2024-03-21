/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E8Broadcast_assignment;

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
    protected boolean[] isDisponible;
    protected Lock mon = new ReentrantLock();
    protected Condition sent = mon.newCondition();
    protected Condition taken = mon.newCondition();

    public Broadcast(int N) {

        for (int i = 0; i < N; i++) {
            disponible[i] = true;
            isDisponible[i] = false;
        }

    }

    public void putValue(Object objecte) {
        mon.lock();

        while (!disponible.equals(isDisponible)) {
            sent.awaitUninterruptibly();
        }
        for (int i = 0; i < disponible.length; i++) {
            disponible[i] = true;
        }
        espai = objecte;
        taken.signalAll();
        mon.unlock();
    }

    public Object getValue(int i) {
        mon.lock();
        while (disponible[i] == false) {
            taken.awaitUninterruptibly();
        }
        disponible[i] = false;
        sent.signal();
        Object objecte = espai;
        mon.unlock();
        return objecte;
    }

}
