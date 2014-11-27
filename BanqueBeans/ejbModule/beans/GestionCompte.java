package beans;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Client;
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
		return compte;
	}

	@Override
	public void modifierSolde(int idCompte, double montant) {
		// TODO Auto-generated method stub
		Compte compte = getCompteById(idCompte);
		Double nouveauSolde = compte.getSolde() + montant;
		compte.setSolde(nouveauSolde);
		em.merge(compte);
	}
	
	
	
}
