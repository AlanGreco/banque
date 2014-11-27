package beans;

import java.util.ArrayList;

import javax.ejb.Remote;

import entities.Compte;

@Remote
public interface GestionCompteRemote {
	public Compte ajouterCompte(Compte compte);

	public void supprimerCompte(Compte compte);

	public ArrayList<Compte> recupererCompteClient(int id);
	public Compte getCompteById (int idCompte);
	public void modifierSolde (int idCompte, double montant);
}
