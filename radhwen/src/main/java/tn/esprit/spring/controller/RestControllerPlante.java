package tn.esprit.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Plante;
import tn.esprit.spring.services.PlanteService;

@RestController
public class RestControllerPlante {

	@Autowired
	PlanteService planteService ;
	
	@PostMapping("/ajouterPlante")
	@ResponseBody
	public int AjouterPlante (@RequestBody Plante plante){
		return planteService.AjouterPlantes(plante);
		
	}
		
		/*@PutMapping("/ajouterPlanteByIdMembre/{idp}/{IdMembre}")
		@ResponseBody
		public void AjouterPlanteByIdMrmbre (@PathVariable ("idp")int idp , @PathVariable("IdMembre")int IdMembre){
			planteService.AjouterPlanteByIdUser(idp, IdMembre);
			
		
		
	}
	*/
}
