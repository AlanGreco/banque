package beans;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Mouvement;

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
	public Mouvement ajouterHistorique(Mouvement historique) {
		em.persist(historique);
		return historique;
	}

	@Override
	public void supprimerHistorique(Mouvement historique) {
		historique = em.find(Mouvement.class, historique.getId());
		em.remove(historique);
	}

}
