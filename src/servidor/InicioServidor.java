/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import comunes.Conexiones;
import comunes.Registro;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Amandine
 */
public class InicioServidor implements Registro {

    private HashMap<String, Integer> jugadores;
    private MulticastSocket ms;
    private DatagramPacket monstruoOut;

    public InicioServidor() throws RemoteException {
        jugadores = new HashMap<String, Integer>();
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
    
    public void creaEngine () {
        System.setProperty("java.security.policy","C:/Users/Amandine/Documents/ITAM/8vo Semestre/Sistemas Distribuidos/ProyectoAlpha/src/servidor/server.policy");
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
        MulticastSocket s = null;
        try {
            InetAddress group = InetAddress.getByName("228.5.6.7");
            s = new MulticastSocket(6789);
            s.joinGroup(group);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        return s;
    }

    public void enviaMonstruo() {

    }

    public static void main(String args[]) throws RemoteException {
        InicioServidor me = new InicioServidor();
    }

}
