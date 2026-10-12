package com.example.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_utilisateurs")
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nom_complet", length = 100, nullable = false)
    private String nom;

    @Column(name = "adresse_email", length = 150, nullable = false, unique = true)
    private String email;

    // Propagation totale des opérations (ALL) et suppression des orphelins
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    public Utilisateur() {}

    public Utilisateur(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

    // Encapsulation de l'association bidirectionnelle
    public void inscrireReservation(Reservation reservation) {
        this.reservations.add(reservation);
        reservation.setClient(this);
    }

    public void annulerReservation(Reservation reservation) {
        this.reservations.remove(reservation);
        reservation.setClient(null);
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<Reservation> getReservations() { return reservations; }
    public void setReservations(List<Reservation> reservations) { this.reservations = reservations; }
}