package com.example.accessingdatarest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.accessingdatarest.entity.Personaje;
import com.example.accessingdatarest.entity.Tecnologia;
import com.example.accessingdatarest.entity.UsoTecnologia;
import com.example.accessingdatarest.entity.UsoTecnologiaKey;
import com.example.accessingdatarest.repository.UsoTecnologiaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsoTecnologiaService {

    @Autowired
    private UsoTecnologiaRepository usoTecnologiaRepository;

    @Autowired
    private PersonajeService personajeService;

    @Autowired
    private TecnologiaService tecnologiaService;

    // CREATE
    public UsoTecnologia createUsoTecnologia(Long personajeId, Long tecnologiaId) {
        Personaje personaje = personajeService.getPersonajeById(personajeId)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje no encontrado con id: " + personajeId));
        
        Tecnologia tecnologia = tecnologiaService.getTecnologiaById(tecnologiaId)
                .orElseThrow(() -> new ResourceNotFoundException("Tecnologia no encontrada con id: " + tecnologiaId));

        UsoTecnologiaKey key = new UsoTecnologiaKey(personajeId, tecnologiaId);
        UsoTecnologia uso = new UsoTecnologia();
        uso.setId(key);
        uso.setPersonaje(personaje);
        uso.setTecnologia(tecnologia);

        return usoTecnologiaRepository.save(uso);
    }

    // READ
    public List<Tecnologia> getTecnologiasByPersonaje(Long personajeId) {
        return usoTecnologiaRepository.findByPersonajeIdPersonaje(personajeId)
                .stream()
                .map(UsoTecnologia::getTecnologia)
                .collect(Collectors.toList());
    }

    public List<Personaje> getPersonajesByTecnologia(Long tecnologiaId) {
        return usoTecnologiaRepository.findByTecnologiaIdTecnologia(tecnologiaId)
                .stream()
                .map(UsoTecnologia::getPersonaje)
                .collect(Collectors.toList());
    }

    // DELETE
    public void deleteUsoTecnologia(Long personajeId, Long tecnologiaId) {
        UsoTecnologiaKey key = new UsoTecnologiaKey(personajeId, tecnologiaId);
        usoTecnologiaRepository.deleteById(key);
    }
}
