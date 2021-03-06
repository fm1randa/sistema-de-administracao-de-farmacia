/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela_de_sair;

import visão.TelaLogin;
import java.awt.Color;
import visão.TelaPrincipal;


/**
 *
 * @author Ellen
 */
public class Sair extends javax.swing.JFrame {

    /**
     * @return the OPCAO
     */
    public boolean isOPCAO() {
        return OPCAO;
    }

    /**
     * @param OPCAO the OPCAO to set
     */
    public void setOPCAO(boolean OPCAO) {
        this.OPCAO = OPCAO;
    }

    /**
     * Creates new form Sair
     */
    String permissao;
    String user;
    String nome;
    private boolean OPCAO = false;
    TelaPrincipal telaPrincipal = new TelaPrincipal(user, permissao, nome);
    public Sair() {
        initComponents();
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        jButtonSair.setBackground(new Color(0, 0, 0, 0));
        jButtonNao.setBackground(new Color(0, 0, 0, 0));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonSair = new javax.swing.JButton();
        jButtonNao = new javax.swing.JButton();
        jLabelBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sair");
        getContentPane().setLayout(null);

        jButtonSair.setBorder(null);
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSair);
        jButtonSair.setBounds(50, 140, 140, 30);

        jButtonNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNaoActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonNao);
        jButtonNao.setBounds(260, 140, 130, 30);

        jLabelBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/SairLogin.png"))); // NOI18N
        getContentPane().add(jLabelBackground);
        jLabelBackground.setBounds(0, 0, 446, 242);

        setSize(new java.awt.Dimension(462, 281));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        // TODO add your handling code here:[
        setOPCAO(true);
        dispose();
        
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jButtonNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNaoActionPerformed
        // TODO add your handling code here:
        setOPCAO(false);
        dispose();
    }//GEN-LAST:event_jButtonNaoActionPerformed

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
            java.util.logging.Logger.getLogger(Sair.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sair.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sair.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sair.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sair().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonNao;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JLabel jLabelBackground;
    // End of variables declaration//GEN-END:variables
}
