package com.example.accessingdatarest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.accessingdatarest.entity.Organization;
import com.example.accessingdatarest.repository.AffiliationRepository;
import com.example.accessingdatarest.repository.OrganizationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private AffiliationRepository affiliationRepository;

    // CRUD básico
    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    public Optional<Organization> getOrganizationById(Long id) {
        return organizationRepository.findById(id);
    }

    public Organization updateOrganization(Long id, Organization organizationDetails) {
        return organizationRepository.findById(id)
                .map(org -> {
                    org.setNombre(organizationDetails.getNombre());
                    org.setCiudadBase(organizationDetails.getCiudadBase());
                    org.setTipo(organizationDetails.getTipo());
                    return organizationRepository.save(org);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Organization no encontrada con id: " + id));
    }

    public void deleteOrganization(Long id) {
        // Primero eliminamos las afiliaciones para evitar problemas de FK
        affiliationRepository.deleteByOrganizationId(id);
        organizationRepository.deleteById(id);
    }

    // Métodos personalizados
    public List<Organization> getOrganizationsByTipo(String tipo) {
        return organizationRepository.findByTipo(tipo);
    }

    public List<Organization> getOrganizationsWithMinMembers(int minMembers) {
        return organizationRepository.findByMinMiembros(minMembers);
    }
}
