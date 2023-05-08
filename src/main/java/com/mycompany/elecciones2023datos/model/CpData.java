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
    String nombrePartido = "";
    int voto = 0;

    int escanosHasta = 0;
    int escanosDesde = 0;

    public static List<CpData> fromCarmenDto(CarmenDTO carmen) {
        List<CpData> res = new ArrayList<>();
        for (CpDTO cpDTO : carmen.getCpDTO()) {
            res.add(new CpData(cpDTO.getCodigoPartido(),
                    cpDTO.getLiteralPartido(), cpDTO.getNumVotantes(), cpDTO.getEscanos_hasta(), cpDTO.getEscanos_desde()));
        }
        return res;
    }
}
