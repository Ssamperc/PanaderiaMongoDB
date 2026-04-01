package com.panaderia.panaderia.controller;

import com.panaderia.panaderia.dto.CreateAsesorDTO;
import com.panaderia.panaderia.model.AsesorModel;
import com.panaderia.panaderia.model.AsesorModelV2;
import com.panaderia.panaderia.service.AsesorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/asesores")
@RequiredArgsConstructor
public class AsesorController {

    private final AsesorService asesorService;

    @PostMapping(value = "/create")
    @Operation(summary = "Endpoint que se encarga de realizar la creacion de asesores - V1")
    public AsesorModel create(@RequestBody CreateAsesorDTO asesor) {
        return asesorService.createAsesor(asesor);
    }

    @PostMapping(value = "/create-v2")
    @Operation(summary = "Endpoint que se encarga de realizar la creacion de asesores - V2")
    public AsesorModelV2 createV2(@RequestBody CreateAsesorDTO asesor) {
        return asesorService.createAsesorV2(asesor);
    }

    @PostMapping(value = "/create-with-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Endpoint que se encarga de realizar la creacion de asesores con archivo")
    public AsesorModel createWithFile(@RequestParam String nombre, @RequestParam String cc, @RequestParam Integer age, @RequestPart MultipartFile file
    ) {
        return asesorService.createAsesorWithFile(nombre, cc, age, file);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar asesor por ID y retornar solo ID y nombre")
    public AsesorModelV2 getById(@PathVariable String id) {
        return asesorService.getAsesorById(id);
    }

    @GetMapping("/page/{page}/{size}")
    @Operation(summary = "Buscar todos los asesores")
    public Page<AsesorModel> getAllAsesores(@PathVariable int page, @PathVariable int size) {
        return asesorService.getAllAsesores(page, size);
    }

    @GetMapping("/asesores/v2")
    @Operation(summary = "Mostrar todos los asesores")
    public List<AsesorModelV2> getAllAsesoresV2() {
        return asesorService.getAllAsesoresV2();
    }

    @GetMapping("/filter/page/{page}/{size}")
    @Operation(summary = "Buscar asesores paginados por nombre")
    public Page<AsesorModelV2> getAllAsesoresV2(@PathVariable int page, @PathVariable int size, @RequestParam(required = false) String nombre
    ) {
        return asesorService.getAllAsesoresV2(page, size, nombre);
    }
}