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

    public void loadArcoAutonomicas() {
        try {
            clienteApi.loadArcoAutonomicas().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadArcoMunicipales() {
        try {
            clienteApi.loadArcoMunicipales().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectedAutonomicas(String codigo) {
        try {
            clienteApi.selectedAutonomicas(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectedMunicipales(String codigo) {
        try {
            clienteApi.selectedMunicipales(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
