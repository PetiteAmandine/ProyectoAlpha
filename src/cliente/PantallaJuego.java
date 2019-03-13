/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import comunes.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author velasam
 */
public class PantallaJuego extends javax.swing.JFrame {

    private Conexiones con;
    private MulticastSocket ms;
    private Socket s;

    /**
     * Creates new form PantallaJuego
     *
     * @param con Clase que contiene datos para establecer conexiones
     */
    public PantallaJuego(Conexiones con) throws InterruptedException {
        initComponents();
        monst1.setVisible(false);
        monst2.setVisible(false);
        monst3.setVisible(false);
        monst4.setVisible(false);
        monst5.setVisible(false);
        monst6.setVisible(false);
        monst7.setVisible(false);
        monst8.setVisible(false);
        monst9.setVisible(false);
        monst10.setVisible(false);
        monst11.setVisible(false);
        monst12.setVisible(false);
        victoria.setVisible(false);
        this.con = con;
        username.setText(con.getUser());
        ms = creaConexionUDP();
        s = creaConexionTCP();
    }

    public MulticastSocket creaConexionUDP() {
        MulticastSocket ms = null;
        try {
            InetAddress group = InetAddress.getByName(con.getMulticastIP());
            ms = new MulticastSocket(con.getMulticastPort());
            ms.joinGroup(group);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        return ms;
    }
    
    public Socket creaConexionTCP() {
        Socket ts = null;
        try {
            ts = new Socket(con.getTcpIP(), con.getTcpPort());
        } catch(UnknownHostException e) {
            System.out.println("Sock:"+e.getMessage()); 
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        return ts;
    }

    public void monstruoRecieve() throws InterruptedException {
        MonstCatcher mc = new MonstCatcher(ms);
        mc.start();
        System.out.println(mc.getMonstNum());
        System.out.println(mc.getMonstLife());
        pintaMonstruo(mc.getMonstNum(), true);
        //Corregir esta parte
        Thread.sleep(mc.getMonstLife() * 1000);
        pintaMonstruo(mc.getMonstNum(), false);
    }

    public void monstruoHit(javax.swing.JLabel monst) throws InterruptedException {
        monst.setEnabled(false);
        monst.setVisible(false);
        KillSender ks = new KillSender(s, con.getUser());
        ks.start();
        victimas.setText("" + ks.getVictimas());
        if (ks.getVictoria())
            parpadeo();
        if (ks.getFin()) {
            victoria.setText("Fin de partida :(");
            victoria.setVisible(true);
            Thread.sleep(2000);
        }
        if (ks.getVictoria() || ks.getFin()) {
            InicioJuego ini = new InicioJuego();
            ini.setVisible(true);
            this.setVisible(false);
        }
    }
    
    public void parpadeo() {
        victoria.setText("¡Victoria!");
        try {
            victoria.setVisible(true);
            Thread.sleep(500);
            victoria.setVisible(false);
            Thread.sleep(500);
            victoria.setVisible(true);
            Thread.sleep(500);
            victoria.setVisible(false);
            Thread.sleep(500);
            victoria.setVisible(true);
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(PantallaJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void pintaMonstruo(int num, boolean pinta) {
        switch (num) {
            case 1: monst1.setVisible(pinta);
                    monst1.setEnabled(pinta);
                    break;
            case 2: monst2.setVisible(pinta);
                    monst2.setEnabled(pinta);
                    break;
            case 3: monst3.setVisible(pinta);
                    monst3.setEnabled(pinta);
                    break;
            case 4: monst4.setVisible(pinta);
                    monst4.setEnabled(pinta);
                    break;
            case 5: monst5.setVisible(pinta);
                    monst5.setEnabled(pinta);
                    break;
            case 6: monst6.setVisible(pinta);
                    monst6.setEnabled(pinta);
                    break;
            case 7: monst7.setVisible(pinta);
                    monst7.setEnabled(pinta);
                    break;
            case 8: monst8.setVisible(pinta);
                    monst8.setEnabled(pinta);
                    break;
            case 9: monst9.setVisible(pinta);
                    monst9.setEnabled(pinta);
                    break;
            case 10: monst10.setVisible(pinta);
                    monst10.setEnabled(pinta);
                    break;
            case 11: monst11.setVisible(pinta);
                    monst11.setEnabled(pinta);
                    break;
            case 12: monst12.setVisible(pinta);
                    monst12.setEnabled(pinta);
                    break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        victimas = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        monst1 = new javax.swing.JLabel();
        monst5 = new javax.swing.JLabel();
        monst2 = new javax.swing.JLabel();
        monst9 = new javax.swing.JLabel();
        monst10 = new javax.swing.JLabel();
        monst6 = new javax.swing.JLabel();
        monst3 = new javax.swing.JLabel();
        monst7 = new javax.swing.JLabel();
        monst11 = new javax.swing.JLabel();
        monst8 = new javax.swing.JLabel();
        monst12 = new javax.swing.JLabel();
        monst4 = new javax.swing.JLabel();
        victoria = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Monster Haven");
        setMinimumSize(new java.awt.Dimension(613, 368));
        setResizable(false);
        getContentPane().setLayout(null);

        victimas.setFont(new java.awt.Font("Retro Gaming", 0, 14)); // NOI18N
        victimas.setForeground(new java.awt.Color(255, 255, 153));
        victimas.setText("0");
        victimas.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        getContentPane().add(victimas);
        victimas.setBounds(530, 20, 70, 18);

        jLabel2.setFont(new java.awt.Font("Retro Gaming", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 153));
        jLabel2.setText("Monstruos:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(420, 20, 100, 18);

        username.setFont(new java.awt.Font("Retro Gaming", 0, 14)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 153));
        username.setText("user");
        getContentPane().add(username);
        username.setBounds(20, 20, 320, 18);

        monst1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/imagenes/monstruo.png"))); // NOI18N
        monst1.setEnabled(false);
        monst1.setName("monst1"); // NOI18N
        monst1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst1MouseClicked(evt);
            }
        });
        getContentPane().add(monst1);
        monst1.setBounds(110, 160, 50, 50);

        monst5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/imagenes/monstruo.png"))); // NOI18N
        monst5.setEnabled(false);
        monst5.setName("monst5"); // NOI18N
        monst5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst5MouseClicked(evt);
            }
        });
        getContentPane().add(monst5);
        monst5.setBounds(110, 210, 50, 50);

        monst2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/imagenes/monstruo.png"))); // NOI18N
        monst2.setEnabled(false);
        monst2.setName("monst2"); // NOI18N
        monst2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst2MouseClicked(evt);
            }
        });
        getContentPane().add(monst2);
        monst2.setBounds(230, 160, 50, 50);

        monst9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/imagenes/monstruo.png"))); // NOI18N
        monst9.setEnabled(false);
        monst9.setName("monst9"); // NOI18N
        monst9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst9MouseClicked(evt);
            }
        });
        getContentPane().add(monst9);
        monst9.setBounds(110, 260, 50, 50);

        monst10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/imagenes/monstruo.png"))); // NOI18N
        monst10.setEnabled(false);
        monst10.setName("monst10"); // NOI18N
        monst10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst10MouseClicked(evt);
            }
        });
        getContentPane().add(monst10);
        monst10.setBounds(230, 260, 50, 50);

        monst6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/imagenes/monstruo.png"))); // NOI18N
        monst6.setEnabled(false);
        monst6.setName("monst6"); // NOI18N
        monst6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst6MouseClicked(evt);
            }
        });
        getContentPane().add(monst6);
        monst6.setBounds(230, 210, 50, 50);

        monst3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/imagenes/monstruo.png"))); // NOI18N
        monst3.setEnabled(false);
        monst3.setName("monst3"); // NOI18N
        monst3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst3MouseClicked(evt);
            }
        });
        getContentPane().add(monst3);
        monst3.setBounds(360, 160, 50, 50);

        monst7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/imagenes/monstruo.png"))); // NOI18N
        monst7.setEnabled(false);
        monst7.setName("monst7"); // NOI18N
        monst7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst7MouseClicked(evt);
            }
        });
        getContentPane().add(monst7);
        monst7.setBounds(360, 210, 50, 50);

        monst11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/imagenes/monstruo.png"))); // NOI18N
        monst11.setEnabled(false);
        monst11.setName("monst11"); // NOI18N
        monst11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst11MouseClicked(evt);
            }
        });
        getContentPane().add(monst11);
        monst11.setBounds(360, 260, 50, 50);

        monst8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/imagenes/monstruo.png"))); // NOI18N
        monst8.setEnabled(false);
        monst8.setName("monst8"); // NOI18N
        monst8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst8MouseClicked(evt);
            }
        });
        getContentPane().add(monst8);
        monst8.setBounds(470, 210, 50, 50);

        monst12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/imagenes/monstruo.png"))); // NOI18N
        monst12.setEnabled(false);
        monst12.setName("monst12"); // NOI18N
        monst12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst12MouseClicked(evt);
            }
        });
        getContentPane().add(monst12);
        monst12.setBounds(470, 260, 50, 50);

        monst4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/imagenes/monstruo.png"))); // NOI18N
        monst4.setEnabled(false);
        monst4.setName("monst4"); // NOI18N
        monst4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst4MouseClicked(evt);
            }
        });
        getContentPane().add(monst4);
        monst4.setBounds(470, 160, 50, 50);

        victoria.setFont(new java.awt.Font("Retro Gaming", 0, 48)); // NOI18N
        victoria.setForeground(new java.awt.Color(255, 255, 153));
        getContentPane().add(victoria);
        victoria.setBounds(180, 80, 320, 60);
        victoria.getAccessibleContext().setAccessibleName("");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/imagenes/fondoBig.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 610, 340);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void monst1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst1MouseClicked
        try {
            monstruoHit(monst1);
        } catch (InterruptedException ex) {
            Logger.getLogger(PantallaJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_monst1MouseClicked

    private void monst2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst2MouseClicked
        try {
            monstruoHit(monst2);
        } catch (InterruptedException ex) {
            Logger.getLogger(PantallaJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_monst2MouseClicked

    private void monst3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst3MouseClicked
        try {
            monstruoHit(monst3);
        } catch (InterruptedException ex) {
            Logger.getLogger(PantallaJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_monst3MouseClicked

    private void monst4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst4MouseClicked
        try {
            monstruoHit(monst4);
        } catch (InterruptedException ex) {
            Logger.getLogger(PantallaJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_monst4MouseClicked

    private void monst8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst8MouseClicked
        try {
            monstruoHit(monst8);
        } catch (InterruptedException ex) {
            Logger.getLogger(PantallaJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_monst8MouseClicked

    private void monst7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst7MouseClicked
        try {
            monstruoHit(monst7);
        } catch (InterruptedException ex) {
            Logger.getLogger(PantallaJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_monst7MouseClicked

    private void monst6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst6MouseClicked
        try {
            monstruoHit(monst6);
        } catch (InterruptedException ex) {
            Logger.getLogger(PantallaJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_monst6MouseClicked

    private void monst5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst5MouseClicked
        try {
            monstruoHit(monst5);
        } catch (InterruptedException ex) {
            Logger.getLogger(PantallaJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_monst5MouseClicked

    private void monst9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst9MouseClicked
        try {
            monstruoHit(monst9);
        } catch (InterruptedException ex) {
            Logger.getLogger(PantallaJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_monst9MouseClicked

    private void monst10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst10MouseClicked
        try {
            monstruoHit(monst10);
        } catch (InterruptedException ex) {
            Logger.getLogger(PantallaJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_monst10MouseClicked

    private void monst11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst11MouseClicked
        try {
            monstruoHit(monst11);
        } catch (InterruptedException ex) {
            Logger.getLogger(PantallaJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_monst11MouseClicked

    private void monst12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst12MouseClicked
        try {
            monstruoHit(monst12);
        } catch (InterruptedException ex) {
            Logger.getLogger(PantallaJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_monst12MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel monst1;
    private javax.swing.JLabel monst10;
    private javax.swing.JLabel monst11;
    private javax.swing.JLabel monst12;
    private javax.swing.JLabel monst2;
    private javax.swing.JLabel monst3;
    private javax.swing.JLabel monst4;
    private javax.swing.JLabel monst5;
    private javax.swing.JLabel monst6;
    private javax.swing.JLabel monst7;
    private javax.swing.JLabel monst8;
    private javax.swing.JLabel monst9;
    private javax.swing.JLabel username;
    private javax.swing.JLabel victimas;
    private javax.swing.JLabel victoria;
    // End of variables declaration//GEN-END:variables
}
