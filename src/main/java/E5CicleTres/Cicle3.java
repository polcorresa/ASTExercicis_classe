/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E5CicleTres;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author polcorresa
 */
public class Cicle3 {

    protected int aturats;
    protected Lock mon = new ReentrantLock();
    protected Condition TresAturats = mon.newCondition();
    boolean sortint = false;

    public void atura() {
        mon.lock();
        while (sortint) {
            TresAturats.awaitUninterruptibly();
        }
        aturats = aturats + 1;

        if (aturats != 3) {
            System.out.println("M'adormo, soc el Thread aturat numero " + aturats);
            TresAturats.awaitUninterruptibly();
        } else {
            System.out.println("Desperto a tots els Threads, soc el thread numero " + aturats);
            sortint = true;
            TresAturats.signalAll();
        }
        aturats--;

        if (aturats == 0) {
            sortint = false;
            TresAturats.signalAll();
        }
        mon.unlock();

        System.out.println("He passat");

    }

}
