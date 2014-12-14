package beans;

import java.util.ArrayList;

import javax.ejb.Remote;

import entities.Compte;
import entities.CompteEpargne;

@Remote
public interface GestionCompteRemote {
	public Compte ajouterCompte(Compte compte);

	public void supprimerCompte(Compte compte);

	public ArrayList<Compte> recupererCompteClient(int id);
	public Compte getCompteById (int idCompte);
	public boolean modifierSolde (int idCompte, double montant);
	public CompteEpargne  calculInteretEpargne(double mouvement, CompteEpargne compte);
	public void crediterLesInterets();

	void supprimerCompte(int idCompte);

	public boolean effectuerVirement(int idCompte1, int idCompte2, Double iMontant);
	public boolean verificationAppartenanceCompte (int idClient, int idCompte);
}
