package com.mycompany.elecciones2023datos.services;

import com.mycompany.elecciones2023datos.model.DbActualResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IClienteApiGestion {
    @POST("/shutdownContext")
    Call<String> cerrarGestion();

    @GET("/dbactual")
    Call<DbActualResponse> getDbActual();

}
