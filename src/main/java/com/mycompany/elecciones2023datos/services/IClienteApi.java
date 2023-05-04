package com.mycompany.elecciones2023datos.services;

import com.mycompany.elecciones2023datos.model.Circunscripcion;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface IClienteApi {
    @GET("/autonomicas/circunscripciones/autonomias")
    Call<List<Circunscripcion>> getAllCircunscripciones();
    @GET("/autonomicas/circunscripciones/autonomias/{codigo}")
    Call<List<Circunscripcion>> getCircunscripcionesByAutonomia(@Path("codigo") String codigo);
    @GET("/autonomicas/circunscripciones/selected/{codigo}")
    Call<Circunscripcion> getByCodigo(@Path("codigo") String codigo);



}
