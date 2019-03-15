/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import comunes.Conexiones;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amandine
 */
public class Jugador extends Thread {

    private Conexiones con;
    private MulticastSocket ms;
    private PantallaJuego pj;

    public Jugador(Conexiones con) {
        try {
            this.con = con;
            ms = creaConexionUDP();
            pj = new PantallaJuego(con);
            pj.setVisible(true);
        } catch (InterruptedException ex) {
            Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MulticastSocket creaConexionUDP() {
        MulticastSocket m = null;
        try {
            InetAddress group = InetAddress.getByName(con.getMulticastIP());
            m = new MulticastSocket(con.getMulticastPort());
            m.joinGroup(group);
            System.out.println("Me conecté a UDP.");
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        return m;
    }

    public Socket creaConexionTCP() {
        Socket ts = null;
        try {
            ts = new Socket(con.getTcpIP(), con.getTcpPort());
            System.out.println("Me conecté a TCP.");
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        return ts;
    }

    @Override
    public void run() {
        MonstCatcher mc = new MonstCatcher(ms, pj);
        mc.start();
    }

}
