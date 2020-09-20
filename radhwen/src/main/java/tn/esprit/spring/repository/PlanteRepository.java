package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.Plante;

public interface PlanteRepository extends JpaRepository<Plante, Integer> {

}
