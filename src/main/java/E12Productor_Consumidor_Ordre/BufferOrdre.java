/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E12Productor_Consumidor_Ordre;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author polcorresa
 */
public class BufferOrdre {

    protected Lock mon;
    protected ArrayList<Condition> condicionsOrdre;
    protected ArrayList<Object> ordreObjecte;
    protected int waiting;

    public void put(Object element) {
        try {
            mon.lock();

            ordreObjecte.add(element);
            if (waiting > 1) {
                condicionsOrdre.get(0).signal();
            }

        } finally {
            mon.unlock();
        }

    }

    public Object get() {
        try {
            mon.lock();

            Condition myCond = mon.newCondition();
            condicionsOrdre.add(myCond);
            waiting++;
            while (waiting > 1 || ordreObjecte.isEmpty()) {
                myCond.awaitUninterruptibly();
            }
            waiting--;

            //Em toca agafar
            condicionsOrdre.remove(0);
            Object myObj = ordreObjecte.get(0);
            return myObj;

        } finally {
            mon.unlock();
        }
    }
}
