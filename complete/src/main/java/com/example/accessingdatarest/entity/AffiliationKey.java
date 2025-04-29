package com.example.accessingdatarest.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class AffiliationKey implements Serializable{
    private Long idPersonaje;
    private Long idOrganization;
    
    public AffiliationKey() {
    }

    public AffiliationKey(Long idPersonaje, Long idOrganization) {
        this.idPersonaje = idPersonaje;
        this.idOrganization = idOrganization;
    }

    public Long getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(Long idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public Long getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(Long idOrganization) {
        this.idOrganization = idOrganization;
    }
}
