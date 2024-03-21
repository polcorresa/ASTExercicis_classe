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
public class OneLaneBridge {
    protected Lock mon = new ReentrantLock();
    protected Condition estaBuit = mon.newCondition();
    protected Condition entrantEsperados = mon.newCondition();
    protected int inside, waiting;
    protected boolean sentit, entrantEsperats;
    
    
    public void entrar(boolean sentitMeu){
    mon.lock();
    if(inside == 0){
        while(entrantEsperats){
            entrantEsperados.awaitUninterruptibly();
        }
        sentit = sentitMeu;
        inside+=1;
    }else{
        waiting += 1;
        while(sentit != sentitMeu){
            estaBuit.awaitUninterruptibly();
        }
        waiting -= 1;
        if(waiting == 0){
            entrantEsperados.signalAll();
        }
        inside += 1;
    }
    mon.unlock();
    
    
    }
    
    public void sortir(boolean sentitMeu){
    mon.lock();
    inside -= 1;
    if(inside == 0 && waiting > 0){
        sentit = !sentit;
        entrantEsperats = true;
        for(int i = 0; i<waiting; i++){
            estaBuit.signal();
        }
    }
    mon.unlock();
    
    
    }
}
