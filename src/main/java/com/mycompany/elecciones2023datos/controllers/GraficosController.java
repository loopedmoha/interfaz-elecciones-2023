package com.mycompany.elecciones2023datos.controllers;

import com.mycompany.elecciones2023datos.DTO.CarmenDTO;
import com.mycompany.elecciones2023datos.model.*;
import com.mycompany.elecciones2023datos.services.IClienteApi;
import com.mycompany.elecciones2023datos.services.IClienteApiGestion;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static com.mycompany.elecciones2023datos.config.cargarConfiguracion;

public class GraficosController {
    Retrofit retrofit;

    Retrofit retrofit2;
    IClienteApi clienteApi;

    IClienteApiGestion clienteApiGestion;

    public GraficosController() {
        Properties propiedades = null;
        try {
            propiedades = cargarConfiguracion();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String ipServer = propiedades.getProperty("ipServer");
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:9090")
                .addConverterFactory(GsonConverterFactory.create()).build();
        retrofit2 = new Retrofit.Builder()
                .baseUrl("http://" + ipServer + ":8080")
                .addConverterFactory(GsonConverterFactory.create()).build();
        clienteApi = retrofit.create(IClienteApi.class);
        clienteApiGestion = retrofit2.create(IClienteApiGestion.class);
    }

    public void initListeners() {
        try {
            clienteApi.initListeners().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CarmenDTO getCarmenDtoOficialAuto(String codigo, String avance) {
        try {
            return clienteApi.getCarmenDtoOficialAuto(codigo, avance).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CarmenDTO getCarmenDtoOficialMuni(String codigo, String avance) {
        try {
            return clienteApi.getCarmenDtoOficialMuni(codigo, avance).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CarmenDTO getCarmenDtoSondeoAuto(String codigo, String avance) {
        try {
            return clienteApi.getCarmenDtoSondeoAuto(codigo, avance).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CarmenDTO getCarmenDtoSondeoMuni(String codigo, String avance) {
        try {
            return clienteApi.getCarmenDtoSondeoMuni(codigo, avance).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Circunscripcion> filtradasPorMostrarMuni(String codigo) {
        try {
            return clienteApi.filtradasPorMostrarMuni(codigo).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Circunscripcion> filtradasPorMostrarAuto(String codigo) {
        try {
            return clienteApi.filtradasPorMostrarAuto(codigo).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Circunscripcion getCircunscripcionPorId(String codigo) {
        try {
            return clienteApi.getCircunscripcionPorId(codigo).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DbActualResponse getDbActual() {
        try {
            return clienteApiGestion.getDbActual().execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraLateralAutonomicas() {
        try {
            clienteApi.entraLateralAutonomicas().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void descargarSondeoEspecialCsvAuto(String codigo, String avance) {
        try {
            clienteApi.descargarSondeoEspecialCsvAuto(codigo, avance).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void descargarSondeoEspecialCsvMuni(String codigo, String avance) {
        try {
            clienteApi.descargarSondeoEspecialCsvMuni(codigo, avance).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraLateralMunicipales() {
        try {
            clienteApi.entraLateralMunicipales().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void despliegaLateralAutonomicas(String codigo) {
        try {
            clienteApi.despliegaLateralAutonomicas(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void despliegaLateralMunicipales(String codigo) {
        try {
            clienteApi.despliegaLateralMunicipales(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void repliegaLateralAutonomicas(String codigo) {
        try {
            clienteApi.repliegaLateralAutonomicas(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void repliegaLateralMunicipales(String codigo) {
        try {
            clienteApi.repliegaLateralMunicipales(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizaLateralAutonomicas(String codigo) {
        try {
            clienteApi.actualizaUnoLateralAutonomicas(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizaLateralMunicipales(String codigo) {
        try {
            clienteApi.actualizaUnoLateralMunicipales(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleLateralAutonomicas() {
        try {
            clienteApi.saleLateralAutonomicas().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleLateralMunicipales() {
        try {
            clienteApi.saleLateralMunicipales().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraFaldonAuto() {
        try {
            clienteApi.entraFaldonAutonomicas().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleFaldonAuto() {
        try {
            clienteApi.saleFaldonAutonomicas().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraFaldonAutoSondeo() {
        try {
            clienteApi.entraFaldonAutonomicasSondeo().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleFaldonAutoSondeo() {
        try {
            clienteApi.saleFaldonAutonomicasSondeo().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizaFaldonAuto() {
        try {
            clienteApi.actualizaFaldonAutonomicas().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void encadenaFaldonAuto() {
        try {
            clienteApi.encadenaFaldonAutonomicas().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deAutoaMuniFaldonAuto() {
        try {
            clienteApi.deAutoaMuniFaldonAutonomicas().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deAutoSondeoAMuniSondeo() {
        try {
            clienteApi.deAutoSondeoAMuniSondeo().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deAutoSondeoAMuni() {
        try {
            clienteApi.deAutoSondeoAMuni().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deAutoSondeoAAuto() {
        try {
            clienteApi.deAutoSondeoAAuto().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void entraFaldonMuni() {
        try {
            clienteApi.entraFaldonMunicipales().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleFaldonMuni() {
        try {
            clienteApi.saleFaldonMunicipales().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraFaldonMuniSondeo() {
        try {
            clienteApi.entraFaldonMunicipalesSondeo().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleFaldonMuniSondeo() {
        try {
            clienteApi.saleFaldonMunicipalesSondeo().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizaFaldonMuni() {
        try {
            clienteApi.actualizaFaldonMunicipales().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void encadenaFaldonMuni() {
        try {
            clienteApi.encadenaFaldonMunicipales().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void encadenaFaldonMunicipalesSondeo() {
        try {
            clienteApi.encadenaFaldonMunicipalesSondeo().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void encadenaFaldonAutonomicasSondeo() {
        try {
            clienteApi.encadenaFaldonSondeoAutonomicas().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deMuniaAutoFaldonMuni() {
        try {
            clienteApi.deMuniaAutoFaldonMunicipales().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deMuniSondeoAAutoSondeo() {
        try {
            clienteApi.deMuniSondeoAAutoSondeo().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deMuniSondeoAMuni() {
        try {
            clienteApi.deMuniSondeoAMuni().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deMuniSondeoAAuto() {
        try {
            clienteApi.deMuniSondeoAAuto().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deMuniASondeoAuto() {
        try {
            clienteApi.deMuniASondeoAuto().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void loadAutonomicas() {
        try {
            clienteApi.loadAutonomicas().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadMunicipales() {
        try {
            clienteApi.loadMunicipales().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //PACTOS

    public void entraPactos() {
        try {
            clienteApi.entraPactos().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void salePactos() {
        try {
            clienteApi.salePactos().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reinicioPactos() {
        try {
            clienteApi.reinicioPactos().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraIzqPactos(int posicion) {
        try {
            clienteApi.entraIzqPactos(posicion).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraDerPactos(int posicion) {
        try {
            clienteApi.entraDerPactos(posicion).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //FALDON SEDES
    public void faldonSedesEntra() {
        try {
            clienteApi.faldonSedesEntra().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void faldonSedesSale() {
        try {
            clienteApi.faldonSedesSale().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //FALDON VOTANTES

    public void faldonVotantesEntra() {
        try {
            clienteApi.faldonVotantesEntra().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void faldonVotantesHistEntra() {
        try {
            clienteApi.faldonVotantesHistEntra().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void faldonVotantesSale() {
        try {
            clienteApi.faldonVotantesSale().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //CARTON PARTICIPACION

    public void entraParticipacionAuto() {
        try {
            clienteApi.entraParticipacionAuto().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraParticipacionEspAuto() {
        try {
            clienteApi.entraParticipacionEspAuto().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraParticipacionAutoDelay() {
        try {
            clienteApi.entraParticipacionAutoDelay().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraParticipacionMuni() {
        try {
            clienteApi.entraParticipacionMuni().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraParticipacionEspMuni() {
        try {
            clienteApi.entraParticipacionEspMuni().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraParticipacionMuniDelay() {
        try {
            clienteApi.entraParticipacionMuniDelay().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleParticipacionAuto() {
        try {
            clienteApi.saleParticipacionAuto().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleParticipacionEspAuto() {
        try {
            clienteApi.saleParticipacionEspAuto().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleParticipacionEspMuni() {
        try {
            clienteApi.saleParticipacionEspMuni().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleParticipacionMuni() {
        try {
            clienteApi.saleParticipacionMuni().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cambiaParticipacionComunidad() {
        try {
            clienteApi.cambiaParticipacionComunidad().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cambiaParticipacionMunicipio() {
        try {
            clienteApi.cambiaParticipacionMunicipio().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraResultadosAuto() {
        try {
            clienteApi.entraResultadosAuto().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraResultadosAutoDelay() {
        try {
            clienteApi.entraResultadosAutoDelay().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraParticipacionEspAutoDelay() {
        try {
            clienteApi.entraParticipacionEspAutoDelay().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraParticipacionEspMuniDelay() {
        try {
            clienteApi.entraParticipacionEspMuniDelay().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraResultadosMuni() {
        try {
            clienteApi.entraResultadosMuni().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraResultadosMuniDelay() {
        try {
            clienteApi.entraResultadosMuniDelay().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void saleResultadosAuto() {
        try {
            clienteApi.saleResultadosAuto().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleResultadosMuni() {
        try {
            clienteApi.saleResultadosMuni().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cambiaResultadosComunidad() {
        try {
            clienteApi.cambiaResultadosComunidad().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cambiaResultadosMunicipio() {
        try {
            clienteApi.cambiaResultadosMunicipio().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraSondeoResultadosAuto() {
        try {
            clienteApi.entraSondeoResultadosAuto().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraSondeoResultadosAutoDelay() {
        try {
            clienteApi.entraSondeoResultadosAutoDelay().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraSondeoResultadosMuni() {
        try {
            clienteApi.entraSondeoResultadosMuni().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraSondeoResultadosMuniDelay() {
        try {
            clienteApi.entraSondeoResultadosMuniDelay().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleSondeoResultadosAuto() {
        try {
            clienteApi.saleSondeoResultadosAuto().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleSondeoResultadosMuni() {
        try {
            clienteApi.saleSondeoResultadosMuni().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cambiaSondeoResultadosComunidad() {
        try {
            clienteApi.cambiaSondeoResultadosComunidad().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cambiaSondeoResultadosMunicipio() {
        try {
            clienteApi.cambiaSondeoResultadosMunicipio().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //CARTONES ARCOS

    public void entraArcoAuto() {
        try {
            clienteApi.arcoEntraAuto().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraArcoAutoDelay() {
        try {
            clienteApi.arcoEntraAutoDelay().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleArcoAuto() {
        try {
            clienteApi.arcoSaleAuto().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void pactosArcoAuto() {
        try {
            clienteApi.arcoPactosAuto().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraArcoAutoSondeo() {
        try {
            clienteApi.arcoSondeoEntraAuto().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraArcoAutoSondeoDelay() {
        try {
            clienteApi.arcoSondeoEntraAutoDelay().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleArcoAutoSondeo() {
        try {
            clienteApi.arcoSondeoSaleAuto().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void pactosArcoAutoSondeo() {
        try {
            clienteApi.arcoSondeoPactosAuto().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraArcoMuni() {
        try {
            clienteApi.arcoEntraMuni().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraArcoMuniDelay() {
        try {
            clienteApi.arcoEntraMuniDelay().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleArcoMuni() {
        try {
            clienteApi.arcoSaleMuni().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void pactosArcoMuni() {
        try {
            clienteApi.arcoPactosMuni().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraArcoMuniSondeo() {
        try {
            clienteApi.arcoSondeoEntraMuni().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraArcoMuniSondeoDelay() {
        try {
            clienteApi.arcoSondeoEntraMuniDelay().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleArcoMuniSondeo() {
        try {
            clienteApi.arcoSondeoSaleMuni().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void pactosArcoMuniSondeo() {
        try {
            clienteApi.arcoSondeoPactosMuni().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetArcoAuto() {
        try {
            clienteApi.resetArcoAuto().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetArcoMuni() {
        try {
            clienteApi.resetArcoMuni().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //ENTRADA Y SALIDA DE PARTIDOS EN ARCO

    public void entraPartidoIzqOficialAuto(String circunscripcion, String partido) {
        try {
            clienteApi.entraPartidoIzqOficialAuto(circunscripcion, partido).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraPartidoIzqPrincipalesAuto(String circunscripcion, String partido) {
        try {
            clienteApi.entraPartidoIzqPrincipalesAuto(circunscripcion, partido).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraPartidoIzqSondeoAuto(String circunscripcion, String partido) {
        try {
            clienteApi.entraPartidoIzqSondeoAuto(circunscripcion, partido).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraPartidoIzqOficialMuni(String circunscripcion, String partido) {
        try {
            clienteApi.entraPartidoIzqOficialMuni(circunscripcion, partido).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraPartidoIzqPrincipalesMuni(String circunscripcion, String partido) {
        try {
            clienteApi.entraPartidoIzqPrincipalesMuni(circunscripcion, partido).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraPartidoIzqSondeoMuni(String circunscripcion, String partido) {
        try {
            clienteApi.entraPartidoIzqSondeoMuni(circunscripcion, partido).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //ENTRA DERECHA

    public void entraPartidoDerOficialAuto(String circunscripcion, String partido) {
        try {
            clienteApi.entraPartidoDerOficialAuto(circunscripcion, partido).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraPartidoDerPrincipalesAuto(String circunscripcion, String partido) {
        try {
            clienteApi.entraPartidoDerPrincipalesAuto(circunscripcion, partido).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraPartidoDerSondeoAuto(String circunscripcion, String partido) {
        try {
            clienteApi.entraPartidoDerSondeoAuto(circunscripcion, partido).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraPartidoDerOficialMuni(String circunscripcion, String partido) {
        try {
            clienteApi.entraPartidoDerOficialMuni(circunscripcion, partido).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraPartidoDerPrincipalesMuni(String circunscripcion, String partido) {
        try {
            clienteApi.entraPartidoDerPrincipalesMuni(circunscripcion, partido).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraPartidoDerSondeoMuni(String circunscripcion, String partido) {
        try {
            clienteApi.entraPartidoDerSondeoMuni(circunscripcion, partido).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //BORRAR

    public void borrarPartido(String circunscripcion, String partido, int tipoArco, int izquierda) {
        try {
            clienteApi.borrarPartido(circunscripcion, partido, tipoArco, izquierda).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //SELECTED

    public void selectCircunscripcionAutonomiaOficialAuto(String codigo, String avance) {
        try {
            clienteApi.selectCircunscripcionAutonomiaOficialAuto(codigo, avance).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectCircunscripcionMapaOficialAuto(String codigo, String avance) {
        try {
            clienteApi.selectCircunscripcionMapaOficialAuto(codigo, avance).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectCircunscripcionAutnomiaSondeoAuto(String codigo, String avance) {
        try {
            clienteApi.selectCircunscripcionAutnomiaSondeoAuto(codigo, avance).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectCircunscripcionMunicipioSondeoMuni(String codigo, String avance) {
        try {
            clienteApi.selectCircunscripcionMunicipioSondeoMuni(codigo, avance).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectCircunscripcionMapaSondeoAuto(String codigo, String avance) {
        try {
            clienteApi.selectCircunscripcionMapaSondeoAuto(codigo, avance).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectCircunscripcionAutonomiaOficialMuni(String codigo, String avance) {
        try {
            clienteApi.selectCircunscripcionAutonomiaOficialMuni(codigo, avance).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectCircunscripcionMapaOficialMuni(String codigo, String avance) {
        try {
            clienteApi.selectCircunscripcionMapaOficialMuni(codigo, avance).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectCircunscripcionAutnomiaSondeoMuni(String codigo, String avance) {
        try {
            clienteApi.selectCircunscripcionAutnomiaSondeoMuni(codigo, avance).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectCircunscripcionAutnomiaOficialMuni(String codigo, String avance) {
        try {
            clienteApi.selectCircunscripcionAutnomiaOficialMuni(codigo, avance).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectCircunscripcionMapaSondeoMuni(String codigo, String avance) {
        try {
            clienteApi.selectCircunscripcionMapaSondeoMuni(codigo, avance).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void descargarResultadosCsvAutoOficial(String codigo) {
        try {
            clienteApi.descargarResultadosCsvAutoOficial(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void descargarResultadosCsvAutoSondeo(String codigo) {
        try {
            clienteApi.descargarResultadosCsvAutoSondeo(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void descargarResultadosCsvMuniOficial(String codigo) {
        try {
            clienteApi.descargarResultadosCsvMuniOficial(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void descargarResultadosCsvMuniSondeo(String codigo) {
        try {
            clienteApi.descargarResultadosCsvMuniSondeo(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void descargarSedesCsv(String codigo) {
        try {
            clienteApi.descargarSedesCsv(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CircunscripcionPartido> getCpsEspania() {
        try {
            return clienteApi.getCpsEspania().execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Partido getPartido(String codigo) {
        try {
            return clienteApi.getPartido(codigo).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //RESET

    public void resetIPF() {
        try {
            clienteApi.resetIPFAuto().execute();
            clienteApi.resetIPFMuni().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // CAMBIOS ENTRE BD

    public void conectPrincipal() {
        try {
            clienteApi.conectPrincipal().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void conectReserva() {
        try {
            clienteApi.conectReserva().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void conectLocal() {
        try {
            clienteApi.conectLocal().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        try {
            clienteApi.update().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateEspania(String avance) {
        try {
            clienteApi.updateEspania(avance).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
