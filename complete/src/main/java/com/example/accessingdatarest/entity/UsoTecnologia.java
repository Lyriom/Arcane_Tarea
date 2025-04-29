package com.example.accessingdatarest.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "uso_tecnologia")
public class UsoTecnologia {
    @Id
    @EmbeddedId
    private UsoTecnologiaKey id;
    
    @ManyToOne
    @MapsId("idPersonaje")
    @JoinColumn(name = "id_personaje")
    private Personaje personaje;
    
    @ManyToOne
    @MapsId("idTecnologia")
    @JoinColumn(name = "id_tecnologia")
    private Tecnologia tecnologia;

    public UsoTecnologia() {
    }

    public UsoTecnologia(UsoTecnologiaKey id, Personaje personaje, Tecnologia tecnologia) {
        this.id = id;
        this.personaje = personaje;
        this.tecnologia = tecnologia;
    }

    public UsoTecnologiaKey getId() {
        return id;
    }

    public void setId(UsoTecnologiaKey id) {
        this.id = id;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public Tecnologia getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(Tecnologia tecnologia) {
        this.tecnologia = tecnologia;
    }
}
