package com.example.accessingdatarest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.accessingdatarest.entity.UsoTecnologia;
import com.example.accessingdatarest.entity.UsoTecnologiaKey;

public interface UsoTecnologiaRepository extends JpaRepository<UsoTecnologia, UsoTecnologiaKey>{
    List<UsoTecnologia> findByPersonajeIdPersonaje(Long personajeId);
    
    List<UsoTecnologia> findByTecnologiaIdTecnologia(Long tecnologiaId);
    
    @Modifying
    @Query("DELETE FROM UsoTecnologia u WHERE u.personaje.idPersonaje = :personajeId")
    void deleteByPersonajeId(@Param("personajeId") Long personajeId);
    
    @Modifying
    @Query("DELETE FROM UsoTecnologia u WHERE u.tecnologia.idTecnologia = :tecnologiaId")
    void deleteByTecnologiaId(@Param("tecnologiaId") Long tecnologiaId);
}
