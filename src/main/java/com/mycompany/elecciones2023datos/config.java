/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.elecciones2023datos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mycompany.elecciones2023datos.controllers.GraficosController;
import org.apache.commons.lang3.StringEscapeUtils;


public class config extends javax.swing.JFrame {

    GraficosController graficosController = new GraficosController();

    public config() throws IOException {
        initComponents();
        cargarIni();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        puertoTL = new javax.swing.JTextField();
        ip1TL = new javax.swing.JTextField();
        bd1TL = new javax.swing.JTextField();
        bd2TL = new javax.swing.JTextField();
        spTL = new javax.swing.JTextField();
        srTL = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        bdFaldones = new javax.swing.JLabel();
        bdCartones = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("IDIOMA");

        jRadioButton1.setText("PRINCIPAL");

        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("RESERVA");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.setText("LOCAL");

        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("IP IPF1:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setText("PUERTO:");

        bdFaldones.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        bdFaldones.setText("BD FALDONES:");

        bdCartones.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        bdCartones.setText("BD CARTONES:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setText("IP SERVIDOR RESERVA:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setText("IP SERVIDOR PRINCIPAL:");

        jButton1.setBackground(new java.awt.Color(153, 255, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("GUARDAR CONFIGURACIÓN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(69, 69, 69)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jRadioButton1)
                                                        .addComponent(jRadioButton2)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jRadioButton3))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(194, 194, 194)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(jLabel4)
                                                                                        .addComponent(jLabel3))
                                                                                .addGap(11, 11, 11))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(bdCartones)
                                                                                .addComponent(bdFaldones, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(18, 18, 18))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel7)
                                                                        .addComponent(jLabel8))
                                                                .addGap(27, 27, 27)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(srTL, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(spTL, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(ip1TL, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(puertoTL, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(bd1TL, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(bd2TL, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(219, 219, 219)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(ip1TL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel3))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(puertoTL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel4))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(bd1TL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(bdFaldones))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(bd2TL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(bdCartones))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(spTL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel8))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(srTL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel7)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(33, 33, 33)
                                                .addComponent(jRadioButton1)
                                                .addGap(27, 27, 27)
                                                .addComponent(jRadioButton2)
                                                .addGap(27, 27, 27)
                                                .addComponent(jRadioButton3)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //IniFileReaderWriter iniFile = new IniFileReaderWriter("C:\\ELECCIONES2023\\config.ini");

    public static Properties cargarConfiguracion() throws IOException {
        Properties propiedades = new Properties();
        FileInputStream archivoConfiguracion = new FileInputStream("C:\\Elecciones2023\\config.properties");
        propiedades.load(archivoConfiguracion);
        archivoConfiguracion.close();
        return propiedades;
    }

    private void cargarIni() throws IOException {

        Properties propiedades = cargarConfiguracion();


        String ip1 = propiedades.getProperty("direccion1");
        ip1TL.setText(ip1);
        String puerto = propiedades.getProperty("puerto");
        puertoTL.setText(puerto);
        String bdCartones = propiedades.getProperty("BDCartones");
        bd1TL.setText(bdCartones);
        String bdFaldones = propiedades.getProperty("BDFaldones");
        bd2TL.setText(bdFaldones);
        String ipServPrincipal = propiedades.getProperty("ipServer");
        spTL.setText(ipServPrincipal);
        String ipServReserva = propiedades.getProperty("ipServerReserva");
        srTL.setText(ipServReserva);

    }

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        graficosController.conectPrincipal();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        graficosController.conectReserva();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        graficosController.conectLocal();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {

            String cartones = StringEscapeUtils.escapeJava(bd1TL.getText());
            String faldones = StringEscapeUtils.escapeJava(bd2TL.getText());

            Properties properties = new Properties();
            properties.setProperty("direccion1", ip1TL.getText());
            properties.setProperty("direccion3", "0");
            properties.setProperty("direccion2", "0");
            properties.setProperty("direccion4", "0");
            properties.setProperty("rutaFicheros", "C:\\\\Elecciones2023\\\\DATOS");
            properties.setProperty("nConexiones", "1");
            properties.setProperty("puerto", puertoTL.getText());
            properties.setProperty("ipServerReserva", srTL.getText());
            properties.setProperty("ipServer", spTL.getText());
            properties.setProperty("BDCartones", cartones);
            properties.setProperty("BDFaldones", faldones);

            FileOutputStream archivo = new FileOutputStream("C:\\Elecciones2023\\config.properties");
            properties.store(archivo, "Archivo de configuración");
            archivo.close();

            System.out.println(properties.getProperty("BDCartones"));

            JOptionPane.showMessageDialog(null, "El archivo se ha guardado correctamente", "Configuracion guardada", JOptionPane.INFORMATION_MESSAGE);


            //CIERRE DE VENTANA CUANDO GUARDO

            dispose();

        } catch (IOException ex) {
            Logger.getLogger(config.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "El archivo no se ha guardado correctamente", "Configuracion no guardada", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed


    private static String escapePropertyValue(String value) {
        StringBuilder sb = new StringBuilder(value.length());
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            switch (c) {
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case ' ':
                    sb.append("\\ ");
                    break;
                case ':':
                    sb.append("\\:");
                    break;
                case '=':
                    sb.append("\\=");
                    break;
                case '<':
                    sb.append("\\<");
                    break;
                case '>':
                    sb.append("\\>");
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }


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
            java.util.logging.Logger.getLogger(config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new config().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(config.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bd1TL;
    private javax.swing.JTextField bd2TL;
    private javax.swing.JLabel bdCartones;
    private javax.swing.JLabel bdFaldones;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JTextField ip1TL;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JTextField puertoTL;
    private javax.swing.JTextField spTL;
    private javax.swing.JTextField srTL;
    // End of variables declaration//GEN-END:variables
}
