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

    int escanosHasta = 0;
    int escanosDesde = 0;
    double porcentajeVoto = 0;


    public static List<CpData> fromCarmenDto(CarmenDTO carmen) {
        List<CpData> res = new ArrayList<>();
        for (CpDTO cpDTO : carmen.getCpDTO()) {
            System.out.println(cpDTO);
            res.add(new CpData(
                    cpDTO.getCodigoPartido(), cpDTO.getSiglas(), cpDTO.getEscanos_desde(), cpDTO.getEscanos_hasta(), cpDTO.getPorcentajeVoto()));
        }
        return res;
    }


}
