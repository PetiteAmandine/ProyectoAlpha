/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

/**
 *
 * @author velasam
 */
public class PantallaJuego extends javax.swing.JFrame {

    /**
     * Creates new form PantallaJuego
     */
    public PantallaJuego() {
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Monster Haven");
        setMinimumSize(new java.awt.Dimension(613, 368));
        setResizable(false);
        getContentPane().setLayout(null);

        monst1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/monstruo.png"))); // NOI18N
        monst1.setEnabled(false);
        monst1.setName("monst1"); // NOI18N
        monst1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst1MouseClicked(evt);
            }
        });
        getContentPane().add(monst1);
        monst1.setBounds(110, 160, 50, 50);

        monst5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/monstruo.png"))); // NOI18N
        monst5.setEnabled(false);
        monst5.setName("monst5"); // NOI18N
        monst5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst5MouseClicked(evt);
            }
        });
        getContentPane().add(monst5);
        monst5.setBounds(110, 210, 50, 50);

        monst2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/monstruo.png"))); // NOI18N
        monst2.setEnabled(false);
        monst2.setName("monst2"); // NOI18N
        monst2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst2MouseClicked(evt);
            }
        });
        getContentPane().add(monst2);
        monst2.setBounds(230, 160, 50, 50);

        monst9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/monstruo.png"))); // NOI18N
        monst9.setEnabled(false);
        monst9.setName("monst9"); // NOI18N
        monst9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst9MouseClicked(evt);
            }
        });
        getContentPane().add(monst9);
        monst9.setBounds(110, 260, 50, 50);

        monst10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/monstruo.png"))); // NOI18N
        monst10.setEnabled(false);
        monst10.setName("monst10"); // NOI18N
        monst10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst10MouseClicked(evt);
            }
        });
        getContentPane().add(monst10);
        monst10.setBounds(230, 260, 50, 50);

        monst6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/monstruo.png"))); // NOI18N
        monst6.setEnabled(false);
        monst6.setName("monst6"); // NOI18N
        monst6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst6MouseClicked(evt);
            }
        });
        getContentPane().add(monst6);
        monst6.setBounds(230, 210, 50, 50);

        monst3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/monstruo.png"))); // NOI18N
        monst3.setEnabled(false);
        monst3.setName("monst3"); // NOI18N
        monst3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst3MouseClicked(evt);
            }
        });
        getContentPane().add(monst3);
        monst3.setBounds(360, 160, 50, 50);

        monst7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/monstruo.png"))); // NOI18N
        monst7.setEnabled(false);
        monst7.setName("monst7"); // NOI18N
        monst7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst7MouseClicked(evt);
            }
        });
        getContentPane().add(monst7);
        monst7.setBounds(360, 210, 50, 50);

        monst11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/monstruo.png"))); // NOI18N
        monst11.setEnabled(false);
        monst11.setName("monst11"); // NOI18N
        monst11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst11MouseClicked(evt);
            }
        });
        getContentPane().add(monst11);
        monst11.setBounds(360, 260, 50, 50);

        monst8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/monstruo.png"))); // NOI18N
        monst8.setEnabled(false);
        monst8.setName("monst8"); // NOI18N
        monst8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst8MouseClicked(evt);
            }
        });
        getContentPane().add(monst8);
        monst8.setBounds(470, 210, 50, 50);

        monst12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/monstruo.png"))); // NOI18N
        monst12.setEnabled(false);
        monst12.setName("monst12"); // NOI18N
        monst12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst12MouseClicked(evt);
            }
        });
        getContentPane().add(monst12);
        monst12.setBounds(470, 260, 50, 50);

        monst4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/monstruo.png"))); // NOI18N
        monst4.setEnabled(false);
        monst4.setName("monst4"); // NOI18N
        monst4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monst4MouseClicked(evt);
            }
        });
        getContentPane().add(monst4);
        monst4.setBounds(470, 160, 50, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente/fondoBig.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 610, 340);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void monst1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_monst1MouseClicked

    private void monst2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_monst2MouseClicked

    private void monst3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_monst3MouseClicked

    private void monst4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_monst4MouseClicked

    private void monst8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_monst8MouseClicked

    private void monst7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_monst7MouseClicked

    private void monst6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_monst6MouseClicked

    private void monst5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_monst5MouseClicked

    private void monst9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_monst9MouseClicked

    private void monst10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_monst10MouseClicked

    private void monst11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_monst11MouseClicked

    private void monst12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monst12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_monst12MouseClicked

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
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaJuego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
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
    // End of variables declaration//GEN-END:variables
}
