package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, mappedBy = "compte")
	private List<Mouvement> historiqueMouvements = new ArrayList<Mouvement>();
	
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
		solde = (double)Math.round(solde*100)/100;
		this.solde = solde;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Mouvement> getHistoriqueMouvements() {
		return historiqueMouvements;
	}

	public void setHistoriqueMouvements(List<Mouvement> historiqueMouvements) {
		this.historiqueMouvements = historiqueMouvements;
	}
	public void effecteurOperation(double montant){
		montant = (double) Math.round(montant*100)/100;
		solde += montant;
	}

}
