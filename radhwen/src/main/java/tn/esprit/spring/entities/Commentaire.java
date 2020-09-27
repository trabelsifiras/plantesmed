package tn.esprit.spring.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Commentaire {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idCommentaire ;


    private String Com;

    @ManyToOne
    private Membre membre ;

    @ManyToOne
    private Plante plante ;

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public String getCom() {
        return Com;
    }

    public void setCom(String com) {
        Com = com;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Plante getPlante() {
        return plante;
    }

    public void setPlante(Plante plante) {
        this.plante = plante;
    }

	public Commentaire() {
		super();
	}

}