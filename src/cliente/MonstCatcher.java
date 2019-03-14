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

    private PantallaJuego pj;
    private MulticastSocket clientSocket;
    private int monstNum, monstAnt;

    public MonstCatcher(MulticastSocket aClientSocket, PantallaJuego pj) {
        clientSocket = aClientSocket;
        this.pj = pj;
        monstAnt = 0;
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
            while (true) {
                messageIn = new DatagramPacket(mens, mens.length);
                clientSocket.receive(messageIn);
                pj.pintaMonstruo(monstAnt, false);
                res = messageIn.getData();
                pos = new String(res).trim();
                monstNum = Integer.parseInt(pos) > 12 ? 1 : Integer.parseInt(pos);
                System.out.println(monstNum);
                pj.pintaMonstruo(monstNum, true);
                monstAnt = monstNum;
            }
        } catch (IOException ex) {
            Logger.getLogger(PantallaJuego.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (clientSocket != null) {
                clientSocket.close();
            }
        }
    }
}

