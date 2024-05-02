/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E8Broadcast_assignmentv2;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author polcorresa
 */
public class Receiver extends Thread{
    protected int id;
    protected Object objecte;
    protected Broadcast mon;
    protected Random rand = new Random();
    
    public Receiver (Broadcast monitor, int id){
        mon = monitor;
        this.id = id;
    }
    
    @Override
    public void run(){
        while(true){
            try {
                Thread.sleep(rand.nextInt(100, 500));
            } catch (InterruptedException ex) {
                Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.objecte = mon.getValue(id);
            System.out.println("I'm Thread " + id +"I received: "+objecte.toString());
            
        }
    }
    
    
}
