package com.panaderia.panaderia.controller;

import com.panaderia.panaderia.entity.CategoriaEntity;
import com.panaderia.panaderia.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    @GetMapping
    public List<CategoriaEntity> getAll() {
        return categoriaRepository.findAll();
    }

    @PostMapping
    public CategoriaEntity create(@RequestBody CategoriaEntity categoria) {
        return categoriaRepository.save(categoria);
    }
}