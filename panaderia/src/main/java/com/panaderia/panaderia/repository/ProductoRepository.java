package com.panaderia.panaderia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<com.panaderia.panaderia.entity.ProductoEntity, Long> {

    Page<com.panaderia.panaderia.entity.ProductoEntity> findByNombreContainingIgnoreCase(String nombre, PageRequest pageRequest);

}