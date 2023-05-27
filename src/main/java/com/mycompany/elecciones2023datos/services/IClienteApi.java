package com.mycompany.elecciones2023datos.services;

import com.mycompany.elecciones2023datos.DTO.CarmenDTO;
import com.mycompany.elecciones2023datos.model.Circunscripcion;
import com.mycompany.elecciones2023datos.model.CircunscripcionPartido;
import com.mycompany.elecciones2023datos.model.Dummy;
import com.mycompany.elecciones2023datos.model.Partido;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface IClienteApi {
    //Iniciar Listeners
    @GET("/init/listeners")
    Call<Dummy> initListeners();

    @POST("/shutdownContext")
    Call<Dummy> closeClient();

    //Leer las autonomias
    @GET("/autonomicas/circunscripciones/autonomias")
    Call<List<Circunscripcion>> getAllAutonomiasAuto();

    @GET("/autonomicas/circunscripciones/filtrada")
    Call<List<Circunscripcion>> filtradasPorMostrarAuto(@Path("codigo") String codigo);

    @GET("/municipales/circunscripciones/autonomias")
    Call<List<Circunscripcion>> getAllAutonomiasMuni();

    @GET("/municipales/circunscripciones/filtrada/{codigo}")
    Call<List<Circunscripcion>> filtradasPorMostrarMuni(@Path("codigo") String codigo);

    @GET("/municipales/circunscripciones/{codigo}/data")
    Call<Circunscripcion> getCircunscripcionPorId(@Path("codigo") String codigo);

    //Leer autonomicas oficial y municipales oficial
    @GET("/autonomicas/carmen/oficial/{codigo}/{avance}/data")
    Call<CarmenDTO> getCarmenDtoOficialAuto(@Path("codigo") String codigo, @Path("avance") String avance);

    @GET("/autonomicas/carmen/oficial/9900000/{avance}/data")
    Call<CarmenDTO> getCarmenDtoEspAuto(@Path("avance") String avance);

    @GET("/municipales/carmen/oficial/9900000/{avance}/data")
    Call<CarmenDTO> getCarmenDtoEspMuni(@Path("avance") String avance);

    @GET("/municipales/carmen/oficial/{codigo}/{avance}/data")
    Call<CarmenDTO> getCarmenDtoOficialMuni(@Path("codigo") String codigo, @Path("avance") String avance);

    //Leer municipales sondeo y autonomicas sondeo
    @GET("/autonomicas/carmen/sondeo/{codigo}/{avance}/data")
    Call<CarmenDTO> getCarmenDtoSondeoAuto(@Path("codigo") String codigo, @Path("avance") String avance);

    @GET("/municipales/carmen/sondeo/{codigo}/{avance}/data")
    Call<CarmenDTO> getCarmenDtoSondeoMuni(@Path("codigo") String codigo, @Path("avance") String avance);

    @GET("/autonomicas/circunscripciones/autonomias/{codigo}")
    Call<List<Circunscripcion>> getCircunscripcionesByAutonomia(@Path("codigo") String codigo);

    //MUNICIPALES
    @GET("/municipales/circunscripciones/municipios/{codigo}")
    Call<List<Circunscripcion>> getMunicipiosByCodigo(@Path("codigo") String codigo);

    @GET("/municipales/cp/espania")
    Call<List<CircunscripcionPartido>> getCpsEspania();

    @GET("/municipales/partidos/{codigo}")
    Call<Partido> getPartido(@Path("codigo") String codigo);


    //---------------- IPF ------------------


    /*
     ****************LATERAL
     */
    @GET("/autonomicas/carmen/lateral/entra")
    Call<Dummy> entraLateralAutonomicas();

    @GET("/municipales/carmen/lateral/entra")
    Call<Dummy> entraLateralMunicipales();

    @GET("/autonomicas/carmen/lateral/{codigo}/despliega")
    Call<Dummy> despliegaLateralAutonomicas(@Path("codigo") String codigo);

    @GET("/municipales/carmen/lateral/{codigo}/despliega")
    Call<Dummy> despliegaLateralMunicipales(@Path("codigo") String codigo);

    @GET("/autonomicas/carmen/lateral/{codigo}/repliega")
    Call<Dummy> repliegaLateralAutonomicas(@Path("codigo") String codigo);

    @GET("/municipales/carmen/lateral/{codigo}/repliega")
    Call<Dummy> repliegaLateralMunicipales(@Path("codigo") String codigo);


    @GET("/autonomicas/carmen/lateral/actualiza")
    Call<Dummy> actualizaLateralAutonomicas(@Path("codigo") String codigo);

    @GET("/municipales/carmen/lateral/actualiza")
    Call<Dummy> actualizaLateralMunicipales();

    @GET("/autonomicas/carmen/lateral/{codigo}/actualiza")
    Call<Dummy> actualizaUnoLateralAutonomicas(@Path("codigo") String codigo);

    @GET("/municipales/carmen/lateral/{codigo}/actualiza")
    Call<Dummy> actualizaUnoLateralMunicipales(@Path("codigo") String codigo);


    @GET("/autonomicas/carmen/lateral/sale")
    Call<Dummy> saleLateralAutonomicas();

    @GET("/municipales/carmen/lateral/sale")
    Call<Dummy> saleLateralMunicipales();

    /*
     ****************CARTON PARTICIPACION
     */

    @GET("/autonomicas/participacion/entra")
    Call<Dummy> entraParticipacionAuto();

    @GET("/autonomicas/participacion/entra/delay")
    Call<Dummy> entraParticipacionAutoDelay();

    @GET("/municipales/participacion/entra")
    Call<Dummy> entraParticipacionMuni();

    @GET("/municipales/participacion/entra/delay")
    Call<Dummy> entraParticipacionMuniDelay();

    @GET("/autonomicas/participacion/cambia")
    Call<Dummy> cambiaParticipacionComunidad();

    @GET("/municipales/participacion/cambia")
    Call<Dummy> cambiaParticipacionMunicipio();

    @GET("/autonomicas/participacion/sale")
    Call<Dummy> saleParticipacionAuto();

    @GET("/municipales/participacion/sale")
    Call<Dummy> saleParticipacionMuni();

    @GET("/autonomicas/participacion/entra/esp")
    Call<Dummy> entraParticipacionEspAuto();

    @GET("/autonomicas/participacion/entra/esp/delay")
    Call<Dummy> entraParticipacionEspAutoDelay();

    @GET("/municipales/participacion/entra/esp")
    Call<Dummy> entraParticipacionEspMuni();

    @GET("/municipales/participacion/entra/esp/delay")
    Call<Dummy> entraParticipacionEspMuniDelay();

    @GET("/autonomicas/participacion/sale/esp")
    Call<Dummy> saleParticipacionEspAuto();

    @GET("/municipales/participacion/sale/esp")
    Call<Dummy> saleParticipacionEspMuni();

    /*
     ****************CARTON RESULTADOS
     */

    @GET("/autonomicas/resultados/entra")
    Call<Dummy> entraResultadosAuto();

    @GET("/autonomicas/resultados/entra/delay")
    Call<Dummy> entraResultadosAutoDelay();

    @GET("/municipales/resultados/entra")
    Call<Dummy> entraResultadosMuni();

    @GET("/municipales/resultados/entra/delay")
    Call<Dummy> entraResultadosMuniDelay();

    @GET("/autonomicas/resultados/cambia")
    Call<Dummy> cambiaResultadosComunidad();

    @GET("/municipales/resultados/cambia")
    Call<Dummy> cambiaResultadosMunicipio();

    @GET("/autonomicas/resultados/sale")
    Call<Dummy> saleResultadosAuto();

    @GET("/municipales/resultados/sale")
    Call<Dummy> saleResultadosMuni();

    //Sondeo
    @GET("/autonomicas/resultados/sondeo/entra")
    Call<Dummy> entraSondeoResultadosAuto();

    @GET("/autonomicas/resultados/sondeo/entra/delay")
    Call<Dummy> entraSondeoResultadosAutoDelay();

    @GET("/municipales/resultados/sondeo/entra")
    Call<Dummy> entraSondeoResultadosMuni();

    @GET("/municipales/resultados/sondeo/entra/delay")
    Call<Dummy> entraSondeoResultadosMuniDelay();

    @GET("/autonomicas/resultados/sondeo/cambia")
    Call<Dummy> cambiaSondeoResultadosComunidad();

    @GET("/municipales/resultados/sondeo/cambia")
    Call<Dummy> cambiaSondeoResultadosMunicipio();

    @GET("/autonomicas/resultados/sondeo/sale")
    Call<Dummy> saleSondeoResultadosAuto();

    @GET("/municipales/resultados/sondeo/sale")
    Call<Dummy> saleSondeoResultadosMuni();

    /*
     **************** CARTONES ARCOS
     */
    @GET("/autonomicas/load")
    Call<Dummy> loadAutonomicas();

    @GET("/autonomicas/arco/entra")
    Call<Dummy> arcoEntraAuto();

    @GET("/autonomicas/arco/entra/delay")
    Call<Dummy> arcoEntraAutoDelay();

    @GET("/autonomicas/arco/sale")
    Call<Dummy> arcoSaleAuto();

    @GET("/autonomicas/arco/pactos")
    Call<Dummy> arcoPactosAuto();

    @GET("/autonomicas/arco/sondeo/entra")
    Call<Dummy> arcoSondeoEntraAuto();

    @GET("/autonomicas/arco/sondeo/entra/delay")
    Call<Dummy> arcoSondeoEntraAutoDelay();

    @GET("/autonomicas/arco/sondeo/sale")
    Call<Dummy> arcoSondeoSaleAuto();

    @GET("/autonomicas/arco/sondeo/pactos")
    Call<Dummy> arcoSondeoPactosAuto();

    @GET("/municipales/load")
    Call<Dummy> loadMunicipales();

    @GET("/autonomicas/arco/entra")
    Call<Dummy> arcoEntraMuni();

    @GET("/autonomicas/arco/entra/delay")
    Call<Dummy> arcoEntraMuniDelay();

    @GET("/autonomicas/arco/sale")
    Call<Dummy> arcoSaleMuni();

    @GET("/autonomicas/arco/pactos")
    Call<Dummy> arcoPactosMuni();

    @GET("/autonomicas/arco/sondeo/entra")
    Call<Dummy> arcoSondeoEntraMuni();

    @GET("/autonomicas/arco/sondeo/entra/delay")
    Call<Dummy> arcoSondeoEntraMuniDelay();

    @GET("/autonomicas/arco/sondeo/sale")
    Call<Dummy> arcoSondeoSaleMuni();

    @GET("/autonomicas/arco/sondeo/pactos")
    Call<Dummy> arcoSondeoPactosMuni();

    @GET("/autonomicas/arco/reset")
    Call<Dummy> resetArcoAuto();

    @GET("/municipales/arco/reset")
    Call<Dummy> resetArcoMuni();

    //ENTRADA Y SALIDA DE PARTIDOS
    @GET("/autonomicas/arco/oficial/{circunscripcion}/{partido}/entraIzq")
    Call<Dummy> entraPartidoIzqOficialAuto(@Path("circunscripcion") String circunscripcion, @Path("partido") String partido);

    @GET("/autonomicas/arco/principales/{circunscripcion}/{partido}/entraIzq")
    Call<Dummy> entraPartidoIzqPrincipalesAuto(@Path("circunscripcion") String circunscripcion, @Path("partido") String partido);

    @GET("/autonomicas/arco/sondeo/{circunscripcion}/{partido}/entraIzq")
    Call<Dummy> entraPartidoIzqSondeoAuto(@Path("circunscripcion") String circunscripcion, @Path("partido") String partido);

    @GET("/municipales/arco/oficial/{circunscripcion}/{partido}/entraIzq")
    Call<Dummy> entraPartidoIzqOficialMuni(@Path("circunscripcion") String circunscripcion, @Path("partido") String partido);

    @GET("/municipales/arco/principales/{circunscripcion}/{partido}/entraIzq")
    Call<Dummy> entraPartidoIzqPrincipalesMuni(@Path("circunscripcion") String circunscripcion, @Path("partido") String partido);

    @GET("/municipales/arco/sondeo/{circunscripcion}/{partido}/entraIzq")
    Call<Dummy> entraPartidoIzqSondeoMuni(@Path("circunscripcion") String circunscripcion, @Path("partido") String partido);

    @GET("/autonomicas/arco/oficial/{circunscripcion}/{partido}/entraDer")
    Call<Dummy> entraPartidoDerOficialAuto(@Path("circunscripcion") String circunscripcion, @Path("partido") String partido);

    @GET("/autonomicas/arco/principales/{circunscripcion}/{partido}/entraDer")
    Call<Dummy> entraPartidoDerPrincipalesAuto(@Path("circunscripcion") String circunscripcion, @Path("partido") String partido);

    @GET("/autonomicas/arco/sondeo/{circunscripcion}/{partido}/entraDer")
    Call<Dummy> entraPartidoDerSondeoAuto(@Path("circunscripcion") String circunscripcion, @Path("partido") String partido);

    @GET("/municipales/arco/oficial/{circunscripcion}/{partido}/entraDer")
    Call<Dummy> entraPartidoDerOficialMuni(@Path("circunscripcion") String circunscripcion, @Path("partido") String partido);

    @GET("/municipales/arco/principales/{circunscripcion}/{partido}/entraDer")
    Call<Dummy> entraPartidoDerPrincipalesMuni(@Path("circunscripcion") String circunscripcion, @Path("partido") String partido);

    @GET("/municipales/arco/sondeo/{circunscripcion}/{partido}/entraDer")
    Call<Dummy> entraPartidoDerSondeoMuni(@Path("circunscripcion") String circunscripcion, @Path("partido") String partido);

    @GET("/autonomicas/arco/{circunscripcion}/{partido}/{tipoArco}/{izquierda}/borrar")
    Call<Dummy> borrarPartido(@Path("circunscripcion") String circunscripcion, @Path("partido") String partido, @Path("tipoArco") int tipoArco, @Path("izquierda") int izquierda);


    /*
     **************** FALDON SEDES
     */
    @GET("/autonomicas/carmen/sedes/entra")
    Call<Dummy> faldonSedesEntra();

    @GET("/autonomicas/carmen/sedes/sale")
    Call<Dummy> faldonSedesSale();

    /*
     **************** FALDON VOTANTES
     */

    @GET("/municipales/carmen/votantes/entra")
    Call<Dummy> faldonVotantesEntra();

    @GET("/municipales/carmen/votantes/historico")
    Call<Dummy> faldonVotantesHistEntra();

    @GET("/municipales/carmen/votantes/sale")
    Call<Dummy> faldonVotantesSale();


    /*
     **************** FALDON INFERIOR
     */
    @GET("/autonomicas/carmen/faldon/entra")
    Call<Dummy> entraFaldonAutonomicas();

    @GET("/autonomicas/carmen/faldon/sondeo/entra")
    Call<Dummy> entraFaldonAutonomicasSondeo();

    @GET("/autonomicas/carmen/faldon/sale")
    Call<Dummy> saleFaldonAutonomicas();

    @GET("/autonomicas/carmen/faldon/sondeo/sale")
    Call<Dummy> saleFaldonAutonomicasSondeo();

    @GET("/autonomicas/carmen/faldon/actualiza")
    Call<Dummy> actualizaFaldonAutonomicas();

    @GET("/autonomicas/carmen/faldon/encadena")
    Call<Dummy> encadenaFaldonAutonomicas();

    @GET("/autonomicas/carmen/faldon/sondeo/encadena")
    Call<Dummy> encadenaFaldonSondeoAutonomicas();

    @GET("/autonomicas/carmen/faldon/deAutoaMuni")
    Call<Dummy> deAutoaMuniFaldonAutonomicas();

    @GET("/autonomicas/carmen/faldon/deAutoSondeoAMuniSondeo")
    Call<Dummy> deAutoSondeoAMuniSondeo();

    @GET("/autonomicas/carmen/faldon/deAutoSondeoAMuni")
    Call<Dummy> deAutoSondeoAMuni();

    @GET("/autonomicas/carmen/faldon/deAutoSondeoAAuto")
    Call<Dummy> deAutoSondeoAAuto();

    @GET("/municipales/carmen/faldon/entra")
    Call<Dummy> entraFaldonMunicipales();

    @GET("/municipales/carmen/faldon/sondeo/entra")
    Call<Dummy> entraFaldonMunicipalesSondeo();

    @GET("/municipales/carmen/faldon/sale")
    Call<Dummy> saleFaldonMunicipales();

    @GET("/municipales/carmen/faldon/sondeo/sale")
    Call<Dummy> saleFaldonMunicipalesSondeo();

    @GET("/municipales/carmen/faldon/actualiza")
    Call<Dummy> actualizaFaldonMunicipales();

    @GET("/municipales/carmen/faldon/encadena")
    Call<Dummy> encadenaFaldonMunicipales();

    @GET("/municipales/carmen/faldon/sondeo/encadena")
    Call<Dummy> encadenaFaldonMunicipalesSondeo();

    @GET("/municipales/carmen/faldon/deMuniaAuto")
    Call<Dummy> deMuniaAutoFaldonMunicipales();

    @GET("/municipales/carmen/faldon/deMuniSondeoAAutoSondeo")
    Call<Dummy> deMuniSondeoAAutoSondeo();

    @GET("/municipales/carmen/faldon/deMuniSondeoAMuni")
    Call<Dummy> deMuniSondeoAMuni();

    @GET("/municipales/carmen/faldon/deMuniSondeoAAuto")
    Call<Dummy> deMuniSondeoAAuto();

    @GET("/municipales/carmen/faldon/deMuniASondeoAuto")
    Call<Dummy> deMuniASondeoAuto();


    //PACTOS INFERIOR

    @GET("/autonomicas/carmen/pactos/entra")
    Call<Dummy> entraPactos();

    @GET("/autonomicas/carmen/pactos/sale")
    Call<Dummy> salePactos();

    @GET("/autonomicas/carmen/pactos/reinicio")
    Call<Dummy> reinicioPactos();

    @GET("/autonomicas/carmen/pactos/{posicion}/entraIzq")
    Call<Dummy> entraIzqPactos(@Path("posicion") int posicion);

    @GET("/autonomicas/carmen/pactos/{posicion}/entraDer")
    Call<Dummy> entraDerPactos(@Path("posicion") int posicion);

    //DESCARGA DE ARCHIVOS
    @GET("/autonomicas/circunscripciones/selected/oficial/f_autonomicas/{codigo}/{avance}")
    Call<Dummy> selectCircunscripcionAutonomiaOficialAuto(@Path("codigo") String codigo, @Path("avance") String avance);

    @GET("/autonomicas/circunscripciones/selected/oficial/mapa_mayorias/{codigo}/{avance}")
    Call<Dummy> selectCircunscripcionMapaOficialAuto(@Path("codigo") String codigo, @Path("avance") String avance);

    @GET("/autonomicas/circunscripciones/selected/sondeo/f_autonomicas/{codigo}/{avance}")
    Call<Dummy> selectCircunscripcionAutnomiaSondeoAuto(@Path("codigo") String codigo, @Path("avance") String avance);


    @GET("/municipales/circunscripciones/selected/sondeo/f_autonomicas/{codigo}/{avance}")
    Call<Dummy> selectCircunscripcionMunicipioSondeoMuni(@Path("codigo") String codigo, @Path("avance") String avance);

    @GET("/autonomicas/circunscripciones/selected/sondeo/mapa_mayorias/{codigo}/{avance}")
    Call<Dummy> selectCircunscripcionMapaSondeoAuto(@Path("codigo") String codigo, @Path("avance") String avance);

    @GET("/municipales/circunscripciones/selected/oficial/f_autonomicas/{codigo}/{avance}")
    Call<Dummy> selectCircunscripcionAutonomiaOficialMuni(@Path("codigo") String codigo, @Path("avance") String avance);

    @GET("/municipales/circunscripciones/selected/oficial/mapa_mayorias/{codigo}/{avance}")
    Call<Dummy> selectCircunscripcionMapaOficialMuni(@Path("codigo") String codigo, @Path("avance") String avance);

    @GET("/municipales/circunscripciones/selected/sondeo/f_autonomicas/{codigo}/{avance}")
    Call<Dummy> selectCircunscripcionAutnomiaSondeoMuni(@Path("codigo") String codigo, @Path("avance") String avance);

    @GET("/municipales/circunscripciones/selected/oficial/f_autonomicas/{codigo}/{avance}")
    Call<Dummy> selectCircunscripcionAutnomiaOficialMuni(@Path("codigo") String codigo, @Path("avance") String avance);

    @GET("/municipales/circunscripciones/selected/sondeo/mapa_mayorias/{codigo}/{avance}")
    Call<Dummy> selectCircunscripcionMapaSondeoMuni(@Path("codigo") String codigo, @Path("avance") String avance);

    @GET("/autonomicas/resultados/oficial/{codigo}/{avance}/csv")
    Call<Dummy> descargarResultadosCsvAutoOficial(@Path("codigo") String codigo, @Path("avance") String avance);


    @GET("/autonomicas/resultados/sondeo/{codigo}/{avance}/csv")
    Call<Dummy> descargarResultadosCsvAutoSondeo(@Path("codigo") String codigo, @Path("avance") String avance);

    @GET("/municipales/resultados/oficial/{codigo}/{avance}/csv")
    Call<Dummy> descargarResultadosCsvMuniOficial(@Path("codigo") String codigo, @Path("avance") String avance);

    @GET("/municipales/resultados/sondeo/{codigo}/{avance}/csv")
    Call<Dummy> descargarResultadosCsvMuniSondeo(@Path("codigo") String codigo, @Path("avance") String avance);

    @GET("/municipales/sedes/{codigo}/csv")
    Call<Dummy> descargarSedesCsv(@Path("codigo") String codigo);

    @GET("/autonomicas/carmen/sondeo/especial/{codigo}/{avance}/csv")
    Call<Dummy> descargarSondeoEspecialCsvAuto(@Path("codigo") String codigo, @Path("avance") String avance);

    @GET("/municipales/carmen/sondeo/especial/{codigo}/{avance}/csv")
    Call<Dummy> descargarSondeoEspecialCsvMuni(@Path("codigo") String codigo, @Path("avance") String avance);

    //RESET
    @GET("/municipales/reset")
    Call<Dummy> resetIPFMuni();

    @GET("/autonomicas/reset")
    Call<Dummy> resetIPFAuto();

    /*
     * CAMBIO ENTRE BD PRINCIPAL, RESERVA, LOCAL
     */

    @GET("/bd/principal")
    Call<Dummy> conectPrincipal();

    @GET("/bd/reserva")
    Call<Dummy> conectReserva();

    @GET("/bd/local")
    Call<Dummy> conectLocal();

    /*
     * UPDATE
     */

    @GET("/autonomicas/circunscripciones/update")
    Call<Dummy> update();

    @GET("/municipales/circunscripciones/update/espania/{avance}")
    Call<Dummy> updateEspania(@Path("avance") String avance);

}
