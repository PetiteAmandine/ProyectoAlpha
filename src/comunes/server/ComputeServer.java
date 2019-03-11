/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes.server;

import comunes.Registro;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo
 */
public class ComputeServer implements Registro {

    public ComputeServer() {
        super();
    }
    
    public static void main(String[] args) {
        
        try {
            System.setProperty("java.security.policy", "C:/Users/velasam/Documents/ITAM/8vo Semestre/Sistemas Distribuidos/ProyectoAlpha/src/comunes/server/server.policy");
            if(System.getSecurityManager()==null)
                System.setSecurityManager(new SecurityManager());
            LocateRegistry.createRegistry(1099);
            ComputeServer engine = new ComputeServer();
            Registro stub = (Registro) UnicastRemoteObject.exportObject(engine,1099);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("Registro", stub);
        } catch (RemoteException ex) {
            Logger.getLogger(ComputeServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean registraJugador(String nombre) throws RemoteException {
        return true;
    }

    
}
