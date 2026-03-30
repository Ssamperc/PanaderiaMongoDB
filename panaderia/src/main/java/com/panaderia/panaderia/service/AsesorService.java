package com.panaderia.panaderia.service;

import com.panaderia.panaderia.dto.CreateAsesorDTO;
import com.panaderia.panaderia.model.AsesorModel;
import com.panaderia.panaderia.model.AsesorModelV2;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AsesorService {

    AsesorModel createAsesor(CreateAsesorDTO createAsesorDTO);

    AsesorModelV2 createAsesorV2(CreateAsesorDTO createAsesorDTO);

    AsesorModelV2 getAsesorById(Long id);

    Page<AsesorModel> getAllAsesores(int page, int size);

    List<AsesorModelV2> getAllAsesoresV2();

    Page<AsesorModelV2> getAllAsesoresV2(int page, int size, String nombre);
}