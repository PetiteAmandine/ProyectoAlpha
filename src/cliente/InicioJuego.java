/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import comunes.Conexiones;
import sun.audio.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author velasam
 */
public class InicioJuego extends javax.swing.JFrame {

    /**
     * Creates new form InicioJuego
     */
    public InicioJuego() {
        initComponents();
    }
    
    public void recuerdaNombre(String nombre) {
        username.setText(nombre);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        error = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Monster Haven");
        setMinimumSize(new java.awt.Dimension(333, 235));
        setPreferredSize(new java.awt.Dimension(333, 200));
        setResizable(false);
        setSize(new java.awt.Dimension(333, 200));
        getContentPane().setLayout(null);

        error.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 10)); // NOI18N
        error.setForeground(new java.awt.Color(255, 255, 153));
        getContentPane().add(error);
        error.setBounds(150, 87, 130, 20);

        jButton1.setBackground(new java.awt.Color(255, 255, 153));
        jButton1.setFont(new java.awt.Font("Retro Gaming", 0, 14)); // NOI18N
        jButton1.setText("¡Empezar!");
        jButton1.setAlignmentY(0.0F);
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 153), 1, true));
        jButton1.setBorderPainted(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setName("botonInicio"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(110, 160, 110, 31);

        jLabel3.setBackground(new java.awt.Color(255, 255, 153));
        jLabel3.setFont(new java.awt.Font("Retro Gaming", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 153));
        jLabel3.setText("Nombre:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 120, 70, 18);

        username.setBackground(new java.awt.Color(255, 255, 255, 0));
        username.setFont(new java.awt.Font("Retro Gaming", 0, 14)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 153));
        username.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true));
        username.setOpaque(false);
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        getContentPane().add(username);
        username.setBounds(130, 110, 160, 30);

        jLabel2.setFont(new java.awt.Font("Retro Gaming", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("MONSTER HAVEN");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 10, 330, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/imagenes/fondoSmol.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 340, 200);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ClienteRegistro cr;
        Conexiones con;
        if (username.getText().equals("")) {
            error.setText("¡Debes elegir un nombre!");
        } else if (username.getText().length() == 1 || username.getText().equals("-") || username.getText().equals("---")) {
            error.setText("Por favor elige otro nombre");
        } else {
            cr = new ClienteRegistro();
            con = cr.registra(username.getText());
            if (con != null) {
                Jugador jug = new Jugador(con);
                this.setVisible(false);
                jug.start();
            } else {
                error.setText("Por favor elige otro nombre");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InicioJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new InicioJuego().setVisible(true);
                    InputStream in = null;
                    String cancion = "C:/Users/Amandine/Documents/ITAM/8vo Semestre/Sistemas Distribuidos/ProyectoAlpha/src/cliente/gigue.wav";
                    in = new FileInputStream(cancion);
                    AudioStream as = new AudioStream(in);
                    AudioPlayer.player.start(as);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(InicioJuego.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(InicioJuego.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel error;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
