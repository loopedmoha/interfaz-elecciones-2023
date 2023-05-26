package com.mycompany.elecciones2023datos.services;

import retrofit2.Retrofit;

import java.io.IOException;
import java.util.Properties;

import static com.mycompany.elecciones2023datos.config.cargarConfiguracion;

public class ClienteApiGestion {
    private static ClienteApiGestion instance = null;

    private Retrofit retrofit = null;


    private static IClienteApiGestion clienteApi;

    private ClienteApiGestion() {
        Properties propiedades = null;
        try {
            propiedades = cargarConfiguracion();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String ipServer = propiedades.getProperty("ipServer");
        retrofit = new Retrofit.Builder().baseUrl("http://" + ipServer + ":8080").build();
        clienteApi = retrofit.create(IClienteApiGestion.class);
    }

    public static ClienteApiGestion getInstance() {
        if (instance == null)
            instance = new ClienteApiGestion();
        return instance;
    }

    public static IClienteApiGestion getCliente() {
        return clienteApi;

    }
}
