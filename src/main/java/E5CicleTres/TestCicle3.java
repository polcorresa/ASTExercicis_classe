/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E5CicleTres;

/**
 *
 * @author polcorresa
 */
public class TestCicle3 {
    public static void main(String[] args){
        
        Cicle3 monitor = new Cicle3();
        /*for(int i = 0; i< 15; i++){
            Corredor corredor = new Corredor(monitor, i);
            corredor.run();
        }*/
        Corredor corredor1 = new Corredor(monitor, 1);
        Corredor corredor2 = new Corredor(monitor, 2);
        Corredor corredor3 = new Corredor(monitor, 3);
        Corredor corredor4 = new Corredor(monitor, 4);
        Corredor corredor5 = new Corredor(monitor, 5);
        Corredor corredor6 = new Corredor(monitor, 6);
        Corredor corredor7 = new Corredor(monitor, 7);
        Corredor corredor8 = new Corredor(monitor, 8);
        Corredor corredor9 = new Corredor(monitor, 9);
        Corredor corredor10 = new Corredor(monitor, 10);
        corredor1.start();
        corredor2.start();
        corredor3.start();
        corredor5.start();
        corredor6.start();
        corredor7.start();
        corredor8.start();
        corredor9.start();
        corredor10.start();
        
        
        
                
                
        
    }
    
}
