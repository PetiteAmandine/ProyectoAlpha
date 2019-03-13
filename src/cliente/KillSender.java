/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Amandine
 */
public class KillSender extends Thread {
        private DataInputStream in;
	private DataOutputStream out;
	private Socket clientSocket;
        private String jugador;
        private boolean victoria, fin;
        private int victimas;
        
	public KillSender (Socket aClientSocket, String jugador) {
	    try {
		clientSocket = aClientSocket;
                this.jugador = jugador;
		in = new DataInputStream(clientSocket.getInputStream());
		out =new DataOutputStream(clientSocket.getOutputStream());
	     } catch(IOException e)  {System.out.println("Connection:"+e.getMessage());}
	}
        
        public boolean getVictoria() {
            return victoria;
        }
        
        public boolean getFin() {
            return fin;
        }
        
        public int getVictimas() {
            return victimas;
        }
        
        @Override
	public void run(){
            try {
                out.writeUTF(jugador);
                victoria = in.readBoolean();
                fin = in.readBoolean();
                victimas = in.readInt();
            } catch (EOFException e) {
                System.out.println("EOF:" + e.getMessage());
            } catch (IOException e) {
                System.out.println("IO:" + e.getMessage());
            }
            try {
            clientSocket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    
}
