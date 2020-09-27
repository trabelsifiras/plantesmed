package tn.esprit.spring.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Membre;

@Controller(value = "util")
@ELBeanName(value = "util")
@ViewScoped

public class UtilController {

	public void verifsesion(){
		try {
			Membre mb=(Membre) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			if(mb==null){
				FacesContext.getCurrentInstance().getExternalContext().redirect("/SpringMVC/loginsignup");
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}

