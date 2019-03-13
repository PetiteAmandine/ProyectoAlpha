/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;

import java.io.Serializable;

/**
 *
 * @author Amandine
 */
public class Conexiones implements Serializable {
    
    private String user;
    private String multicastIP;
    private int multicastPort;

    public Conexiones(String user, String multicastIP, int multicastPort) {
        this.user = user;
        this.multicastIP = multicastIP;
        this.multicastPort = multicastPort;
    }

    public String getUser() {
        return user;
    }
    
    public String getMulticastIP() {
        return multicastIP;
    }

    public int getMulticastPort() {
        return multicastPort;
    }
    
}
