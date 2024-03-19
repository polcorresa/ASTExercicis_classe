/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E1Cursa100m;

import java.util.Random;

/**
 *
 * @author polcorresa
 */
public class CursaCentMetres {
    private int posicio1;
    private int posicio2;
    
    
    public void avancaCursa (){
        Random rand = new Random();
        
        if(rand.nextBoolean()){
            posicio1= posicio1+1;
            System.out.println("Corredor 1 ha recorregut "+ posicio1 +"m");}
        else{
            posicio2=posicio2+1;
            System.out.println("Corredor 2 ha recorregut "+ posicio1 +"m");
        }
        
   }
    
    public boolean cursaAcabada (){
        return (posicio1 == 100 || posicio2 == 100);           
    }
}

 
