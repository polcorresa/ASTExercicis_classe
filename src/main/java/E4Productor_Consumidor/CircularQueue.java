/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E4Productor_Consumidor;

import java.util.Iterator;

/**
 *
 * @author polcorresa
 */
class CircularQueue<E> {
    private final E[] queue;
    private final int N;
    private int n, G;

    public CircularQueue(int N) {
        this.N = N;
        queue = (E[]) (new Object[N]);
    }


    public int size() {
        return this.N;
    }


    public int free() {
        return this.N-this.n;
    }


    public boolean empty() {
        if (n == 0) return true;
        return false;
    }


    public boolean full() {
        if (n == N)
            return true;
        return false;
    }
  
    public E peekFirst() {
        if (this.empty()) return null;
        return this.queue[G];
    }


    public E get() {
        if (this.empty()) return null;
        E e = this.queue[G];
        G = (G+1)%N;
        n--;
        return e;        
    }

    public void put(E e) {
        if(!this.full()){
        this.queue[(G+n)%N] = e;
        n++;
        }
        else {
        System.out.println("La cola está llena.");
        }
    }

    @Override
    public String toString() {
        if(this.empty()){return "";}
        int i;
        String string = "";
        
        for(i = 0; i< n;i++){
            
            string = string + this.queue[(G+i)%n].toString();
            string = string + ", ";
        }
        return string;
    }


    public Iterator<E> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator {

        private int pos = G-1;
               
        @Override
        public boolean hasNext() {
            if ((pos+1)%N == (G+n)%N) return false;
            return true;
        }

        @Override
        public E next() {
            if (this.hasNext()){
                pos = (pos+1)%N;
                E e = queue[pos];
                
                return e;
            }
            return null;
        }

        @Override
        public void remove() {
           int i;
            n--;
            for(i = pos; i<G+n; i++){
               queue[i%N] = queue [(i+1)%N];
           }
            pos--;
        }

    }
}

    

