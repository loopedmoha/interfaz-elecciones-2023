package com.mycompany.elecciones2023datos.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CpDTO {
    private String codigoPartido;
    private String codigoPadre;
    private int escanos_desde;
    private int escanos_hasta;
    private int escanos_hasta_hist;
    private double porcentajeVoto;
    private double porcentajeVotoHistorico;
    private int numVotantes;
    private String siglas;
    private String literalPartido;
    private int escanos_desde_sondeo;
    private int escanos_hasta_sondeo;
    private double porcentajeVotoSondeo;
}
