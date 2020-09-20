package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.Membre;

public interface MembreRepository extends JpaRepository<Membre, Integer> {

}
