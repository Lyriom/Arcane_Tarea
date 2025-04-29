package com.example.accessingdatarest.controller;

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

import com.example.accessingdatarest.entity.Tecnologia;
import com.example.accessingdatarest.service.TecnologiaService;

@RestController
@RequestMapping("/api/tecnologias")
public class TecnologiaController {

    @Autowired
    private TecnologiaService tecnologiaService;

    @GetMapping
    public ResponseEntity<List<Tecnologia>> getAllTecnologias() {
        List<Tecnologia> tecnologias = tecnologiaService.getAllTecnologias();
        return ResponseEntity.ok(tecnologias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tecnologia> getTecnologiaById(@PathVariable Long id) {
        return tecnologiaService.getTecnologiaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tecnologia> createTecnologia(@RequestBody Tecnologia tecnologia) {
        Tecnologia nuevaTecnologia = tecnologiaService.createTecnologia(tecnologia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTecnologia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tecnologia> updateTecnologia(@PathVariable Long id, 
                                                      @RequestBody Tecnologia tecnologiaDetails) {
        Tecnologia updatedTecnologia = tecnologiaService.updateTecnologia(id, tecnologiaDetails);
        return ResponseEntity.ok(updatedTecnologia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTecnologia(@PathVariable Long id) {
        tecnologiaService.deleteTecnologia(id);
        return ResponseEntity.noContent().build();
    }

    // Métodos personalizados
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Tecnologia>> getTecnologiasByTipo(@PathVariable String tipo) {
        List<Tecnologia> tecnologias = tecnologiaService.getTecnologiasByTipo(tipo);
        return ResponseEntity.ok(tecnologias);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Tecnologia>> searchTecnologiasByName(@RequestParam String nombre) {
        List<Tecnologia> tecnologias = tecnologiaService.searchTecnologiasByName(nombre);
        return ResponseEntity.ok(tecnologias);
    }
}
