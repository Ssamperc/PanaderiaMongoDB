package com.panaderia.panaderia.repository;

import com.panaderia.panaderia.entity.CategoriaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoriaRepository extends MongoRepository<CategoriaEntity, String> {
}