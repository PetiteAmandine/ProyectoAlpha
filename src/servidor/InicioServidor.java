/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import comunes.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amandine
 */
public class InicioServidor implements Registro {

    private HashMap<String, Integer> jugadores;
    private HashMap<String, Integer> puertos;
    private int puertoAct;
    private boolean ganador;

    public InicioServidor() throws RemoteException {
        jugadores = new HashMap<>();
        puertos = new HashMap<>();
        puertoAct = 1000;
        ganador = false;
        LocateRegistry.createRegistry(1099);
        creaEngine();
    }

    public HashMap<String, Integer> getJugadores() {
        return jugadores;
    }

    public HashMap<String, Integer> getPuertos() {
        return puertos;
    }

    public boolean getGanador() {
        return ganador;
    }

    synchronized public void setGanador(boolean g) {
        ganador = g;
    }

    @Override
    public Conexiones registraJugador(String nombre) throws RemoteException {
        Conexiones c = null;
        if (!jugadores.containsKey(nombre)) {
            jugadores.put(nombre, 0);
            puertos.put(nombre, puertoAct);
            c = new Conexiones(nombre, "228.5.6.7", 6789, "127.0.0.1", 1000);
            puertoAct++;
        }
        return c;
    }

    public void creaEngine() {
        System.setProperty("java.security.policy", "C:/Users/Eduardo/ProyectoAlpha/src/servidor/server.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Registry registry = LocateRegistry.getRegistry();
            InicioServidor engine = this;
            Registro stub = (Registro) UnicastRemoteObject.exportObject(engine, 1099);
            registry.rebind("Registro", stub);
            System.out.println("Registry engine bound");
        } catch (Exception e) {
            System.err.println("Registry engine exception:");
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws RemoteException, InterruptedException {
        try {
            InicioServidor me = new InicioServidor();
            ServerSocket ss = new ServerSocket(1000);
            MonstSender mos = new MonstSender();
            mos.start();
            while (true) {
                Socket socket = ss.accept();
                KillCatcher kc = new KillCatcher(socket, me.getJugadores(), me.getPuertos(), mos.getLife());
                kc.start();
                if (kc.getGanador()) {
                    me.getJugadores().clear();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(InicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class MonstSender extends Thread {

    MulticastSocket clientSocket;
    long life;
    InetAddress group;

    public MonstSender() {
        try {
            clientSocket = new MulticastSocket(6789);
            this.group = InetAddress.getByName("228.5.6.7");
            clientSocket.joinGroup(group);
            System.out.println("Creado multicast.");
        } catch (IOException ex) {
            Logger.getLogger(MonstSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public long getLife() {
        return life;
    }

    @Override
    public void run() {
        byte[] m = new byte[100];
        DatagramPacket messageOut;
        Monstruo monst;
        String pos;
        try {
            while (true) {
                System.out.println("Enviando monstruos.");
                monst = new Monstruo();
                pos = monst.getPosicion() + "";
                m = pos.getBytes();
                messageOut = new DatagramPacket(m, m.length, group, 6789);
                clientSocket.send(messageOut);
                life = monst.getTiempoVida() * 1000 + System.currentTimeMillis();
                System.out.println("Posición: " + monst.getPosicion() + " tiempo de vida: " + monst.getTiempoVida());
                Thread.sleep(monst.getTiempoVida() * 1000);
            }
        } catch (IOException ex) {
            Logger.getLogger(InicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MonstSender.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (clientSocket != null) {
                clientSocket.close();
            }
        }
    }
}

class KillCatcher extends Thread {

    long life;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    HashMap<String, Integer> jugadores;
    HashMap<String, Integer> puertos;
    final int nec = 3;
    boolean ganador = false;

    public KillCatcher(Socket socket, HashMap<String, Integer> jugadores, HashMap<String, Integer> puertos, long life) {
        try {
            this.socket = socket;
            this.jugadores = jugadores;
            this.puertos = puertos;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            this.life = life;
        } catch (IOException ex) {
            Logger.getLogger(KillCatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean getGanador() {
        return ganador;
    }

    synchronized public boolean incKill(String jugador) throws IOException {
        if (System.currentTimeMillis() > life) {
            return false;
        } else {
            jugadores.put(jugador, jugadores.get(jugador) + 1);
            return jugadores.get(jugador) == nec;
        }
    }

    @Override
    public void run() {
        String jugador;
        System.out.println("Esperando golpes.");
        while (true) {
            try {
                jugador = in.readUTF();
                ganador = incKill(jugador);
                if (ganador) {
                    out.writeUTF(jugador);
                } else {
                    out.writeUTF("-");
                }
                out.writeInt(jugadores.get(jugador));
            } catch (UnknownHostException e) {
                System.out.println("Sock:" + e.getMessage());
            } catch (EOFException e) {
                System.out.println("EOF:" + e.getMessage());
            } catch (IOException e) {
                System.out.println("IO:" + e.getMessage());
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(KillCatcher.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
