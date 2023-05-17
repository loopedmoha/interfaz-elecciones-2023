package com.mycompany.elecciones2023datos.controllers;

import com.mycompany.elecciones2023datos.services.IClienteApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class GraficosController {
    Retrofit retrofit;
    IClienteApi clienteApi;

    public GraficosController() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:9090")
                .addConverterFactory(GsonConverterFactory.create()).build();
        clienteApi = retrofit.create(IClienteApi.class);
    }


    public void entraLateralAutonomicas() {
        try {
            clienteApi.entraLateralAutonomicas().execute();
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

    //SELECTED

    public void selectedAutonomicasOficiales(String codigo) {
        try {
            clienteApi.selectedAutonomicasOficiales(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectedAutonomicasSondeo(String codigo) {
        try {
            clienteApi.selectedAutonomicasSondeo(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectedMunicipalesOficiales(String codigo) {
        try {
            clienteApi.selectedMunicipalesOficiales(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectedMunicipalesSondeo(String codigo) {
        try {
            clienteApi.selectedMunicipalesSondeo(codigo).execute();
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


}
