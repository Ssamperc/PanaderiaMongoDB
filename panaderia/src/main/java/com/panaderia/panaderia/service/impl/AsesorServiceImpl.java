package com.panaderia.panaderia.service.impl;

import com.panaderia.panaderia.dto.CreateAsesorDTO;
import com.panaderia.panaderia.dto.SyncAsesorDTO;
import com.panaderia.panaderia.entity.AsesorEntity;
import com.panaderia.panaderia.mappers.AsesorMapper;
import com.panaderia.panaderia.model.AsesorModel;
import com.panaderia.panaderia.model.AsesorModelV2;
import com.panaderia.panaderia.repository.AsesorRepository;
import com.panaderia.panaderia.service.AsesorService;
import com.panaderia.panaderia.service.MigrateAsesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AsesorServiceImpl implements AsesorService {

    private final AsesorRepository asesorRepository;
    private final AsesorMapper asesorMapper;
    private final MigrateAsesorService migrateAsesorService;

    @Value("${app.files.asesor.upload-dir}")
    private String uploadDir;

    @Override
    public AsesorModel createAsesor(CreateAsesorDTO createAsesorDTO) {
        String id = UUID.randomUUID().toString();

        AsesorEntity asesorEntity = new AsesorEntity();
        asesorEntity.setId(id);
        asesorEntity.setNombre(createAsesorDTO.getNombre());
        asesorEntity.setCc(createAsesorDTO.getCc());
        asesorEntity.setAge(createAsesorDTO.getAge());

        AsesorEntity saved = asesorRepository.save(asesorEntity);

        SyncAsesorDTO syncDto = new SyncAsesorDTO(
                saved.getId(),
                saved.getNombre(),
                saved.getCc(),
                saved.getAge()
        );
        migrateAsesorService.sendAsesorToOtherService(syncDto);

        return asesorMapper.entityToModel(saved);
    }

    @Override
    public AsesorModelV2 createAsesorV2(CreateAsesorDTO createAsesorDTO) {
        String id = UUID.randomUUID().toString();

        AsesorEntity asesorEntity = new AsesorEntity();
        asesorEntity.setId(id);
        asesorEntity.setNombre(createAsesorDTO.getNombre());
        asesorEntity.setCc(createAsesorDTO.getCc());
        asesorEntity.setAge(createAsesorDTO.getAge());

        AsesorEntity saved = asesorRepository.save(asesorEntity);

        SyncAsesorDTO syncDto = new SyncAsesorDTO(
                saved.getId(),
                saved.getNombre(),
                saved.getCc(),
                saved.getAge()
        );
        migrateAsesorService.sendAsesorToOtherService(syncDto);

        return asesorMapper.entityToModelV2(saved);
    }

    @Override
    public AsesorModel createAsesorWithFile(String nombre, String cc, Integer age, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("El archivo es obligatorio");
        }

        try {
            byte[] fileBytes = file.getBytes();
            String base64 = Base64.getEncoder().encodeToString(fileBytes);

            AsesorEntity asesorEntity = new AsesorEntity();
            asesorEntity.setId(UUID.randomUUID().toString());
            asesorEntity.setNombre(nombre);
            asesorEntity.setCc(cc);
            asesorEntity.setAge(age);
            asesorEntity.setEvidence(base64);

            AsesorEntity saved = asesorRepository.save(asesorEntity);
            return asesorMapper.entityToModel(saved);
        } catch (IOException e) {
            throw new RuntimeException("Error al convertir el archivo a Base64", e);
        }
    }

    @Override
    public AsesorModelV2 getAsesorById(String id) {
        AsesorEntity asesor = asesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe un asesor con id: " + id));

        return asesorMapper.entityToModelV2(asesor);
    }

    @Override
    public Page<AsesorModel> getAllAsesores(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return asesorRepository.findAll(pageRequest).map(asesorMapper::entityToModel);
    }

    @Override
    public List<AsesorModelV2> getAllAsesoresV2() {
        return asesorRepository.findAll()
                .stream()
                .map(asesorMapper::entityToModelV2)
                .toList();
    }

    @Override
    public Page<AsesorModelV2> getAllAsesoresV2(int page, int size, String nombre) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return asesorRepository.findByNombreContainingIgnoreCase(nombre, pageRequest)
                .map(asesorMapper::entityToModelV2);
    }
}