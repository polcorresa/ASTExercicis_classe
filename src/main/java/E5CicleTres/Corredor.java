/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E5CicleTres;

/**
 *
 * @author polcorresa
 */
public class Corredor extends Thread{
    public int id;
    protected Cicle3 monitor;
    public Corredor(Cicle3 monitor, int id){
        this.monitor = monitor;
        this.id = id;
    }
    

    @Override
    public void run() {
        monitor.atura();
        
    }
    
}
