/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.elecciones2023datos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Fede
 */
public class PactosOpcion2 extends javax.swing.JFrame {

    
    private JButton botonSeleccionado = null;
    DefaultTableModel modeltablaIzq = new DefaultTableModel();
    DefaultTableModel modeltablaDcha = new DefaultTableModel();
    private int filaSeleccionadaAnterior = -1;

    
    public PactosOpcion2() {
        initComponents();
        tablaIzq.setModel(modeltablaIzq);
        tablaDcha.setModel(modeltablaDcha);
        cargarDatos();
    
    }
    private void cargarDatos(){
        modeltablaIzq.addColumn("PARTIDOS IZQ");
        modeltablaDcha.addColumn("PARTIDOS DCHA");
        
        String[] datosEleccionesIzq = {"Partido Socialista Obrero Español", "Ciudadanos", 
                             "Unidas Podemos","Más País", "Partido Nacionalista Vasco"};
        String[] datosEleccionesDcha = {"Partido Popular", "Ciudadanos", 
                              "Vox", "Más País"};

        for (String partido : datosEleccionesIzq) {
            modeltablaIzq.addRow(new Object[]{partido});
        }
        for (String partido : datosEleccionesDcha) {
            modeltablaDcha.addRow(new Object[]{partido});
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIzqDcha = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnDchaIzq = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaPactosIzq = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaIzq = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaPactosDcha = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaDcha = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tipoDatos = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblMayoriaAbsoluta = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblEscTotalesIzq = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblEscTotalesDcha = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PACTOS 2023");

        btnIzqDcha.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnIzqDcha.setText("=>");
        btnIzqDcha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzqDchaActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("=>");
        jButton3.setToolTipText("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnDchaIzq.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDchaIzq.setText("<=");
        btnDchaIzq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDchaIzqActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("<=");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(153, 0, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("RESET");

        jButton6.setBackground(new java.awt.Color(153, 255, 153));
        jButton6.setForeground(new java.awt.Color(0, 0, 0));
        jButton6.setText("ENTRA");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 153, 153));
        jButton7.setForeground(new java.awt.Color(0, 0, 0));
        jButton7.setText("SALE");

        tablaPactosIzq.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaPactosIzq.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PACTOS IZQ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tablaPactosIzq);
        if (tablaPactosIzq.getColumnModel().getColumnCount() > 0) {
            tablaPactosIzq.getColumnModel().getColumn(0).setResizable(false);
        }

        tablaIzq.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaIzq.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "PARTIDOS IZQ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tablaIzq);
        if (tablaIzq.getColumnModel().getColumnCount() > 0) {
            tablaIzq.getColumnModel().getColumn(0).setResizable(false);
        }

        tablaPactosDcha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaPactosDcha.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PACTOS DCHA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tablaPactosDcha);
        if (tablaPactosDcha.getColumnModel().getColumnCount() > 0) {
            tablaPactosDcha.getColumnModel().getColumn(0).setResizable(false);
        }

        tablaDcha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaDcha.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "PARTIDOS DCHA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tablaDcha);
        if (tablaDcha.getColumnModel().getColumnCount() > 0) {
            tablaDcha.getColumnModel().getColumn(0).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Circunscripción");
        jLabel1.setToolTipText("");

        tipoDatos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tipoDatos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tipoDatos.setText("MUNICIPALES");
        tipoDatos.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mayoria absoluta: ");

        lblMayoriaAbsoluta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMayoriaAbsoluta.setText("0");

        jLabel3.setText("Total escaños: ");

        lblEscTotalesIzq.setText("0");

        jLabel4.setText("Total escaños: ");

        lblEscTotalesDcha.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(22, 22, 22)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblEscTotalesIzq)
                                    .addGap(591, 591, 591)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblEscTotalesDcha)
                                    .addContainerGap(62, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(tipoDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnDchaIzq, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnIzqDcha, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMayoriaAbsoluta)
                .addGap(386, 386, 386))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblEscTotalesIzq)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblMayoriaAbsoluta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lblEscTotalesDcha))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnIzqDcha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDchaIzq)
                        .addGap(85, 85, 85))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addComponent(jButton5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3))
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoDatos))
                .addGap(16, 16, 16))
        );

        jLabel1.getAccessibleContext().setAccessibleName("lblCircunscripcion");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIzqDchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzqDchaActionPerformed
        
        
        
        int filaSeleccionada = tablaIzq.getSelectedRow();
        if (filaSeleccionada != -1) {
            
            String texto = (String) tablaIzq.getValueAt(filaSeleccionada, 0);
            tablaIzq.setValueAt(texto, filaSeleccionada, 0);
            DefaultTableModel modelPactos = (DefaultTableModel) tablaPactosIzq.getModel();
            boolean repetido = false;
            for (int i = 0; i < modelPactos.getRowCount(); i++) {
                String textoTablaPactos = (String) modelPactos.getValueAt(i, 0);
                if (texto.equals(textoTablaPactos)) {
                    repetido = true;
                    break;
                }
            }
            if (!repetido) {
                modelPactos.addRow(new Object[]{texto});
            }
            
            
            TableCellRenderer renderer = tablaIzq.getCellRenderer(filaSeleccionada, 0);
            Component componente = tablaIzq.prepareRenderer(renderer, filaSeleccionada, 0);
            componente.setBackground(Color.LIGHT_GRAY);
            tablaIzq.clearSelection(); // deseleccionar la fila seleccionada
        }

        
    }//GEN-LAST:event_btnIzqDchaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed



    
    private void btnDchaIzqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDchaIzqActionPerformed
            
     
        
        
        int filaSeleccionada = tablaPactosIzq.getSelectedRow();
        if (filaSeleccionada != -1) {
            DefaultTableModel modelPactos = (DefaultTableModel) tablaPactosIzq.getModel();
            String texto = (String) modelPactos.getValueAt(filaSeleccionada, 0);

            // Restaurar color de fila seleccionada anteriormente (si la hay)
            if (filaSeleccionadaAnterior != -1) {
                tablaIzq.getCellRenderer(filaSeleccionadaAnterior, 0).getTableCellRendererComponent(tablaIzq,
                        tablaIzq.getValueAt(filaSeleccionadaAnterior, 0), false, false, filaSeleccionadaAnterior, 0)
                        .setBackground(Color.WHITE);
            }

            // Actualizar color de fila seleccionada actual
            for (int i = 0; i < tablaIzq.getRowCount(); i++) {
                String textoFila = (String) tablaIzq.getValueAt(i, 0);
                if (texto.equals(textoFila)) {
                    tablaIzq.getCellRenderer(i, 0).getTableCellRendererComponent(tablaIzq, texto, false, false, i, 0)
                            .setBackground(Color.WHITE);
                    filaSeleccionadaAnterior = i; // Actualizar fila seleccionada anteriormente
                    break;
                }
            }

            // Eliminar fila seleccionada de tablaPactosIzq
            modelPactos.removeRow(filaSeleccionada);
        }
    }//GEN-LAST:event_btnDchaIzqActionPerformed

    
    //ARREGLAR CAMBIO DE TABLAS
    //MAIN SALIR CENTRADO
    
    private void resaltarBoton(JButton boton) {
        // Desactivar el resaltado del botón anteriormente seleccionado
        if (botonSeleccionado != null) {
            botonSeleccionado.setBackground(null);
            botonSeleccionado.setOpaque(false);
        }

        // Activar el resaltado del botón recién seleccionado
        botonSeleccionado = boton;
        botonSeleccionado.setBackground(Color.YELLOW);
        botonSeleccionado.setOpaque(true);
    }
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        resaltarBoton(jButton5);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(PactosOpcion2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PactosOpcion2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PactosOpcion2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PactosOpcion2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PactosOpcion2 pactos2 = new PactosOpcion2();
                pactos2.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
                pactos2.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDchaIzq;
    private javax.swing.JButton btnIzqDcha;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblEscTotalesDcha;
    private javax.swing.JLabel lblEscTotalesIzq;
    private javax.swing.JLabel lblMayoriaAbsoluta;
    private javax.swing.JTable tablaDcha;
    private javax.swing.JTable tablaIzq;
    private javax.swing.JTable tablaPactosDcha;
    private javax.swing.JTable tablaPactosIzq;
    private javax.swing.JLabel tipoDatos;
    // End of variables declaration//GEN-END:variables
}
