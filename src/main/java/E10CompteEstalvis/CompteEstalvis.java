/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E10CompteEstalvis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author polcorresa
 */
public class CompteEstalvis {

    protected Lock mon = new ReentrantLock();
    protected double estalvis;
    protected Condition empty = mon.newCondition();


    

    public void dispositar(double quantitat) {
        mon.lock();
        estalvis += quantitat;
        empty.signalAll();
        mon.unlock();
    }

    public void extreure(double quantitat) {
           
        mon.lock();
        
        
        
        
        while (quantitat > estalvis) {
            empty.awaitUninterruptibly();
        }
        
        estalvis -= quantitat;

        mon.unlock();
    }

}
