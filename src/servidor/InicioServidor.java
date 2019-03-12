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
import java.net.Socket;
import java.net.SocketException;
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
    private MulticastSocket ms;
    private InetAddress grupo;
    private final int n = 10;

    public InicioServidor() throws RemoteException {
        jugadores = new HashMap<>();
        LocateRegistry.createRegistry(1099);
        creaEngine();
        ms = creaMulticast();
    }

    @Override
    public Conexiones registraJugador(String nombre) throws RemoteException {
        Conexiones c = null;
        if (!jugadores.containsKey(nombre)) {
            jugadores.put(nombre, 0);
            c = new Conexiones(nombre, "228.5.6.7", 6789);
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

    public MulticastSocket creaMulticast() {
        MulticastSocket ms = null;
        try {
            grupo = InetAddress.getByName("228.5.6.7");
            ms = new MulticastSocket(6789);
            ms.joinGroup(grupo);
            System.out.println("Grupo multicast establecido.");
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        return ms;
    }

    public void enviaMonstruo(InetAddress group) {
        byte[] m;
        DatagramPacket messageOut;
        try {
            while (true) {
                Monstruo monst = new Monstruo();
                String pos = monst.getPosicion() + "";
                String tiempo = monst.getTiempoVida() + "";
                m = pos.getBytes();
                messageOut = new DatagramPacket(m, m.length, group, 6789);
                ms.send(messageOut);
                m = tiempo.getBytes();
                messageOut = new DatagramPacket(m, m.length, group, 6789);
                ms.send(messageOut);
                Thread.sleep(7000);
            }
        } catch (IOException ex) {
            Logger.getLogger(InicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(InicioServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) throws RemoteException {
        InicioServidor me = new InicioServidor();
        me.enviaMonstruo(me.grupo);
    }

}