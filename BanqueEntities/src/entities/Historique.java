package entities;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Historique
 *
 */
@Entity
public class Historique implements Serializable {
	@Id
	@GeneratedValue
	private int id;
	private ArrayList<Double> mouvements;
	
	private static final long serialVersionUID = 1L;

	public Historique() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Double> getMouvements() {
		return mouvements;
	}

	public void setMouvements(ArrayList<Double> mouvements) {
		this.mouvements = mouvements;
	}

}
