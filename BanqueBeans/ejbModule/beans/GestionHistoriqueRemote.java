package beans;


import javax.ejb.Remote;

import entities.Compte;
import entities.Mouvement;

@Remote
public interface GestionHistoriqueRemote {
	public Mouvement ajouterHistorique (Mouvement historique);
	public void supprimerHistorique (Mouvement historique);
	public void ajouterMouvement(double montant, Compte compte);
	void ajouterMouvement(double montant, Compte compte, String commentaire);

}
