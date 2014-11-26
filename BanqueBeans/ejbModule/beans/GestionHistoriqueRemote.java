package beans;


import javax.ejb.Remote;

import entities.Mouvement;

@Remote
public interface GestionHistoriqueRemote {
	public Mouvement ajouterHistorique (Mouvement historique);
	public void supprimerHistorique (Mouvement historique);

}
