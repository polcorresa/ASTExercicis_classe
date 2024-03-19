/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E3Comptador;

import static java.lang.Thread.sleep;

/**
 *
 * @author polcorresa
 */
public class ComptadorPositiu {
    
    protected int nCont;
    protected MutexPeterson control;
    
    public void inc(){
        control.entrar();
        nCont = nCont + 1;
        
        control.sortir();
    
    }
    
    public void dec(){
        boolean dec = false;
        while (!dec){
            control.entrar();
            if(nCont>0){
                nCont = nCont-1;
                dec = true;
            }
            control.sortir();
            try{sleep(200);}catch(InterruptedException exe){}
        }

    }
    
}
