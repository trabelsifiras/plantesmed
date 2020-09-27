package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Membre;
import tn.esprit.spring.repository.MembreRepository;

@Service("MembreService")
public class MembreService {
/*
	@Autowired
	MembreRepository membrerepository ;
	


	@Transactional(readOnly = true)
	public Membre findByUsernameOrEmail(String usernameOrEmail) {
		Membre user = null;
		try {
			user = membrerepository.findByUsernameOrEmail(usernameOrEmail);
		} catch (Exception e) {
			throw e;
		}
		return user;
	}*/
}
