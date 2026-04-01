package com.panaderia.panaderia.repository;

import com.panaderia.panaderia.entity.ProductoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends MongoRepository<ProductoEntity, Long> {

    Page<com.panaderia.panaderia.entity.ProductoEntity> findByNombreContainingIgnoreCase(String nombre, PageRequest pageRequest);

}