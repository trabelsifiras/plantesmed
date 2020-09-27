package tn.esprit.spring.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Commentaire;
import tn.esprit.spring.entities.Membre;
import tn.esprit.spring.entities.Plante;
import tn.esprit.spring.repository.CommentaireRepository;
import tn.esprit.spring.repository.MembreRepository;
import tn.esprit.spring.repository.PlanteRepository;

@Service
public class PlanteService {

	@Autowired
	PlanteRepository planteRepository ;
	@Autowired
	MembreRepository membreRepository ;
	@Autowired 
	CommentaireRepository cr;
	
	public int AjouterPlantes ( Plante plante){
		planteRepository.save(plante);
		return plante.getId();
	
	}
	
	public void AjouterPlanteByIdUser(Plante plt,int Idmembre){
		Membre m = membreRepository.findById(Idmembre).get();
		plt.setMembre(m);
		planteRepository.save(plt);

	}
	public void addcomentaire(int idmembre ,int idplante,String com){
		
		Plante p=planteRepository.findById(idplante).get();
		Membre m=membreRepository.findById(idmembre).get();
		
		Commentaire coment=new Commentaire();
		
		coment.setMembre(m);
		coment.setPlante(p);
		coment.setCom(com);
		cr.save(coment);
	
	}
	

}
