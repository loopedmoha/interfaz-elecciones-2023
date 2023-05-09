package com.mycompany.elecciones2023datos.services;

import com.mycompany.elecciones2023datos.DTO.CarmenDTO;
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

    @GET("/autonomicas/carmen/data/{codigo}")
    Call<CarmenDTO> getCarmenDto(@Path("codigo") String codigo);

    //MUNICIPALES
    @GET("/municipales/circunscripciones/municipios/{codigo}")
    Call<List<Circunscripcion>> getMunicipiosByCodigo(@Path("codigo") String codigo);

    @GET("/municipales/carmen/oficial/{codigo}/data")
    Call<CarmenDTO> getCarmenDtoOficial(@Path("codigo") String codigo);

    @GET("/municipales/carmen/sondeo/{codigo}/data")
    Call<CarmenDTO> getCarmenDtoSondeo(@Path("codigo") String codigo);


    //---------------- IPF ------------------


    /*
     ****************LATERAL
     */
    @GET("/autonomicas/carmen/lateral/entra")
    Call<String> entraLateralAutonomicas();

    @GET("/municipales/carmen/lateral/entra")
    Call<String> entraLateralMunicipales();

    @GET("/autonomicas/carmen/lateral/{codigo}/despliega")
    Call<String> despliegaLateralAutonomicas(@Path("codigo") String codigo);

    @GET("/municipales/carmen/lateral/{codigo}/despliega")
    Call<String> despliegaLateralMunicipales(@Path("codigo") String codigo);

    @GET("/autonomicas/carmen/lateral/{codigo}/repliega")
    Call<String> repliegaLateralAutonomicas(@Path("codigo") String codigo);

    @GET("/municipales/carmen/lateral/{codigo}/repliega")
    Call<String> repliegaLateralMunicipales(@Path("codigo") String codigo);


    @GET("/autonomicas/carmen/lateral/actualiza")
    Call<String> actualizaLateralAutonomicas(@Path("codigo") String codigo);

    @GET("/municipales/carmen/lateral/actualiza")
    Call<String> actualizaLateralMunicipales();

    @GET("/autonomicas/carmen/lateral/{codigo}/actualiza")
    Call<String> actualizaUnoLateralAutonomicas(@Path("codigo") String codigo);

    @GET("/municipales/carmen/lateral/{codigo}/actualiza")
    Call<String> actualizaUnoLateralMunicipales(@Path("codigo") String codigo);


    @GET("/autonomicas/carmen/lateral/sale")
    Call<String> saleLateralAutonomicas();

    @GET("/municipales/carmen/lateral/sale")
    Call<String> saleLateralMunicipales();

    /*
     ****************LATERAL
     */


    @GET("/autonomicas/carmen/faldon/entra")
    Call<String> entraFaldonAutonomicas();

    @GET("/autonomicas/carmen/faldon/sale")
    Call<String> saleFaldonAutonomicas();

    /*
     **************** CARTONES ARCOS
     */
    @GET("/autonomicas/arco/load")
    Call<String> loadArcoAutonomicas();

    @GET("/municipales/arco/load")
    Call<String> loadArcoMunicipales();

    @GET("/autonomicas/circunscripciones/selected/{codigo}")
    Call<String> selectedAutonomicas(@Path("codigo") String codigo);

    @GET("/municipales/circunscripciones/selected/{codigo}")
    Call<String> selectedMunicipales(@Path("codigo") String codigo);

}
