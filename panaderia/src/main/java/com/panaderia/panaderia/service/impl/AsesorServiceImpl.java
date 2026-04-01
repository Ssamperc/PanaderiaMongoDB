package com.panaderia.panaderia.service.impl;

import com.panaderia.panaderia.dto.CreateAsesorDTO;
import com.panaderia.panaderia.entity.AsesorEntity;
import com.panaderia.panaderia.mappers.AsesorMapper;
import com.panaderia.panaderia.model.AsesorModel;
import com.panaderia.panaderia.model.AsesorModelV2;
import com.panaderia.panaderia.repository.AsesorRepository;
import com.panaderia.panaderia.service.AsesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AsesorServiceImpl implements AsesorService {

    private final AsesorRepository asesorRepository;
    private final AsesorMapper asesorMapper;

    @Value("${app.files.asesor.upload-dir}")
    private String uploadDir;

    @Override
    public AsesorModel createAsesor(CreateAsesorDTO createAsesorDTO) {
        AsesorEntity asesorEntity = new AsesorEntity();
        asesorEntity.setId(UUID.randomUUID().toString());
        asesorEntity.setNombre(createAsesorDTO.getNombre());
        asesorEntity.setCc(createAsesorDTO.getCc());
        asesorEntity.setAge(createAsesorDTO.getAge());

        AsesorEntity saved = asesorRepository.save(asesorEntity);
        return asesorMapper.entityToModel(saved);
    }

    @Override
    public AsesorModelV2 createAsesorV2(CreateAsesorDTO createAsesorDTO) {
        AsesorEntity asesorEntity = new AsesorEntity();
        asesorEntity.setId(UUID.randomUUID().toString());
        asesorEntity.setNombre(createAsesorDTO.getNombre());
        asesorEntity.setCc(createAsesorDTO.getCc());
        asesorEntity.setAge(createAsesorDTO.getAge());

        AsesorEntity saved = asesorRepository.save(asesorEntity);
        return asesorMapper.entityToModelV2(saved);
    }

    @Override
    public AsesorModel createAsesorWithFile(String nombre, String cc, Integer age, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("El archivo es obligatorio");
        }

        try {
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);

            String originalFilename = file.getOriginalFilename();
            String safeFilename = UUID.randomUUID() + "_" + (originalFilename != null ? originalFilename : "archivo");
            Path filePath = uploadPath.resolve(safeFilename);

            Files.copy(file.getInputStream(), filePath);

            AsesorEntity asesorEntity = new AsesorEntity();
            asesorEntity.setId(UUID.randomUUID().toString());
            asesorEntity.setNombre(nombre);
            asesorEntity.setCc(cc);
            asesorEntity.setAge(age);
            asesorEntity.setEvidence("uploads/asesores/" + safeFilename);

            AsesorEntity saved = asesorRepository.save(asesorEntity);
            return asesorMapper.entityToModel(saved);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo", e);
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