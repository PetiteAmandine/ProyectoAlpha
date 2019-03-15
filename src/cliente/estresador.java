/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import comunes.Conexiones;

/**
 *
 * @author Eduardo
 */
public class estresador {
    
    public static void main(String args[]){
        /*InicioJuego[] estresar=new InicioJuego[6];
        for(int i=0; i<estresar.length;i++){
            estresar[i].main(args);
            //estresar[i].usuario(String.valueOf(i));
        }*/
        System.setProperty("sun.net.maxDatagramSockets","1000");
        Conexiones con;
        int cantidadPrueba=501;
        ClienteRegistro[] cr=new ClienteRegistro[cantidadPrueba];
        Jugador[] jugadores=new Jugador[cantidadPrueba];
        for(int i=0; i<cr.length;i++){
            cr[i]=new ClienteRegistro();
            con=cr[i].registra(String.valueOf(i));
            if (con != null) {
                jugadores[i] = new Jugador(con);
                jugadores[i].start();
            }
        }
        long timeStart=System.currentTimeMillis();
        boolean loop=true;
        while(loop){
            for(int i=0; i<cr.length;i++){
                if(jugadores[i].getPj().prendidos()!=0){
                    jugadores[i].getPj().pintaMonstruo(1, false);
                    jugadores[i].getPj().pintaMonstruo(2, false);
                    jugadores[i].getPj().pintaMonstruo(3, false);
                    jugadores[i].getPj().pintaMonstruo(4, false);
                    jugadores[i].getPj().pintaMonstruo(5, false);
                    jugadores[i].getPj().pintaMonstruo(6, false);
                    jugadores[i].getPj().pintaMonstruo(7, false);
                    jugadores[i].getPj().pintaMonstruo(8, false);
                    jugadores[i].getPj().pintaMonstruo(9, false);
                    jugadores[i].getPj().pintaMonstruo(10, false);
                    jugadores[i].getPj().pintaMonstruo(11, false);
                    jugadores[i].getPj().pintaMonstruo(12, false);
                    jugadores[i].getPj().enviaGolpe();
                    if(jugadores[i].getPj().vict())
                        loop=false;
                }
            }
        }
        System.out.println(String.valueOf("Tardo: " + (System.currentTimeMillis()-timeStart)) + " ms en correr " + (cantidadPrueba-1) + " pruebas");
    }
    
}
