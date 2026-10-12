package com.example;

import com.example.model.Equipement;
import com.example.model.Reservation;
import com.example.model.Salle;
import com.example.model.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class App {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestion-reservations-pu");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Utilisateur user = new Utilisateur();
            user.setEmail("alice@example.com");
            user.setNom("Alice Dupont");

            Salle salle = new Salle();
            salle.setCode("Salle A");
            salle.setPlaces(30);

            Equipement projecteur = new Equipement();
            projecteur.setLibelle("Projecteur");

            salle.ajouterEquipement(projecteur);

            Reservation reservation = new Reservation();
            reservation.setDebut(LocalDateTime.now());
            reservation.setFin(LocalDateTime.now().plusHours(2));
            reservation.setSalleReservee(salle);
            reservation.setClient(user);

            user.inscrireReservation(reservation);

            // Persistance de la salle pour éviter l'erreur TransientPropertyValueException
            em.persist(salle);

            // Persistance de l'utilisateur (la cascade propage la persistance à la réservation)
            em.persist(user);

            tx.commit();
            System.out.println("Données enregistrées avec succès !");

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }
}