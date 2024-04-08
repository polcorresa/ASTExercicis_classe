/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E10CompteEstalvis;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author polcorresa
 */
public class CompteEstalvisOrdenats {
    protected Lock mon = new ReentrantLock();
    protected double estalvis;
    protected Condition empty = mon.newCondition();
    protected ArrayList<Condition> treure = new ArrayList();
    protected ArrayList<Double> quantitatATreure = new ArrayList();


    

    public void dispositar(double quantitat) {
        mon.lock();
        estalvis += quantitat;
        empty.signalAll();
        mon.unlock();
    }

    public void extreure(double quantitat) {
           
        mon.lock();
        Condition cond = mon.newCondition();
        treure.add(cond);
        quantitatATreure.add(quantitat);
        
        
        
        
        
        while (quantitat > estalvis) {
            empty.awaitUninterruptibly();
        }
        
        estalvis -= quantitat;

        mon.unlock();
    }
    
}
