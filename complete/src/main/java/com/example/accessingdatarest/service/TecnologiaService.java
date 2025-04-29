package com.example.accessingdatarest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.accessingdatarest.entity.Tecnologia;
import com.example.accessingdatarest.repository.TecnologiaRepository;
import com.example.accessingdatarest.repository.UsoTecnologiaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TecnologiaService {
    
    @Autowired
    private TecnologiaRepository tecnologiaRepository;

    @Autowired
    private UsoTecnologiaRepository usoTecnologiaRepository;

    // CRUD básico
    public Tecnologia createTecnologia(Tecnologia tecnologia) {
        return tecnologiaRepository.save(tecnologia);
    }

    public List<Tecnologia> getAllTecnologias() {
        return tecnologiaRepository.findAll();
    }

    public Optional<Tecnologia> getTecnologiaById(Long id) {
        return tecnologiaRepository.findById(id);
    }

    public Tecnologia updateTecnologia(Long id, Tecnologia tecnologiaDetails) {
        return tecnologiaRepository.findById(id)
                .map(tech -> {
                    tech.setNombre(tecnologiaDetails.getNombre());
                    tech.setTipo(tecnologiaDetails.getTipo());
                    tech.setDescription(tecnologiaDetails.getDescription());
                    return tecnologiaRepository.save(tech);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Tecnologia no encontrada con id: " + id));
    }

    public void deleteTecnologia(Long id) {
        // Eliminar primero los usos de la tecnología
        usoTecnologiaRepository.deleteByTecnologiaId(id);
        tecnologiaRepository.deleteById(id);
    }

    // Métodos personalizados
    public List<Tecnologia> getTecnologiasByTipo(String tipo) {
        return tecnologiaRepository.findByTipo(tipo);
    }

    public List<Tecnologia> searchTecnologiasByName(String nombre) {
        return tecnologiaRepository.searchByNombre(nombre);
    }
}
