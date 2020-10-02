package tn.esprit.spring.entities;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Plante implements Serializable{
	
	private static final long serialVersionUID = 6191889143079517027L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	
	private String nom ;
	
	private String description ;
	
	private String article ;
	
	private String photos ;
	
	@JsonIgnore
	@OneToMany(mappedBy="plante",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Collection<Commentaire> comments=new ArrayList<>();
	
	@ManyToOne
	public Membre membre ;
	
	
	private Long etat_pub ;
	
	
	public Plante() {
		super();
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public Long getEtat_pub() {
		return etat_pub;
	}

	public void setEtat_pub(Long etat_pub) {
		this.etat_pub = etat_pub;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Plante(String description, String photos, Membre membre) {
		super();
		this.description = description;
		this.photos = photos;
		this.membre = membre;
	}

	public Plante(String description, String photos) {
		super();
		this.description = description;
		this.photos = photos;
	}

	public Collection<Commentaire> getComments() {
		return comments;
	}

	public void setComments(Collection<Commentaire> comments) {
		this.comments = comments;
	}

	

	
	

}
