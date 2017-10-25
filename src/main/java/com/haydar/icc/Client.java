package main.java.com.haydar.icc;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends Thread {

    private char myBuffer[][];
    private int id;
    private ICC imc;

    public Client(ICC imb, int id) {
        this.id = id;
        this.imc = imb;
        myBuffer = new char[1000][1000];
    }

    private void writeCopyBuffer() throws InterruptedException {
        int fno = this.imc.capture();
        System.out.printf("client %d now is writing to framebuffer : %d \n", id, fno);
        this.imc.memCopy(myBuffer, fno);
        System.out.printf("client %d now is reading from framebuffer : %d \n", id, fno);
        System.out.printf("client %d initial character %c\n" , id, myBuffer[0][0]);
    }

    @Override
    public synchronized void run() {
        super.run();
        try {
            System.out.println("Now is client: " + id);
            this.writeCopyBuffer();
        } catch (InterruptedException e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public char[][] getMyBuffer() {
        return myBuffer;
    }

    public ICC getImc() {
        return imc;
    }
}
