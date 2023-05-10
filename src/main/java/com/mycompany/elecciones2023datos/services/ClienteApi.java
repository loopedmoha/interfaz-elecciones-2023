package com.mycompany.elecciones2023datos.services;

import retrofit2.Retrofit;

public class ClienteApi {
    private static ClienteApi instance = null;
    private Retrofit retrofit = null;
    private static IClienteApi clienteApi;

    private ClienteApi() {
        retrofit = new Retrofit.Builder().baseUrl("http://10.10.55.220:9090").build();
        clienteApi = retrofit.create(IClienteApi.class);
    }

    public static ClienteApi getInstance() {
        if (instance == null)
            instance = new ClienteApi();
        return instance;
    }

    public static IClienteApi getCliente(){
        return clienteApi;

    }
}
