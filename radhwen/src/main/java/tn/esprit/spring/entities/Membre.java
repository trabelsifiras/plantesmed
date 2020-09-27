package tn.esprit.spring.entities;

import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity 
@SessionScoped
public class Membre implements Serializable {
	 private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	
	private String nom ;
	
	private String prenom ;
	
	private String mail ; 
	
	private String login ;
	
	private String password ;
	
	private String image;
	@DateTimeFormat(pattern="dd/MM/yy")
	private Date date_debut ;
	
	@DateTimeFormat(pattern="dd/MM/yy")
	private Date date_fin ;

	private Long etat ;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Role role;
	
	@OneToMany(mappedBy="membre")
	private Collection<Plante> plantes = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Plante> getPlantes() {
		return plantes;
	}

	public void setPlantes(Collection<Plante> plantes) {
		this.plantes = plantes;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	public Long getEtat() {
		return etat;
	}

	public void setEtat(Long etat) {
		this.etat = etat;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
