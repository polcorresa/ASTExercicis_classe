/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E6Intercanviador;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author polcorresa
 */
public class Intercanviador2 {

    protected Object objecte;
    protected int arribats;
    protected Lock mon;
    protected Condition itsMe = mon.newCondition();
    protected Condition acabat = mon.newCondition();

    public Object Intercanviar(Object objecteInt) {
        mon.lock();
        Object aux = null;
        arribats++;
        while (arribats>2){
            acabat.awaitUninterruptibly();
        }
        if(arribats == 1){
            objecte = objecteInt;
            itsMe.awaitUninterruptibly();
            aux = objecte;
            arribats = 0;
            acabat.signal();
            acabat.signal();
        }
        else{
            aux = objecte;
            objecte = objecteInt;
            itsMe.signalAll();  
        }
        mon.unlock();
        return aux;
        
        
    }
}
