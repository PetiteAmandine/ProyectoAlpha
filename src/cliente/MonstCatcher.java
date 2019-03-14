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

    public MonstCatcher(MulticastSocket aClientSocket) {
        clientSocket = aClientSocket;
    }

    public int getMonstNum() {
        return monstNum;
    }

    @Override
    public void run() {
        byte[] mens = new byte[100];
        byte[] res;
        String pos;
        DatagramPacket messageIn;
        try {
            messageIn = new DatagramPacket(mens, mens.length);
            clientSocket.receive(messageIn);
            res = messageIn.getData();
            pos = new String(res);
            pos = pos.trim();
            monstNum = Integer.parseInt(pos);
        } catch (IOException ex) {
            Logger.getLogger(PantallaJuego.class.getName()).log(Level.SEVERE, null, ex);
        } /*finally {
                if(clientSocket !=null) 
                    clientSocket.close();
            }*/
    }

}
