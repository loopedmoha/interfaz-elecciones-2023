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


    public void entraLateralAutonomica(String codigo) {
        String res = null;
        try {
            res = clienteApi.entraLateralAutonomica(codigo).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(res);
    }

    public void entraLateralMunicipal(String codigo) {
        try {
            clienteApi.entraLateralMunicipal(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleLateralAutonomica(String codigo) {
        try {
            clienteApi.saleLateralAutonomica(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saleLateralMunicipal(String codigo) {
        try {
            clienteApi.saleLateralMunicipal(codigo).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entraFaldonAuto() {
        try {
            clienteApi.entraFaldonAutonomico().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saleFaldonAuto() {
        try {
            clienteApi.saleFaldonAutonomico().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
