package entities;

import entities.Compte;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ComptePlatine
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class ComptePlatine extends Compte implements Serializable {

	private int penalite;
	private int decouvertAutorise;
	
	public int getPenalite() {
		return penalite;
	}

	public void setPenalite(int penalite) {
		this.penalite = penalite;
	}

	public int getDecouvertAutorise() {
		return decouvertAutorise;
	}

	public void setDecouvertAutorise(int decouvertAutorise) {
		this.decouvertAutorise = decouvertAutorise;
	}

	private static final long serialVersionUID = 1L;

	public ComptePlatine() {
		super();
	}
   
}
