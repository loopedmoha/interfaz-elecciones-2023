/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.elecciones2023datos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fede
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private JButton botonSeleccionado = null;
    private JButton botonSeleccionado2 = null;
    
    public Main() {
        initComponents();
     
        
        Tablas();
        
        ListSelectionModel selectionModel = TablaGraficos.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    tablaComunidades.clearSelection();
                    tablaMunicipios.clearSelection();
                    TablaFaldones.clearSelection();
                }
            }
        });
        
        ListSelectionModel selectionModel2 = TablaFaldones.getSelectionModel();
        selectionModel2.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    tablaComunidades.clearSelection();
                    tablaMunicipios.clearSelection();
                    TablaGraficos.clearSelection();
                }
            }
        });
    }
    

    private void Tablas(){
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaComunidades = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaGraficos = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaMunicipios = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnReplegar = new javax.swing.JButton();
        btnSondeoAutonomicas = new javax.swing.JButton();
        btnSondeoMunicipales = new javax.swing.JButton();
        btnDatosAutonomicas = new javax.swing.JButton();
        btnDatosMunicipales = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaFaldones = new javax.swing.JTable();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        lblEscrutado = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblEscrutado1 = new javax.swing.JLabel();
        btnAvance1 = new javax.swing.JButton();
        btnAvance2 = new javax.swing.JButton();
        btnAvance3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ELECCIONES 2023");

        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cod", "Partido", "Escaños", "Votos"
            }
        ));
        jScrollPane1.setViewportView(tablaDatos);

        tablaComunidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Aragón"},
                {"Asturias"},
                {"Madrid"},
                {"Cantabria"},
                {"Castilla-La Mancha"},
                {"Navarra"},
                {"Valencia"},
                {"Extremadura"},
                {"Galicia"},
                {"Baleares"},
                {"Canarias"},
                {"La Rioja"},
                {"Murcia"}
            },
            new String [] {
                "COMUNIDADES"
            }
        ));
        tablaComunidades.getTableHeader().setResizingAllowed(false);
        jScrollPane2.setViewportView(tablaComunidades);
        if (tablaComunidades.getColumnModel().getColumnCount() > 0) {
            tablaComunidades.getColumnModel().getColumn(0).setResizable(false);
        }

        TablaGraficos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Mapa Electoral"},
                {"Faldon Mapa"},
                {"Pactometro"}
            },
            new String [] {
                "CARTONES"
            }
        ));
        TablaGraficos.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                TablaGraficosHierarchyChanged(evt);
            }
        });
        jScrollPane3.setViewportView(TablaGraficos);
        if (TablaGraficos.getColumnModel().getColumnCount() > 0) {
            TablaGraficos.getColumnModel().getColumn(0).setResizable(false);
        }

        tablaMunicipios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "MUNICIPIOS"
            }
        ));
        jScrollPane4.setViewportView(tablaMunicipios);
        if (tablaMunicipios.getColumnModel().getColumnCount() > 0) {
            tablaMunicipios.getColumnModel().getColumn(0).setResizable(false);
        }

        jButton1.setBackground(new java.awt.Color(153, 255, 153));
        jButton1.setText("ENTRA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 102, 102));
        jButton2.setText("SALE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("PACTOS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(153, 0, 51));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("RESET");

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Fede\\Documents\\NetBeansProjects\\Elecciones2023DATOS\\Imagenes\\iconconfig.png")); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        btnReplegar.setBackground(new java.awt.Color(255, 153, 51));
        btnReplegar.setText("REPLEGAR");
        btnReplegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReplegarActionPerformed(evt);
            }
        });

        btnSondeoAutonomicas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSondeoAutonomicas.setText("SONDEO AUTONOMICAS");
        btnSondeoAutonomicas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSondeoAutonomicasActionPerformed(evt);
            }
        });

        btnSondeoMunicipales.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSondeoMunicipales.setText("SONDEO MUNICIPALES");
        btnSondeoMunicipales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSondeoMunicipalesActionPerformed(evt);
            }
        });

        btnDatosAutonomicas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDatosAutonomicas.setText("DATOS AUTONOMICAS");
        btnDatosAutonomicas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatosAutonomicasActionPerformed(evt);
            }
        });

        btnDatosMunicipales.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDatosMunicipales.setText("DATOS MUNICIPALES");
        btnDatosMunicipales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatosMunicipalesActionPerformed(evt);
            }
        });

        TablaFaldones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Mapa Electoral"},
                {"Faldon Mapa"},
                {"Pactometro"}
            },
            new String [] {
                "FALDONES"
            }
        ));
        jScrollPane5.setViewportView(TablaFaldones);
        if (TablaFaldones.getColumnModel().getColumnCount() > 0) {
            TablaFaldones.getColumnModel().getColumn(0).setResizable(false);
        }

        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCheckBox1.setText("ACTUALIZAR ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel2.setText("ESCRUTADO:");

        lblEscrutado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblEscrutado.setText("---");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel3.setText("PARTICIPACION:");

        lblEscrutado1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblEscrutado1.setText("---");

        btnAvance1.setText("AVANCE 1");
        btnAvance1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvance1ActionPerformed(evt);
            }
        });

        btnAvance2.setText("AVANCE 2");
        btnAvance2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvance2ActionPerformed(evt);
            }
        });

        btnAvance3.setText("AVANCE 3");
        btnAvance3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvance3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSondeoAutonomicas, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSondeoMunicipales, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(92, 92, 92)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDatosMunicipales, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDatosAutonomicas, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEscrutado, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAvance2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEscrutado1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAvance3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAvance1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(207, 207, 207)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnReplegar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSondeoAutonomicas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDatosAutonomicas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox1)
                            .addComponent(btnAvance1))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(lblEscrutado))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAvance2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(lblEscrutado1)
                                    .addComponent(btnAvance3)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnSondeoMunicipales, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDatosMunicipales, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReplegar)
                    .addComponent(jButton5))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
       
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        
         GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();
        
        /*JFrame pactometroFin = new PactometroFin();
        pactometroFin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pactometroFin.setLocation(screenWidth/4, screenHeight/2);
        pactometroFin.setVisible(true);
        
        JFrame pactometro2 = new Pactometro2();
        pactometro2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pactometro2.setLocation(screenWidth/4, screenHeight/9);
        pactometro2.setVisible(true);*/
        JFrame pactos = new PactosOpcion2();
        pactos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pactos.setLocation(screenWidth/4, screenHeight/2);
        pactos.setVisible(true);
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();
        
        JFrame config;
        try {
            config = new config();
            
            config.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            config.setLocation(screenWidth/4, screenHeight/4);
            config.setVisible(true);
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnReplegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReplegarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReplegarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnSondeoAutonomicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSondeoAutonomicasActionPerformed
        // TODO add your handling code here:
        resaltarBoton(btnSondeoAutonomicas);
    }//GEN-LAST:event_btnSondeoAutonomicasActionPerformed

    private void btnDatosAutonomicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatosAutonomicasActionPerformed
        // TODO add your handling code here:
        resaltarBoton(btnDatosAutonomicas);
    }//GEN-LAST:event_btnDatosAutonomicasActionPerformed

    private void btnSondeoMunicipalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSondeoMunicipalesActionPerformed
        // TODO add your handling code here:
        resaltarBoton(btnSondeoMunicipales);
    }//GEN-LAST:event_btnSondeoMunicipalesActionPerformed

    private void btnDatosMunicipalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatosMunicipalesActionPerformed
        // TODO add your handling code here:
        resaltarBoton(btnDatosMunicipales);
    }//GEN-LAST:event_btnDatosMunicipalesActionPerformed

    private void TablaGraficosHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_TablaGraficosHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TablaGraficosHierarchyChanged

    private void btnAvance1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvance1ActionPerformed
        // TODO add your handling code here:
        
        resaltarBotonAvances(btnAvance1);
    }//GEN-LAST:event_btnAvance1ActionPerformed

    private void btnAvance2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvance2ActionPerformed
        // TODO add your handling code here:
        
        resaltarBotonAvances(btnAvance2);
    }//GEN-LAST:event_btnAvance2ActionPerformed

    private void btnAvance3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvance3ActionPerformed
        // TODO add your handling code here:
        resaltarBotonAvances(btnAvance3);
    }//GEN-LAST:event_btnAvance3ActionPerformed

    
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
    
    private void resaltarBotonAvances(JButton boton) {
        // Desactivar el resaltado del botón anteriormente seleccionado
        if (botonSeleccionado2 != null) {
            botonSeleccionado2.setBackground(null);
            botonSeleccionado2.setOpaque(false);
        }

        // Activar el resaltado del botón recién seleccionado
        botonSeleccionado2 = boton;
        botonSeleccionado2.setBackground(new Color(173, 216, 230));
        botonSeleccionado2.setOpaque(true);
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaFaldones;
    private javax.swing.JTable TablaGraficos;
    private javax.swing.JButton btnAvance1;
    private javax.swing.JButton btnAvance2;
    private javax.swing.JButton btnAvance3;
    private javax.swing.JButton btnDatosAutonomicas;
    private javax.swing.JButton btnDatosMunicipales;
    private javax.swing.JButton btnReplegar;
    private javax.swing.JButton btnSondeoAutonomicas;
    private javax.swing.JButton btnSondeoMunicipales;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblEscrutado;
    private javax.swing.JLabel lblEscrutado1;
    private javax.swing.JTable tablaComunidades;
    private javax.swing.JTable tablaDatos;
    private javax.swing.JTable tablaMunicipios;
    // End of variables declaration//GEN-END:variables
}
