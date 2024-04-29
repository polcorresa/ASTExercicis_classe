/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E7GroupEx_assignment;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author polcorresa
 */
public class Tester extends Thread {
    protected GroupExV2 monitor;
    
    public Tester (GroupExV2 mon){
        monitor = mon;
    }
    
    public void run(){
        Random random = new Random();
        int b = random.nextInt(100, 500);
        try {
            Thread.sleep(b);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
    }
        monitor.enter();
        System.out.println("He entrado!");
        
        int a = random.nextInt(100, 500);
        try {
            Thread.sleep(a);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
    }
        monitor.exit();
        System.out.println("He salido!");
        
    }
    
}
