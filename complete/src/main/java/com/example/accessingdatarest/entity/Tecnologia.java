package com.example.accessingdatarest.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tecnologia")
public class Tecnologia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTecnologia;
    
    private String nombre;
    private String tipo;
    private String description;
    
    @OneToMany(mappedBy = "tecnologia", cascade = CascadeType.ALL)
    private List<UsoTecnologia> usos;

    public Tecnologia() {
    }

    public Tecnologia(Long idTecnologia, String nombre, String tipo, String description, List<UsoTecnologia> usos) {
        this.idTecnologia = idTecnologia;
        this.nombre = nombre;
        this.tipo = tipo;
        this.description = description;
        this.usos = usos;
    }

    public Long getIdTecnologia() {
        return idTecnologia;
    }

    public void setIdTecnologia(Long idTecnologia) {
        this.idTecnologia = idTecnologia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UsoTecnologia> getUsos() {
        return usos;
    }

    public void setUsos(List<UsoTecnologia> usos) {
        this.usos = usos;
    }
}
