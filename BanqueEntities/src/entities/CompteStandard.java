package entities;

import entities.Compte;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: CompteStandard
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class CompteStandard extends Compte implements Serializable {

	private int penalite;
	
	public int getPenalite() {
		return penalite;
	}

	public void setPenalite(int penalite) {
		this.penalite = penalite;
	}

	private static final long serialVersionUID = 1L;

	public CompteStandard() {
		super();
	}
   
}
