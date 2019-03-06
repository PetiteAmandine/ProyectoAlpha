/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package comunes;

import java.util.Random;

/**
 *
 * @author velasam
 */
public class Monstruo {
    private int posicion;
    private int tiempoVida;

    public Monstruo() {
        Random r = new Random(System.currentTimeMillis());
        posicion = r.nextInt(12) + 1;
        tiempoVida = r.nextInt(3) + 1;
    }

    public int getPosicion() {
        return posicion;
    }

    public int getTiempoVida() {
        return tiempoVida;
    }
    
}
