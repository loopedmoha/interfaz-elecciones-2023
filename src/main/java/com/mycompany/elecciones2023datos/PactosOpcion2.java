/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.elecciones2023datos;

import com.mycompany.elecciones2023datos.DTO.CarmenDTO;
import com.mycompany.elecciones2023datos.DTO.CpDTO;
import com.mycompany.elecciones2023datos.controllers.GraficosController;
import com.mycompany.elecciones2023datos.model.Partido;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


/**
 * @author Fede
 */
public class PactosOpcion2 extends javax.swing.JFrame {
    private JButton botonSeleccionado = null;
    DefaultTableModel modeltablaIzq = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel modeltablaDcha = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private int filaSeleccionadaAnterior = -1;
    private int filaSeleccionadaAnteriorDcha = -1;
    List<Point> celdasPintadas = new ArrayList<>();
    List<Point> celdasPintadasTDcha = new ArrayList<>();

    GraficosController graficosController = new GraficosController();
    private String codigo;
    private int tipoElecciones;
    private boolean oficiales;
    private int arcoOFaldon;
    private List<Partido> partidos;
    private List<CpDTO> partidosIzqDentro;
    private List<CpDTO> partidosDerDentro;
    private CarmenDTO dto;
    boolean pactosIn = false;

    private String avance;

    public PactosOpcion2() {
        this.avance = avance;
        initComponents();
        tablaIzq.setModel(modeltablaIzq);
        tablaDcha.setModel(modeltablaDcha);
        cargarDatos();

        DefaultListSelectionModel selectionModel = new DefaultListSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tablaIzq.setSelectionModel(selectionModel);


    }

    public PactosOpcion2(int arcoOFaldon, String codigo, int tipoElecciones, boolean oficiales, String avance) {
        this.avance = avance;
        initComponents();
        this.codigo = codigo;
        this.tipoElecciones = tipoElecciones;
        this.oficiales = oficiales;
        this.arcoOFaldon = arcoOFaldon;
        partidos = new ArrayList<>();
        partidosIzqDentro = new ArrayList<>();
        partidosDerDentro = new ArrayList<>();
        tablaIzq.setModel(modeltablaIzq);
        tablaDcha.setModel(modeltablaDcha);
        cargarDatos();
        tablaPactosDcha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaPactosIzq.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


    }


    private void pintarCeldasGuardadas(JTable table, Color color) {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (celdasPintadas.contains(new Point(row, column))) {
                    c.setBackground(color);
                    // Almacenar el color original del texto
                    Color originalForeground = c.getForeground();
                    c.setForeground(originalForeground);
                } else {
                    // Restaurar el color de fondo predeterminado para las demás celdas
                    c.setBackground(table.getBackground());
                    // Restaurar el color de texto predeterminado
                    c.setForeground(table.getForeground());
                }


                return c;
            }
        };

        // Establecer el renderer personalizado para todas las columnas de la tabla
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        table.repaint();
    }

    private void pintarCeldasGuardadasDcha(JTable table, Color color) {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (celdasPintadasTDcha.contains(new Point(row, column))) {
                    c.setBackground(color);
                    // Almacenar el color original del texto
                    Color originalForeground = c.getForeground();
                    c.setForeground(originalForeground);
                } else {
                    // Restaurar el color de fondo predeterminado para las demás celdas
                    c.setBackground(table.getBackground());
                    // Restaurar el color de texto predeterminado
                    c.setForeground(table.getForeground());
                }


                return c;
            }
        };

        // Establecer el renderer personalizado para todas las columnas de la tabla
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        table.repaint();
    }

    public void restaurarColorCeldas(JTable table, ArrayList<Point> celdasGuardadas) {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();

        for (Point punto : celdasGuardadas) {
            int fila = punto.x;
            int columna = punto.y;
            Component component = table.prepareRenderer(renderer, fila, columna);
            component.setBackground(table.getBackground());
        }

        table.repaint();
    }


    private void cargarDatos() {

        switch (tipoElecciones) {
            case 1 -> tipoDatos.setText("MUNICIPALES OFICIALES");
            case 2 -> tipoDatos.setText("AUTONÓMICAS OFICIALES");
            case 3 -> tipoDatos.setText("MUNICIPALES SONDEO");
            case 4 -> tipoDatos.setText("AUTONÓMICAS SONDEO");
        }

        if (codigo != null) {
            modeltablaIzq.addColumn("PARTIDOS IZQ");
            modeltablaDcha.addColumn("PARTIDOS DCHA");
            dto = switch (tipoElecciones) {
                case 1 -> graficosController.getCarmenDtoOficialMuni(codigo, avance);
                case 2 -> graficosController.getCarmenDtoOficialAuto(codigo, avance);
                case 3 -> graficosController.getCarmenDtoSondeoMuni(codigo, avance);
                case 4 -> graficosController.getCarmenDtoSondeoAuto(codigo, avance);
                default -> null;
            };
            lblNombreCircunscripcion.setText(dto.getCircunscripcion().getNombreCircunscripcion());
            dto.getCpDTO().forEach(cp -> {
                Partido p = graficosController.getPartido(cp.getCodigoPartido());
                partidos.add(p);
            });

            for (String partido : partidos.stream().map(Partido::getSiglas).toList()) {
                modeltablaIzq.addRow(new Object[]{partido});
                modeltablaDcha.addRow(new Object[]{partido});
            }
            lblMayoriaAbsoluta.setText(getMayoria(dto.getCircunscripcion().getEscanios()) + "");
        }
    }

    private int getMayoria(int escanios) {
        return (escanios / 2) + 1;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEntraPartidoIzquierda = new javax.swing.JButton();
        btnSalePartidoDer = new javax.swing.JButton();
        btnSalePartidoIzq = new javax.swing.JButton();
        btnEntraPartidoDer = new javax.swing.JButton();
        btnEntra = new javax.swing.JButton();
        btnSale = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaPactosIzq = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaIzq = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaPactosDcha = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaDcha = new javax.swing.JTable();
        lblNombreCircunscripcion = new javax.swing.JLabel();
        tipoDatos = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblMayoriaAbsoluta = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblEscTotalesIzq = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblEscTotalesDcha = new javax.swing.JLabel();
        btnReset1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PACTOS 2023");

        btnEntraPartidoIzquierda.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEntraPartidoIzquierda.setText("=>");
        btnEntraPartidoIzquierda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntraPartidoIzquierdaActionPerformed(evt);
            }
        });

        btnSalePartidoDer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalePartidoDer.setText("=>");
        btnSalePartidoDer.setToolTipText("");
        btnSalePartidoDer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalePartidoDerActionPerformed(evt);
            }
        });

        btnSalePartidoIzq.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalePartidoIzq.setText("<=");
        btnSalePartidoIzq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalePartidoIzqActionPerformed(evt);
            }
        });

        btnEntraPartidoDer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEntraPartidoDer.setText("<=");
        btnEntraPartidoDer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntraPartidoDerActionPerformed(evt);
            }
        });

        btnEntra.setBackground(new java.awt.Color(153, 255, 153));
        btnEntra.setForeground(new java.awt.Color(0, 0, 0));
        btnEntra.setText("ENTRA");
        btnEntra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntraActionPerformed(evt);
            }
        });

        btnSale.setBackground(new java.awt.Color(255, 153, 153));
        btnSale.setForeground(new java.awt.Color(0, 0, 0));
        btnSale.setText("SALE");
        btnSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaleActionPerformed(evt);
            }
        });

        tablaPactosIzq.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaPactosIzq.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "PACTOS IZQ"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane5.setViewportView(tablaPactosIzq);
        if (tablaPactosIzq.getColumnModel().getColumnCount() > 0) {
            tablaPactosIzq.getColumnModel().getColumn(0).setResizable(false);
        }

        tablaIzq.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaIzq.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null},
                        {null},
                        {null},
                        {null}
                },
                new String[]{
                        "PARTIDOS IZQ"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane6.setViewportView(tablaIzq);
        if (tablaIzq.getColumnModel().getColumnCount() > 0) {
            tablaIzq.getColumnModel().getColumn(0).setResizable(false);
        }

        tablaPactosDcha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaPactosDcha.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "PACTOS DCHA"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane7.setViewportView(tablaPactosDcha);
        if (tablaPactosDcha.getColumnModel().getColumnCount() > 0) {
            tablaPactosDcha.getColumnModel().getColumn(0).setResizable(false);
        }

        tablaDcha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaDcha.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null},
                        {null},
                        {null},
                        {null}
                },
                new String[]{
                        "PARTIDOS DCHA"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane8.setViewportView(tablaDcha);
        if (tablaDcha.getColumnModel().getColumnCount() > 0) {
            tablaDcha.getColumnModel().getColumn(0).setResizable(false);
        }

        lblNombreCircunscripcion.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblNombreCircunscripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreCircunscripcion.setText("Circunscripción");
        lblNombreCircunscripcion.setToolTipText("");

        tipoDatos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tipoDatos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tipoDatos.setText("MUNICIPALES");
        tipoDatos.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mayoria absoluta: ");

        lblMayoriaAbsoluta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMayoriaAbsoluta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMayoriaAbsoluta.setText("0");

        jLabel3.setText("Total escaños: ");

        lblEscTotalesIzq.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEscTotalesIzq.setText("0");

        jLabel4.setText("Total escaños: ");

        lblEscTotalesDcha.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEscTotalesDcha.setText("0");

        btnReset1.setBackground(new java.awt.Color(153, 0, 51));
        btnReset1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReset1.setForeground(new java.awt.Color(255, 255, 255));
        btnReset1.setText("RESET");
        btnReset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(41, 41, 41)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(btnEntra, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnSale, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(btnReset1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(tipoDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(256, 256, 256)
                                                                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(lblEscTotalesIzq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addGap(45, 45, 45)
                                                                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(lblEscTotalesDcha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(btnSalePartidoIzq, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(btnEntraPartidoIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(btnEntraPartidoDer, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(btnSalePartidoDer, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(228, 228, 228)
                                                .addComponent(lblNombreCircunscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(55, 55, 55))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMayoriaAbsoluta, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(373, 373, 373))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnEntra, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSale, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNombreCircunscripcion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(lblMayoriaAbsoluta))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(lblEscTotalesDcha)
                                        .addComponent(jLabel3)
                                        .addComponent(lblEscTotalesIzq))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(btnEntraPartidoIzquierda)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnSalePartidoIzq)
                                                .addGap(85, 85, 85))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(88, 88, 88)
                                                        .addComponent(btnEntraPartidoDer)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btnSalePartidoDer))
                                                .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tipoDatos)
                                        .addComponent(btnReset1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16))
        );

        lblNombreCircunscripcion.getAccessibleContext().setAccessibleName("lblCircunscripcion");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void vaciarTablas() {

        DefaultTableModel modelPactosIzq = (DefaultTableModel) tablaPactosIzq.getModel();
        DefaultTableModel modelPactosDcha = (DefaultTableModel) tablaPactosDcha.getModel();

        modelPactosIzq.setRowCount(0);
        modelPactosDcha.setRowCount(0);
    }

    private void btnEntraPartidoIzquierdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntraPartidoIzquierdaActionPerformed
        int filaSeleccionada = tablaIzq.getSelectedRow();
        celdasPintadas.add(new Point(filaSeleccionada, 0));
        pintarCeldasGuardadas(tablaIzq, Color.GREEN);

        for (Point celda : celdasPintadas) {
            int fila = (int) celda.getX();
            int columna = (int) celda.getY();
            System.out.println("Fila: " + fila + ", Columna: " + columna);
        }


        System.out.println(" ");
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
                partidosIzqDentro.add(dto.getCpDTO().get(filaSeleccionada));
                int escanos;
                if (oficiales) {
                    escanos = Integer.parseInt(lblEscTotalesIzq.getText()) + dto.getCpDTO().get(filaSeleccionada).getEscanos_hasta();
                } else {
                    escanos = Integer.parseInt(lblEscTotalesIzq.getText()) + dto.getCpDTO().get(filaSeleccionada).getEscanos_hasta_sondeo();
                }
                lblEscTotalesIzq.setText(escanos + "");

                if (arcoOFaldon == 1) {
                    switch (tipoElecciones) {
                        case 1 ->
                                graficosController.entraPartidoIzqOficialMuni(dto.getCircunscripcion().getCodigo(), dto.getCpDTO().get(filaSeleccionada).getCodigoPartido());
                        case 2 ->
                                graficosController.entraPartidoIzqOficialAuto(dto.getCircunscripcion().getCodigo(), dto.getCpDTO().get(filaSeleccionada).getCodigoPartido());
                        case 3 ->
                                graficosController.entraPartidoIzqSondeoMuni(dto.getCircunscripcion().getCodigo(), dto.getCpDTO().get(filaSeleccionada).getCodigoPartido());
                        case 4 ->
                                graficosController.entraPartidoIzqSondeoAuto(dto.getCircunscripcion().getCodigo(), dto.getCpDTO().get(filaSeleccionada).getCodigoPartido());
                    }
                } else if (arcoOFaldon == 2) {
                    graficosController.entraIzqPactos(filaSeleccionada + 1);
                }
            }

            TableCellRenderer renderer = tablaIzq.getCellRenderer(filaSeleccionada, 0);
            Component componente = tablaIzq.prepareRenderer(renderer, filaSeleccionada, 0);
            //componente.setBackground(Color.LIGHT_GRAY);
            tablaIzq.clearSelection(); // deseleccionar la fila seleccionada
        }


    }//GEN-LAST:event_btnEntraPartidoIzquierdaActionPerformed

    private void btnSalePartidoIzqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalePartidoIzqActionPerformed
        int filaSeleccionada = tablaPactosIzq.getSelectedRow();

        int columnaSeleccionada = tablaIzq.getSelectedColumn();
        // String textoCeldaSeleccionada = tablaPactosIzq.getValueAt(filaSeleccionada, 0).toString();

        DefaultTableModel modelPactosIzq = (DefaultTableModel) tablaPactosIzq.getModel();

//       System.out.println("seleccionado:" + modelPactosIzq.getValueAt(filaSeleccionada, 0));

        for (int i = 0; i < celdasPintadas.size(); i++) {
            Point punto = celdasPintadas.get(i);
            int x = (int) punto.getX();
            int y = (int) punto.getY();


//            
//            System.out.println(x + " " + y);
//            System.out.println(modeltablaIzq.getValueAt(x, y));
            // Realiza las operaciones que desees con cada punto

            if (tablaPactosIzq.getSelectedRow() >= 0 && x >= 0 && y >= 0) {
                if (modelPactosIzq.getValueAt(filaSeleccionada, 0) == modeltablaIzq.getValueAt(x, y)) {
                    System.out.println("Se eliminara:" + modeltablaIzq.getValueAt(x, y));
                    System.out.println("Se eliminara:" + modelPactosIzq.getValueAt(filaSeleccionada, 0));

                    // Eliminar el elemento del ArrayList
                    celdasPintadas.remove(i);
                    pintarCeldasGuardadas(tablaIzq, Color.GREEN);
                    // Actualizar el índice para continuar la iteración correctamente
                    i--;
                }
            }
        }


        if (filaSeleccionada != -1) {
            DefaultTableModel modelPactos = (DefaultTableModel) tablaPactosIzq.getModel();

            int escanos;
            if (oficiales) {
                escanos = Integer.parseInt(lblEscTotalesIzq.getText()) - partidosIzqDentro.get(filaSeleccionada).getEscanos_hasta();
            } else {
                escanos = Integer.parseInt(lblEscTotalesIzq.getText()) - partidosIzqDentro.get(filaSeleccionada).getEscanos_hasta_sondeo();
            }
            lblEscTotalesIzq.setText(escanos + "");

            String siglas = tablaPactosIzq.getValueAt(tablaPactosIzq.getSelectedRow(), 0).toString();
            Partido seleccionado = partidos.stream().filter(partido -> partido.getSiglas().equals(siglas)).
                    findFirst().orElse(null);
            String cod = seleccionado.getCodigo();
            //int posicion = partidos.indexOf(seleccionado);
            if (arcoOFaldon == 1) {
                graficosController.borrarPartido(dto.getCircunscripcion().getCodigo(), cod, tipoElecciones, 1);
            } else if (arcoOFaldon == 2) {
                // graficosController.salePactosIzq();
            }

            // Eliminar fila seleccionada de tablaPactosIzq
            modelPactos.removeRow(filaSeleccionada);
            partidosIzqDentro.remove(filaSeleccionada);
        }
    }//GEN-LAST:event_btnSalePartidoIzqActionPerformed

    private void btnEntraPartidoDerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntraPartidoDerActionPerformed
        int filaSeleccionada = tablaDcha.getSelectedRow();

        celdasPintadasTDcha.add(new Point(filaSeleccionada, 0));
        pintarCeldasGuardadasDcha(tablaDcha, Color.GREEN);


        if (filaSeleccionada != -1) {
            String texto = (String) tablaDcha.getValueAt(filaSeleccionada, 0);
            tablaDcha.setValueAt(texto, filaSeleccionada, 0);
            DefaultTableModel modelPactos = (DefaultTableModel) tablaPactosDcha.getModel();
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
                partidosDerDentro.add(dto.getCpDTO().get(filaSeleccionada));
                int escanos;
                if (oficiales) {
                    escanos = Integer.parseInt(lblEscTotalesDcha.getText()) + dto.getCpDTO().get(filaSeleccionada).getEscanos_hasta();
                } else {
                    escanos = Integer.parseInt(lblEscTotalesDcha.getText()) + dto.getCpDTO().get(filaSeleccionada).getEscanos_hasta_sondeo();
                }
                lblEscTotalesDcha.setText(escanos + "");

                if (arcoOFaldon == 1) {
                    switch (tipoElecciones) {
                        case 1 ->
                                graficosController.entraPartidoDerOficialMuni(dto.getCircunscripcion().getCodigo(), dto.getCpDTO().get(filaSeleccionada).getCodigoPartido());
                        case 2 ->
                                graficosController.entraPartidoDerOficialAuto(dto.getCircunscripcion().getCodigo(), dto.getCpDTO().get(filaSeleccionada).getCodigoPartido());
                        case 3 ->
                                graficosController.entraPartidoDerSondeoMuni(dto.getCircunscripcion().getCodigo(), dto.getCpDTO().get(filaSeleccionada).getCodigoPartido());
                        case 4 ->
                                graficosController.entraPartidoDerSondeoAuto(dto.getCircunscripcion().getCodigo(), dto.getCpDTO().get(filaSeleccionada).getCodigoPartido());
                    }
                } else if (arcoOFaldon == 2) {
                    graficosController.entraDerPactos(filaSeleccionada + 1);
                }
            }
            TableCellRenderer renderer = tablaDcha.getCellRenderer(filaSeleccionada, 0);
            Component componente = tablaDcha.prepareRenderer(renderer, filaSeleccionada, 0);
            //componente.setBackground(Color.LIGHT_GRAY);
            tablaDcha.clearSelection(); // deseleccionar la fila seleccionada
        }
    }//GEN-LAST:event_btnEntraPartidoDerActionPerformed

    private void btnSalePartidoDerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalePartidoDerActionPerformed
        int filaSeleccionada = tablaPactosDcha.getSelectedRow();

        int columnaSeleccionada = tablaDcha.getSelectedColumn();
        // String textoCeldaSeleccionada = tablaPactosIzq.getValueAt(filaSeleccionada, 0).toString();

        DefaultTableModel modelPactosDcha = (DefaultTableModel) tablaPactosDcha.getModel();

//       System.out.println("seleccionado:" + modelPactosIzq.getValueAt(filaSeleccionada, 0));

        for (int i = 0; i < celdasPintadasTDcha.size(); i++) {
            Point punto = celdasPintadasTDcha.get(i);
            int x = (int) punto.getX();
            int y = (int) punto.getY();


//            
//            System.out.println(x + " " + y);
//            System.out.println(modeltablaIzq.getValueAt(x, y));
            // Realiza las operaciones que desees con cada punto

            if (tablaPactosDcha.getSelectedRow() >= 0 && x >= 0 && y >= 0) {
                if (modelPactosDcha.getValueAt(filaSeleccionada, 0) == modeltablaDcha.getValueAt(x, y)) {
                    System.out.println("Se eliminara:" + modeltablaDcha.getValueAt(x, y));
                    System.out.println("Se eliminara:" + modelPactosDcha.getValueAt(filaSeleccionada, 0));

                    // Eliminar el elemento del ArrayList
                    celdasPintadasTDcha.remove(i);
                    pintarCeldasGuardadasDcha(tablaDcha, Color.GREEN);
                    // Actualizar el índice para continuar la iteración correctamente
                    i--;
                }
            }
        }
        if (filaSeleccionada != -1) {
            DefaultTableModel modelPactos = (DefaultTableModel) tablaPactosDcha.getModel();

            int escanos;
            if (oficiales) {
                escanos = Integer.parseInt(lblEscTotalesDcha.getText()) - partidosDerDentro.get(filaSeleccionada).getEscanos_hasta();
            } else {
                escanos = Integer.parseInt(lblEscTotalesDcha.getText()) - partidosDerDentro.get(filaSeleccionada).getEscanos_hasta_sondeo();
            }
            lblEscTotalesDcha.setText(escanos + "");

            String siglas = tablaPactosDcha.getValueAt(tablaPactosDcha.getSelectedRow(), 0).toString();
            String cod = partidos.stream().filter(partido -> partido.getSiglas().equals(siglas)).
                    findFirst().orElse(null).getCodigo();
            graficosController.borrarPartido(dto.getCircunscripcion().getCodigo(), cod, tipoElecciones, 0);

            // Eliminar fila seleccionada de tablaPactosIzq
            modelPactos.removeRow(filaSeleccionada);
            partidosDerDentro.remove(filaSeleccionada);
        }
    }//GEN-LAST:event_btnSalePartidoDerActionPerformed


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

    private void btnEntraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntraActionPerformed
        celdasPintadas.clear();
        pintarCeldasGuardadas(tablaIzq, Color.white);
        celdasPintadasTDcha.clear();
        pintarCeldasGuardadasDcha(tablaDcha, Color.white);

        vaciarTablas();
        lblEscTotalesIzq.setText("0");
        lblEscTotalesDcha.setText("0");
        if (arcoOFaldon == 1) {
            switch (tipoElecciones) {
                case 1 -> {
                    graficosController.pactosArcoAuto();
                    graficosController.resetArcoMuni();
                }
                case 2 -> {
                    graficosController.pactosArcoMuni();
                    graficosController.resetArcoAuto();
                }
                case 3 -> {
                    graficosController.pactosArcoAutoSondeo();
                    graficosController.resetArcoMuni();
                }
                case 4 -> {
                    graficosController.pactosArcoMuniSondeo();
                    graficosController.resetArcoAuto();
                }
            }
        } else if (arcoOFaldon == 2) {
            if (!pactosIn) {
                graficosController.entraPactos();
                pactosIn = true;
            } else {
                graficosController.reinicioPactos();
            }
        }
    }//GEN-LAST:event_btnEntraActionPerformed

    private void btnSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaleActionPerformed
        celdasPintadas.clear();
        pintarCeldasGuardadas(tablaIzq, Color.white);
        celdasPintadasTDcha.clear();
        pintarCeldasGuardadasDcha(tablaDcha, Color.white);

        vaciarTablas();
        lblEscTotalesIzq.setText("0");
        lblEscTotalesDcha.setText("0");
        if (arcoOFaldon == 1) {
            switch (tipoElecciones) {
                case 1 -> {
                    graficosController.pactosArcoAuto();
                    graficosController.resetArcoMuni();
                }
                case 2 -> {
                    graficosController.pactosArcoMuni();
                    graficosController.resetArcoAuto();
                }
                case 3 -> {
                    graficosController.pactosArcoAutoSondeo();
                    graficosController.resetArcoMuni();
                }
                case 4 -> {
                    graficosController.pactosArcoMuniSondeo();
                    graficosController.resetArcoAuto();
                }
            }
        } else if (arcoOFaldon == 2) {
            graficosController.salePactos();
            pactosIn = false;
        }

    }//GEN-LAST:event_btnSaleActionPerformed

    private void btnReset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset1ActionPerformed

        celdasPintadas.clear();
        pintarCeldasGuardadas(tablaIzq, Color.white);
        celdasPintadasTDcha.clear();
        pintarCeldasGuardadasDcha(tablaDcha, Color.white);


        lblEscTotalesIzq.setText("0");
        lblEscTotalesDcha.setText("0");
        if (arcoOFaldon == 1) {
            switch (tipoElecciones) {
                case 1 -> {
                    graficosController.pactosArcoAuto();
                    graficosController.resetArcoMuni();
                }
                case 2 -> {
                    graficosController.pactosArcoMuni();
                    graficosController.resetArcoAuto();
                }
                case 3 -> {
                    graficosController.pactosArcoAutoSondeo();
                    graficosController.resetArcoMuni();
                }
                case 4 -> {
                    graficosController.pactosArcoMuniSondeo();
                    graficosController.resetArcoAuto();
                }
            }
        } else if (arcoOFaldon == 2) {
            graficosController.reinicioPactos();
        }

    }//GEN-LAST:event_btnReset1ActionPerformed


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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
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
    private javax.swing.JButton btnEntra;
    private javax.swing.JButton btnEntraPartidoDer;
    private javax.swing.JButton btnEntraPartidoIzquierda;
    private javax.swing.JButton btnReset1;
    private javax.swing.JButton btnSale;
    private javax.swing.JButton btnSalePartidoDer;
    private javax.swing.JButton btnSalePartidoIzq;
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
    private javax.swing.JLabel lblNombreCircunscripcion;
    private javax.swing.JTable tablaDcha;
    private javax.swing.JTable tablaIzq;
    private javax.swing.JTable tablaPactosDcha;
    private javax.swing.JTable tablaPactosIzq;
    private javax.swing.JLabel tipoDatos;
    // End of variables declaration//GEN-END:variables
}
