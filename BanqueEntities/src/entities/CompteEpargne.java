package entities;

import entities.Compte;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: CompteEpargne
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class CompteEpargne extends Compte implements Serializable {

	private int tauxInteret ;
	private static final long serialVersionUID = 1L;
	private double compteInteret;
	
	public CompteEpargne() {
		super();
	}

	public int getTauxInteret() {
		return tauxInteret;
	}

	public void setTauxInteret(int tauxInteret) {
		this.tauxInteret = tauxInteret;
	}

	public double getCompteInteret() {
		return compteInteret;
	}

	public void setCompteInteret(double compteInteret) {
		this.compteInteret = compteInteret;
	}

}