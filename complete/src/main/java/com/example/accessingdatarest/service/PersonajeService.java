package com.example.accessingdatarest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.accessingdatarest.entity.Personaje;
import com.example.accessingdatarest.repository.PersonajeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepository;

    // CREATE
    public Personaje createPersonaje(Personaje personaje) {
        return personajeRepository.save(personaje);
    }

    // READ
    public List<Personaje> getAllPersonajes() {
        return personajeRepository.findAll();
    }

    public Optional<Personaje> getPersonajeById(Long id) {
        return personajeRepository.findById(id);
    }

    // UPDATE
    public Personaje updatePersonaje(Long id, Personaje personajeDetails) {
        return personajeRepository.findById(id)
                .map(personaje -> {
                    personaje.setNombre(personajeDetails.getNombre());
                    personaje.setOrigen(personajeDetails.getOrigen());
                    personaje.setRol(personajeDetails.getRol());
                    personaje.setAlineacion(personajeDetails.getAlineacion());
                    return personajeRepository.save(personaje);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Personaje no encontrado con id: " + id));
    }

    // DELETE
    public void deletePersonaje(Long id) {
        personajeRepository.deleteById(id);
    }

    // Métodos personalizados
    public List<Personaje> getPersonajesByAlineacion(String alineacion) {
        return personajeRepository.findByAlineacion(alineacion);
    }

    public List<Personaje> searchPersonajesByName(String nombre) {
        return personajeRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Personaje> getPersonajesByOrganization(Long orgId) {
        return personajeRepository.findByOrganizationId(orgId);
    }
}
