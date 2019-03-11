/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import comunes.Registro;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;

/**
 *
 * @author Amandine
 */
public class ServidorRegistro implements Registro {
    
    private HashSet<String> jugadores;
    
    public ServidorRegistro() throws RemoteException {
        super();
        jugadores = new HashSet<String>();
    }

    @Override
    public boolean registraJugador(String nombre) throws RemoteException {
        if (!jugadores.contains(nombre)) {
            jugadores.add(nombre);
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        System.setProperty("java.security.policy","C:/Users/Amandine/Documents/ITAM/8vo Semestre/Sistemas Distribuidos/ProyectoAlpha/src/servidor/server.policy");
        if (System.getSecurityManager() == null) {
           System.setSecurityManager(new SecurityManager());
        }
        
        try {
            LocateRegistry.createRegistry(1099);
            String name = "Registro";
            ServidorRegistro engine = new ServidorRegistro();
            Registro stub = (Registro) UnicastRemoteObject.exportObject(engine, 1099);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            
            System.out.println("Registry engine bound");
        } catch (Exception e) {
            System.err.println("Registry engine exception:");
            e.printStackTrace();
        }
    }
    
}
