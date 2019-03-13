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
    private String tcpIP;
    private int tcpPort;

    public Conexiones(String user, String multicastIP, int multicastPort, String tcpIP, int tcpPort) {
        this.user = user;
        this.multicastIP = multicastIP;
        this.multicastPort = multicastPort;
        this.tcpIP = tcpIP;
        this.tcpPort = tcpPort;
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

    public String getTcpIP() {
        return tcpIP;
    }

    public int getTcpPort() {
        return tcpPort;
    }

}
