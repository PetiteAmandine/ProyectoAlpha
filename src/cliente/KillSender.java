/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import comunes.Conexiones;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amandine
 */
public class KillSender extends Thread {

    private DataInputStream in;
    private DataOutputStream out;
    private Socket clientSocket;
    private String jugador, ganador;
    private boolean fin;
    private int victimas;

    public KillSender(Conexiones con) {
        clientSocket = null;
        try {
            clientSocket = new Socket(con.getTcpIP(), con.getTcpPort());
            System.out.println("Me conecté a TCP.");
            jugador = con.getUser();
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            fin = false;
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

    public boolean getFin() {
        return fin;
    }

    public int getVictimas() {
        return victimas;
    }

    public String getGanador() {
        return ganador;
    }

    @Override
    public void run() {
        try {
            out.writeUTF(jugador);
            ganador = in.readUTF();
            victimas = in.readInt();
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException ex) {
                    Logger.getLogger(KillSender.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
