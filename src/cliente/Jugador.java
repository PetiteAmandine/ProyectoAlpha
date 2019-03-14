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
import javax.swing.JLabel;

/**
 *
 * @author Amandine
 */
public class Jugador extends Thread {
    
    private Conexiones con;
    private MulticastSocket ms;
    private Socket s;
    private PantallaJuego pj;
    private boolean fin;
    
    public Jugador(Conexiones con) {
        try {
            this.con = con;
            ms = creaConexionUDP();
            s = creaConexionTCP();
            pj = new PantallaJuego(con.getUser());
            pj.setVisible(true);
            fin = false;
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
        } catch(UnknownHostException e) {
            System.out.println("Sock:"+e.getMessage()); 
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        return ts;
    }
    
    @Override
    public void run() {
        try {
            while (!fin) {
                monstruoRecieve();
                System.out.println("Recibiendo monstruos.");
                if (pj.getVictima() != 0) {
                    monstruoHit(pj.getVictima());
                    pj.resetVictima();
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public void monstruoRecieve() throws InterruptedException {
        MonstCatcher mc = new MonstCatcher(ms);
        mc.start();
        mc.join();
        System.out.println(mc.getMonstNum());
        pj.pintaMonstruo(mc.getMonstNum(), true);
    }

    public void monstruoHit(int monst) throws InterruptedException {
        pj.pintaMonstruo(monst, false);
        KillSender ks = new KillSender(s, con.getUser());
        ks.start();
        ks.join();
        System.out.println("Golpe enviado.");
        pj.editaVictimas("" + ks.getVictimas());
        String ganador = ks.getGanador();
        if (ganador != null && !ganador.equals("-")) {
            pj.pintaVictoria(ganador);
            pj.parpadeo();
            fin = true;
            InicioJuego ini = new InicioJuego();
            ini.setVisible(true);
            pj.setVisible(false);
        }
    }
    
}
