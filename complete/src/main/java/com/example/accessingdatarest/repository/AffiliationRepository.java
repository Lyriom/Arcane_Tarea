package com.example.accessingdatarest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.accessingdatarest.entity.Affiliation;
import com.example.accessingdatarest.entity.AffiliationKey;

public interface AffiliationRepository extends JpaRepository<Affiliation, AffiliationKey>{
    List<Affiliation> findByOrganizationIdOrganization(Long organizationId);
    
    List<Affiliation> findByPersonajeIdPersonaje(Long personajeId);
    
    @Query("SELECT a FROM Affiliation a WHERE a.organization.idOrganization = :orgId AND a.rol = :rol")
    List<Affiliation> findByOrganizationAndRol(@Param("orgId") Long orgId, 
                                             @Param("rol") String rol);
    
    @Modifying
    @Query("DELETE FROM Affiliation a WHERE a.organization.idOrganization = :orgId")
    void deleteByOrganizationId(@Param("orgId") Long orgId);
    
    @Modifying
    @Query("DELETE FROM Affiliation a WHERE a.personaje.idPersonaje = :personajeId")
    void deleteByPersonajeId(@Param("personajeId") Long personajeId);
}

