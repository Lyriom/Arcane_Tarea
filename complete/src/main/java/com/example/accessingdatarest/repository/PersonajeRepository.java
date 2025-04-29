package com.example.accessingdatarest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.accessingdatarest.entity.Personaje;

public interface PersonajeRepository extends JpaRepository<Personaje, Long>{
    List<Personaje> findByAlineacion(String alineacion);
    List<Personaje> findByNombreContainingIgnoreCase(String nombre);
    
    @Query("SELECT p FROM Personaje p WHERE p.rol = :rol AND p.alineacion = :alineacion")
    List<Personaje> findByRolAndAlineacion(@Param("rol") String rol, 
                                          @Param("alineacion") String alineacion);
    
    @Query("SELECT p FROM Personaje p JOIN p.afiliaciones a WHERE a.organization.idOrganization = :orgId")
    List<Personaje> findByOrganizationId(@Param("orgId") Long orgId);
}
