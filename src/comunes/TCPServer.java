package comunes;

/**
 *
 * @author Eduardo
 */

import java.net.*;
import java.io.*;

public class TCPServer {
    
    public static void main (String args[]) {
	try{
		int serverPort = 7896; 
		ServerSocket listenSocket = new ServerSocket(serverPort);
		while(true) {
			System.out.println("Waiting for messages..."); 
                        Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made. 
			Connection c = new Connection(clientSocket);
                        c.start();
		}
	} catch(IOException e) {System.out.println("Listen :"+ e.getMessage());}
    }
}
class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    public Connection (Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out =new DataOutputStream(clientSocket.getOutputStream());
         } catch(IOException e)  {System.out.println("Connection:"+e.getMessage());}
    }

    @Override
	public void run(){
            AddressBook ab = new AddressBook();
            int data;
            String name;
            try {
                //Realiza las m consultas del hilo
                data = in.readInt();
                while (data != -1) {
                    name = ab.getRecord(data).getName();
                    if (name.equals(""))
                        out.writeUTF("Error, pops");
                    else
                        out.writeUTF(name);
                    data = in.readInt();
                }
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