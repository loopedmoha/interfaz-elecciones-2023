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

    public void entraParticipacionAuto() {
        try {
            clienteApi.entraParticipacionAuto().execute();
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

    public void saleParticipacionAuto() {
        try {
            clienteApi.saleParticipacionAuto().execute();
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
            clienteApi.cambiaParticipacionMunipio().execute();
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

    public void entraResultadosMuni() {
        try {
            clienteApi.entraResultadosMuni().execute();
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

    public void resetIPF() {
        try {
            clienteApi.resetIPFAuto().execute();
            clienteApi.resetIPFMuni().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
