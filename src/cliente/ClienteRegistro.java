/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import comunes.*;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amandine
 */
public class ClienteRegistro {
    
    public Conexiones registra(String jugador) {
        Conexiones con = null;
        try {
            System.setProperty("java.security.policy", "C:/Users/Amandine/Documents/ITAM/8vo Semestre/Sistemas Distribuidos/ProyectoAlpha/src/cliente/client.policy");
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            Registry registry = LocateRegistry.getRegistry("localhost");
            Registro reg = (Registro) registry.lookup("Registro");
            con = reg.registraJugador(jugador);
        } catch (NotBoundException ex) {
            Logger.getLogger(ClienteRegistro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccessException ex) {
            Logger.getLogger(ClienteRegistro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
}
