/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amandine
 */
public class MonstCatcher extends Thread {

    private MulticastSocket clientSocket;
    private int monstNum;
    private int monstLife;

    public MonstCatcher(MulticastSocket aClientSocket) {
        clientSocket = aClientSocket;
    }

    public int getMonstNum() {
        return monstNum;
    }

    public int getMonstLife() {
        return monstLife;
    }

    @Override
    public void run() {
        byte[] mens = new byte[100];
        byte[] res;
        String pos;
        String tiempo;
        DatagramPacket messageIn;
        try {
            messageIn = new DatagramPacket(mens, mens.length);
            clientSocket.receive(messageIn);
            res = messageIn.getData();
            pos = new String(res);
            pos = pos.trim();
            monstNum = Integer.parseInt(pos);
            clientSocket.receive(messageIn);
            res = messageIn.getData();
            tiempo = new String(res);
            tiempo = tiempo.trim();
            monstLife = Integer.parseInt(tiempo);
            System.out.println(pos);
            System.out.println(tiempo);
        } catch (IOException ex) {
            Logger.getLogger(PantallaJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
