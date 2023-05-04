package com.mycompany.elecciones2023datos.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CarmenDtoList {
    private int size;
    private List<CarmenDTO> carmenDTOList;
}
