package com.example.accessingdatarest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.accessingdatarest.entity.Personaje;
import com.example.accessingdatarest.entity.Relacion;
import com.example.accessingdatarest.entity.RelacionKey;
import com.example.accessingdatarest.repository.RelacionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RelacionService {

    @Autowired
    private RelacionRepository relacionRepository;

    @Autowired
    private PersonajeService personajeService;

    // CREATE
    public Relacion createRelacion(Long idPersonajeA, Long idPersonajeB, String tipoRelacion) {
        if (idPersonajeA.equals(idPersonajeB)) {
            throw new IllegalArgumentException("Un personaje no puede tener relación consigo mismo");
        }
        
        Personaje personajeA = personajeService.getPersonajeById(idPersonajeA)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje no encontrado con id: " + idPersonajeA));
        
        Personaje personajeB = personajeService.getPersonajeById(idPersonajeB)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje no encontrado con id: " + idPersonajeB));

        RelacionKey key = new RelacionKey(idPersonajeA, idPersonajeB);
        Relacion relacion = new Relacion();
        relacion.setId(key);
        relacion.setPersonajeA(personajeA);
        relacion.setPersonajeB(personajeB);
        relacion.setTipoRelacion(tipoRelacion);

        return relacionRepository.save(relacion);
    }

    // READ
    public List<Relacion> getRelacionesByPersonaje(Long personajeId) {
        return relacionRepository.findByPersonajeAIdPersonaje_OrPersonajeBIdPersonaje(personajeId, personajeId);
    }

    public List<Relacion> getRelacionesByPersonajeAndTipo(Long personajeId, String tipo) {
        return relacionRepository.findByPersonajeAndTipo(personajeId, tipo);
    }

    // DELETE
    public void deleteRelacion(Long idPersonajeA, Long idPersonajeB) {
        RelacionKey key = new RelacionKey(idPersonajeA, idPersonajeB);
        relacionRepository.deleteById(key);
    }

    // Método auxiliar
    public boolean existsRelacion(Long idPersonajeA, Long idPersonajeB) {
        return relacionRepository.existsByPersonajeAIdPersonaje_AndPersonajeBIdPersonaje(idPersonajeA, idPersonajeB);
    }
}
