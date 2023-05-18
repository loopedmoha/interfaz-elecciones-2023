package com.mycompany.elecciones2023datos.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarmenDTO {
    private CircunscripcionDTO circunscripcion;
    private int numPartidos;
    private List<CpDTO> cpDTO;
}
