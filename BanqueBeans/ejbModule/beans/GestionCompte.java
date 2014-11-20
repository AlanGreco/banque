package beans;

import javax.ejb.Stateful;

import entities.Compte;

/**
 * Session Bean implementation class GestionCompte
 */
@Stateful
public class GestionCompte implements GestionCompteRemote, GestionCompteLocal {

    /**
     * Default constructor. 
     */
    public GestionCompte() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Compte ajouterCompte(Compte compte) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerCompte(Compte compte) {
		// TODO Auto-generated method stub
		
	}

}
