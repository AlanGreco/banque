package beans;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Historique;

/**
 * Session Bean implementation class GestionHistorique
 */
@Stateless
@LocalBean
public class GestionHistorique implements GestionHistoriqueRemote, GestionHistoriqueLocal {

	@PersistenceContext
	EntityManager em;

    /**
     * Default constructor. 
     */
    public GestionHistorique() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Historique ajouterHistorique(Historique historique) {
		em.persist(historique);
		return historique;
	}

	@Override
	public void supprimerHistorique(Historique historique) {
		historique = em.find(Historique.class, historique.getId());
		em.remove(historique);
	}

}
