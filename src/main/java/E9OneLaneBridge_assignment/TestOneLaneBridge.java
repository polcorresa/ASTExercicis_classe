/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E9OneLaneBridge_assignment;

/**
 *
 * @author polcorresa
 */
public class TestOneLaneBridge {
    public static void main(String[] args){
        OneLaneBridge mon = new OneLaneBridge();
        Tester test1 = new Tester(mon, true);
        Tester test2 = new Tester(mon, false);
        Tester test3 = new Tester(mon, true);
        Tester test4 = new Tester(mon, false);
        Tester test5 = new Tester(mon, true);
        Tester test6 = new Tester(mon, false);
        Tester test7 = new Tester(mon, true);
        Tester test8 = new Tester(mon, false);
        Tester test9 = new Tester(mon, true);
        Tester test0 = new Tester(mon, false);
        
        
        test0.start();
        test1.start();
        test2.start();
        test3.start();
        test4.start();
        test5.start();
        test6.start();
        test7.start();
        test8.start();
        test9.start();
    }
    
}
