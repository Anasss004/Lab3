package com.example.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_reservations")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date_debut", nullable = false)
    private LocalDateTime debut;

    @Column(name = "date_fin", nullable = false)
    private LocalDateTime fin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_utilisateur", nullable = false)
    private Utilisateur client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_salle", nullable = false)
    private Salle salleReservee;

    public Reservation() {
    }

    public Reservation(LocalDateTime debut, LocalDateTime fin, Utilisateur client, Salle salleReservee) {
        this.debut = debut;
        this.fin = fin;
        this.client = client;
        this.salleReservee = salleReservee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDebut() {
        return debut;
    }

    public void setDebut(LocalDateTime debut) {
        this.debut = debut;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }

    public Utilisateur getClient() {
        return client;
    }

    public void setClient(Utilisateur client) {
        this.client = client;
    }

    public Salle getSalleReservee() {
        return salleReservee;
    }

    public void setSalleReservee(Salle salleReservee) {
        this.salleReservee = salleReservee;
    }
}