package com.mycompany.elecciones2023datos.model;

import com.mycompany.elecciones2023datos.DTO.CarmenDTO;
import com.mycompany.elecciones2023datos.DTO.CpDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CpData {
    String codigo = "";
    String siglas = "";
    int escanosDesde = 0;
    int escanosHasta = 0;
    int escanosHist = 0;
    double porcentajeVoto = 0;
    int votantes = 0;
    private int escanos_desde_sondeo = 0;
    private int escanos_hasta_sondeo = 0;
    private double porcentajeVotoSondeo = 0.0;


    public static List<CpData> fromCarmenDto(CarmenDTO carmen) {
        List<CpData> res = new ArrayList<>();
        for (CpDTO cpDTO : carmen.getCpDTO()) {
            //System.out.println(cpDTO);
            res.add(new CpData(
                    cpDTO.getCodigoPartido(), cpDTO.getSiglas(), cpDTO.getEscanos_desde(), cpDTO.getEscanos_hasta(),
                    cpDTO.getEscanos_hasta_hist(), cpDTO.getPorcentajeVoto(), cpDTO.getNumVotantes(),
                    cpDTO.getEscanos_desde_sondeo(), cpDTO.getEscanos_hasta_sondeo(), cpDTO.getPorcentajeVotoSondeo()));
        }
        return res;
    }

    public static CpData fromCP(CircunscripcionPartido cp, String siglas) {
        CpData res = new CpData(
                cp.getKey().getPartido(), siglas, cp.getEscanos_desde(), cp.getEscanos_hasta(),
                cp.getEscanos_hasta_hist(), cp.getPorcentajeVoto(), cp.getNumVotantes(),
                cp.getEscanos_desde_sondeo(), cp.getEscanos_hasta_sondeo(), cp.getPorcentajeVotoSondeo());

        return res;
    }


}
