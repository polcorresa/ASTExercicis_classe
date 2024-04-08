/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E11Atracció;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author polcorresa
 */
public class MuntanyaRussa {
    
    protected int sizeVago, passatgersIn, passatgersOut;
    protected boolean sortint, entrant, enMarxa;
    protected Lock mon = new ReentrantLock();
    protected Condition vagoPle = mon.newCondition();
    protected Condition atraccioParada = mon.newCondition();
    protected Condition enCurs = mon.newCondition();
    
    public void pujar(){   
        mon.lock();
        passatgersOut++;
        while(passatgersIn==sizeVago || sortint){
            vagoPle.awaitUninterruptibly();
        }
        passatgersOut--;
        //passatgersIn<= sizeVago
        passatgersIn++;
        if(passatgersIn == sizeVago){
            vagoPle.signal();
        }
        mon.unlock();
        
    }
    public void baixar(){
        mon.lock();
        while(enMarxa){
            enCurs.awaitUninterruptibly();
        }
        sortint = true;
        passatgersIn--;
        if(passatgersIn == 0){
            sortint = false;
            vagoPle.signalAll();
        }
        mon.unlock();
    
    }
    public void arrencar(){
        mon.lock();
        if(passatgersIn != sizeVago){
            vagoPle.awaitUninterruptibly();
        }
        enMarxa = true;
        try {
            //VagoPle
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(MuntanyaRussa.class.getName()).log(Level.SEVERE, null, ex);
        }
        mon.unlock();
    }
    public void arribada(){
        mon.lock();
        enMarxa = false;
        enCurs.signalAll();
        mon.unlock();
        
    }
    
}
