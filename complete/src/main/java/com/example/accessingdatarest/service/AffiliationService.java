package com.example.accessingdatarest.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.accessingdatarest.entity.Affiliation;
import com.example.accessingdatarest.entity.AffiliationKey;
import com.example.accessingdatarest.entity.Organization;
import com.example.accessingdatarest.entity.Personaje;
import com.example.accessingdatarest.repository.AffiliationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AffiliationService {

    @Autowired
    private AffiliationRepository affiliationRepository;

    @Autowired
    private PersonajeService personajeService;

    @Autowired OrganizationService organizationService;

    // CREATE
    public Affiliation createAffiliation(Long personajeId, Long organizationId, String rol, LocalDate fechaIngreso) {
        Personaje personaje = personajeService.getPersonajeById(personajeId)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje no encontrado con id: " + personajeId));
        
        Organization organization = organizationService.getOrganizationById(organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Organization no encontrada con id: " + organizationId));

        AffiliationKey key = new AffiliationKey(personajeId, organizationId);
        Affiliation affiliation = new Affiliation();
        affiliation.setId(key);
        affiliation.setPersonaje(personaje);
        affiliation.setOrganization(organization);
        affiliation.setRol(rol);
        affiliation.setFechaIngreso(fechaIngreso);

        return affiliationRepository.save(affiliation);
    }

    // READ
    public List<Affiliation> getAffiliationsByPersonaje(Long personajeId) {
        return affiliationRepository.findByPersonajeIdPersonaje(personajeId);
    }

    public List<Affiliation> getAffiliationsByOrganization(Long organizationId) {
        return affiliationRepository.findByOrganizationIdOrganization(organizationId);
    }

    public List<Affiliation> getMembersByRole(Long organizationId, String rol) {
        return affiliationRepository.findByOrganizationAndRol(organizationId, rol);
    }

    // UPDATE
    public Affiliation updateAffiliationRole(Long personajeId, Long organizationId, String newRole) {
        AffiliationKey key = new AffiliationKey(personajeId, organizationId);
        return affiliationRepository.findById(key)
                .map(aff -> {
                    aff.setRol(newRole);
                    return affiliationRepository.save(aff);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Afiliación no encontrada"));
    }

    // DELETE
    public void deleteAffiliation(Long personajeId, Long organizationId) {
        AffiliationKey key = new AffiliationKey(personajeId, organizationId);
        affiliationRepository.deleteById(key);
    }
}
