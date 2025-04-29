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
import com.example.accessingdatarest.service.OrganizationService;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping
    public ResponseEntity<List<Organization>> getAllOrganizations() {
        List<Organization> organizations = organizationService.getAllOrganizations();
        return ResponseEntity.ok(organizations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organization> getOrganizationById(@PathVariable Long id) {
        return organizationService.getOrganizationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Organization> createOrganization(@RequestBody Organization organization) {
        Organization nuevaOrganization = organizationService.createOrganization(organization);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaOrganization);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organization> updateOrganization(@PathVariable Long id, 
                                                         @RequestBody Organization organizationDetails) {
        Organization updatedOrganization = organizationService.updateOrganization(id, organizationDetails);
        return ResponseEntity.ok(updatedOrganization);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id) {
        organizationService.deleteOrganization(id);
        return ResponseEntity.noContent().build();
    }

    // Métodos personalizados
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Organization>> getOrganizationsByTipo(@PathVariable String tipo) {
        List<Organization> organizations = organizationService.getOrganizationsByTipo(tipo);
        return ResponseEntity.ok(organizations);
    }

    @GetMapping("/{id}/miembros")
    public ResponseEntity<List<Personaje>> getMiembrosByOrganization(@PathVariable Long id) {
        // Implementar este método en el servicio si es necesario
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/min-miembros")
    public ResponseEntity<List<Organization>> getOrganizationsWithMinMembers(
            @RequestParam(defaultValue = "1") int minMembers) {
        List<Organization> organizations = organizationService.getOrganizationsWithMinMembers(minMembers);
        return ResponseEntity.ok(organizations);
    }
}
