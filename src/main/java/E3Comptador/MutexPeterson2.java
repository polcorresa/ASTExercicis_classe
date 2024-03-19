/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E3Comptador;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author polcorresa
 */
public class MutexPeterson2 implements Mutex {
    
    public int ultim;
    public boolean dins;
    private int id;
    
    
    @Override
    public void entrar() {
        this.dins = true;
        ultim = this.id;
        while(dins && this.ultim == this.id){
        }
        
    }

    @Override
    public void sortir() {
        dins = false;
    }
    
    
}

interface Mutex{
    public void entrar();
    
    public void sortir();
}
