package com.example.accessingdatarest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdatarest.entity.Personaje;
import com.example.accessingdatarest.entity.Tecnologia;
import com.example.accessingdatarest.entity.UsoTecnologia;
import com.example.accessingdatarest.service.UsoTecnologiaService;

@RestController
@RequestMapping("/api/uso-tecnologia")
public class UsoTecnologiaController {

    @Autowired
    private UsoTecnologiaService usoTecnologiaService;

    @PostMapping
    public ResponseEntity<UsoTecnologia> createUsoTecnologia(
            @RequestParam Long personajeId,
            @RequestParam Long tecnologiaId) {
        
        UsoTecnologia nuevoUso = usoTecnologiaService.createUsoTecnologia(personajeId, tecnologiaId);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUso);
    }

    @GetMapping("/personaje/{personajeId}")
    public ResponseEntity<List<Tecnologia>> getTecnologiasByPersonaje(@PathVariable Long personajeId) {
        List<Tecnologia> tecnologias = usoTecnologiaService.getTecnologiasByPersonaje(personajeId);
        return ResponseEntity.ok(tecnologias);
    }

    @GetMapping("/tecnologia/{tecnologiaId}")
    public ResponseEntity<List<Personaje>> getPersonajesByTecnologia(@PathVariable Long tecnologiaId) {
        List<Personaje> personajes = usoTecnologiaService.getPersonajesByTecnologia(tecnologiaId);
        return ResponseEntity.ok(personajes);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUsoTecnologia(
            @RequestParam Long personajeId,
            @RequestParam Long tecnologiaId) {
        
        usoTecnologiaService.deleteUsoTecnologia(personajeId, tecnologiaId);
        return ResponseEntity.noContent().build();
    }
}
