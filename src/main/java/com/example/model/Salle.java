package com.example.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_salles")
public class Salle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "designation", nullable = false)
    private String code;

    @Column(name = "capacite_max")
    private Integer places;

    // Relation avec Reservation (avec cascade totale)
    @OneToMany(mappedBy = "salleReservee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> listeReservations = new ArrayList<>();

    // Relation avec Equipement (sans REMOVE pour préserver les équipements partagés)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "tj_salle_equipement",
            joinColumns = @JoinColumn(name = "ref_salle"),
            inverseJoinColumns = @JoinColumn(name = "ref_equipement")
    )
    private List<Equipement> listeEquipements = new ArrayList<>();

    public Salle() {}

    public Salle(String code, Integer places) {
        this.code = code;
        this.places = places;
    }

    // Méthodes d'association pour les équipements (Many-To-Many)
    public void ajouterEquipement(Equipement equipement) {
        this.listeEquipements.add(equipement);
        equipement.getSallesAssociees().add(this);
    }

    public void enleverEquipement(Equipement equipement) {
        this.listeEquipements.remove(equipement);
        equipement.getSallesAssociees().remove(this);
    }

    // Méthodes d'association pour les réservations (One-To-Many)
    public void affecterReservation(Reservation reservation) {
        this.listeReservations.add(reservation);
        reservation.setSalleReservee(this);
    }

    public void libererReservation(Reservation reservation) {
        this.listeReservations.remove(reservation);
        reservation.setSalleReservee(null);
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public Integer getPlaces() { return places; }
    public void setPlaces(Integer places) { this.places = places; }
    public List<Reservation> getListeReservations() { return listeReservations; }
    public void setListeReservations(List<Reservation> listeReservations) { this.listeReservations = listeReservations; }
    public List<Equipement> getListeEquipements() { return listeEquipements; }
    public void setListeEquipements(List<Equipement> listeEquipements) { this.listeEquipements = listeEquipements; }
}