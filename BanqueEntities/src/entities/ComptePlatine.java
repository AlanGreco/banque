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
	
	private static final long serialVersionUID = 1L;

	public ComptePlatine() {
		super();
	}
   
}
