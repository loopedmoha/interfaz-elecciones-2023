package com.mycompany.elecciones2023datos.services;

import com.mycompany.elecciones2023datos.DTO.CarmenDTO;
import com.mycompany.elecciones2023datos.model.Circunscripcion;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface IClienteApi {

    //Leer las autonomias
    @GET("/autonomicas/circunscripciones/autonomias")
    Call<List<Circunscripcion>> getAllAutonomiasAuto();

    @GET("/municipales/circunscripciones/autonomias")
    Call<List<Circunscripcion>> getAllAutonomiasMuni();


    //Leer autonomicas oficial y municipales oficial
    @GET("/autonomicas/carmen/oficial/{codigo}/data")
    Call<CarmenDTO> getCarmenDtoOficialAuto(@Path("codigo") String codigo);

    @GET("/municipales/carmen/oficial/{codigo}/data")
    Call<CarmenDTO> getCarmenDtoOficialMuni(@Path("codigo") String codigo);

    //Leer municipales oficial y autonomicas oficial
    @GET("/autonomicas/carmen/sondeo/{codigo}/data")
    Call<CarmenDTO> getCarmenDtoSondeoAuto(@Path("codigo") String codigo);

    @GET("/municipales/carmen/sondeo/{codigo}/data")
    Call<CarmenDTO> getCarmenDtoSondeoMuni(@Path("codigo") String codigo);

    @GET("/autonomicas/circunscripciones/autonomias/{codigo}")
    Call<List<Circunscripcion>> getCircunscripcionesByAutonomia(@Path("codigo") String codigo);

    //MUNICIPALES
    @GET("/municipales/circunscripciones/municipios/{codigo}")
    Call<List<Circunscripcion>> getMunicipiosByCodigo(@Path("codigo") String codigo);


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
     ****************CARTON PARTICIPACION
     */

    @GET("/autonomicas/participacion/entra")
    Call<String> entraParticipacionAuto();

    @GET("/municipales/participacion/entra")
    Call<String> entraParticipacionMuni();

    @GET("/autonomicas/participacion/cambia")
    Call<String> cambiaParticipacionComunidad();

    @GET("/municipales/participacion/cambia")
    Call<String> cambiaParticipacionMunicipio();

    @GET("/autonomicas/participacion/sale")
    Call<String> saleParticipacionAuto();

    @GET("/municipales/participacion/sale")
    Call<String> saleParticipacionMuni();

    /*
     ****************CARTON RESULTADOS
     */

    @GET("/autonomicas/resultados/entra")
    Call<String> entraResultadosAuto();

    @GET("/municipales/resultados/entra")
    Call<String> entraResultadosMuni();

    @GET("/autonomicas/resultados/cambia")
    Call<String> cambiaResultadosComunidad();

    @GET("/municipales/resultados/cambia")
    Call<String> cambiaResultadosMunicipio();

    @GET("/autonomicas/resultados/sale")
    Call<String> saleResultadosAuto();

    @GET("/municipales/resultados/sale")
    Call<String> saleResultadosMuni();

    //Sondeo
    @GET("/autonomicas/resultados/sondeo/entra")
    Call<String> entraSondeoResultadosAuto();

    @GET("/municipales/resultados/sondeo/entra")
    Call<String> entraSondeoResultadosMuni();

    @GET("/autonomicas/resultados/sondeo/cambia")
    Call<String> cambiaSondeoResultadosComunidad();

    @GET("/municipales/resultados/sondeo/cambia")
    Call<String> cambiaSondeoResultadosMunicipio();

    @GET("/autonomicas/resultados/sondeo/sale")
    Call<String> saleSondeoResultadosAuto();

    @GET("/municipales/resultados/sondeo/sale")
    Call<String> saleSondeoResultadosMuni();

    /*
     **************** CARTONES ARCOS
     */
    @GET("/autonomicas/load")
    Call<String> loadAutonomicas();

    @GET("/autonomicas/arco/entra")
    Call<String> arcoEntraAuto();

    @GET("/autonomicas/arco/sale")
    Call<String> arcoSaleAuto();

    @GET("/autonomicas/arco/pactos")
    Call<String> arcoPactosAuto();

    @GET("/autonomicas/arco/sondeo/entra")
    Call<String> arcoSondeoEntraAuto();

    @GET("/autonomicas/arco/sondeo/sale")
    Call<String> arcoSondeoSaleAuto();

    @GET("/autonomicas/arco/sondeo/pactos")
    Call<String> arcoSondeoPactosAuto();

    @GET("/municipales/load")
    Call<String> loadMunicipales();

    @GET("/autonomicas/arco/entra")
    Call<String> arcoEntraMuni();

    @GET("/autonomicas/arco/sale")
    Call<String> arcoSaleMuni();

    @GET("/autonomicas/arco/pactos")
    Call<String> arcoPactosMuni();

    @GET("/autonomicas/arco/sondeo/entra")
    Call<String> arcoSondeoEntraMuni();

    @GET("/autonomicas/arco/sondeo/sale")
    Call<String> arcoSondeoSaleMuni();

    @GET("/autonomicas/arco/sondeo/pactos")
    Call<String> arcoSondeoPactosMuni();


    /*
     **************** FALDON INFERIOR
     */
    @GET("/autonomicas/carmen/faldon/entra")
    Call<String> entraFaldonAutonomicas();

    @GET("/autonomicas/carmen/faldon/sale")
    Call<String> saleFaldonAutonomicas();

    @GET("/autonomicas/carmen/faldon/actualiza")
    Call<String> actualizaFaldonAutonomicas();

    @GET("/autonomicas/carmen/faldon/encadena")
    Call<String> encadenaFaldonAutonomicas();

    @GET("/autonomicas/carmen/faldon/deAutoaMuni")
    Call<String> deAutoaMuniFaldonAutonomicas();

    @GET("/autonomicas/carmen/faldon/deAutoSondeoAMuniSondeo")
    Call<String> deAutoSondeoAMuniSondeo();

    @GET("/autonomicas/carmen/faldon/deAutoSondeoAMuni")
    Call<String> deAutoSondeoAMuni();

    @GET("/autonomicas/carmen/faldon/deAutoSondeoAAuto")
    Call<String> deAutoSondeoAAuto();

    @GET("/municipales/carmen/faldon/entra")
    Call<String> entraFaldonMunicipales();

    @GET("/municipales/carmen/faldon/sale")
    Call<String> saleFaldonMunicipales();

    @GET("/municipales/carmen/faldon/actualiza")
    Call<String> actualizaFaldonMunicipales();

    @GET("/municipales/carmen/faldon/encadena")
    Call<String> encadenaFaldonMunicipales();

    @GET("/municipales/carmen/faldon/deMuniaAuto")
    Call<String> deMuniaAutoFaldonMunicipales();

    @GET("/municipales/carmen/faldon/deMuniSondeoAAutoSondeo")
    Call<String> deMuniSondeoAAutoSondeo();

    @GET("/municipales/carmen/faldon/deMuniSondeoAMuni")
    Call<String> deMuniSondeoAMuni();

    @GET("/municipales/carmen/faldon/deMuniSondeoAAuto")
    Call<String> deMuniSondeoAAuto();

    //DESCARGA DE ARCHIVOS
    @GET("/autonomicas/circunscripciones/selected/oficial/{codigo}")
    Call<String> selectedAutonomicasOficiales(@Path("codigo") String codigo);

    @GET("/autonomicas/circunscripciones/selected/sondeo/{codigo}")
    Call<String> selectedAutonomicasSondeo(@Path("codigo") String codigo);

    @GET("/municipales/circunscripciones/selected/oficial/{codigo}")
    Call<String> selectedMunicipalesOficiales(@Path("codigo") String codigo);

    @GET("/municipales/circunscripciones/selected/sondeo/{codigo}")
    Call<String> selectedMunicipalesSondeo(@Path("codigo") String codigo);

    //RESET
    @GET("/municipales/reset")
    Call<String> resetIPFMuni();

    @GET("/autonomicas/reset")
    Call<String> resetIPFAuto();

}
