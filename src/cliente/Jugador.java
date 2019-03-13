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
    private Socket s;
    private PantallaJuego pj;
    
    public Jugador(Conexiones con) {
        try {
            this.con = con;
            ms = creaConexionUDP();
            s = creaConexionTCP();
            pj = new PantallaJuego(con, this);
            pj.setVisible(true);
        } catch (InterruptedException ex) {
            Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public MulticastSocket creaConexionUDP() {
        MulticastSocket ms = null;
        try {
            InetAddress group = InetAddress.getByName(con.getMulticastIP());
            ms = new MulticastSocket(con.getMulticastPort());
            ms.joinGroup(group);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        return ms;
    }
    
    public Socket creaConexionTCP() {
        Socket ts = null;
        try {
            ts = new Socket(con.getTcpIP(), con.getTcpPort());
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
            monstruoRecieve();
        } catch (InterruptedException ex) {
            Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void monstruoRecieve() throws InterruptedException {
        MonstCatcher mc = new MonstCatcher(ms);
        mc.start();
        System.out.println(mc.getMonstNum());
        System.out.println(mc.getMonstLife());
        pj.pintaMonstruo(mc.getMonstNum(), true);
        pj.pintaMonstruo(mc.getMonstNum(), false);
    }

    public void monstruoHit(javax.swing.JLabel monst) throws InterruptedException {
        monst.setEnabled(false);
        monst.setVisible(false);
        KillSender ks = new KillSender(s, con.getUser());
        ks.start();
        pj.editaVictimas("" + ks.getVictimas());
        if (ks.getVictoria())
            pj.parpadeo();
        if (ks.getFin()) {
            pj.editaVictimas("Fin de partida :(");
            pj.pintaVictoria(true);
            Thread.sleep(2000);
        }
        if (ks.getVictoria() || ks.getFin()) {
            InicioJuego ini = new InicioJuego();
            ini.setVisible(true);
            pj.setVisible(false);
        }
    }
    
}
