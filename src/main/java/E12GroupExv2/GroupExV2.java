/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E12GroupExv2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author polcorresa
 */
public class GroupExV2 {

    protected Lock mon;
    protected int capacitat, intentantEntrar, inside;
    protected Condition potEntrar;

    protected boolean passant, ocupat;

    public GroupExV2(int n) {
        this.mon = new ReentrantLock();
        potEntrar = mon.newCondition();

        this.capacitat = n;
        ocupat = false;
        passant = false;

    }

    public void enter() {
        try {
            mon.lock();
            intentantEntrar++;

            while (passant) {
                potEntrar.awaitUninterruptibly();
            }
            while (intentantEntrar < capacitat || ocupat) {
                potEntrar.awaitUninterruptibly();
            }

            //Son N, no està ocupat i no estan passant.
            if (intentantEntrar == capacitat) {
                passant = true;
                potEntrar.signalAll();
            }
            intentantEntrar--;

            if (inside == capacitat) {
                ocupat = true;
                passant = false;
                potEntrar.signalAll();
            }
        } finally {
            mon.unlock();
        }

    }

    public void exit() {
        try {
            mon.lock();
            inside--;
            if (inside == 0) {
                ocupat = false;
                potEntrar.signalAll();
            }
        } finally {
            mon.unlock();
        }
    }

}
