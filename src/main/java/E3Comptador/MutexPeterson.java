/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E3Comptador;

/**
 *
 * @author polcorresa
 */
public class MutexPeterson implements Mutex {
    
    private int ultim;
    private boolean dinsT1;
    private boolean dinsT2;
    
    @Override
    public void entrar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void sortir() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}

interface Mutex{
    public void entrar();
    
    public void sortir();
}
