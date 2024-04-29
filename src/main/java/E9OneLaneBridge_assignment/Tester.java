/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E9OneLaneBridge_assignment;

import E9OneLaneBridge_assignment.OneLaneBridge;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author polcorresa
 */
public class Tester extends Thread {
    protected OneLaneBridgeV2 monitor;
    protected boolean sentit;
    
    public Tester (OneLaneBridgeV2 mon, boolean meuSentit){
        monitor = mon;
        sentit = meuSentit;
    }
    
    public void run(){
        Random random = new Random();
        int b = random.nextInt(100, 500);
        try {
            Thread.sleep(b);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
    }
        monitor.entrar(sentit);
        
        
        int a = random.nextInt(100, 500);
        try {
            Thread.sleep(a);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
    }
        monitor.sortir(sentit);
        
        
    }
    
}
