package com.example.accessingdatarest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.accessingdatarest.entity.Tecnologia;

public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long>{
    List<Tecnologia> findByTipo(String tipo);

    @Query("SELECT t FROM Tecnologia t WHERE LOWER(t.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Tecnologia> searchByNombre(@Param("nombre") String nombre);
    
    @Query("SELECT t FROM Tecnologia t JOIN t.usos u WHERE u.personaje.idPersonaje = :personajeId")
    List<Tecnologia> findByPersonajeId(@Param("personajeId") Long personajeId);
}
