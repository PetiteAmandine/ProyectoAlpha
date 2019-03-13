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
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amandine
 */
public class InicioServidor implements Registro {

    private HashMap<String, Integer> jugadores;
    private final int n = 10;

    public HashMap<String, Integer> getJugadores() {
        return jugadores;
    }

    public InicioServidor() throws RemoteException {
        jugadores = new HashMap<>();
        LocateRegistry.createRegistry(1099);
        //creaEngine();
    }

    @Override
    public Conexiones registraJugador(String nombre) throws RemoteException {
        Conexiones c = null;
        if (!jugadores.containsKey(nombre)) {
            jugadores.put(nombre, 0);
            c = new Conexiones(nombre, "228.5.6.7", 6789, "", 0);
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
        InicioServidor me = new InicioServidor();
        me.creaEngine();
        try {
            InetAddress group = InetAddress.getByName("228.5.6.7");
            MulticastSocket ms = new MulticastSocket(6789);
            ms.joinGroup(group);
            while (true) {
                MonstSender mos = new MonstSender(ms, group);
                mos.start();
                Thread.sleep(5000);
            }
        } catch (IOException ex) {
            Logger.getLogger(InicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        KillReceiver receptor= new KillReceiver(me.getJugadores());
        receptor.run();
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

class KillReceiver extends Thread {
    
    HashMap<String, Integer> jugadores;
    final int nec=10;
    ArrayList<Integer> serverPorts;
    
    public KillReceiver(HashMap<String, Integer> jugadores) {
        this.jugadores = jugadores;
        serverPorts= new ArrayList<Integer>();
    }
    
    synchronized public boolean incKill(String jugador){
        boolean aux=false;
        if(jugadores.get(jugador)+1==nec){
            this.end(jugador);
            aux = true;
        } else
            jugadores.put(jugador, jugadores.get(jugador)+1);
        return aux;
    }
    
    synchronized private void end(String jugador){
        //Mandar mensaje de victoria a jugador ganador (parametro) y de perdiste a los demas
    }
    
    @Override
    public void run() {
        
        Socket s = null;
        try {
            int serverPort = 7896;
            
            s = new Socket("localhost", serverPort);
            //   s = new Socket("127.0.0.1", serverPort);    
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            KillReceiver receptor = new KillReceiver(me.getJugadores());
            boolean aux=false;
            String data;
            while(!aux){
                data=in.readUTF();
                aux=receptor.incKill(data);
            }
            for(Map.Entry<String, Integer> jugador:me.getJugadores().entrySet()){
                out.writeUTF("El juego ha acabado, el ganador es " + data);
            }
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
            }
        }
    }
}
