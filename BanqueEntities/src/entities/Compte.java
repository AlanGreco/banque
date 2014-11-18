package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Compte
 *
 */
@Entity
public class Compte implements Serializable {

	@Id
	@GeneratedValue
	int id;
	private double solde;
	private Historique historique;
	
	@ManyToOne
	Client client;

	private static final long serialVersionUID = 1L;

	public Compte() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Historique getHistorique() {
		return historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
