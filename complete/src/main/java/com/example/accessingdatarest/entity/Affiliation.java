package com.example.accessingdatarest.entity;

import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "affiliation")
public class Affiliation {
    @Id
    @EmbeddedId
    private AffiliationKey id;
    
    private String rol;
    private LocalDate fechaIngreso;
    
    @ManyToOne
    @MapsId("idPersonaje")
    @JoinColumn(name = "id_personaje")
    private Personaje personaje;
    
    @ManyToOne
    @MapsId("idOrganization")
    @JoinColumn(name = "id_organization")
    private Organization organization;

    public Affiliation() {
    }

    public Affiliation(AffiliationKey id, String rol, LocalDate fechaIngreso, Personaje personaje,
            Organization organization) {
        this.id = id;
        this.rol = rol;
        this.fechaIngreso = fechaIngreso;
        this.personaje = personaje;
        this.organization = organization;
    }

    public AffiliationKey getId() {
        return id;
    }

    public void setId(AffiliationKey id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
