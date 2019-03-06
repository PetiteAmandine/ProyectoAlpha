/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import interfaces.Compute;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo
 */
public class ComputeClient {

    public static void main(String[] args) throws RemoteException {
        
        try {
            System.setProperty("java.security.policy", "file:/C:/Users/Eduardo/Documents/NetBeansProjects/JavaRMI/src/client/client.policy");
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry("localhost");
            
            Compute comp = (Compute) registry.lookup(name);
            
            System.out.println("3*3=" + comp.square(3));
            System.out.println("3*3*3=" + comp.power(3, 3));
            
        } catch (NotBoundException ex) {
            Logger.getLogger(ComputeClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccessException ex) {
            Logger.getLogger(ComputeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
