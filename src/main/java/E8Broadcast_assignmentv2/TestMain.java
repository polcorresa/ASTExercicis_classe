/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E8Broadcast_assignmentv2;

/**
 *
 * @author polcorresa
 */
public class TestMain {

    public static void main(String[] args) {
        Broadcast monitor = new Broadcast(10);
       
        Sender sender = new Sender(monitor, "5");
        sender.start();
        Receiver receiver0 = new Receiver(monitor, 0);
        receiver0.start();

        Receiver receiver1 = new Receiver(monitor, 1);
        receiver1.start();

        Receiver receiver2 = new Receiver(monitor, 2);
        receiver2.start();

        Receiver receiver3 = new Receiver(monitor, 3);
        receiver3.start();

        Receiver receiver4 = new Receiver(monitor, 4);
        receiver4.start();

        Receiver receiver5 = new Receiver(monitor, 5);
        receiver5.start();

        Receiver receiver6 = new Receiver(monitor, 6);
        receiver6.start();

        Receiver receiver7 = new Receiver(monitor, 7);
        receiver7.start();

        Receiver receiver8 = new Receiver(monitor, 8);
        receiver8.start();

        Receiver receiver9 = new Receiver(monitor, 9);
        receiver9.start();
        Sender sender2 = new Sender(monitor, "10");
        sender2.start();

    }

}
