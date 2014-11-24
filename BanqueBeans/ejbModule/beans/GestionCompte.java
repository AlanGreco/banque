package beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Compte;

/**
 * Session Bean implementation class GestionCompte
 */
@Stateless
public class GestionCompte implements GestionCompteRemote, GestionCompteLocal {

    /**
     * Default constructor. 
     */
    public GestionCompte() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext
	EntityManager em;

	@Override
	public Compte ajouterCompte(Compte compte) {
		em.persist(compte);
		return compte;	
	}

	@Override
	public void supprimerCompte(Compte compte) {
		compte = em.find(Compte.class,compte.getId());
		em.remove(compte);
	}

}
