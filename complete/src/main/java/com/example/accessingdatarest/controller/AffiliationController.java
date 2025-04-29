package com.example.accessingdatarest.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdatarest.entity.Affiliation;
import com.example.accessingdatarest.service.AffiliationService;

@RestController
@RequestMapping("/api/affiliations")
public class AffiliationController {

    @Autowired
    private AffiliationService affiliationService;

    @PostMapping
    public ResponseEntity<Affiliation> createAffiliation(
            @RequestParam Long personajeId,
            @RequestParam Long organizationId,
            @RequestParam String rol,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaIngreso) {
        
        Affiliation nuevaAffiliation = affiliationService.createAffiliation(
                personajeId, organizationId, rol, fechaIngreso);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAffiliation);
    }

    @GetMapping("/personaje/{personajeId}")
    public ResponseEntity<List<Affiliation>> getAffiliationsByPersonaje(@PathVariable Long personajeId) {
        List<Affiliation> affiliations = affiliationService.getAffiliationsByPersonaje(personajeId);
        return ResponseEntity.ok(affiliations);
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<List<Affiliation>> getAffiliationsByOrganization(@PathVariable Long organizationId) {
        List<Affiliation> affiliations = affiliationService.getAffiliationsByOrganization(organizationId);
        return ResponseEntity.ok(affiliations);
    }

    @GetMapping("/organization/{organizationId}/rol/{rol}")
    public ResponseEntity<List<Affiliation>> getMembersByRole(
            @PathVariable Long organizationId,
            @PathVariable String rol) {
        
        List<Affiliation> affiliations = affiliationService.getMembersByRole(organizationId, rol);
        return ResponseEntity.ok(affiliations);
    }

    @PutMapping("/update-rol")
    public ResponseEntity<Affiliation> updateAffiliationRole(
            @RequestParam Long personajeId,
            @RequestParam Long organizationId,
            @RequestParam String newRole) {
        
        Affiliation updatedAffiliation = affiliationService.updateAffiliationRole(
                personajeId, organizationId, newRole);
        return ResponseEntity.ok(updatedAffiliation);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAffiliation(
            @RequestParam Long personajeId,
            @RequestParam Long organizationId) {
        
        affiliationService.deleteAffiliation(personajeId, organizationId);
        return ResponseEntity.noContent().build();
    }
}
