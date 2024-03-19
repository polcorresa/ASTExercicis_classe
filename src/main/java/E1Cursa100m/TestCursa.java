/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E1Cursa100m;

/**
 *
 * @author polcorresa
 */
public class TestCursa {
    public static void main(String[] args){
        CursaCentMetres cursa = new CursaCentMetres();
        
        while(!cursa.cursaAcabada()){
        cursa.avancaCursa();
        }
        System.out.println("Cursa acabada");
        
    }
    
}
