package com.example.accessingdatarest.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdatarest.entity.Organization;
import com.example.accessingdatarest.entity.Personaje;
import com.example.accessingdatarest.service.PersonajeService;

@RestController
@RequestMapping("/api/personajes")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @GetMapping
    public ResponseEntity<List<Personaje>> getAllPersonajes() {
        List<Personaje> personajes = personajeService.getAllPersonajes();
        return ResponseEntity.ok(personajes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personaje> getPersonajeById(@PathVariable Long id) {
        return personajeService.getPersonajeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Personaje> createPersonaje(@RequestBody Personaje personaje) {
        Personaje nuevoPersonaje = personajeService.createPersonaje(personaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPersonaje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personaje> updatePersonaje(@PathVariable Long id, 
                                                   @RequestBody Personaje personajeDetails) {
        Personaje updatedPersonaje = personajeService.updatePersonaje(id, personajeDetails);
        return ResponseEntity.ok(updatedPersonaje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonaje(@PathVariable Long id) {
        personajeService.deletePersonaje(id);
        return ResponseEntity.noContent().build();
    }

    // Métodos personalizados
    @GetMapping("/alineacion/{alineacion}")
    public ResponseEntity<List<Personaje>> getPersonajesByAlineacion(@PathVariable String alineacion) {
        List<Personaje> personajes = personajeService.getPersonajesByAlineacion(alineacion);
        return ResponseEntity.ok(personajes);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Personaje>> searchPersonajesByName(@RequestParam String nombre) {
        List<Personaje> personajes = personajeService.searchPersonajesByName(nombre);
        return ResponseEntity.ok(personajes);
    }

    @GetMapping("/{id}/organizaciones")
    public ResponseEntity<List<Organization>> getOrganizacionesByPersonaje(@PathVariable Long id) {
        // Implementar este método en el servicio si es necesario
        return ResponseEntity.ok(Collections.emptyList());
    }
}
