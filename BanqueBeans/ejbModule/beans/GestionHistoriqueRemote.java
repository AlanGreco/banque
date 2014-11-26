package beans;


import javax.ejb.Remote;

import entities.Historique;

@Remote
public interface GestionHistoriqueRemote {
	public Historique ajouterHistorique (Historique historique);
	public void supprimerHistorique (Historique historique);

}
