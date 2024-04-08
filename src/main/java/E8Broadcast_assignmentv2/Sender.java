/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E8Broadcast_assignmentv2;

/**
 *
 * @author polcorresa
 */
public class Sender extends Thread{
    protected Object object;
    protected Broadcast mon;
    
    
    public Sender(Broadcast monitor, Object objecte){
        object = objecte;
        mon = monitor;
    }
    public void run(){
        while(true){
            
            mon.putValue(object);
            System.out.println("I'm a sender, i sent "+ object.toString());
        }
    }
    
}
