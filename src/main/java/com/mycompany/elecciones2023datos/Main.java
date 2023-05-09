/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.elecciones2023datos;

import com.mycompany.elecciones2023datos.DTO.CarmenDTO;
import com.mycompany.elecciones2023datos.controllers.GraficosController;
import com.mycompany.elecciones2023datos.model.Circunscripcion;
import com.mycompany.elecciones2023datos.model.CpData;
import com.mycompany.elecciones2023datos.services.IClienteApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * @author Fede
 */
public class Main extends javax.swing.JFrame {

    Retrofit retrofit;
    IClienteApi clienteApi;

    Map<String, List<Circunscripcion>> circunscripcionesAutonomicas = new HashMap<>();
    Map<String, List<Circunscripcion>> cicunscripcionesMunicipales = new HashMap<>();
    Map<String, String> nombreCodigo = new HashMap<>();
    Map<String, String> nombreCodigoMunicipal = new HashMap<>();

    String selectedDb = "";

    GraficosController graficosController = new GraficosController();

    private boolean lateralIn = false;


    /**
     * Creates new form Main
     */

    public void initCircunscripcionesAutonomicas() {
        try {
            var autonomias = clienteApi.getAllCircunscripciones().execute().body();
            autonomias.stream()
                    .map(Circunscripcion::getNombreCircunscripcion).forEach(auto -> circunscripcionesAutonomicas.put(auto, null));
            for (Circunscripcion autonomia : autonomias) {
                var auxList = clienteApi.getCircunscripcionesByAutonomia(autonomia.getCodigo()).execute().body();
                nombreCodigo.put(autonomia.getNombreCircunscripcion(), autonomia.getCodigo());
                auxList.forEach(x -> {
                    nombreCodigoMunicipal.put(x.getNombreCircunscripcion(), x.getCodigo());
                });
                circunscripcionesAutonomicas.put(autonomia.getNombreCircunscripcion(), auxList);
            }


        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void initCircunscripcionesMunicipales() {
        try {
            var autonomias = clienteApi.getAllCircunscripciones().execute().body();
            autonomias.stream()
                    .map(Circunscripcion::getNombreCircunscripcion).forEach(auto -> cicunscripcionesMunicipales.put(auto, null));
            for (Circunscripcion autonomia : autonomias) {
                var auxList = clienteApi.getMunicipiosByCodigo(autonomia.getCodigo()).execute().body();
                for (Circunscripcion circunscripcion : auxList) {
                    nombreCodigoMunicipal.put(circunscripcion.getNombreCircunscripcion(), circunscripcion.getCodigo());
                }
                cicunscripcionesMunicipales.put(autonomia.getNombreCircunscripcion(), auxList);
            }


        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showDataTable(String nombre) {
        //Solo hay seleccioanda una autonomia
        try {
            CarmenDTO carmenDTO = clienteApi.getCarmenDto(nombreCodigo.get(nombre)).execute().body();
            List<CpData> datos = CpData.fromCarmenDto(carmenDTO);
            printData(datos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void showDataTableMunicipio(String nombre) {
        try {
            CarmenDTO carmenDTO = clienteApi.getCarmenDtoOficial(nombreCodigoMunicipal.get(nombre)).execute().body();
            System.out.println(carmenDTO);
            assert carmenDTO != null;
            List<CpData> datos = CpData.fromCarmenDto(carmenDTO);
            printData(datos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printData(List<CpData> list) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("CODIGO");
        tableModel.addColumn("NOMBRE");
        tableModel.addColumn("VOTANTES");
        tableModel.addColumn("ESCANOS");


        for (CpData cpDTO : list) {
            Object[] rowData = {cpDTO.getCodigo(), cpDTO.getNombrePartido(),
                    cpDTO.getVoto(), "" + cpDTO.getEscanosHasta() + "/" + cpDTO.getEscanosDesde()};
            tableModel.addRow(rowData);
        }
        JScrollPane scrollPane = new JScrollPane(tablaDatos);
        tablaDatos = new JTable(tableModel);
        tablaDatos.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jScrollPane1.setViewportView(tablaDatos);
    }

    public Main() {
        retrofit = new Retrofit.Builder().baseUrl("http://localhost:9090").addConverterFactory(GsonConverterFactory.create()).build();
        clienteApi = retrofit.create(IClienteApi.class);
        initCircunscripcionesAutonomicas();
        initCircunscripcionesMunicipales();
        initComponents();
        Tablas();


    }

    private void Tablas() {

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
        tablaGraficos = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaMunicipios = new javax.swing.JTable();
        entra = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        comboDatos = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ELECCIONES 2023");

        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Cod", "Partido", "Escaños", "Votos"
                }
        ));
        jScrollPane1.setViewportView(tablaDatos);
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("COMUNIDADES");

        for (String s : circunscripcionesAutonomicas.keySet()) {
            tableModel.addRow(new Object[]{s});
        }
        JScrollPane scrollPane = new JScrollPane(tablaComunidades);

        tablaComunidades = new JTable(tableModel);

        //tablaComunidades.getTableHeader().setResizingAllowed(false);
        jScrollPane2.setViewportView(tablaComunidades);

        tablaComunidades.getSelectionModel().addListSelectionListener(e -> {
            String codAutonomia;
            if (((String) Objects.requireNonNull(comboDatos.getSelectedItem())).endsWith("AUTONOMICAS")) {
                int selectedRow = tablaComunidades.getSelectedRow();
                if (selectedRow != -1) {
                    loadSelectedAutonomicas((String) tablaComunidades.getValueAt(selectedRow, 0));
                    codAutonomia = nombreCodigoMunicipal.get(tablaComunidades.getValueAt(selectedRow, 0));
                    //TODO:Hacer un switch aqui para distinguir con qué datos actualizamos: Oficiales A o M, Sondeo A o M
                    graficosController.selectedAutonomicas(codAutonomia);
                }
                showDataTable((String) tablaComunidades.getValueAt(selectedRow, 0));
            } else {
                int selectedRow = tablaComunidades.getSelectedRow();
                if (selectedRow != -1) {
                    loadSelectedMunicipales((String) tablaComunidades.getValueAt(selectedRow, 0));
                    codAutonomia = nombreCodigoMunicipal.get(tablaComunidades.getValueAt(selectedRow, 0));
                    //TODO:Hacer un switch aqui para distinguir con qué datos actualizamos: Oficiales A o M, Sondeo A o M
                    graficosController.selectedMunicipales(codAutonomia);
                }
                showDataTable((String) tablaComunidades.getValueAt(selectedRow, 0));

            }
        });


        tablaGraficos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {"Mapa Electoral"},
                        {"Faldon Mapa"},
                        {"Pactometro"},
                        {"Lateral"}
                },
                new String[]{
                        "GRAFICOS"
                }
        ));
        jScrollPane3.setViewportView(tablaGraficos);
        if (tablaGraficos.getColumnModel().getColumnCount() > 0) {
            tablaGraficos.getColumnModel().getColumn(0).setResizable(false);
        }

        entra.setBackground(new java.awt.Color(153, 255, 153));
        entra.setText("ENTRA");
        entra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entraActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 102, 102));
        jButton2.setText("SALE");
        jButton2.addActionListener(e -> saleEvent());

        comboDatos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboDatos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"OFICIALES MUNICIPALES", "OFICIALES AUTONOMICAS", "SONDEO MUNICIPALES", "SONDEO AUTONOMICAS"}));
        selectedDb = "OFICIALES MUNICIPALES";
        comboDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDatosActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(242, 242, 242));
        jButton3.setIcon(new javax.swing.ImageIcon("src/main/resources/Imagenes/actualizar.png")); // NOI18N

        jButton4.setText("PACTOS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(153, 0, 51));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("RESET");

        jLabel1.setIcon(new javax.swing.ImageIcon("src/main/resources/Imagenes/iconconfig.png")); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton5)
                                                .addGap(260, 260, 260)
                                                .addComponent(entra, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(45, 45, 45)
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(19, 19, 19)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(28, 28, 28)
                                                .addComponent(comboDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(comboDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(entra, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton5)
                                                .addGap(15, 15, 15)))
                                .addGap(18, 18, 18))
        );
        comboDatos.addActionListener(e -> {
            selectedDb = (String) comboDatos.getSelectedItem();
            System.out.println(selectedDb);
        });
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saleEvent() {
        if (tablaGraficos.getSelectedRow() == 3) {

        }
    }

    private void loadSelectedAutonomicas(String cod) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("MUNICIPIOS");
        var municipios = circunscripcionesAutonomicas.get(cod);
        for (Circunscripcion municipio : municipios) {
            tableModel.addRow(new Object[]{municipio.getNombreCircunscripcion()});
        }
        JScrollPane scrollPane = new JScrollPane(tablaMunicipios);
        tablaMunicipios = new JTable(tableModel);
        tablaComunidades.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(tablaMunicipios);
        tablaMunicipios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = tablaMunicipios.getSelectedRow();
                if (selectedRow != -1) {
                    String nombreMunicipio = (String) tablaMunicipios.getValueAt(selectedRow, 0);
                    showDataTableMunicipio(nombreMunicipio);
                }
            }
        });


    }

    private void loadSelectedMunicipales(String cod) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("MUNICIPIOS");
        var municipios = cicunscripcionesMunicipales.get(cod);
        for (Circunscripcion municipio : municipios) {
            tableModel.addRow(new Object[]{municipio.getNombreCircunscripcion()});
        }

        JScrollPane scrollPane = new JScrollPane(tablaMunicipios);
        tablaMunicipios = new JTable(tableModel);
        tablaMunicipios.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jScrollPane4.setViewportView(tablaMunicipios);
    }

    private void entraActionPerformed(java.awt.event.ActionEvent evt) {
        switch (comboDatos.getSelectedIndex()) {
            //OFICIALES MUNICIPALES
            case 0 -> {
                if (tablaGraficos.getSelectedRow() != -1) {
                    switch (tablaGraficos.getSelectedRow()) {
                        case 0 -> System.out.println(0);
                        case 1 -> System.out.println(1);
                        case 2 -> System.out.println(2);
                        case 3 -> {
                            String codCCAA = null;
                            if (tablaComunidades.getSelectedRow() != -1) {
                                codCCAA = nombreCodigoMunicipal.get(tablaComunidades.getValueAt(tablaComunidades.getSelectedRow(), 0)).substring(0, 2);
                                graficosController.actualizaLateralMunicipales(codCCAA);
                            }
                            if (!lateralIn) {
                                graficosController.entraLateralMunicipales();
                                //TODO:Poner lateralIN =false en el sale o al pasar a otro gráfico compatible
                                lateralIn = true;
                            } else {
                                if (codCCAA != null) {
                                    graficosController.despliegaLateralMunicipales(codCCAA);
                                }
                            }
                        }
                        default -> System.out.println("Default");
                    }
                }
            }
            //OFICIALES AUTONOMICAS
            case 1 -> {
                if (tablaGraficos.getSelectedRow() != -1) {
                    switch (tablaGraficos.getSelectedRow()) {
                        case 0 -> System.out.println(0);
                        case 1 -> System.out.println(1);
                        case 2 -> System.out.println(2);
                        case 3 -> System.out.println(3);
                        default -> System.out.println("Default");
                    }
                }
            }
            //SONDEO MUNICIPALES
            case 2 -> {
                if (tablaGraficos.getSelectedRow() != -1) {
                    switch (tablaGraficos.getSelectedRow()) {
                        case 0 -> System.out.println(0);
                        case 1 -> System.out.println(1);
                        case 2 -> System.out.println(2);
                        case 3 -> System.out.println(3);
                        default -> System.out.println("Default");
                    }
                }
            }
            //SONDEO AUTONOMICAS
            case 3 -> {
                if (tablaGraficos.getSelectedRow() != -1) {
                    switch (tablaGraficos.getSelectedRow()) {
                        case 0 -> System.out.println(0);
                        case 1 -> System.out.println(1);
                        case 2 -> System.out.println(2);
                        case 3 -> System.out.println(3);
                        default -> System.out.println("Default");
                    }
                }
            }
            default -> System.out.println("Default");
        }

        // if (tablaGraficos.getSelectedRow() == 3) {
        //     int selectedRow = -1;
        //     if (tablaMunicipios.getSelectedRow() != -1) {
        //         selectedRow = tablaMunicipios.getSelectedRow();
        //         var codigo = nombreCodigoMunicipal.get(tablaMunicipios.getValueAt(selectedRow, 0));
        //         if (selectedDb.equals("DATOS AUTONOMICAS") || selectedDb.equals("SONDEO AUTONOMICAS")) {
        //             System.out.println("ENTRA AUTO");
//
        //             graficosController.entraLateralAutonomicas();
        //         } else {
        //             System.out.println("ENTRA MUNI");
//
        //             graficosController.entraLateralMunicipales();
        //         }
        //         System.out.println(codigo);
        //     } else {
        //         selectedRow = tablaComunidades.getSelectedRow();
        //         var codigo = nombreCodigoMunicipal.get(tablaComunidades.getValueAt(selectedRow, 0));
        //         if (selectedDb.equals("DATOS AUTONOMICAS") || selectedDb.equals("SONDEO AUTONOMICAS")) {
        //             System.out.println("ENTRA AUTO");
//
        //             graficosController.entraLateralAutonomicas();
        //         } else {
        //             System.out.println("ENTRA MUNI");
//
        //             graficosController.entraLateralMunicipales();
        //         }
        //         System.out.println(codigo);
        //     }
        // } else if (tablaGraficos.getSelectedRow() == 1) {
        //     int selectedRow = -1;
        //     if (tablaMunicipios.getSelectedRow() != -1) {
        //         selectedRow = tablaMunicipios.getSelectedRow();
        //         var codigo = nombreCodigoMunicipal.get(tablaMunicipios.getValueAt(selectedRow, 0));
        //         if (selectedDb.equals("DATOS AUTONOMICAS") || selectedDb.equals("SONDEO AUTONOMICAS")) {
        //             System.out.println("ENTRA AUTO");
//
        //             graficosController.entraFaldonAuto();
        //         } else {
        //             System.out.println("ENTRA MUNI");
//
        //             graficosController.entraLateralMunicipales();
        //         }
        //         System.out.println(codigo);
        //     } else {
        //         selectedRow = tablaComunidades.getSelectedRow();
        //         var codigo = nombreCodigoMunicipal.get(tablaComunidades.getValueAt(selectedRow, 0));
        //         if (selectedDb.equals("DATOS AUTONOMICAS") || selectedDb.equals("SONDEO AUTONOMICAS")) {
        //             System.out.println("ENTRA AUTO");
//
        //             graficosController.entraLateralAutonomicas();
        //         } else {
        //             System.out.println("ENTRA MUNI");
//
        //             graficosController.entraLateralMunicipales();
        //         }
        //         System.out.println(codigo);
        //     }
        // } else if (tablaGraficos.getSelectedRow() == 0) {
        //     graficosController.loadArcoAutonomicas();
        // }


    }//GEN-LAST:event_entraActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        switch (comboDatos.getSelectedIndex()) {
            //OFICIALES MUNICIPALES
            case 0 -> {
                if (tablaGraficos.getSelectedRow() != -1) {
                    switch (tablaGraficos.getSelectedRow()) {
                        case 0 -> System.out.println(0);
                        case 1 -> System.out.println(1);
                        case 2 -> System.out.println(2);
                        case 3 -> {
                            if (lateralIn) {
                                graficosController.saleLateralMunicipales();
                                //TODO:Poner lateralIN =false en el sale o al pasar a otro gráfico compatible
                                lateralIn = false;
                            }
                        }
                        default -> System.out.println("Default");
                    }
                }
            }
            //OFICIALES AUTONOMICAS
            case 1 -> {
                if (tablaGraficos.getSelectedRow() != -1) {
                    switch (tablaGraficos.getSelectedRow()) {
                        case 0 -> System.out.println(0);
                        case 1 -> System.out.println(1);
                        case 2 -> System.out.println(2);
                        case 3 -> System.out.println(3);
                        default -> System.out.println("Default");
                    }
                }
            }
            //SONDEO MUNICIPALES
            case 2 -> {
                if (tablaGraficos.getSelectedRow() != -1) {
                    switch (tablaGraficos.getSelectedRow()) {
                        case 0 -> System.out.println(0);
                        case 1 -> System.out.println(1);
                        case 2 -> System.out.println(2);
                        case 3 -> System.out.println(3);
                        default -> System.out.println("Default");
                    }
                }
            }
            //SONDEO AUTONOMICAS
            case 3 -> {
                if (tablaGraficos.getSelectedRow() != -1) {
                    switch (tablaGraficos.getSelectedRow()) {
                        case 0 -> System.out.println(0);
                        case 1 -> System.out.println(1);
                        case 2 -> System.out.println(2);
                        case 3 -> System.out.println(3);
                        default -> System.out.println("Default");
                    }
                }
            }
            default -> System.out.println("Default");
        }


        //Parte de colocación del botón
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();

        JFrame pactometroFin = new PactometroFin();
        pactometroFin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pactometroFin.setLocation(screenWidth / 4, screenHeight / 2);
        pactometroFin.setVisible(true);

        JFrame pactometro2 = new Pactometro2();
        pactometro2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pactometro2.setLocation(screenWidth / 4, screenHeight / 9);
        pactometro2.setVisible(true);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void comboDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDatosActionPerformed
        // TODO add your handling code here:
        
       /* String selectedOption = comboDatos.getSelectedItem().toString();
        
        if (selectedOption.equals("SONDEO AUTONÓMICAS") || selectedOption.equals("SONDEO MUNICIPALES")) {
            jScrollPane4.setVisible(false);
        } else {
            jScrollPane4.setVisible(true);
        }
      */


    }//GEN-LAST:event_comboDatosActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();

        JFrame config = new config();
        config.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        config.setLocation(screenWidth / 4, screenHeight / 4);
        config.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable tablaGraficos;
    private javax.swing.JComboBox<String> comboDatos;
    private javax.swing.JButton entra;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tablaComunidades;
    private javax.swing.JTable tablaDatos;
    private javax.swing.JTable tablaMunicipios;
    // End of variables declaration//GEN-END:variables
}
