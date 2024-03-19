/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E4Productor_Consumidor;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author polcorresa
 */
public class Productor<E> implements Runnable{
    
    public Buffer<E> buffer;
    
    public Productor(Buffer<E> buffer){
        this.buffer = buffer;
    }
   

    @Override
    public void run() {
        while(true){
            Object element = new Object();
             buffer.put((E) element);
             System.out.println("Added object: "+ element.toString());
            try {
                sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }
    
}
