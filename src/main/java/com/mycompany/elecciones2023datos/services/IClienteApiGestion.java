package com.mycompany.elecciones2023datos.services;

import com.mycompany.elecciones2023datos.model.DbActualResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IClienteApiGestion {
    @POST("/shutdownContext")
    Call<String> cerrarGestion();

    @GET("/dbactual")
    Call<DbActualResponse> getDbActual();
    @GET("/avance/{codigo}")
    Call<String> setAvance(@Path("codigo") String codigo);

}
