package agendatcpsockets;

/**
 *
 * @author Eduardo
 */
import java.net.*;
import java.io.*;
import java.util.Random;

public class TCPClient {
    
    public static void main(String args[]) {
        
        Socket s = null;
        try {
            int serverPort = 7896;
            
            s = new Socket("localhost", serverPort);
            //   s = new Socket("127.0.0.1", serverPort);    
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out
                    = new DataOutputStream(s.getOutputStream());
            Random r = new Random();
            long[] tiempos=new long[100];
            for (int i = 0; i < 100; i++) {
                tiempos[i]=System.currentTimeMillis();
                out.writeInt(r.nextInt(6));
                String data = in.readUTF();
                if (data.equals("Error, pops")) {
                    System.out.println("Esa persona no existe");
                } else {
                    System.out.println("Nombre: " + data);
                }
                tiempos[i]=System.currentTimeMillis()-tiempos[i];
            }
            FileOutputStream output = new FileOutputStream("tiempos.txt");
            String aux;
            for(int i=0;i<100;i++){
                aux=tiempos[i]+";";
                output.write(aux.getBytes());
            }
            out.writeInt(-1);
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
            }
        }
    }
}
