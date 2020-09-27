package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {

}
