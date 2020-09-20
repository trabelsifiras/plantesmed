package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.repository.MembreRepository;

@Service
public class MembreService {

	@Autowired
	MembreRepository membrerepository ;
	
	
	
}
