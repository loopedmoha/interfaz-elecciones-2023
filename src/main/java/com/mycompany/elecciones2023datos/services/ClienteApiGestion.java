package com.mycompany.elecciones2023datos.services;

import retrofit2.Retrofit;

public class ClienteApiGestion {
    private static ClienteApiGestion instance = null;

    private Retrofit retrofit = null;

    private static IClienteApiGestion clienteApi;

    private ClienteApiGestion() {
        retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080").build();
        clienteApi = retrofit.create(IClienteApiGestion.class);
    }

    public static ClienteApiGestion getInstance() {
        if (instance == null)
            instance = new ClienteApiGestion();
        return instance;
    }

    public static IClienteApiGestion getCliente(){
        return clienteApi;

    }
}
