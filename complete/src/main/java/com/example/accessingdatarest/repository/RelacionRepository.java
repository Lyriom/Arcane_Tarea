package com.example.accessingdatarest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.accessingdatarest.entity.Relacion;
import com.example.accessingdatarest.entity.RelacionKey;

public interface RelacionRepository extends JpaRepository<Relacion, RelacionKey>{
    List<Relacion> findByPersonajeAIdPersonaje_OrPersonajeBIdPersonaje(Long idPersonajeA, Long idPersonajeB);

    @Query("SELECT r FROM Relacion r WHERE (r.personajeA.idPersonaje = :idPersonaje OR r.personajeB.idPersonaje = :idPersonaje) AND r.tipoRelacion = :tipo")
    List<Relacion> findByPersonajeAndTipo(@Param("idPersonaje") Long idPersonaje, 
                                         @Param("tipo") String tipo);
    
    boolean existsByPersonajeAIdPersonaje_AndPersonajeBIdPersonaje(Long idPersonajeA, Long idPersonajeB);

}
