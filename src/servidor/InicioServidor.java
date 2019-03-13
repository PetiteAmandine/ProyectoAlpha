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
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
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
            c = new Conexiones(nombre, "228.5.6.7", 6789, "localhost", puertoAct);
            puertoAct++;
        }
        return c;
    }

    public void creaEngine() {
        System.setProperty("java.security.policy", "C:/Users/Amandine/Documents/ITAM/8vo Semestre/Sistemas Distribuidos/ProyectoAlpha/src/servidor/server.policy");
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
        InicioServidor me = new InicioServidor();
        me.creaEngine();
        try {
            InetAddress group = InetAddress.getByName("228.5.6.7");
            MulticastSocket ms = new MulticastSocket(6789);
            ms.joinGroup(group);
            while (!me.getGanador()) {
                for (Integer p : me.getPuertos().values()) {
                    if (me.getGanador()) {
                        break;
                    }
                    ServerSocket ss = new ServerSocket(p);
                    Socket s = ss.accept();
                    MonstSender mos = new MonstSender(ms, group);
                    mos.start();
                    KillCatcher kc = new KillCatcher(s, me.getJugadores(), me.getPuertos());
                    kc.start();
                    me.setGanador(kc.getGanador());
                }
                if (!me.getGanador()) {
                    for (Integer p : me.getPuertos().values()) {
                        Socket auxs = new Socket("localhost", p);
                        DataOutputStream out = new DataOutputStream(auxs.getOutputStream());
                        out.writeBoolean(false);
                    }
                }
            }
            for (Integer p : me.getPuertos().values()) {
                Socket auxs = new Socket("localhost", p);
                DataOutputStream out = new DataOutputStream(auxs.getOutputStream());
                out.writeBoolean(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(InicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class MonstSender extends Thread {

    MulticastSocket clientSocket;
    InetAddress group;

    public MonstSender(MulticastSocket aClientSocket, InetAddress aGroup) {
        clientSocket = aClientSocket;
        group = aGroup;
    }

    @Override
    public void run() {
        byte[] m;
        DatagramPacket messageOut;
        try {
            Monstruo monst = new Monstruo();
            String pos = monst.getPosicion() + "";
            String tiempo = monst.getTiempoVida() + "";
            m = pos.getBytes();
            messageOut = new DatagramPacket(m, m.length, group, 6789);
            clientSocket.send(messageOut);
            System.out.println(pos);
            System.out.println(tiempo);
            m = tiempo.getBytes();
            messageOut = new DatagramPacket(m, m.length, group, 6789);
            clientSocket.send(messageOut);
        } catch (IOException ex) {
            Logger.getLogger(InicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class KillCatcher extends Thread {

    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    HashMap<String, Integer> jugadores;
    HashMap<String, Integer> puertos;
    final int nec = 10;
    boolean ganador = false;

    public KillCatcher(Socket aSocket, HashMap<String, Integer> jugadores, HashMap<String, Integer> puertos) {
        try {
            socket = aSocket;
            this.jugadores = jugadores;
            this.puertos = puertos;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(KillCatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    synchronized public boolean getGanador() {
        return ganador;
    }

    synchronized public boolean incKill(String jugador) throws IOException {
        if (false) {
            //si no estuve a tiempo
            return false;
        } else {
            jugadores.put(jugador, jugadores.get(jugador) + 1);
            return jugadores.get(jugador) == nec;
        }
    }

    @Override
    public void run() {
        try {
            String jugador;
            jugador = in.readUTF();
            ganador = incKill(jugador);
            out.writeBoolean(ganador);
            out.writeInt(jugadores.get(jugador));
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        }
    }
}
