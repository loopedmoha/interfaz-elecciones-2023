/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.elecciones2023datos;

import com.mycompany.elecciones2023datos.DTO.CarmenDTO;
import com.mycompany.elecciones2023datos.DTO.CircunscripcionDTO;
import com.mycompany.elecciones2023datos.controllers.GraficosController;
import com.mycompany.elecciones2023datos.model.Circunscripcion;
import com.mycompany.elecciones2023datos.model.CircunscripcionPartido;
import com.mycompany.elecciones2023datos.model.CpData;
import com.mycompany.elecciones2023datos.services.IClienteApi;
//import com.mycompany.elecciones2023datos.services.IClienteApiGestion;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 * @author Fede
 */
public class Main extends javax.swing.JFrame {

    Retrofit retrofit;
    Retrofit retrofitGestion;
    IClienteApi clienteApi;
    //IClienteApiGestion clienteApiGestion;


    TreeMap<String, List<Circunscripcion>> circunscripcionesAutonomicas = new TreeMap<>();
    TreeMap<String, List<Circunscripcion>> circunscripcionesMunicipales = new TreeMap<>();
    TreeMap<String, String> nombreCodigo = new TreeMap<>();
    TreeMap<String, String> nombreCodigoAuto = new TreeMap<>();
    TreeMap<String, String> nombreCodigoMunicipal = new TreeMap<>();

    TreeMap<String, String> nombreCodigoAutonomicas = new TreeMap<>();

    String selectedDb = "";

    GraficosController graficosController = new GraficosController();

    private int tipoElecciones = 2;
    boolean isEspana = false;
    private boolean oficiales = true;
    private boolean lateralIn = false;
    private boolean inferiorAutoIn = false;
    private boolean inferiorAutoSondeoIn = false;
    private boolean inferiorMuniIn = false;
    private boolean inferiorMuniSondeoIn = false;
    private boolean participacionIn = false;
    private boolean participacionEspIn = false;
    private boolean resultadosIn = false;
    private boolean votantesIn = false;
    private boolean isComunidad = false;
    private boolean isMunicipio = false;
    private boolean arcoIn = false;

    private String avance = "1";

    private static final String CONFIG_FILE_PATH = "C:\\Elecciones2023\\config.properties";


    /**
     * Creates new form Main
     */
    private JButton botonSeleccionado = null;
    private JButton botonSeleccionado2 = null;

    public void initCircunscripcionesAutonomicas() {
        try {
            List<Circunscripcion> autonomiasMuni;

            autonomiasMuni = clienteApi.getAllAutonomiasMuni().execute().body();
            autonomiasMuni.stream().filter(x -> x.getCodigo().endsWith("00000"))
                    .map(Circunscripcion::getNombreCircunscripcion).forEach(auto -> circunscripcionesAutonomicas.put(auto, null));

            List<Circunscripcion> autonomiasAuto;
            autonomiasAuto = clienteApi.getAllAutonomiasAuto().execute().body();
            autonomiasAuto.stream().map(Circunscripcion::getNombreCircunscripcion).forEach(auto -> circunscripcionesAutonomicas.put(auto, null));

            for (Circunscripcion autonomia : autonomiasMuni) {
                var auxList = clienteApi.getCircunscripcionesByAutonomia(autonomia.getCodigo()).execute().body();
                auxList.sort(Comparator.comparing(Circunscripcion::getCodigo));
                nombreCodigo.put(autonomia.getNombreCircunscripcion().replaceAll(" ", ""), autonomia.getCodigo());
                auxList.forEach(x -> {
                    nombreCodigoMunicipal.put(x.getNombreCircunscripcion(), x.getCodigo());
                });
                circunscripcionesAutonomicas.put(autonomia.getNombreCircunscripcion(), auxList);
            }
            for (Circunscripcion autonomia : autonomiasAuto) {
                if (autonomia != null) {
                    var auxList = clienteApi.getCircunscripcionesByAutonomia(autonomia.getCodigo()).execute().body();
                    auxList.sort(Comparator.comparing(Circunscripcion::getCodigo));
                    nombreCodigoAuto.put(autonomia.getNombreCircunscripcion(), autonomia.getCodigo());
                    auxList.forEach(x -> {
                        nombreCodigoAutonomicas.put(x.getNombreCircunscripcion(), x.getCodigo());
                    });
                    circunscripcionesAutonomicas.put(autonomia.getNombreCircunscripcion(), auxList);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void initCircunscripcionesMunicipales() {
        try {
            var autonomias = clienteApi.getAllAutonomiasMuni().execute().body();
            autonomias.stream()
                    .map(Circunscripcion::getNombreCircunscripcion).forEach(auto -> circunscripcionesMunicipales.put(auto.replaceAll(" ", ""), null));
            for (Circunscripcion autonomia : autonomias) {
                var auxList = clienteApi.getMunicipiosByCodigo(autonomia.getCodigo()).execute().body();
                auxList.sort(Comparator.comparing(Circunscripcion::getCodigo));
                for (Circunscripcion circunscripcion : auxList) {
                    nombreCodigoMunicipal.put(circunscripcion.getNombreCircunscripcion(), circunscripcion.getCodigo());
                }
                circunscripcionesMunicipales.put(autonomia.getNombreCircunscripcion().replaceAll(" ", ""), auxList);
            }


        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showDataTable(CarmenDTO carmen) {
        //Solo hay seleccioanda una autonomia
        //CarmenDTO carmenDTO = null;
        //    switch (selectedDb) {
        //        case "DA" -> carmenDTO = clienteApi.getCarmenDtoOficialAuto(nombreCodigo.get(nombre)).execute().body();
        //        case "SA" -> carmenDTO = clienteApi.getCarmenDtoSondeoAuto(nombreCodigo.get(nombre)).execute().body();
        //        case "SM" -> carmenDTO = clienteApi.getCarmenDtoSondeoMuni(nombreCodigo.get(nombre)).execute().body();
        //        default -> carmenDTO = clienteApi.getCarmenDtoOficialMuni(nombreCodigo.get(nombre)).execute().body();
        //    }
        //    // CarmenDTO carmenDTO = clienteApi.getCarmenDto(nombreCodigo.get(nombre)).execute().body();
        List<CpData> datos = CpData.fromCarmenDto(carmen);
        printData(datos);
    }

    public void showDataTableOficialMunicipio(CarmenDTO carmen) {
        List<CpData> datos = CpData.fromCarmenDto(carmen);

        printData(datos);
    }

    public void showDataTableSondeoMunicipio(CarmenDTO carmen) {
        List<CpData> datos = CpData.fromCarmenDto(carmen);
        printData(datos);
    }

    public void showDataTableOficialAutonomicas(CarmenDTO carmen) {
        List<CpData> datos = CpData.fromCarmenDto(carmen);
        printData(datos);
    }

    public void showDataTableSondeoAutonomicas(CarmenDTO carmen) {
        List<CpData> datos = CpData.fromCarmenDto(carmen);
        printData(datos);

    }

    public void printDataEsp() throws IOException {
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // establece todas las celdas no editables
            }
        };

        CarmenDTO esp = null;

        tableModel.addColumn("ESCRUTADO");
        tableModel.addColumn("PARTICIPACION");
        tableModel.addColumn("PARTICIPACION H");


        tablaDatos.setModel(tableModel);
        switch (tipoElecciones) {
            //oficiales municipales
            case 1 -> {
                esp = clienteApi.getCarmenDtoOficialMuni("9900000", avance).execute().body();
                //System.out.println(esp);

            }
            //oficiales autonomicas
            case 2 -> {
                esp = clienteApi.getCarmenDtoOficialAuto("9900000", avance).execute().body();
                // System.out.println(esp);

            }
            //sondeo municipales
            case 3 -> {
                esp = clienteApi.getCarmenDtoSondeoMuni("9900000", avance).execute().body();
                //System.out.println(esp);

            }
            //sondeo autonomicas
            case 4 -> {
                esp = clienteApi.getCarmenDtoSondeoAuto("9900000", avance).execute().body();
                //System.out.println(esp);

            }
        }
        CircunscripcionDTO espCirc = esp.getCircunscripcion();
        Object[] rowData = {espCirc.getEscrutado(), espCirc.getParticipacion(), espCirc.getParticipacionHist(),
                // espCirc.getAvance1(), espCirc.getAvance2(), espCirc.getAvance3(),
                // espCirc.getAvance1Hist(), espCirc.getAvance2Hist(), espCirc.getAvance3Hist()
        };
        switch (selectedDb) {
            case "SM" -> graficosController.selectCircunscripcionMapaSondeoMuni("9900000", avance);
            case "SA" -> graficosController.selectCircunscripcionMapaSondeoAuto("9900000", avance);
            case "DA" -> graficosController.selectCircunscripcionMapaOficialAuto("9900000", avance);
            default -> graficosController.selectCircunscripcionMapaOficialMuni("9900000", avance);

        }
        tableModel.addRow(rowData);
        tablaDatos.setModel(tableModel);
        tablaDatos.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tablaDatos);

    }

    public void printDataVotantes() {
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // establece todas las celdas no editables
            }
        };

        tableModel.addColumn("CODIGO");
        tableModel.addColumn("PARTIDO");
        tableModel.addColumn("VOTANTES");


        tablaDatos.setModel(tableModel);
        CarmenDTO votantesDTO = graficosController.getCarmenDtoOficialMuni("9900000", avance);
        var list = votantesDTO.getCpDTO();
        list.forEach(cpDTO -> {
            Object[] rowData = {cpDTO.getCodigoPartido(), cpDTO.getSiglas(), cpDTO.getNumVotantes()};
            tableModel.addRow(rowData);
        });

        tablaDatos.setModel(tableModel);
        tablaDatos.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tablaDatos);
    }

    public void printData(List<CpData> list) {
        isEspana = (TablaCartones.getSelectedRow() == 3);
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // establece todas las celdas no editables
            }
        };
        CpData otros = null;
        if (!isEspana) {
            tableModel.addColumn("COD");
            tableModel.addColumn("SIGLAS");
            tableModel.addColumn("E.D");
            tableModel.addColumn("E.H");
            tableModel.addColumn("HIST");
            tableModel.addColumn("% VOTO");
            tableModel.addColumn("VOTANTES");
            if (oficiales) {
                for (CpData cpDTO : list) {
                    Object[] rowData = {cpDTO.getCodigo(), cpDTO.getSiglas(),
                            cpDTO.getEscanosDesde(), cpDTO.getEscanosHasta(), cpDTO.getEscanosHist(),
                            cpDTO.getPorcentajeVoto(), cpDTO.getVotantes()};
                    tableModel.addRow(rowData);
                }

            } else {
                for (CpData cpDTO : list) {
                    Object[] rowData = {cpDTO.getCodigo(), cpDTO.getSiglas(),
                            cpDTO.getEscanos_desde_sondeo(), cpDTO.getEscanos_hasta_sondeo(), cpDTO.getEscanosHist(), cpDTO.getPorcentajeVotoSondeo(), cpDTO.getVotantes()};
                    tableModel.addRow(rowData);
                }
            }
        } else {
            tableModel.addColumn("ESCRUTADO");
            tableModel.addColumn("PARTICIPACION");
            tableModel.addColumn("PARTICIPACION H");

            if (oficiales) {
                //System.out.println(list);
                for (CpData cpDTO : list) {
                    Object[] rowData = {cpDTO.getCodigo(), cpDTO.getSiglas(),
                            cpDTO.getEscanosDesde(), cpDTO.getEscanosHasta(),
                            cpDTO.getEscanosHist(), cpDTO.getPorcentajeVoto(), cpDTO.getVotantes()};
                    tableModel.addRow(rowData);
                }

            } else {
                //System.out.println(list);

                for (CpData cpDTO : list) {
                    Object[] rowData = {cpDTO.getCodigo(), cpDTO.getSiglas(),
                            cpDTO.getEscanos_desde_sondeo(), cpDTO.getEscanos_hasta_sondeo(),
                            cpDTO.getEscanosHist(), cpDTO.getPorcentajeVotoSondeo(), cpDTO.getVotantes()};
                    tableModel.addRow(rowData);
                }
            }
        }
        JScrollPane scrollPane = new JScrollPane(tablaDatos);
        tablaDatos.setModel(tableModel);
        tablaDatos.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tablaDatos);

    }


    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        retrofit = new Retrofit.Builder().baseUrl("http://localhost:9090").addConverterFactory(GsonConverterFactory.create()).build();
        clienteApi = retrofit.create(IClienteApi.class);
        // retrofitGestion = new Retrofit.Builder().baseUrl("http://172.28.51.28:8080").addConverterFactory(GsonConverterFactory.create()).build();

        // clienteApiGestion = retrofitGestion.create(IClienteApiGestion.class);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    System.out.println("Cerrando");
                    clienteApi.closeClient().execute().wait();
                } catch (IOException | InterruptedException ex) {
                    System.out.println("Cliente cerrado");
                }
            }
        });
        graficosController.initListeners();
        initCircunscripcionesAutonomicas();
        initCircunscripcionesMunicipales();
        initComponents();
        resaltarBotonAvances(btnAvance1);
        jCheckBox1.setVisible(false);
        lblConexion.setText(graficosController.getDbActual().getResultado());
        resaltarBoton(btnDatosAutonomicas);
        TablaCartones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TablaFaldones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaMunicipios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaComunidades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        ListSelectionModel selectionModel = TablaCartones.getSelectionModel();


        TablaCartones.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    tablaMunicipios.clearSelection();
                    tablaComunidades.clearSelection();
                    tablaDatos.clearSelection();
                    TablaFaldones.clearSelection();
                }
            }
        });
        TablaFaldones.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    tablaComunidades.clearSelection();
                    tablaMunicipios.clearSelection();
                    tablaDatos.clearSelection();
                    TablaCartones.clearSelection();
                }
            }
        });

//        selectionModel.addListSelectionListener(new ListSelectionListener() {
//            public void valueChanged(ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting()) {
//                    tablaComunidades.clearSelection();
//                    tablaMunicipios.clearSelection();
//                    TablaFaldones.clearSelection();
//                }
//            }
//        });
//
//        ListSelectionModel selectionModel2 = TablaFaldones.getSelectionModel();
//        selectionModel2.addListSelectionListener(new ListSelectionListener() {
//            public void valueChanged(ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting()) {
//                    tablaComunidades.clearSelection();
//                    tablaMunicipios.clearSelection();
//                    TablaCartones.clearSelection();
//                }
//            }
//        });

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("Imagenes/iconconfig.png");
        ImageIcon icon = null;
        try {
            icon = new ImageIcon(ImageIO.read(inputStream));
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        btnReplegar = new javax.swing.JButton();
        btnSondeoAutonomicas = new javax.swing.JButton();
        btnSondeoMunicipales = new javax.swing.JButton();
        btnDatosAutonomicas = new javax.swing.JButton();
        btnDatosMunicipales = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaFaldones = new javax.swing.JTable();

        jCheckBox1 = new javax.swing.JCheckBox();
        ButtonGroup buttonGroup = new ButtonGroup();
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
        btnConfig = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        lblConexion = new javax.swing.JLabel();
        cbRegional = new javax.swing.JCheckBox();
//buttonGroup.add(jCheckBox1);
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
        tablaComunidades.setModel(tableModel);
        rellenarCCAA(2);
        JScrollPane scrollPane = new JScrollPane(tablaComunidades);
        tablaComunidades.getTableHeader().setResizingAllowed(false);
        jScrollPane2.setViewportView(tablaComunidades);
        if (tablaComunidades.getColumnModel().getColumnCount() > 0) {
            tablaComunidades.getColumnModel().getColumn(0).setResizable(false);
        }

        tablaDatos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                if (tablaDatos.getSelectedRow() != -1) {
                    if (TablaFaldones.getSelectedRow() == 2) {
                        if (tablaDatos.getSelectedRow() != -1) {
                            String codigo = tablaDatos.getValueAt(tablaDatos.getSelectedRow(), 0).toString();
                            graficosController.descargarSedesCsv(codigo);
                        }
                    }
                }
            }
        });

        TablaFaldones.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                if (TablaFaldones.getSelectedRow() != -1) {
                    if (TablaFaldones.getSelectedRow() == 1) {
                        graficosController.update();
                    }
                    if (TablaFaldones.getSelectedRow() == 2) {
                        vaciarTablas();
                        List<CircunscripcionPartido> cps = graficosController.getCpsEspania();
                        List<CpData> cpdatas = new ArrayList<>();
                        cps.forEach(cp -> {
                            String siglas = graficosController.getPartido(cp.getKey().getPartido()).getSiglas();
                            CpData data = CpData.fromCP(cp, siglas);
                            cpdatas.add(data);
                        });
                        printData(cpdatas);
                    } else if (TablaFaldones.getSelectedRow() == 3) {
                        graficosController.updateEspania(avance);
                        vaciarTablas();
                        printDataVotantes();
                    } else if (TablaFaldones.getSelectedRow() != -1) {
                        rellenarCCAA(tipoElecciones);
                    }
                }
            }
        });

        tablaComunidades.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                if (tablaComunidades.getSelectedRow() != -1) {
                    String codAutonomia;
                    CarmenDTO carmen = null;
                    isComunidad = true;
                    isMunicipio = false;
                    int selectedRow = tablaComunidades.getSelectedRow();
                    if (selectedRow != -1) {
                        if (tipoElecciones == 2 || tipoElecciones == 4) {
                            loadSelectedAutonomicas((String) tablaComunidades.getValueAt(selectedRow, 0));
                            codAutonomia = nombreCodigo.get(((String) tablaComunidades.getValueAt(selectedRow, 0)).replaceAll(" ", ""));
                            if (codAutonomia.equals("1800000") || codAutonomia.equals("1900000")) {
                                if (oficiales) {
                                    try {
                                        if (TablaCartones.getSelectedRow() != 3) {
                                            carmen = clienteApi.getCarmenDtoOficialMuni(codAutonomia, avance).execute().body();
                                            if (TablaCartones.getSelectedRow() == 2) {
                                                graficosController.selectCircunscripcionMapaOficialMuni(codAutonomia, avance);
                                            }
                                            if (TablaFaldones.getSelectedRow() == 0 || TablaFaldones.getSelectedRow() == 3) {
                                                graficosController.selectCircunscripcionAutonomiaOficialMuni(codAutonomia, avance);
                                            }
                                            if (TablaCartones.getSelectedRow() == 0) {
                                                if (tablaComunidades.getSelectedRow() != -1) {
                                                    graficosController.descargarResultadosCsvMuniOficial(codAutonomia);
                                                    graficosController.selectCircunscripcionMapaOficialMuni(codAutonomia, avance);
                                                }
                                            }
                                        }
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                } else {
                                    try {
                                        if (TablaCartones.getSelectedRow() != 3) {
                                            carmen = clienteApi.getCarmenDtoSondeoMuni(codAutonomia, avance).execute().body();
                                            if (TablaCartones.getSelectedRow() == 1 || TablaCartones.getSelectedRow() == 2) {
                                                graficosController.selectCircunscripcionMapaSondeoMuni(codAutonomia, avance);
                                            }
                                            if (TablaFaldones.getSelectedRow() == 0 || TablaFaldones.getSelectedRow() == 3) {
                                                graficosController.selectCircunscripcionAutnomiaSondeoMuni(codAutonomia, avance);
                                            }
                                            if (TablaCartones.getSelectedRow() == 0) {
                                                if (tablaComunidades.getSelectedRow() != -1) {
                                                    graficosController.descargarResultadosCsvMuniSondeo(codAutonomia);
                                                    graficosController.selectCircunscripcionMapaSondeoMuni(codAutonomia, avance);
                                                }
                                            }
                                        }
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                                showDataTable(carmen);
                            } else {
                                if (oficiales) {
                                    try {
                                        if (TablaCartones.getSelectedRow() != 3) {
                                            carmen = clienteApi.getCarmenDtoOficialAuto(codAutonomia, avance).execute().body();
                                            if (TablaCartones.getSelectedRow() == 1 || TablaCartones.getSelectedRow() == 2) {
                                                graficosController.selectCircunscripcionMapaOficialAuto(codAutonomia, avance);
                                            }
                                            if (TablaFaldones.getSelectedRow() == 0 || TablaFaldones.getSelectedRow() == 3) {
                                                graficosController.selectCircunscripcionAutonomiaOficialAuto(codAutonomia, avance);
                                            }
                                            if (TablaCartones.getSelectedRow() == 0) {
                                                if (tablaComunidades.getSelectedRow() != -1) {
                                                    String nombreCCAA = tablaComunidades.getValueAt(tablaComunidades.getSelectedRow(), 0).toString();
                                                    String codigo = nombreCodigoAuto.get(nombreCCAA);
                                                    graficosController.descargarResultadosCsvAutoOficial(codigo);
                                                    graficosController.selectCircunscripcionMapaOficialAuto(codigo, avance);
                                                }
                                            }
                                        } else {
                                            carmen = clienteApi.getCarmenDtoOficialAuto("9900000", avance).execute().body();
                                            graficosController.selectCircunscripcionMapaOficialAuto("9900000", avance);
                                        }
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                } else {
                                    try {
                                        if (TablaCartones.getSelectedRow() != 3) {
                                            carmen = clienteApi.getCarmenDtoSondeoAuto(codAutonomia, avance).execute().body();
                                            if (TablaCartones.getSelectedRow() == 2) {
                                                graficosController.selectCircunscripcionMapaSondeoAuto(codAutonomia, avance);
                                            }
                                            if (TablaFaldones.getSelectedRow() == 0 || TablaFaldones.getSelectedRow() == 3) {
                                                graficosController.selectCircunscripcionAutnomiaSondeoAuto(codAutonomia, avance);
                                            }
                                            if (TablaCartones.getSelectedRow() == 0) {
                                                if (tablaComunidades.getSelectedRow() != -1) {
                                                    String nombreCCAA = tablaComunidades.getValueAt(tablaComunidades.getSelectedRow(), 0).toString();
                                                    String codigo = nombreCodigoAuto.get(nombreCCAA);
                                                    graficosController.descargarResultadosCsvAutoSondeo(codigo);
                                                    graficosController.selectCircunscripcionMapaSondeoAuto(codigo, avance);
                                                }
                                            }
                                        } else {
                                            carmen = clienteApi.getCarmenDtoSondeoAuto("9900000", avance).execute().body();
                                            graficosController.selectCircunscripcionMapaSondeoAuto("9900000", avance);
                                        }
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                                showDataTable(carmen);
                            }
                        } else {
                            loadSelectedMunicipales((String) tablaComunidades.getValueAt(selectedRow, 0));
                            codAutonomia = nombreCodigo.get(((String) tablaComunidades.getValueAt(selectedRow, 0)).replaceAll(" ", ""));
                            ((javax.swing.table.DefaultTableModel) tablaDatos.getModel()).setRowCount(0);
                            if (oficiales) {
                                try {
                                    if (TablaCartones.getSelectedRow() != 3) {
                                        carmen = clienteApi.getCarmenDtoOficialMuni(codAutonomia, avance).execute().body();
                                        if (TablaCartones.getSelectedRow() == 2) {
                                            //  graficosController.selectCircunscripcionMapaOficialMuni(codAutonomia);
                                        }
                                        if (TablaFaldones.getSelectedRow() == 0 || TablaFaldones.getSelectedRow() == 3) {
                                            //    graficosController.selectCircunscripcionAutonomiaOficialMuni(codAutonomia);
                                        }
                                        if (TablaCartones.getSelectedRow() == 0) {
                                            if (tablaComunidades.getSelectedRow() != -1) {
                                                String nombreCCAA = tablaComunidades.getValueAt(tablaComunidades.getSelectedRow(), 0).toString();
                                                String codigo = nombreCodigo.get(nombreCCAA);
                                                graficosController.descargarResultadosCsvMuniOficial(codigo);
                                            }
                                        }
                                    } else {
                                        carmen = clienteApi.getCarmenDtoOficialMuni("9900000", avance).execute().body();
                                        graficosController.selectCircunscripcionMapaOficialMuni("9900000", avance);
                                    }
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            } else {
                                try {
                                    if (TablaCartones.getSelectedRow() != 3) {
                                        carmen = clienteApi.getCarmenDtoSondeoMuni(codAutonomia, avance).execute().body();
                                        if (TablaCartones.getSelectedRow() == 1 || TablaCartones.getSelectedRow() == 2) {
                                            //graficosController.selectCircunscripcionMapaSondeoMuni(codAutonomia);
                                        }
                                        if (TablaFaldones.getSelectedRow() == 0 || TablaFaldones.getSelectedRow() == 3) {
                                            //graficosController.selectCircunscripcionAutnomiaSondeoMuni(codAutonomia);
                                        }
                                        if (TablaCartones.getSelectedRow() == 0) {
                                            if (tablaComunidades.getSelectedRow() != -1) {
                                                String nombreCCAA = tablaComunidades.getValueAt(tablaComunidades.getSelectedRow(), 0).toString();
                                                String codigo = nombreCodigo.get(nombreCCAA);
                                                graficosController.descargarResultadosCsvMuniSondeo(codigo);
                                            }
                                        }
                                    } else {
                                        carmen = clienteApi.getCarmenDtoSondeoMuni("9900000", avance).execute().body();
                                        graficosController.selectCircunscripcionMapaSondeoMuni("9900000", avance);
                                    }
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                        lblEscrutado.setText(carmen.getCircunscripcion().getEscrutado() + "");
                        lblParticipacion.setText(carmen.getCircunscripcion().getParticipacion() + "");
                        lblPartHistorica.setText(carmen.getCircunscripcion().getParticipacionHistorico() + "");
                        lblEscanosTotales.setText(carmen.getCircunscripcion().getEscanios() + "");
                    }
                }
            }
            if (tablaComunidades.getColumnModel().getColumnCount() > 0) {
                tablaComunidades.getColumnModel().getColumn(0).setResizable(false);
            }
        });

        TablaCartones.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]

                        {
                                {"Resultados"},
                                {"Participación"},
                                {"Arco"},
                                {"Participacion España"}
                        },
                new String[]

                        {"CARTONES"}
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
        if (TablaCartones.getColumnModel().

                getColumnCount() > 0) {
            TablaCartones.getColumnModel().getColumn(0).setResizable(false);
        }

        tablaMunicipios.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]
                        {
                                {null},
                                {null},
                                {null},
                                {null}
                        },
                new String[]

                        {
                                "CIRCUNSCRIPCIONES"
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
        if (tablaMunicipios.getColumnModel().

                getColumnCount() > 0) {
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
                //TablaCartones.getSelectionModel().clearSelection();
            }
        });

        btnSondeoMunicipales.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSondeoMunicipales.setText("SONDEO MUNICIPALES");
        btnSondeoMunicipales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSondeoMunicipalesActionPerformed(evt);
                //TablaCartones.getSelectionModel().clearSelection();
            }
        });

        btnDatosAutonomicas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDatosAutonomicas.setText("DATOS AUTONOMICAS");
        btnDatosAutonomicas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatosAutonomicasActionPerformed(evt);
                //TablaCartones.getSelectionModel().clearSelection();
            }
        });

        btnDatosMunicipales.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDatosMunicipales.setText("DATOS MUNICIPALES");
        btnDatosMunicipales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatosMunicipalesActionPerformed(evt);
                //TablaCartones.getSelectionModel().clearSelection();
            }
        });

        TablaFaldones.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]

                        {
                                {"Inferior"},
                                {"Lateral"},
                                {"Sedes"},
                                {"Votantes"}
                        },
                new String[]

                        {
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
        if (TablaFaldones.getColumnModel().

                getColumnCount() > 0) {
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

        btnConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconconfig.png"))); // NOI18N
        btnConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigActionPerformed(evt);
            }
        });

        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        lblConexion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblConexion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConexion.setText("...");

        cbRegional.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbRegional.setText("REGIONAL");


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().
                setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).
                        addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().
                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).
                                        addGroup(layout.createSequentialGroup().
                                                addContainerGap().
                                                addComponent(lblConexion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).
                                                addGap(18, 18, 18)).
                                        addGroup(layout.createSequentialGroup().
                                                addGap(48, 48, 48).
                                                addComponent(btnConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))).
                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).
                                        addGroup(layout.createSequentialGroup().
                                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).
                                                        addComponent(btnDatosMunicipales, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                        addComponent(btnDatosAutonomicas, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)).
                                                addGap(49, 49, 49).
                                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).
                                                        addComponent(btnSondeoAutonomicas, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                        addComponent(btnSondeoMunicipales, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))).
                                        addComponent(cbRegional)).
                                addGap(31, 31, 31).
                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).
                                        addGroup(layout.createSequentialGroup().
                                                addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).
                                                addComponent(lblEscrutado, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)).
                                        addGroup(layout.createSequentialGroup().
                                                addComponent(lblEscTotales).
                                                addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).
                                                addComponent(lblEscanosTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)).
                                        addGroup(layout.createSequentialGroup().
                                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).
                                                        addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                        addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)).
                                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).
                                                        addGroup(layout.createSequentialGroup().
                                                                addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).
                                                                addComponent(lblParticipacion, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)).
                                                        addGroup(layout.createSequentialGroup().
                                                                addGap(12, 12, 12).
                                                                addComponent(lblPartHistorica, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))).
                                        addGroup(layout.createSequentialGroup().
                                                addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).
                                                addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))).
                                addGap(33, 33, 33).
                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).
                                        addComponent(btnAvance2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE).
                                        addComponent(btnAvance3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE).
                                        addComponent(btnAvance1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)).
                                addGap(24, 24, 24)).
                        addGroup(layout.createSequentialGroup().
                                addGap(34, 34, 34).
                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).
                                        addGroup(layout.createSequentialGroup().
                                                addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE).
                                                addComponent(btnEntra, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                addGap(45, 45, 45).
                                                addComponent(btnSale, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                addGap(45, 45, 45).
                                                addComponent(btnReplegar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                addGap(136, 136, 136).
                                                addComponent(btnPactos, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                addGap(33, 33, 33)).
                                        addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup().
                                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).
                                                        addGroup(layout.createSequentialGroup().
                                                                addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                                addGap(19, 19, 19)).
                                                        addGroup(layout.createSequentialGroup().
                                                                addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                                addGap(18, 18, 18))).
                                                addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                addGap(18, 18, 18).
                                                addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                addGap(31, 31, 31).
                                                addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))).
                                addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).
                        addGroup(layout.createSequentialGroup().
                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).
                                        addGroup(layout.createSequentialGroup().
                                                addGap(32, 32, 32).
                                                addComponent(btnConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).
                                                addComponent(lblConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)).
                                        addGroup(layout.createSequentialGroup().
                                                addGap(25, 25, 25).
                                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).
                                                        addComponent(jCheckBox1).
                                                        addComponent(btnActualizar).
                                                        addComponent(cbRegional)).
                                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).
                                                        addGroup(layout.createSequentialGroup().
                                                                addGap(18, 18, 18).
                                                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).
                                                                        addComponent(lblEscanosTotales).
                                                                        addComponent(lblEscTotales)).
                                                                addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).
                                                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).
                                                                        addComponent(jLabel2).
                                                                        addComponent(lblEscrutado))).
                                                        addGroup(layout.createSequentialGroup().
                                                                addGap(26, 26, 26).
                                                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).
                                                                        addComponent(btnDatosMunicipales, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                                        addComponent(btnSondeoMunicipales, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)).
                                                                addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).
                                                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).
                                                                        addComponent(btnSondeoAutonomicas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                                        addComponent(btnDatosAutonomicas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                                        addComponent(jLabel3).
                                                                        addComponent(lblParticipacion))))).
                                        addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().
                                                addContainerGap().
                                                addComponent(btnAvance1).
                                                addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).
                                                addComponent(btnAvance2).
                                                addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).
                                                addComponent(btnAvance3))).
                                addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).
                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).
                                        addComponent(lblPartHistorica).
                                        addComponent(jLabel4)).
                                addGap(18, 18, 18).
                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).
                                        addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).
                                        addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).
                                        addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE).
                                        addGroup(layout.createSequentialGroup().
                                                addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE).
                                                addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).
                                                addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))).
                                addGap(31, 31, 31).
                                addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).
                                        addComponent(btnEntra, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE).
                                        addComponent(btnSale, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE).
                                        addComponent(btnPactos, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE).
                                        addComponent(btnReplegar).
                                        addComponent(btnReset)).
                                addGap(19, 19, 19))
        );
        TablaCartones.getSelectionModel().

                addListSelectionListener(e ->

                {
                    if (!e.getValueIsAdjusting()) {
                        if (TablaCartones.getSelectedRow() != -1) {
                            if (TablaCartones.getSelectedRow() == 3) {
                                entreParticipacionEsp();
                            } else if (TablaCartones.getSelectedRow() != -1) {
                                rellenarCCAA(tipoElecciones);
                            }
                        }
                    }
                });

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
        municipios = municipios.stream().distinct().collect(Collectors.toList());
        municipios.sort(Comparator.comparing(Circunscripcion::getCodigo));
        for (Circunscripcion municipio : municipios) {
            if (!municipio.getCodigo().endsWith("00000"))
                tableModel.addRow(new Object[]{municipio.getNombreCircunscripcion()});
        }
        JScrollPane scrollPane = new JScrollPane(tablaMunicipios);
        tablaMunicipios = new JTable(tableModel);
        tablaComunidades.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(tablaMunicipios);

        tablaMunicipios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tablaMunicipios.getSelectedRow();
                if (selectedRow != -1) {
                    String nombreMunicipio = (String) tablaMunicipios.getValueAt(selectedRow, 0);
                    String codMunicipio;
                    CarmenDTO carmen = null;
                    isComunidad = false;
                    isMunicipio = true;
                    if (tipoElecciones == 2 || tipoElecciones == 4) {
                        codMunicipio = nombreCodigoAutonomicas.get(tablaMunicipios.getValueAt(selectedRow, 0));
                        if (oficiales) {
                            try {
                                carmen = clienteApi.getCarmenDtoOficialAuto(codMunicipio, avance).execute().body();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            if (TablaCartones.getSelectedRow() == 1 || TablaCartones.getSelectedRow() == 2) {
                                graficosController.selectCircunscripcionMapaOficialAuto(codMunicipio, avance);
                            }
                            if (TablaFaldones.getSelectedRow() == 0 || TablaFaldones.getSelectedRow() == 3) {
                                graficosController.selectCircunscripcionAutonomiaOficialAuto(codMunicipio, avance);
                            }
                            if (TablaCartones.getSelectedRow() == 0) {
                                graficosController.descargarResultadosCsvAutoOficial(codMunicipio);
                                graficosController.selectCircunscripcionMapaOficialAuto(codMunicipio, avance);
                            }
                        } else {
                            try {
                                carmen = clienteApi.getCarmenDtoSondeoAuto(codMunicipio, avance).execute().body();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            if (TablaCartones.getSelectedRow() == 1 || TablaCartones.getSelectedRow() == 2) {
                                graficosController.selectCircunscripcionMapaSondeoAuto(codMunicipio, avance);
                            }
                            if (TablaFaldones.getSelectedRow() == 0 || TablaFaldones.getSelectedRow() == 3) {
                                graficosController.selectCircunscripcionAutnomiaSondeoAuto(codMunicipio, avance);
                            }
                            if (TablaCartones.getSelectedRow() == 0) {
                                graficosController.descargarResultadosCsvAutoSondeo(codMunicipio);
                                graficosController.selectCircunscripcionMapaSondeoAuto(codMunicipio, avance);
                            }
                        }
                    } else {
                        codMunicipio = nombreCodigoMunicipal.get(tablaMunicipios.getValueAt(selectedRow, 0));
                        if (oficiales) {
                            try {
                                carmen = clienteApi.getCarmenDtoOficialMuni(codMunicipio, avance).execute().body();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            if (TablaCartones.getSelectedRow() == 1 || TablaCartones.getSelectedRow() == 2) {
                                graficosController.selectCircunscripcionMapaOficialMuni(codMunicipio, avance);
                            }
                            if (TablaFaldones.getSelectedRow() == 0 || TablaFaldones.getSelectedRow() == 3) {
                                graficosController.selectCircunscripcionAutonomiaOficialMuni(codMunicipio, avance);
                            }
                            if (TablaCartones.getSelectedRow() == 0) {
                                graficosController.descargarResultadosCsvMuniOficial(codMunicipio);
                                graficosController.selectCircunscripcionMapaOficialMuni(codMunicipio, avance);
                            }
                        } else {
                            try {
                                carmen = clienteApi.getCarmenDtoSondeoMuni(codMunicipio, avance).execute().body();
                                graficosController.selectCircunscripcionMapaSondeoMuni(codMunicipio, avance);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            if (TablaCartones.getSelectedRow() == 1 || TablaCartones.getSelectedRow() == 2) {
                                graficosController.selectCircunscripcionMapaSondeoMuni(codMunicipio, avance);
                            }
                            if (TablaFaldones.getSelectedRow() == 0 || TablaFaldones.getSelectedRow() == 3) {
                                graficosController.selectCircunscripcionAutnomiaSondeoMuni(codMunicipio, avance);
                            }
                            if (TablaCartones.getSelectedRow() == 0) {
                                graficosController.descargarResultadosCsvMuniSondeo(codMunicipio);
                                graficosController.selectCircunscripcionMapaSondeoMuni(codMunicipio, avance);
                            }
                            //graficosController.selectedMunicipalesSondeo(codMunicipio);
                        }
                    }
                    lblEscrutado.setText(carmen.getCircunscripcion().getEscrutado() + "");
                    lblParticipacion.setText(carmen.getCircunscripcion().getParticipacion() + "");
                    lblPartHistorica.setText(carmen.getCircunscripcion().getParticipacionHistorico() + "");
                    lblEscanosTotales.setText(carmen.getCircunscripcion().getEscanios() + "");
                    switch (selectedDb) {
                        case "DA" -> showDataTableOficialAutonomicas(carmen);
                        case "SA" -> showDataTableSondeoAutonomicas(carmen);
                        case "SM" -> showDataTableSondeoMunicipio(carmen);
                        default -> showDataTableOficialMunicipio(carmen);
                    }
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
        List<Circunscripcion> municipios = new ArrayList<>();
        if (!cbRegional.isSelected()) {
            if (circunscripcionesMunicipales.get(cod).size() != 0) {
                String codComunidad = circunscripcionesMunicipales.get(cod).get(0).getCodigoComunidad();
                municipios = graficosController.filtradasPorMostrarMuni(codComunidad);
            }
        } else {
            municipios = circunscripcionesMunicipales.get(cod.replaceAll(" ", "")).stream().toList();
            municipios = new ArrayList<>(municipios.stream()
                    .distinct()
                    .collect(Collectors.toMap(Circunscripcion::getCodigo, Function.identity(), (municipio1, municipio2) -> municipio1))
                    .values());
            municipios = municipios.stream().filter(muni -> !muni.getCodigo().endsWith("000")).collect(Collectors.toList());
            municipios.sort(Comparator.comparing(Circunscripcion::getCodigo));

            List<Circunscripcion> municipiosSinDuplicados = new ArrayList<>();
            Set<String> nombresUnicos = new HashSet<>();
            for (Circunscripcion municipio : municipios) {
                String nombre = municipio.getNombreCircunscripcion();
                if (!nombresUnicos.contains(nombre)) {
                    nombresUnicos.add(nombre);
                    municipiosSinDuplicados.add(municipio);
                }
            }
            municipios = municipiosSinDuplicados;
        }

        for (Circunscripcion municipio : municipios) {
            tableModel.addRow(new Object[]{municipio.getNombreCircunscripcion()});
        }

        JScrollPane scrollPane = new JScrollPane(tablaMunicipios);
        tablaMunicipios = new JTable(tableModel);
        jScrollPane4.setViewportView(tablaMunicipios);
        tablaMunicipios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tablaMunicipios.getSelectedRow();
                if (selectedRow != -1) {
                    String nombreMunicipio = (String) tablaMunicipios.getValueAt(selectedRow, 0);
                    String codMunicipio;
                    CarmenDTO carmen = null;
                    isComunidad = false;
                    isMunicipio = true;
                    if (tipoElecciones == 2 || tipoElecciones == 4) {
                        codMunicipio = nombreCodigoAutonomicas.get(tablaMunicipios.getValueAt(selectedRow, 0));
                        if (oficiales) {
                            try {
                                carmen = clienteApi.getCarmenDtoOficialAuto(codMunicipio, avance).execute().body();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            if (TablaCartones.getSelectedRow() == 1 || TablaCartones.getSelectedRow() == 2) {
                                graficosController.selectCircunscripcionMapaOficialAuto(codMunicipio, avance);
                            }
                            if (TablaFaldones.getSelectedRow() == 0 || TablaFaldones.getSelectedRow() == 3) {
                                graficosController.selectCircunscripcionAutonomiaOficialAuto(codMunicipio, avance);
                            }
                            if (TablaCartones.getSelectedRow() == 0) {
                                graficosController.descargarResultadosCsvAutoOficial(codMunicipio);
                                graficosController.selectCircunscripcionMapaOficialAuto(codMunicipio, avance);
                            }
                        } else {
                            try {
                                carmen = clienteApi.getCarmenDtoSondeoAuto(codMunicipio, avance).execute().body();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            if (TablaCartones.getSelectedRow() == 1 || TablaCartones.getSelectedRow() == 2) {
                                graficosController.selectCircunscripcionMapaSondeoAuto(codMunicipio, avance);
                            }
                            if (TablaFaldones.getSelectedRow() == 0 || TablaFaldones.getSelectedRow() == 3) {
                                graficosController.selectCircunscripcionAutnomiaSondeoAuto(codMunicipio, avance);
                            }
                            if (TablaCartones.getSelectedRow() == 0) {
                                graficosController.descargarResultadosCsvAutoSondeo(codMunicipio);
                                graficosController.selectCircunscripcionMapaSondeoAuto(codMunicipio, avance);
                            }
                        }
                    } else {
                        codMunicipio = nombreCodigoMunicipal.get(tablaMunicipios.getValueAt(selectedRow, 0));
                        if (oficiales) {
                            try {
                                carmen = clienteApi.getCarmenDtoOficialMuni(codMunicipio, avance).execute().body();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            if (TablaCartones.getSelectedRow() == 1 || TablaCartones.getSelectedRow() == 2) {
                                graficosController.selectCircunscripcionMapaOficialMuni(codMunicipio, avance);
                            }
                            if (TablaFaldones.getSelectedRow() == 0 || TablaFaldones.getSelectedRow() == 3) {
                                graficosController.selectCircunscripcionAutnomiaOficialMuni(codMunicipio, avance);
                            }
                            if (TablaCartones.getSelectedRow() == 0) {
                                graficosController.descargarResultadosCsvMuniOficial(codMunicipio);
                                graficosController.selectCircunscripcionMapaOficialMuni(codMunicipio, avance);
                            }
                        } else {
                            try {
                                graficosController.selectCircunscripcionMapaSondeoMuni(codMunicipio, avance);
                                carmen = clienteApi.getCarmenDtoSondeoMuni(codMunicipio, avance).execute().body();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            if (TablaCartones.getSelectedRow() == 1 || TablaCartones.getSelectedRow() == 2) {
                                graficosController.selectCircunscripcionMapaSondeoMuni(codMunicipio, avance);
                            }
                            if (TablaFaldones.getSelectedRow() == 0 || TablaFaldones.getSelectedRow() == 3) {
                                graficosController.selectCircunscripcionAutnomiaSondeoMuni(codMunicipio, avance);
                            }
                            if (TablaCartones.getSelectedRow() == 0) {
                                graficosController.descargarResultadosCsvMuniSondeo(codMunicipio);
                                graficosController.selectCircunscripcionMapaSondeoMuni(codMunicipio, avance);
                            }
                            //graficosController.selectedMunicipalesSondeo(codMunicipio);
                        }
                    }
                    lblEscrutado.setText(carmen.getCircunscripcion().getEscrutado() + "");
                    lblParticipacion.setText(carmen.getCircunscripcion().getParticipacion() + "");
                    lblPartHistorica.setText(carmen.getCircunscripcion().getParticipacionHistorico() + "");
                    lblEscanosTotales.setText(carmen.getCircunscripcion().getEscanios() + "");
                    switch (selectedDb) {
                        case "DA" -> showDataTableOficialAutonomicas(carmen);
                        case "SA" -> showDataTableSondeoAutonomicas(carmen);
                        case "SM" -> showDataTableSondeoMunicipio(carmen);
                        default -> showDataTableOficialMunicipio(carmen);
                    }
                }
            }
        });
    }

    private boolean dejoEntrarEntra() {
        boolean valido = false;
        int rowComunidad = tablaComunidades.getSelectedRow();

        if (rowComunidad != -1) {
            if (tablaMunicipios.getSelectedRow() != -1 || tipoElecciones == 2 || tipoElecciones == 4 || nombreCodigo.get(tablaComunidades.getValueAt(rowComunidad, 0)).equals("1800000") || nombreCodigo.get(tablaComunidades.getValueAt(rowComunidad, 0)).equals("1900000")) {
                valido = true;
            }
        }
        if (TablaCartones.getSelectedRow() == 3 || TablaFaldones.getSelectedRow() == 3) {
            valido = true;
        }
        if (TablaFaldones.getSelectedRow() == 2) {
            valido = true;
        }
        return valido;
    }

    private boolean dejoEntrarPactos() {
        boolean valido = false;
        int rowComunidad = tablaComunidades.getSelectedRow();

        if (rowComunidad != -1) {
            if (tablaMunicipios.getSelectedRow() != -1 || tipoElecciones == 2 || tipoElecciones == 4 || nombreCodigo.get(tablaComunidades.getValueAt(rowComunidad, 0)).equals("1800000") || nombreCodigo.get(tablaComunidades.getValueAt(rowComunidad, 0)).equals("1900000")) {
                valido = true;
            }
        }
        return valido;
    }

    private void btnEntraActionPerformed(java.awt.event.ActionEvent evt) {
        if (dejoEntrarEntra()) {
            switch (tipoElecciones) {
                //OFICIALES MUNICIPALES
                case 1 -> {
                    switch (TablaCartones.getSelectedRow()) {
                        //RESULTADOS
                        case 0 -> {
                            if (!resultadosIn) {
                                if (sacarCartonAnteriorMuni()) {
                                    graficosController.entraResultadosMuniDelay();
                                } else {
                                    graficosController.entraResultadosMuni();
                                }
                                resultadosIn = true;
                            } else if (isComunidad) {
                                graficosController.cambiaResultadosComunidad();
                            } else if (isMunicipio) {
                                graficosController.cambiaResultadosMunicipio();
                            }
                        }
                        //PARTICIPACION
                        case 1 -> {
                            if (!participacionIn) {
                                if (sacarCartonAnteriorMuni()) {
                                    graficosController.entraParticipacionMuniDelay();
                                } else {
                                    graficosController.entraParticipacionMuni();
                                }
                                participacionIn = true;
                            } else if (isComunidad) {
                                graficosController.cambiaParticipacionComunidad();
                            } else if (isMunicipio) {
                                graficosController.cambiaParticipacionMunicipio();
                            }
                        }
                        //ARCO
                        case 2 -> {
                            if (!arcoIn) {
                                if (sacarCartonAnteriorMuni()) {
                                    graficosController.entraArcoMuniDelay();
                                } else {
                                    graficosController.entraArcoMuni();
                                }
                                arcoIn = true;
                            }
                        }
                        case 3 -> {
                            if (!participacionEspIn) {
                                if (sacarCartonAnteriorMuni()) {
                                    graficosController.entraParticipacionEspMuniDelay();

                                } else {
                                    graficosController.entraParticipacionEspMuni();
                                }

                                participacionEspIn = true;
                            }

                        }
                        default -> System.out.print("");
                    }
                    switch (TablaFaldones.getSelectedRow()) {
                        //INFERIOR
                        case 0 -> {
                            if (!inferiorMuniIn && !inferiorAutoIn && !inferiorAutoSondeoIn && !inferiorMuniSondeoIn) {
                                graficosController.entraFaldonMuni();
                                inferiorMuniIn = true;
                            } else if (inferiorAutoIn) {
                                graficosController.deAutoaMuniFaldonAuto();
                                inferiorMuniIn = true;
                                inferiorAutoIn = false;
                            } else if (inferiorMuniIn) {
                                graficosController.encadenaFaldonMuni();
                            } else if (inferiorAutoSondeoIn) {
                                graficosController.deAutoSondeoAMuni();
                                inferiorAutoSondeoIn = false;
                                inferiorMuniIn = true;
                            } else if (inferiorMuniSondeoIn) {
                                graficosController.deMuniSondeoAMuni();
                                inferiorMuniSondeoIn = false;
                                inferiorMuniIn = true;
                            }
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
                        case 2 -> graficosController.faldonSedesEntra();
                        //VOTOS MILLONES
                        case 3 -> {
                            if (votantesIn) {
                                graficosController.faldonVotantesHistEntra();
                            } else {
                                graficosController.faldonVotantesEntra();
                                votantesIn = true;
                            }
                        }
                        default -> System.out.print("");
                    }
                }
                //OFICIALES AUTONOMICAS
                case 2 -> {
                    switch (TablaCartones.getSelectedRow()) {
                        //RESULTADOS
                        case 0 -> {
                            if (!resultadosIn) {
                                if (sacarCartonAnteriorAuto()) {
                                    graficosController.entraResultadosAutoDelay();
                                } else {
                                    graficosController.entraResultadosAuto();
                                }
                                resultadosIn = true;
                            } else if (isComunidad) {
                                graficosController.cambiaResultadosComunidad();
                            } else if (isMunicipio) {
                                graficosController.cambiaResultadosMunicipio();
                            }
                        }
                        //PARTICIPACION
                        case 1 -> {
                            if (!participacionIn) {
                                if (sacarCartonAnteriorAuto()) {
                                    graficosController.entraParticipacionAutoDelay();
                                } else {
                                    graficosController.entraParticipacionAuto();
                                }
                                participacionIn = true;
                            } else if (isComunidad) {
                                graficosController.cambiaParticipacionComunidad();
                            } else if (isMunicipio) {
                                graficosController.cambiaParticipacionMunicipio();
                            }
                        }
                        //ARCO
                        case 2 -> {
                            if (!arcoIn) {
                                if (sacarCartonAnteriorAuto()) {
                                    graficosController.entraArcoAutoDelay();
                                } else {
                                    graficosController.entraArcoAuto();
                                }
                                arcoIn = true;
                            }
                        }

                        case 3 -> {
                            if (!participacionEspIn) {
                                if (sacarCartonAnteriorMuni()) {
                                    graficosController.entraParticipacionEspAutoDelay();
                                } else {
                                    graficosController.entraParticipacionEspAuto();
                                }
                                participacionEspIn = true;
                            }
                        }
                        default -> System.out.print("");
                    }
                    switch (TablaFaldones.getSelectedRow()) {
                        //INFERIOR
                        case 0 -> {
                            if (!inferiorMuniIn && !inferiorAutoIn && !inferiorAutoSondeoIn && !inferiorMuniSondeoIn) {
                                graficosController.entraFaldonAuto();
                                inferiorAutoIn = true;
                            } else if (inferiorMuniIn) {
                                graficosController.deMuniaAutoFaldonMuni();
                                inferiorMuniIn = false;
                                inferiorAutoIn = true;
                            } else if (inferiorAutoIn) {
                                graficosController.encadenaFaldonAuto();
                            } else if (inferiorAutoSondeoIn) {
                                graficosController.deAutoSondeoAAuto();
                                inferiorAutoSondeoIn = false;
                                inferiorAutoIn = true;
                            } else if (inferiorMuniSondeoIn) {
                                graficosController.deMuniSondeoAAuto();
                                inferiorMuniSondeoIn = false;
                                inferiorAutoIn = true;
                            }
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
                        case 2 -> graficosController.faldonSedesEntra();
                        //VOTOS MILLONES
                        case 3 -> {
                            if (votantesIn) {
                                graficosController.faldonVotantesHistEntra();
                            } else {
                                graficosController.faldonVotantesEntra();
                                votantesIn = true;
                            }
                        }
                        default -> System.out.print("");
                    }
                }
                //SONDEO MUNICIPALES
                case 3 -> {
                    switch (TablaCartones.getSelectedRow()) {
                        //RESULTADOS
                        case 0 -> {
                            if (!resultadosIn) {
                                if (sacarCartonAnteriorMuni()) {
                                    graficosController.entraSondeoResultadosMuniDelay();
                                } else {
                                    graficosController.entraSondeoResultadosMuni();
                                }
                                resultadosIn = true;
                            } else if (isComunidad) {
                                graficosController.cambiaSondeoResultadosComunidad();
                            } else if (isMunicipio) {
                                graficosController.cambiaSondeoResultadosMunicipio();
                            }
                        }
                        //PARTICIPACION
                        case 1 -> {
                        }
                        //          if (!participacionIn) {
                        //              if (sacarCartonAnteriorMuni()) {
                        //                  graficosController.entraParticipacionMuniDelay();
                        //              } else {
                        //                  graficosController.entraParticipacionMuni();
                        //              }
                        //              participacionIn = true;
                        //          } else if (isComunidad) {
                        //              graficosController.cambiaParticipacionComunidad();
                        //          } else if (isMunicipio) {
                        //              graficosController.cambiaParticipacionMunicipio();
                        //          }
                        //      }
                        //ARCO
                        case 2 -> {
                            if (!arcoIn) {
                                if (sacarCartonAnteriorMuni()) {
                                    graficosController.entraArcoMuniSondeoDelay();
                                } else {
                                    graficosController.entraArcoMuniSondeo();
                                }

                                arcoIn = true;
                            }
                        }
                        case 3 -> {
                            if (!participacionEspIn) {
                                if (sacarCartonAnteriorMuni()) {
                                    graficosController.entraParticipacionEspMuniDelay();
                                } else {
                                    graficosController.entraParticipacionEspMuni();
                                }
                                participacionEspIn = true;
                            }

                        }
                        default -> System.out.print("");
                    }
                    switch (TablaFaldones.getSelectedRow()) {
                        //INFERIOR

                        case 0 -> {
                            if (!inferiorMuniIn && !inferiorAutoIn && !inferiorAutoSondeoIn && !inferiorMuniSondeoIn) {
                                graficosController.entraFaldonMuniSondeo();
                                inferiorMuniSondeoIn = true;
                            } else if (inferiorAutoSondeoIn) {
                                graficosController.deAutoSondeoAMuniSondeo();
                                inferiorMuniSondeoIn = true;
                                inferiorAutoSondeoIn = false;
                            } else if (inferiorMuniSondeoIn) {
                                graficosController.encadenaFaldonMunicipalesSondeo();
                            } else if (inferiorMuniIn || inferiorAutoIn) {
                                inferiorMuniSondeoIn = true;
                                graficosController.entraFaldonMuniSondeo();
                                inferiorMuniIn = false;
                                inferiorAutoIn = false;
                            }
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
                                lateralIn = true;
                            } else {
                                if (codCCAA != null) {
                                    graficosController.despliegaLateralMunicipales(codCCAA);
                                }
                            }
                        }
                        //SEDES
                        case 2 -> graficosController.faldonSedesEntra();
                        //VOTOS MILLONES
                        case 3 -> {
                            if (votantesIn) {
                                graficosController.faldonVotantesHistEntra();
                            } else {
                                graficosController.faldonVotantesEntra();
                                votantesIn = true;
                            }
                        }
                        default -> System.out.print("");
                    }
                }
                //SONDEO AUTONOMICAS
                case 4 -> {
                    switch (TablaCartones.getSelectedRow()) {
                        //RESULTADOS
                        case 0 -> {
                            if (!resultadosIn) {
                                if (sacarCartonAnteriorAuto()) {
                                    graficosController.entraSondeoResultadosAutoDelay();
                                } else {
                                    graficosController.entraSondeoResultadosAuto();
                                }
                                resultadosIn = true;
                            } else if (isComunidad) {
                                graficosController.cambiaSondeoResultadosComunidad();
                            } else if (isMunicipio) {
                                graficosController.cambiaSondeoResultadosMunicipio();
                            }
                        }
                        //PARTICIPACION
                        case 1 -> {
                        }
                        //      if (!participacionIn) {
                        //          sacarCartonAnteriorAuto();
                        //          graficosController.entraParticipacionAuto();
                        //          participacionIn = true;
                        //      } else if (isComunidad) {
                        //          graficosController.cambiaParticipacionComunidad();
                        //      } else if (isMunicipio) {
                        //          graficosController.cambiaParticipacionMunicipio();
                        //      }
                        //  }
                        //ARCO
                        case 2 -> {
                            if (!arcoIn) {
                                sacarCartonAnteriorAuto();
                                graficosController.entraArcoAutoSondeo();
                                arcoIn = true;
                            }
                        }
                        case 3 -> {
                            if (!participacionEspIn) {
                                if (sacarCartonAnteriorMuni()) {
                                    graficosController.entraParticipacionEspAutoDelay();
                                } else {
                                    graficosController.entraParticipacionEspAuto();
                                }
                                participacionEspIn = true;
                            }
                        }
                        default -> System.out.print("");
                    }
                    switch (TablaFaldones.getSelectedRow()) {
                        //INFERIOR
                        case 0 -> {
                            if (!inferiorMuniIn && !inferiorAutoIn && !inferiorAutoSondeoIn && !inferiorMuniSondeoIn) {
                                graficosController.entraFaldonAutoSondeo();
                                inferiorAutoSondeoIn = true;
                            } else if (inferiorMuniSondeoIn) {
                                graficosController.deMuniSondeoAAutoSondeo();
                                inferiorMuniSondeoIn = false;
                                inferiorAutoSondeoIn = true;
                            } else if (inferiorAutoSondeoIn) {
                                graficosController.encadenaFaldonAutonomicasSondeo();
                            } else if (inferiorMuniIn) {
                                inferiorAutoSondeoIn = true;
                                graficosController.deMuniASondeoAuto();
                                inferiorMuniIn = false;
                            } else if (inferiorAutoIn) {
                                inferiorAutoSondeoIn = true;
                                graficosController.entraFaldonAutoSondeo();
                                inferiorAutoIn = false;
                            }
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
                                lateralIn = true;
                            } else {
                                if (codCCAA != null) {
                                    graficosController.despliegaLateralAutonomicas(codCCAA);
                                }
                            }
                        }
                        //SEDES
                        case 2 -> graficosController.faldonSedesEntra();
                        //VOTOS MILLONES
                        case 3 -> {
                            if (votantesIn) {
                                graficosController.faldonVotantesHistEntra();
                            } else {
                                graficosController.faldonVotantesEntra();
                                votantesIn = true;
                            }
                        }
                        default -> System.out.print("");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "La selección no es válida para hacer ENTRA", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void btnPactosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPactosActionPerformed
        if (dejoEntrarPactos()) {
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
            JFrame pactos = null;
            int arcoOFaldon = 0;
            if (TablaCartones.getSelectedRow() == 2) {
                arcoOFaldon = 1;
            }
            if (TablaFaldones.getSelectedRow() == 0) {
                arcoOFaldon = 2;
            }
            switch (tipoElecciones) {
                case 1, 3 -> {
                    String codigo;
                    if (tablaMunicipios.getSelectedRow() != -1) {
                        codigo = nombreCodigoMunicipal.get(tablaMunicipios.getValueAt(tablaMunicipios.getSelectedRow(), 0).toString());
                    } else if (tablaComunidades.getSelectedRow() != -1) {
                        codigo = nombreCodigo.get(tablaComunidades.getValueAt(tablaComunidades.getSelectedRow(), 0).toString());
                    } else {
                        codigo = null;
                    }
                    pactos = new PactosOpcion2(arcoOFaldon, codigo, tipoElecciones, oficiales, avance);
                }

                case 2, 4 -> {
                    String codigo;
                    if (tablaMunicipios.getSelectedRow() != -1) {
                        codigo = nombreCodigoAutonomicas.get(tablaMunicipios.getValueAt(tablaMunicipios.getSelectedRow(), 0).toString());
                    } else if (tablaComunidades.getSelectedRow() != -1) {
                        codigo = nombreCodigoAutonomicas.get(tablaComunidades.getValueAt(tablaComunidades.getSelectedRow(), 0).toString());
                    } else {
                        codigo = null;
                    }
                    pactos = new PactosOpcion2(arcoOFaldon, codigo, tipoElecciones, oficiales, avance);
                }
            }
            pactos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            pactos.setLocation(screenWidth / 4, screenHeight / 2);
            pactos.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Pactos no válido con la selección actual", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnPactosActionPerformed

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
                    case 0 -> {
                        graficosController.saleResultadosMuni();
                        resultadosIn = false;
                    }
                    //PARTICIPACION
                    case 1 -> {
                        graficosController.saleParticipacionMuni();
                        participacionIn = false;
                    }
                    //ARCO
                    case 2 -> {
                        graficosController.saleArcoMuni();
                        arcoIn = false;
                    }
                    case 3 -> {
                        graficosController.saleParticipacionEspMuni();
                        participacionEspIn = false;

                    }

                    default -> System.out.print("");
                }
                switch (TablaFaldones.getSelectedRow()) {
                    //INFERIOR
                    case 0 -> {
                        graficosController.saleFaldonMuni();
                        inferiorMuniIn = false;
                    }
                    //LATERAL
                    case 1 -> {
                        if (lateralIn) {
                            graficosController.saleLateralMunicipales();
                            lateralIn = false;
                        }
                    }
                    //SEDES
                    case 2 -> graficosController.faldonSedesSale();
                    //VOTANTES
                    case 3 -> {
                        graficosController.faldonVotantesSale();
                        votantesIn = false;
                    }
                    default -> System.out.print("");
                }
            }
            //OFICIALES AUTONOMICAS
            case 2 -> {
                switch (TablaCartones.getSelectedRow()) {
                    //RESULTADOS
                    case 0 -> {
                        graficosController.saleResultadosAuto();
                        resultadosIn = false;
                    }
                    //PARTICIPACION
                    case 1 -> {
                        graficosController.saleParticipacionAuto();
                        participacionIn = false;
                    }
                    //ARCO
                    case 2 -> {
                        graficosController.saleArcoAuto();
                        arcoIn = false;
                    }
                    case 3 -> {
                        graficosController.saleParticipacionEspAuto();
                        participacionEspIn = false;

                    }
                    default -> System.out.print("");
                }
                switch (TablaFaldones.getSelectedRow()) {
                    //INFERIOR
                    case 0 -> {
                        graficosController.saleFaldonAuto();
                        inferiorAutoIn = false;
                    }
                    //LATERAL
                    case 1 -> {
                        if (lateralIn) {
                            graficosController.saleLateralAutonomicas();
                            lateralIn = false;
                        }
                    }
                    //SEDES
                    case 2 -> graficosController.faldonSedesSale();
                    //VOTANTES
                    case 3 -> {
                        graficosController.faldonVotantesSale();
                        votantesIn = false;
                    }
                    default -> System.out.print("");
                }
            }
            //SONDEO MUNICIPALES
            case 3 -> {
                switch (TablaCartones.getSelectedRow()) {
                    //RESULTADOS
                    case 0 -> {
                        graficosController.saleSondeoResultadosMuni();
                        resultadosIn = false;
                    }
                    //PARTICIPACION
                    case 1 -> {
                        graficosController.saleParticipacionMuni();
                        participacionIn = false;
                    }
                    //ARCO
                    case 2 -> {
                        graficosController.saleArcoMuniSondeo();
                        arcoIn = false;
                    }
                    case 3 -> {
                        graficosController.saleParticipacionEspMuni();
                        participacionEspIn = false;

                    }
                    default -> System.out.print("");
                }
                switch (TablaFaldones.getSelectedRow()) {
                    //INFERIOR
                    case 0 -> {
                        graficosController.saleFaldonMuniSondeo();
                        inferiorMuniSondeoIn = false;
                    }
                    //LATERAL
                    case 1 -> {
                        if (lateralIn) {
                            graficosController.saleLateralMunicipales();
                            lateralIn = false;
                        }
                    }
                    //SEDES
                    case 2 -> graficosController.faldonSedesSale();
                    //VOTANTES
                    case 3 -> {
                        graficosController.faldonVotantesSale();
                        votantesIn = false;
                    }
                    default -> System.out.print("");
                }
            }
            //SONDEO AUTONOMICAS
            case 4 -> {
                switch (TablaCartones.getSelectedRow()) {
                    //RESULTADOS
                    case 0 -> {
                        graficosController.saleSondeoResultadosAuto();
                        resultadosIn = false;
                    }
                    //PARTICIPACION
                    case 1 -> {
                        graficosController.saleParticipacionAuto();
                        participacionIn = false;
                    }
                    //ARCO
                    case 2 -> {
                        graficosController.saleArcoAutoSondeo();
                        arcoIn = false;
                    }
                    case 3 -> {
                        graficosController.saleParticipacionEspAuto();
                        participacionEspIn = false;
                    }
                    default -> System.out.print("");
                }
                switch (TablaFaldones.getSelectedRow()) {
                    //INFERIOR
                    case 0 -> {
                        graficosController.saleFaldonAutoSondeo();
                        inferiorAutoSondeoIn = false;
                    }
                    //LATERAL
                    case 1 -> {
                        if (lateralIn) {
                            graficosController.saleLateralAutonomicas();
                            lateralIn = false;
                        }
                    }
                    //SEDES
                    case 2 -> graficosController.faldonSedesSale();
                    //VOTANTES
                    case 3 -> {
                        graficosController.faldonVotantesSale();
                        votantesIn = false;
                    }
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
        rellenarCCAA(tipoElecciones);
    }//GEN-LAST:event_btnSondeoAutonomicasActionPerformed

    private void btnDatosAutonomicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatosAutonomicasActionPerformed
        vaciarTablas();
        tipoElecciones = 2;
        oficiales = true;
        resaltarBoton(btnDatosAutonomicas);
        selectedDb = "DA";
        rellenarCCAA(tipoElecciones);
    }//GEN-LAST:event_btnDatosAutonomicasActionPerformed

    private void btnSondeoMunicipalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSondeoMunicipalesActionPerformed
        vaciarTablas();
        tipoElecciones = 3;
        oficiales = false;
        resaltarBoton(btnSondeoMunicipales);
        selectedDb = "SM";
        rellenarCCAA(tipoElecciones);
    }//GEN-LAST:event_btnSondeoMunicipalesActionPerformed

    private void btnDatosMunicipalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatosMunicipalesActionPerformed
        vaciarTablas();
        tipoElecciones = 1;
        oficiales = true;
        resaltarBoton(btnDatosMunicipales);
        selectedDb = "DM";
        rellenarCCAA(tipoElecciones);
    }//GEN-LAST:event_btnDatosMunicipalesActionPerformed

    private void vaciarTablas() {
        ((javax.swing.table.DefaultTableModel) tablaComunidades.getModel()).setRowCount(0);
        ((javax.swing.table.DefaultTableModel) tablaMunicipios.getModel()).setRowCount(0);
        ((javax.swing.table.DefaultTableModel) tablaDatos.getModel()).setRowCount(0);
        isComunidad = false;
        isMunicipio = false;
    }

    private void rellenarCCAA(int tipo) {
        vaciarTablas();
        switch (tipo) {
            case 1, 3 -> {
                DefaultTableModel tableModel = (DefaultTableModel) tablaComunidades.getModel();
                List<String> ccaa = circunscripcionesMunicipales.keySet().stream().filter(x -> !x.endsWith("00000")).collect(Collectors.toList());
                ccaa = ccaa.subList(0, ccaa.size() - 1);
                ccaa.remove("Ceuta");
                ccaa.remove("Melilla");
                for (String s : ccaa) {
                    tableModel.addRow(new Object[]{s});
                }
                tablaComunidades.setModel(tableModel);
            }
            case 2, 4 -> {
                DefaultTableModel tableModel = (DefaultTableModel) tablaComunidades.getModel();
                List<String> lista = new ArrayList<>(nombreCodigoAuto.keySet().stream().toList().subList(0, nombreCodigoAuto.keySet().stream().toList().size() - 1).stream().toList());
                lista.add("Ceuta");
                lista.add("Melilla");
                for (String s : lista) {
                    tableModel.addRow(new Object[]{s});
                }
                tablaComunidades.setModel(tableModel);
            }
            case 5 -> {
                DefaultTableModel tableModel = (DefaultTableModel) tablaComunidades.getModel();
                List<String> ccaa = circunscripcionesAutonomicas.keySet().stream()
                        .filter(s -> s.toLowerCase().equals("total nacional")).toList();
                for (String s : ccaa) {
                    tableModel.addRow(new Object[]{s});
                }
                tablaComunidades.setModel(tableModel);
            }
        }
    }

    private boolean sacarCartonAnteriorAuto() {
        boolean venimosDeOtro = false;
        if (participacionIn) {
            graficosController.saleParticipacionAuto();
            participacionIn = false;
            venimosDeOtro = true;
        }
        if (resultadosIn) {
            if (oficiales) {
                graficosController.saleResultadosAuto();

            } else {
                graficosController.saleSondeoResultadosAuto();
            }
            resultadosIn = false;
            venimosDeOtro = true;
        }
        if (arcoIn) {
            if (oficiales) {
                graficosController.saleArcoAuto();
            } else {
                graficosController.saleArcoAutoSondeo();
            }
            arcoIn = false;
            venimosDeOtro = true;
        }
        if (participacionEspIn) {
            graficosController.saleParticipacionEspAuto();
            participacionEspIn = false;
            venimosDeOtro = true;
        }
        return venimosDeOtro;
    }

    private boolean sacarCartonAnteriorMuni() {
        boolean venimosDeOtro = false;
        if (participacionIn) {
            graficosController.saleParticipacionMuni();
            participacionIn = false;
            venimosDeOtro = true;
        }
        if (resultadosIn) {
            if (oficiales) {
                graficosController.saleResultadosMuni();
            } else {
                graficosController.saleSondeoResultadosMuni();
            }
            resultadosIn = false;
            venimosDeOtro = true;
        }
        if (arcoIn) {
            if (oficiales) {
                graficosController.saleArcoMuni();
            } else {
                graficosController.saleArcoMuniSondeo();
            }
            arcoIn = false;
            venimosDeOtro = true;
        }
        if (participacionEspIn) {
            graficosController.saleParticipacionEspMuni();
            participacionEspIn = false;
            venimosDeOtro = true;
        }
        return venimosDeOtro;
    }

    private void TablaCartonesHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_TablaCartonesHierarchyChanged
    }//GEN-LAST:event_TablaCartonesHierarchyChanged

    private void btnAvance1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvance1ActionPerformed
        resaltarBotonAvances(btnAvance1);
        avance = "1";
    }//GEN-LAST:event_btnAvance1ActionPerformed

    private void btnAvance2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvance2ActionPerformed
        resaltarBotonAvances(btnAvance2);
        avance = "2";
    }//GEN-LAST:event_btnAvance2ActionPerformed

    private void btnAvance3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvance3ActionPerformed
        resaltarBotonAvances(btnAvance3);
        avance = "3";
    }//GEN-LAST:event_btnAvance3ActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        graficosController.resetIPF();
        vaciarTablas();
        TablaFaldones.clearSelection();
        TablaCartones.clearSelection();
        inferiorAutoIn = false;
        inferiorAutoSondeoIn = false;
        inferiorMuniIn = false;
        inferiorMuniSondeoIn = false;
        participacionIn = false;
        participacionEspIn = false;
        resultadosIn = false;
        votantesIn = false;
        arcoIn = false;

    }//GEN-LAST:event_btnResetActionPerformed

    private void btnConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigActionPerformed


        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();


        File configFile = new File(CONFIG_FILE_PATH);

        if (configFile.exists()) {
            // El archivo ya existe
            JFrame config;
            try {
                config = new config(lblConexion);
                config.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                config.setLocation(screenWidth / 4, screenHeight / 4);
                config.setVisible(true);

            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // El archivo no existe, lo creamos
            try {

                JOptionPane.showMessageDialog(null, "El archivo se ha creado correctamente", "Archivo creado", JOptionPane.INFORMATION_MESSAGE);

                configFile.getParentFile().mkdirs();
                configFile.createNewFile();

                Properties properties = new Properties();
                properties.setProperty("direccion1", "10.10.54.140");
                properties.setProperty("direccion3", "0");
                properties.setProperty("puerto", "5123");
                properties.setProperty("ipServerReserva", "127.0.0.1");
                properties.setProperty("direccion2", "0");
                properties.setProperty("direccion4", "0");
                properties.setProperty("ipServer", "127.0.0.1");
                properties.setProperty("BDCartones", "<CARTONES>");
                properties.setProperty("rutaFicheros", "C:\\\\Elecciones2023\\\\DATOS");
                properties.setProperty("nConexiones", "1");
                properties.setProperty("BDFaldones", "<FALDONES>");

                FileOutputStream fos = new FileOutputStream(configFile);
                properties.store(fos, "#Archivo de configuracion");
                fos.close();

                System.out.println("Archivo creado en la ruta: " + configFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }//GEN-LAST:event_btnConfigActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        graficosController.update();
    }//GEN-LAST:event_btnActualizarActionPerformed

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

    private void entreParticipacionEsp() {
        vaciarTablas();
        // rellenarCCAA(5);
        try {
            printDataEsp();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAvance1;
    private javax.swing.JButton btnAvance2;
    private javax.swing.JButton btnAvance3;
    private javax.swing.JButton btnConfig;
    private javax.swing.JButton btnDatosAutonomicas;
    private javax.swing.JButton btnDatosMunicipales;
    private javax.swing.JButton btnEntra;
    private javax.swing.JButton btnPactos;
    private javax.swing.JButton btnReplegar;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSale;
    private javax.swing.JButton btnSondeoAutonomicas;
    private javax.swing.JButton btnSondeoMunicipales;
    private javax.swing.JCheckBox cbRegional;
    private javax.swing.JLabel lblConexion;
    private javax.swing.JCheckBox jCheckBox1;
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
