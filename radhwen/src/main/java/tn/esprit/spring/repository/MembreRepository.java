package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Membre;

public interface MembreRepository extends JpaRepository<Membre, Integer> {
	 @Query("SELECT u FROM Membre u WHERE u.mail=:Email AND u.password=:pasword")
	   public Membre findByEmailandpassword(@Param("Email")String Email,@Param("pasword")String pasword);
}
