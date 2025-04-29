package com.example.accessingdatarest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.accessingdatarest.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long>{
    List<Organization> findByTipo(String tipo);

    List<Organization> findByCiudadBase(String ciudadBase);
    
    @Query("SELECT o FROM Organization o WHERE SIZE(o.miembros) > :minMiembros")
    List<Organization> findByMinMiembros(@Param("minMiembros") int minMiembros);
}
