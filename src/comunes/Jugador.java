/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author velasam
 */
public class Jugador implements Serializable{
    
    private int monstruos, id;
    private ArrayList<Object> conexiones;

    public Jugador() {
        monstruos = 0;
        conexiones= new ArrayList<Object>();
        id = new Random(System.currentTimeMillis()).nextInt();
    }

    public void newId() {
        id=new Random(System.currentTimeMillis()).nextInt();
    }

    public int getMonstruos() {
        return monstruos;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Object> getConexiones() {
        return conexiones;
    }
    
    public void monstPlus(){
        monstruos++;
    }
    
    public byte addConexion(Object conexion){
        //8 es default error, 0 es UDP, 1 es TCP, 2 es RMI
        byte resp=8;
        if(conexion.getClass().getName().equals("TCPClient")){
            resp=0;
        } else if(conexion.getClass().getName().equals("MulticastReceivingPeer")) {
            resp=1;
        } else if(conexion.getClass().getName().equals("ComputeClient")) {
            resp=2;
        }
        return resp;
    }
}
