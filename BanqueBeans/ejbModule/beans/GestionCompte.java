package beans;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Compte;
import entities.Mouvement;

/**
 * Session Bean implementation class GestionCompte
 */
@Stateless
public class GestionCompte implements GestionCompteRemote, GestionCompteLocal {

	@EJB(name="GestionHistoriqueRemote")
	GestionHistorique gestionHistorique;
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

	@Override
	public ArrayList<Compte> recupererCompteClient(int id) {
		// TODO Auto-generated method stub
		String request = "Select c from Compte c Where client_id = '"+id+"'";
		ArrayList<Compte> listCompte = (ArrayList<Compte>) em.createQuery(request).getResultList();
		return listCompte;
	}

	@Override
	public Compte getCompteById(int idCompte) {
		Compte compte;
		compte = em.find(Compte.class,idCompte);
		compte.getHistoriqueMouvements().size();
		return compte;
	}

	@Override
	public void modifierSolde(int idCompte, double montant) {
		// TODO Auto-generated method stub
		Compte compte = getCompteById(idCompte);
		Double nouveauSolde = compte.getSolde() + montant;
		Date date = new Date();
		List<Mouvement> histo = compte.getHistoriqueMouvements();
		Mouvement mouvement = new Mouvement(montant, date);
		mouvement.setCompte(compte);
		compte.setSolde(nouveauSolde);
		compte.setHistoriqueMouvements(histo);
		em.merge(compte);
		
		gestionHistorique.ajouterHistorique(mouvement);
	}
	
	
	
}
