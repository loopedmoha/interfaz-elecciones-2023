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
    @GET("/autonomicas/carmen/{codigo}/entra")
    Call<String> entraLateralAutonomica(@Path("codigo") String codigo);

    @GET("/municipales/carmen/{codigo}/entra")
    Call<String> entraLateralMunicipal(@Path("codigo") String codigo);


    @GET("/autonomicas/carmen/{codigo}/actualiza")
    Call<String> actualizaLateralAutonomica(@Path("codigo") String codigo);

    @GET("/municipales/carmen/{codigo}/actualiza")
    Call<String> actualizaLateralMunicipal(@Path("codigo") String codigo);


    @GET("/autonomicas/carmen/{codigo}/sale")
    Call<String> saleLateralAutonomica(@Path("codigo") String codigo);

    @GET("/municipales/carmen/{codigo}/entra")
    Call<String> saleLateralMunicipal(@Path("codigo") String codigo);

    /*
     ****************LATERAL
     */


    @GET("/autonomicas/carmen/carton/entra")
    Call<String> entraFaldonAutonomico();

    @GET("/autonomicas/carmen/carton/sale")
    Call<String> saleFaldonAutonomico();
}
