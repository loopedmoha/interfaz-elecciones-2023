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


    public void entraLateralAutonomicas(String codigo) {
        String res = null;
        try {
            res = clienteApi.entraLateralAutonomicas(codigo).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(res);
    }

    public void entraLateralMunicipales(String codigo) {
        try {
            clienteApi.entraLateralMunicipales(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleLateralAutonomicas(String codigo) {
        try {
            clienteApi.saleLateralAutonomicas(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleLateralMunicipales(String codigo) {
        try {
            clienteApi.saleLateralMunicipales(codigo).execute();
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
