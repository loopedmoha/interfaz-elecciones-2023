package com.mycompany.elecciones2023datos.services;

import retrofit2.Call;
import retrofit2.http.POST;

public interface IClienteApiGestion {
    @POST("/shutdownContext")
    Call<String> cerrarGestion();
}
