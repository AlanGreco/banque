package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Historique
 *
 */
@Entity
public class Mouvement implements Serializable {
	@Id
	@GeneratedValue
	private int id;
	
	private static final long serialVersionUID = 1L;

	private double montant;
	private Date date;
	
	@ManyToOne
	Compte compte;
	
	public Mouvement() {
		super();
	}
	
	public Mouvement(double montant, Date date) {
		super();
		this.montant = montant;
		this.date = date;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}


}
