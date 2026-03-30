package com.panaderia.panaderia.repository;

import com.panaderia.panaderia.entity.AsesorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsesorRepository extends MongoRepository<AsesorEntity, Long> {

    Page<AsesorEntity> findByNombreContainingIgnoreCase(String nombre, PageRequest pageRequest);

}