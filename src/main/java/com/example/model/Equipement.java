package com.example.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_equipements")
public class Equipement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "libelle", nullable = false, unique = true)
    private String libelle;

    @ManyToMany(mappedBy = "listeEquipements")
    private List<Salle> sallesAssociees = new ArrayList<>();

    public Equipement() {
    }

    public Equipement(String libelle) {
        this.libelle = libelle;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Salle> getSallesAssociees() {
        return sallesAssociees;
    }

    public void setSallesAssociees(List<Salle> sallesAssociees) {
        this.sallesAssociees = sallesAssociees;
    }
}