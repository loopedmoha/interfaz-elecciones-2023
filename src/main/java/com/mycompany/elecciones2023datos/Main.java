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

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * @author Fede
 */
public class Main extends javax.swing.JFrame {

    Retrofit retrofit;
    IClienteApi clienteApi;

    TreeMap<String, List<Circunscripcion>> circunscripcionesAutonomicas = new TreeMap<>();
    TreeMap<String, List<Circunscripcion>> cicunscripcionesMunicipales = new TreeMap<>();
    TreeMap<String, String> nombreCodigo = new TreeMap<>();
    TreeMap<String, String> nombreCodigoMunicipal = new TreeMap<>();

    String selectedDb = "";

    GraficosController graficosController = new GraficosController();


    private boolean oficiales = true;
    private int tipoElecciones = 1;
    private boolean lateralIn = false;
    private boolean inferiorAutoIn = false;
    private boolean inferiorMuniIn = false;
    private boolean participacionIn = false;


    /**
     * Creates new form Main
     */
    private JButton botonSeleccionado = null;
    private JButton botonSeleccionado2 = null;

    public void initCircunscripcionesAutonomicas() {
        try {
            var autonomias = clienteApi.getAllAutonomiasAuto().execute().body();
            autonomias.stream()
                    .map(Circunscripcion::getNombreCircunscripcion).forEach(auto -> circunscripcionesAutonomicas.put(auto, null));
            for (Circunscripcion autonomia : autonomias) {
                var auxList = clienteApi.getCircunscripcionesByAutonomia(autonomia.getCodigo()).execute().body();
                auxList.sort(Comparator.comparing(Circunscripcion::getCodigo));
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
            var autonomias = clienteApi.getAllAutonomiasMuni().execute().body();
            autonomias.stream()
                    .map(Circunscripcion::getNombreCircunscripcion).forEach(auto -> cicunscripcionesMunicipales.put(auto.replaceAll(" ", ""), null));
            for (Circunscripcion autonomia : autonomias) {
                var auxList = clienteApi.getMunicipiosByCodigo(autonomia.getCodigo()).execute().body();
                auxList.sort(Comparator.comparing(Circunscripcion::getCodigo));
                for (Circunscripcion circunscripcion : auxList) {
                    nombreCodigoMunicipal.put(circunscripcion.getNombreCircunscripcion(), circunscripcion.getCodigo());
                }
                cicunscripcionesMunicipales.put(autonomia.getNombreCircunscripcion().replaceAll(" ", ""), auxList);
            }


        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showDataTable(String nombre) {
        //Solo hay seleccioanda una autonomia
        CarmenDTO carmenDTO = null;
        try {
            switch (selectedDb) {
                case "DA" -> carmenDTO = clienteApi.getCarmenDtoOficialAuto(nombreCodigo.get(nombre)).execute().body();
                case "SA" -> carmenDTO = clienteApi.getCarmenDtoSondeoAuto(nombreCodigo.get(nombre)).execute().body();
                case "SM" -> carmenDTO = clienteApi.getCarmenDtoSondeoMuni(nombreCodigo.get(nombre)).execute().body();
                default -> carmenDTO = clienteApi.getCarmenDtoOficialMuni(nombreCodigo.get(nombre)).execute().body();
            }
            // CarmenDTO carmenDTO = clienteApi.getCarmenDto(nombreCodigo.get(nombre)).execute().body();
            List<CpData> datos = CpData.fromCarmenDto(carmenDTO);
            printData(datos);
        } catch (IOException e) {
            throw new RuntimeException(e);


        }
    }

    public void showDataTableOficialMunicipio(String nombre) {
        var a = nombreCodigoMunicipal.get(nombre);

        try {
            CarmenDTO carmenDTO = clienteApi.getCarmenDtoOficialMuni(nombreCodigoMunicipal.get(nombre)).execute().body();
            System.out.println(carmenDTO);
            assert carmenDTO != null;
            List<CpData> datos = CpData.fromCarmenDto(carmenDTO);
            printData(datos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showDataTableSondeoMunicipio(String nombre) {
        var a = nombreCodigoMunicipal.get(nombre);

        try {
            CarmenDTO carmenDTO = clienteApi.getCarmenDtoSondeoMuni(nombreCodigoMunicipal.get(nombre)).execute().body();
            System.out.println(carmenDTO);
            assert carmenDTO != null;
            List<CpData> datos = CpData.fromCarmenDto(carmenDTO);
            printData(datos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showDataTableOficialAutonomicas(String nombre) {
        try {
            var a = nombreCodigoMunicipal.get(nombre);
            CarmenDTO carmenDTO = clienteApi.getCarmenDtoOficialAuto(nombreCodigoMunicipal.get(nombre)).execute().body();
            System.out.println(carmenDTO);
            assert carmenDTO != null;
            List<CpData> datos = CpData.fromCarmenDto(carmenDTO);
            printData(datos);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public void showDataTableSondeoAutonomicas(String nombre) {
        var a = nombreCodigoMunicipal.get(nombre);

        try {
            CarmenDTO carmenDTO = clienteApi.getCarmenDtoSondeoAuto(nombreCodigoMunicipal.get(nombre)).execute().body();
            System.out.println(carmenDTO);
            assert carmenDTO != null;
            List<CpData> datos = CpData.fromCarmenDto(carmenDTO);
            printData(datos);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public void printData(List<CpData> list) {
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // establece todas las celdas no editables
            }
        };
        tableModel.addColumn("COD");
        tableModel.addColumn("SIGLAS");
        tableModel.addColumn("E.D");
        tableModel.addColumn("E.H");
        tableModel.addColumn("HIST");
        tableModel.addColumn("% VOTO");
        tableModel.addColumn("VOTANTES");


        for (CpData cpDTO : list) {
            Object[] rowData = {cpDTO.getCodigo(), cpDTO.getSiglas(),
                    cpDTO.getEscanosDesde(), cpDTO.getEscanosHasta(), cpDTO.getEscanosHist(), cpDTO.getPorcentajeVoto(), cpDTO.getVotantes()};
            tableModel.addRow(rowData);
        }
        JScrollPane scrollPane = new JScrollPane(tablaDatos);
        tablaDatos.setModel(tableModel);
        tablaDatos.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tablaDatos);
    }

    public Main() {
        retrofit = new Retrofit.Builder().baseUrl("http://localhost:9090").addConverterFactory(GsonConverterFactory.create()).build();
        clienteApi = retrofit.create(IClienteApi.class);
        initCircunscripcionesAutonomicas();
        initCircunscripcionesMunicipales();
        initComponents();
        resaltarBoton(btnDatosMunicipales);
        TablaCartones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TablaFaldones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaMunicipios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaComunidades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        ListSelectionModel selectionModel = TablaCartones.getSelectionModel();
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
                    TablaCartones.clearSelection();
                }
            }
        });

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("Imagenes/iconconfig.png");
        ImageIcon icon = null;
        try {
            icon = new ImageIcon(ImageIO.read(inputStream));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        jLabel1.setIcon(icon);
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
        TablaCartones = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaMunicipios = new javax.swing.JTable();
        btnEntra = new javax.swing.JButton();
        btnSale = new javax.swing.JButton();
        btnPactos = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
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
        lblParticipacion = new javax.swing.JLabel();
        btnAvance1 = new javax.swing.JButton();
        btnAvance2 = new javax.swing.JButton();
        btnAvance3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblPartHistorica = new javax.swing.JLabel();
        lblEscanosTotales = new javax.swing.JLabel();
        lblEscTotales = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ELECCIONES 2023");

        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null}
                },
                new String[]{
                        "CODIGO", "SIGLAS", "E.D", "E.H", "% VOTO"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaDatos);
        if (tablaDatos.getColumnModel().getColumnCount() > 0) {
            tablaDatos.getColumnModel().getColumn(0).setResizable(false);
            tablaDatos.getColumnModel().getColumn(1).setResizable(false);
            tablaDatos.getColumnModel().getColumn(2).setResizable(false);
            tablaDatos.getColumnModel().getColumn(3).setResizable(false);
            tablaDatos.getColumnModel().getColumn(4).setResizable(false);
        }

        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // establece todas las celdas no editables
            }
        };

        tableModel.addColumn("COMUNIDADES");
        for (String s : circunscripcionesAutonomicas.keySet()) {
            tableModel.addRow(new Object[]{s});
        }
        tablaComunidades.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaComunidades);
        tablaComunidades.getTableHeader().setResizingAllowed(false);
        jScrollPane2.setViewportView(tablaComunidades);

        tablaComunidades.getSelectionModel().addListSelectionListener(e -> {
            String codAutonomia;
            CarmenDTO carmen = null;
            if (tipoElecciones == 2 || tipoElecciones == 4) {
                int selectedRow = tablaComunidades.getSelectedRow();
                if (selectedRow != -1) {
                    loadSelectedAutonomicas((String) tablaComunidades.getValueAt(selectedRow, 0));
                    codAutonomia = nombreCodigo.get(tablaComunidades.getValueAt(selectedRow, 0));
                    //TODO:Hacer un switch aqui para distinguir con qué datos actualizamos: Oficiales A o M, Sondeo A o M
                    if (oficiales) {
                        try {
                            carmen = clienteApi.getCarmenDtoOficialAuto(codAutonomia).execute().body();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        graficosController.selectedAutonomicasOficiales(codAutonomia);
                    } else {
                        try {
                            carmen = clienteApi.getCarmenDtoSondeoAuto(codAutonomia).execute().body();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        graficosController.selectedAutonomicasSondeo(codAutonomia);
                    }
                    showDataTable((String) tablaComunidades.getValueAt(selectedRow, 0));
                }

            } else {
                int selectedRow = tablaComunidades.getSelectedRow();
                if (selectedRow != -1) {
                    loadSelectedMunicipales((String) tablaComunidades.getValueAt(selectedRow, 0));
                    codAutonomia = nombreCodigoMunicipal.get(tablaComunidades.getValueAt(selectedRow, 0));
                    //TODO:Hacer un switch aqui para distinguir con qué datos actualizamos: Oficiales A o M, Sondeo A o M
                    if (oficiales) {
                        try {
                            carmen = clienteApi.getCarmenDtoOficialMuni(codAutonomia).execute().body();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        graficosController.selectedMunicipalesOficiales(codAutonomia);
                    } else {
                        try {
                            carmen = clienteApi.getCarmenDtoSondeoMuni(codAutonomia).execute().body();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        graficosController.selectedMunicipalesSondeo(codAutonomia);
                    }
                    showDataTable((String) tablaComunidades.getValueAt(selectedRow, 0));
                }

            }
            lblEscrutado.setText(carmen.getCircunscripcion().getEscrutado() + "");
            lblParticipacion.setText(carmen.getCircunscripcion().getParticipacion() + "");
            lblPartHistorica.setText(carmen.getCircunscripcion().getParticipacionHist() + "");
            if (tablaComunidades.getColumnModel().getColumnCount() > 0) {
                tablaComunidades.getColumnModel().getColumn(0).setResizable(false);
            }
        });
        if (tablaComunidades.getColumnModel().getColumnCount() > 0) {
            tablaComunidades.getColumnModel().getColumn(0).setResizable(false);
        }

        TablaCartones.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {"Resultados"},
                        {"Participación"},
                        {"Arco individual"},
                        {"Arco comparado"}
                },
                new String[]{
                        "CARTONES"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        TablaCartones.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                TablaCartonesHierarchyChanged(evt);
            }
        });
        jScrollPane3.setViewportView(TablaCartones);
        if (TablaCartones.getColumnModel().getColumnCount() > 0) {
            TablaCartones.getColumnModel().getColumn(0).setResizable(false);
        }

        tablaMunicipios.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null},
                        {null},
                        {null},
                        {null}
                },
                new String[]{
                        "MUNICIPIOS"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane4.setViewportView(tablaMunicipios);
        if (tablaMunicipios.getColumnModel().getColumnCount() > 0) {
            tablaMunicipios.getColumnModel().getColumn(0).setResizable(false);
        }

        btnEntra.setBackground(new java.awt.Color(153, 255, 153));
        btnEntra.setText("ENTRA");
        btnEntra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntraActionPerformed(evt);
            }
        });

        btnSale.setBackground(new java.awt.Color(255, 102, 102));
        btnSale.setText("SALE");
        btnSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaleActionPerformed(evt);
            }
        });

        btnPactos.setText("PACTOS");
        btnPactos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPactosActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(153, 0, 51));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("RESET");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon("Imagenes\\iconconfig.png")); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jLabel1MouseClicked(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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
                new Object[][]{
                        {"Mapa Electoral"},
                        {"Faldon Mapa"},
                        {"Pactometro"}
                },
                new String[]{
                        "FALDONES"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
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

        lblParticipacion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblParticipacion.setText("---");

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

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel4.setText("PART HISTORICO:");

        lblPartHistorica.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPartHistorica.setText("---");

        lblEscanosTotales.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblEscanosTotales.setText("---");

        lblEscTotales.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblEscTotales.setText("ESC TOTALES:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnEntra, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(45, 45, 45)
                                                .addComponent(btnSale, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(45, 45, 45)
                                                .addComponent(btnReplegar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(136, 136, 136)
                                                .addComponent(btnPactos, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(42, 42, 42))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
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
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(78, 78, 78)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnDatosMunicipales, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnDatosAutonomicas, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(49, 49, 49)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnSondeoAutonomicas, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnSondeoMunicipales, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(31, 31, 31)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(lblEscrutado, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lblEscTotales)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(lblEscanosTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(lblParticipacion, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(12, 12, 12)
                                                                                .addComponent(lblPartHistorica, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                .addGap(63, 63, 63)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btnAvance2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnAvance3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnAvance1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(62, 62, 62)))
                                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                .addGap(32, 32, 32)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(btnDatosMunicipales, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(btnSondeoMunicipales, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel1)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(btnSondeoAutonomicas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(btnDatosAutonomicas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel2)
                                                                        .addComponent(lblEscrutado))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel3)
                                                                        .addComponent(lblParticipacion))))
                                                .addGap(7, 7, 7)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblPartHistorica)
                                                        .addComponent(jLabel4))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btnAvance1)
                                                        .addComponent(jCheckBox1))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblEscTotales)
                                                        .addComponent(lblEscanosTotales))
                                                .addGap(10, 10, 10)
                                                .addComponent(btnAvance2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnAvance3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)))
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
                                        .addComponent(btnEntra, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSale, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnPactos, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnReplegar)
                                        .addComponent(btnReset))
                                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadSelectedAutonomicas(String cod) {
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // establece todas las celdas no editables
            }
        };
        tableModel.addColumn("CIRCUNSCRIPCIONES");
        var municipios = circunscripcionesAutonomicas.get(cod);
        for (Circunscripcion municipio : municipios) {
            if (!municipio.getCodigo().endsWith("00000"))
                tableModel.addRow(new Object[]{municipio.getNombreCircunscripcion()});
        }
        JScrollPane scrollPane = new JScrollPane(tablaMunicipios);
        tablaMunicipios = new JTable(tableModel);
        tablaComunidades.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(tablaMunicipios);

        tablaMunicipios.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tablaMunicipios.getSelectedRow();
            if (selectedRow != -1) {
                String nombreMunicipio = (String) tablaMunicipios.getValueAt(selectedRow, 0);
                String codMunicipio = nombreCodigoMunicipal.get(tablaMunicipios.getValueAt(selectedRow, 0));
                CarmenDTO carmen = null;
                if (tipoElecciones == 2 || tipoElecciones == 4) {
                    if (oficiales) {
                        try {
                            carmen = clienteApi.getCarmenDtoOficialAuto(codMunicipio).execute().body();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        graficosController.selectedAutonomicasOficiales(codMunicipio);
                    } else {
                        try {
                            carmen = clienteApi.getCarmenDtoSondeoAuto(codMunicipio).execute().body();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        graficosController.selectedAutonomicasSondeo(codMunicipio);
                    }
                } else {
                    if (oficiales) {
                        try {
                            carmen = clienteApi.getCarmenDtoOficialMuni(codMunicipio).execute().body();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        graficosController.selectedMunicipalesOficiales(codMunicipio);
                    } else {
                        try {
                            carmen = clienteApi.getCarmenDtoSondeoMuni(codMunicipio).execute().body();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        graficosController.selectedMunicipalesSondeo(codMunicipio);
                    }
                }
                lblEscrutado.setText(carmen.getCircunscripcion().getEscrutado() + "");
                lblParticipacion.setText(carmen.getCircunscripcion().getParticipacion() + "");
                lblPartHistorica.setText(carmen.getCircunscripcion().getParticipacionHist() + "");
                switch (selectedDb) {
                    case "DA" -> showDataTableOficialAutonomicas(nombreMunicipio);
                    case "SA" -> showDataTableSondeoAutonomicas(nombreMunicipio);
                    case "SM" -> showDataTableSondeoMunicipio(nombreMunicipio);
                    default -> showDataTableOficialMunicipio(nombreMunicipio);
                }
            }
        });
    }


    private void loadSelectedMunicipales(String cod) {
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // establece todas las celdas no editables
            }
        };
        tableModel.addColumn("CIRCUNSCRIPCIONES");
        var municipios = cicunscripcionesMunicipales.get(cod.replaceAll(" ", ""));
        municipios.sort(Comparator.comparing(Circunscripcion::getCodigo));
        for (Circunscripcion municipio : municipios) {
            if (!municipio.getCodigo().endsWith("00000"))
                tableModel.addRow(new Object[]{municipio.getNombreCircunscripcion()});
        }

        JScrollPane scrollPane = new JScrollPane(tablaMunicipios);
        tablaMunicipios = new JTable(tableModel);
        jScrollPane4.setViewportView(tablaMunicipios);
        tablaMunicipios.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tablaMunicipios.getSelectedRow();
            if (selectedRow != -1) {
                String nombreMunicipio = (String) tablaMunicipios.getValueAt(selectedRow, 0);
                String codMunicipio = nombreCodigoMunicipal.get(tablaMunicipios.getValueAt(selectedRow, 0));
                CarmenDTO carmen = null;
                if (tipoElecciones == 2 || tipoElecciones == 4) {
                    if (oficiales) {
                        try {
                            carmen = clienteApi.getCarmenDtoOficialAuto(codMunicipio).execute().body();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        graficosController.selectedAutonomicasOficiales(codMunicipio);
                    } else {
                        try {
                            carmen = clienteApi.getCarmenDtoSondeoAuto(codMunicipio).execute().body();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        graficosController.selectedAutonomicasSondeo(codMunicipio);
                    }
                } else {
                    if (oficiales) {
                        try {
                            carmen = clienteApi.getCarmenDtoOficialMuni(codMunicipio).execute().body();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        graficosController.selectedMunicipalesOficiales(codMunicipio);
                    } else {
                        try {
                            carmen = clienteApi.getCarmenDtoSondeoMuni(codMunicipio).execute().body();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        graficosController.selectedMunicipalesSondeo(codMunicipio);
                    }
                }
                lblEscrutado.setText(carmen.getCircunscripcion().getEscrutado() + "");
                lblParticipacion.setText(carmen.getCircunscripcion().getParticipacion() + "");
                lblPartHistorica.setText(carmen.getCircunscripcion().getParticipacionHist() + "");
                switch (selectedDb) {
                    case "DA" -> showDataTableOficialAutonomicas(nombreMunicipio);
                    case "SA" -> showDataTableSondeoAutonomicas(nombreMunicipio);
                    case "SM" -> showDataTableSondeoMunicipio(nombreMunicipio);
                    default -> showDataTableOficialMunicipio(nombreMunicipio);
                }
            }
        });
    }

    private void btnEntraActionPerformed(java.awt.event.ActionEvent evt) {
        switch (tipoElecciones) {
            //OFICIALES MUNICIPALES
            case 1 -> {
                switch (TablaCartones.getSelectedRow()) {
                    //RESULTADOS
                    case 0 -> graficosController.loadMunicipales();
                    //PARTICIPACION
                    case 1 -> {
                        if (!participacionIn) {
                            graficosController.entraParticipacionMuni();
                            //TODO:Poner lateralIN =false en el sale o al pasar a otro gráfico compatible
                            participacionIn = true;
                        } else {
                            graficosController.cambiaParticipacionMuni();
                        }
                    }
                    //ARCO INDIVIDUAL
                    case 2 -> graficosController.loadMunicipales();
                    //ARCO COMPARADO
                    case 3 -> graficosController.loadMunicipales();
                    default -> System.out.print("");
                }
                switch (TablaFaldones.getSelectedRow()) {
                    //INFERIOR
                    //TODO: ENCADENA
                    case 0 -> {
                        graficosController.entraFaldonAuto();
                        inferiorMuniIn = true;
                    }
                    //LATERAL
                    case 1 -> {
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
                    //SEDES
                    case 2 -> System.out.println("SEDES MUNI OFI");
                    //VOTOS MILLONES
                    case 3 -> System.out.println("SEDES MUNI OFI");
                    default -> System.out.print("");
                }

            }
            //OFICIALES AUTONOMICAS
            case 2 -> {
                switch (TablaCartones.getSelectedRow()) {
                    //RESULTADOS
                    case 0 -> graficosController.loadAutonomicas();
                    //PARTICIPACION
                    case 1 -> {
                        if (!participacionIn) {
                            graficosController.entraParticipacionAuto();
                            //TODO:Poner lateralIN =false en el sale o al pasar a otro gráfico compatible
                            participacionIn = true;
                        } else {
                            graficosController.cambiaParticipacionAuto();
                        }
                    }
                    //ARCO INDIVIDUAL
                    case 2 -> graficosController.loadAutonomicas();
                    //ARCO COMPARADO
                    case 3 -> graficosController.loadAutonomicas();
                    default -> System.out.print("");
                }
                switch (TablaFaldones.getSelectedRow()) {
                    //INFERIOR
                    //TODO: ENCADENA
                    case 0 -> {
                        graficosController.entraFaldonAuto();
                        inferiorAutoIn = true;
                    }
                    //LATERAL
                    case 1 -> {
                        String codCCAA = null;
                        if (tablaComunidades.getSelectedRow() != -1) {
                            codCCAA = nombreCodigoMunicipal.get(tablaComunidades.getValueAt(tablaComunidades.getSelectedRow(), 0)).substring(0, 2);
                            graficosController.actualizaLateralAutonomicas(codCCAA);
                        }
                        if (!lateralIn) {
                            graficosController.entraLateralAutonomicas();
                            //TODO:Poner lateralIN =false en el sale o al pasar a otro gráfico compatible
                            lateralIn = true;
                        } else {
                            if (codCCAA != null) {
                                graficosController.despliegaLateralAutonomicas(codCCAA);
                            }
                        }
                    }
                    //SEDES
                    case 2 -> System.out.println("SEDES AUTO OFI");
                    //VOTOS MILLONES
                    case 3 -> System.out.println("SEDES AUTO OFI");
                    default -> System.out.print("");
                }

            }
            //SONDEO MUNICIPALES
            case 3 -> {
                switch (TablaCartones.getSelectedRow()) {
                    //RESULTADOS
                    case 0 -> graficosController.loadMunicipales();
                    //PARTICIPACION
                    case 1 -> {
                        if (!participacionIn) {
                            graficosController.entraParticipacionMuni();
                            //TODO:Poner lateralIN =false en el sale o al pasar a otro gráfico compatible
                            participacionIn = true;
                        } else {
                            graficosController.cambiaParticipacionMuni();
                        }
                    }
                    //ARCO INDIVIDUAL
                    case 2 -> graficosController.loadMunicipales();
                    //ARCO COMPARADO
                    case 3 -> graficosController.loadMunicipales();
                    default -> System.out.print("");
                }
                switch (TablaFaldones.getSelectedRow()) {
                    //INFERIOR
                    //TODO: ENCADENA
                    case 0 -> {
                        graficosController.entraFaldonAuto();
                        inferiorMuniIn = true;
                    }
                    //LATERAL
                    case 1 -> {
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
                    //SEDES
                    case 2 -> System.out.println("SEDES MUNI OFI");
                    //VOTOS MILLONES
                    case 3 -> System.out.println("SEDES MUNI OFI");
                    default -> System.out.print("");
                }

            }
            //SONDEO AUTONOMICAS
            case 4 -> {
                switch (TablaCartones.getSelectedRow()) {
                    //RESULTADOS
                    case 0 -> graficosController.loadAutonomicas();
                    //PARTICIPACION
                    case 1 -> {
                        if (!participacionIn) {
                            graficosController.entraParticipacionAuto();
                            //TODO:Poner lateralIN =false en el sale o al pasar a otro gráfico compatible
                            participacionIn = true;
                        } else {
                            graficosController.cambiaParticipacionAuto();
                        }
                    }
                    //ARCO INDIVIDUAL
                    case 2 -> graficosController.loadAutonomicas();
                    //ARCO COMPARADO
                    case 3 -> graficosController.loadAutonomicas();
                    default -> System.out.print("");
                }
                switch (TablaFaldones.getSelectedRow()) {
                    //INFERIOR
                    //TODO: ENCADENA
                    case 0 -> {
                        graficosController.entraFaldonAuto();
                        inferiorAutoIn = true;
                    }
                    //LATERAL
                    case 1 -> {
                        String codCCAA = null;
                        if (tablaComunidades.getSelectedRow() != -1) {
                            codCCAA = nombreCodigoMunicipal.get(tablaComunidades.getValueAt(tablaComunidades.getSelectedRow(), 0)).substring(0, 2);
                            graficosController.actualizaLateralAutonomicas(codCCAA);
                        }
                        if (!lateralIn) {
                            graficosController.entraLateralAutonomicas();
                            //TODO:Poner lateralIN =false en el sale o al pasar a otro gráfico compatible
                            lateralIn = true;
                        } else {
                            if (codCCAA != null) {
                                graficosController.despliegaLateralAutonomicas(codCCAA);
                            }
                        }
                    }
                    //SEDES
                    case 2 -> System.out.println("SEDES AUTO OFI");
                    //VOTOS MILLONES
                    case 3 -> System.out.println("SEDES AUTO OFI");
                    default -> System.out.print("");
                }
            }
        }
    }

    private void btnPactosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPactosActionPerformed
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
        pactos.setLocation(screenWidth / 4, screenHeight / 2);
        pactos.setVisible(true);

    }//GEN-LAST:event_btnPactosActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) throws IOException {//GEN-FIRST:event_jLabel1MouseClicked


        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();

        // Verificar si el archivo existe
        File file = new File("C:\\ELECCIONES2023\\config.ini");
        if (!file.exists()) {
            // Si no existe, crear el archivo y escribir los valores iniciales
            System.out.println("no existe");
            JOptionPane.showMessageDialog(null, "El archivo se ha creado, vuelva a pulsar el botón", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            file.createNewFile();
            try {
                IniFileReaderWriter ini = new IniFileReaderWriter("C:\\ELECCIONES2023\\config.ini");
                ini.setValue("ip1", "ip1", "127.0.0.1");
                ini.setValue("puerto", "puerto", "5123");
                ini.setValue("bd1", "bd1", "<FALDONES>");
                ini.setValue("bd2", "bd2", "<CARTONES>");
                ini.setValue("ipServPrincipal", "ipServPrincipal", "127.0.0.1");
                ini.setValue("ipServReserva", "ipServReserva", "127.0.0.1");
                ini.store(file);
            } catch (IOException e) {
                System.err.println("Error al crear el archivo config.ini: " + e.getMessage());
                System.exit(1);
            }
        } else {
            System.out.println("existe");
            JFrame config;
            try {
                config = new config();

                config.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                config.setLocation(screenWidth / 4, screenHeight / 4);
                config.setVisible(true);

            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnReplegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReplegarActionPerformed
        String codCCAA = null;
        if (tablaComunidades.getSelectedRow() != -1) {
            codCCAA = nombreCodigoMunicipal.get(tablaComunidades.getValueAt(tablaComunidades.getSelectedRow(), 0)).substring(0, 2);
        }
        if (tipoElecciones == 2 || tipoElecciones == 4) {
            graficosController.repliegaLateralAutonomicas(codCCAA);
        }
        if (tipoElecciones == 1 || tipoElecciones == 3) {
            graficosController.repliegaLateralMunicipales(codCCAA);
        }
    }//GEN-LAST:event_btnReplegarActionPerformed

    private void btnSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaleActionPerformed
        switch (tipoElecciones) {
            //OFICIALES MUNICIPALES
            case 1 -> {
                switch (TablaCartones.getSelectedRow()) {
                    //RESULTADOS
                    case 0 -> System.out.println("Sale Resultados");
                    //PARTICIPACION
                    case 1 -> System.out.println("Sale Participación");
                    //ARCO INDIVIDUAL
                    case 2 -> System.out.println("Sale Arco principal");
                    //ARCO COMPARADO
                    case 3 -> System.out.println("Sale Arco comparativo");
                    default -> System.out.print("");
                }
                switch (TablaFaldones.getSelectedRow()) {
                    //INFERIOR
                    case 0 -> {
                        if (inferiorAutoIn || inferiorMuniIn) {
                            graficosController.saleFaldonAuto();
                            inferiorAutoIn = false;
                            inferiorMuniIn = false;
                        }
                    }
                    //LATERAL
                    case 1 -> {
                        if (lateralIn) {
                            graficosController.saleLateralMunicipales();
                            //TODO:Poner lateralIN =false en el sale o al pasar a otro gráfico compatible
                            lateralIn = false;
                        }
                    }
                    //VOTANTES
                    case 2 -> System.out.println("Sale Arco principal");
                    //SEDES
                    case 3 -> System.out.println("Sale Arco comparativo");
                    default -> System.out.print("");
                }
            }
            //OFICIALES AUTONOMICAS
            case 2 -> {
                switch (TablaCartones.getSelectedRow()) {
                    //RESULTADOS
                    case 0 -> System.out.println("Sale Resultados");
                    //PARTICIPACION
                    case 1 -> System.out.println("Sale Participación");
                    //ARCO INDIVIDUAL
                    case 2 -> System.out.println("Sale Arco principal");
                    //ARCO COMPARADO
                    case 3 -> System.out.println("Sale Arco comparativo");
                    default -> System.out.print("");
                }
                switch (TablaFaldones.getSelectedRow()) {
                    //INFERIOR
                    case 0 -> {
                        if (inferiorAutoIn || inferiorMuniIn) {
                            graficosController.saleFaldonAuto();
                            inferiorAutoIn = false;
                            inferiorMuniIn = false;
                        }
                    }
                    //LATERAL
                    case 1 -> {
                        if (lateralIn) {
                            graficosController.saleLateralAutonomicas();
                            //TODO:Poner lateralIN =false en el sale o al pasar a otro gráfico compatible
                            lateralIn = false;
                        }
                    }
                    //VOTANTES
                    case 2 -> System.out.println("Sale Arco principal");
                    //SEDES
                    case 3 -> System.out.println("Sale Arco comparativo");
                    default -> System.out.print("");
                }
            }
            //SONDEO MUNICIPALES
            case 3 -> {
                switch (TablaCartones.getSelectedRow()) {
                    //RESULTADOS
                    case 0 -> System.out.println("Sale Resultados");
                    //PARTICIPACION
                    case 1 -> System.out.println("Sale Participación");
                    //ARCO INDIVIDUAL
                    case 2 -> System.out.println("Sale Arco principal");
                    //ARCO COMPARADO
                    case 3 -> System.out.println("Sale Arco comparativo");
                    default -> System.out.print("");
                }
                switch (TablaFaldones.getSelectedRow()) {
                    //INFERIOR
                    case 0 -> {
                        if (inferiorAutoIn || inferiorMuniIn) {
                            graficosController.saleFaldonAuto();
                            inferiorAutoIn = false;
                            inferiorMuniIn = false;
                        }
                    }
                    //LATERAL
                    case 1 -> {
                        if (lateralIn) {
                            graficosController.saleLateralMunicipales();
                            //TODO:Poner lateralIN =false en el sale o al pasar a otro gráfico compatible
                            lateralIn = false;
                        }
                    }
                    //VOTANTES
                    case 2 -> System.out.println("Sale Arco principal");
                    //SEDES
                    case 3 -> System.out.println("Sale Arco comparativo");
                    default -> System.out.print("");
                }
            }
            //SONDEO AUTONOMICAS
            case 4 -> {
                switch (TablaCartones.getSelectedRow()) {
                    //RESULTADOS
                    case 0 -> System.out.println("Sale Resultados");
                    //PARTICIPACION
                    case 1 -> System.out.println("Sale Participación");
                    //ARCO INDIVIDUAL
                    case 2 -> System.out.println("Sale Arco principal");
                    //ARCO COMPARADO
                    case 3 -> System.out.println("Sale Arco comparativo");
                    default -> System.out.print("");
                }
                switch (TablaFaldones.getSelectedRow()) {
                    //INFERIOR
                    case 0 -> {
                        if (inferiorAutoIn || inferiorMuniIn) {
                            graficosController.saleFaldonAuto();
                            inferiorAutoIn = false;
                            inferiorMuniIn = false;
                        }
                    }
                    //LATERAL
                    case 1 -> {
                        if (lateralIn) {
                            graficosController.saleLateralAutonomicas();
                            //TODO:Poner lateralIN =false en el sale o al pasar a otro gráfico compatible
                            lateralIn = false;
                        }
                    }
                    //VOTANTES
                    case 2 -> System.out.println("Sale Arco principal");
                    //SEDES
                    case 3 -> System.out.println("Sale Arco comparativo");
                    default -> System.out.print("");
                }
            }
        }
    }//GEN-LAST:event_btnSaleActionPerformed

    private void btnSondeoAutonomicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSondeoAutonomicasActionPerformed

        vaciarTablas();

        tipoElecciones = 4;
        oficiales = false;
        resaltarBoton(btnSondeoAutonomicas);
        selectedDb = "SA";
    }//GEN-LAST:event_btnSondeoAutonomicasActionPerformed

    private void btnDatosAutonomicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatosAutonomicasActionPerformed
        vaciarTablas();
        tipoElecciones = 2;
        oficiales = true;
        resaltarBoton(btnDatosAutonomicas);
        selectedDb = "DA";
    }//GEN-LAST:event_btnDatosAutonomicasActionPerformed

    private void btnSondeoMunicipalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSondeoMunicipalesActionPerformed
        vaciarTablas();
        tipoElecciones = 3;
        oficiales = false;
        resaltarBoton(btnSondeoMunicipales);
        selectedDb = "SM";
    }//GEN-LAST:event_btnSondeoMunicipalesActionPerformed

    private void btnDatosMunicipalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatosMunicipalesActionPerformed
        vaciarTablas();
        tipoElecciones = 1;
        oficiales = true;
        resaltarBoton(btnDatosMunicipales);
        selectedDb = "DM";
    }//GEN-LAST:event_btnDatosMunicipalesActionPerformed


    private void vaciarTablas() {
        ((javax.swing.table.DefaultTableModel) tablaMunicipios.getModel()).setRowCount(0);
        ((javax.swing.table.DefaultTableModel) tablaDatos.getModel()).setRowCount(0);
    }


    private void TablaCartonesHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_TablaCartonesHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TablaCartonesHierarchyChanged

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

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetActionPerformed


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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
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
    private javax.swing.JTable TablaCartones;
    private javax.swing.JTable TablaFaldones;
    private javax.swing.JButton btnAvance1;
    private javax.swing.JButton btnAvance2;
    private javax.swing.JButton btnAvance3;
    private javax.swing.JButton btnDatosAutonomicas;
    private javax.swing.JButton btnDatosMunicipales;
    private javax.swing.JButton btnEntra;
    private javax.swing.JButton btnPactos;
    private javax.swing.JButton btnReplegar;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSale;
    private javax.swing.JButton btnSondeoAutonomicas;
    private javax.swing.JButton btnSondeoMunicipales;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblEscTotales;
    private javax.swing.JLabel lblEscanosTotales;
    private javax.swing.JLabel lblEscrutado;
    private javax.swing.JLabel lblPartHistorica;
    private javax.swing.JLabel lblParticipacion;
    private javax.swing.JTable tablaComunidades;
    private javax.swing.JTable tablaDatos;
    private javax.swing.JTable tablaMunicipios;
    // End of variables declaration//GEN-END:variables
}
