/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E4Productor_Consumidor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author polcorresa
 */
public class Buffer<E> {
    protected CircularQueue<E> queue;
    protected Lock mon;
    protected Condition noBuida, noPlena;
    
    
    public Buffer (int N){
        this.queue = new CircularQueue(N);
        this.mon = new ReentrantLock();
        this.noBuida = mon.newCondition();
        this.noPlena = mon.newCondition();
        
    }
    public void put (E object){
        mon.lock();
        while(queue.full()){
            try {
                noPlena.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.queue.put(object);
        noBuida.signal();
        mon.unlock();
    }
    public E get(){
        mon.lock();
        while(queue.empty()){
            try {
                noBuida.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        E object = queue.get();
        noPlena.signal();
        mon.unlock();
        return object;
        
                
    }
}
