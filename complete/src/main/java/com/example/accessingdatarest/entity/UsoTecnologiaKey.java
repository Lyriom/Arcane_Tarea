package com.example.accessingdatarest.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class UsoTecnologiaKey implements Serializable{
    private Long idPersonaje;
    private Long idTecnologia;
    
    public UsoTecnologiaKey() {
    }

    public UsoTecnologiaKey(Long idPersonaje, Long idTecnologia) {
        this.idPersonaje = idPersonaje;
        this.idTecnologia = idTecnologia;
    }

    public Long getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(Long idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public Long getIdTecnologia() {
        return idTecnologia;
    }

    public void setIdTecnologia(Long idTecnologia) {
        this.idTecnologia = idTecnologia;
    }
}
