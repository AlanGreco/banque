package beans;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Client;

/**
 * Session Bean implementation class GestionClients
 */
@Stateful
public class GestionClients implements GestionClientsRemote, GestionClientsLocal {


	@PersistenceContext
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public GestionClients() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Client ajouterClient(Client c) {
		// TODO Auto-generated method stub
		em.persist(c);
		return c;
	}

	@Override
	public void supprimerClient(Client c) {
		// TODO Auto-generated method stub
		c=em.find(Client.class,c.getId());
		em.remove(c);
		
	}

	@Override
	public void modifierMotDePasse(String mdp, Client c) {
		// TODO Auto-generated method stub
		c=em.find(Client.class,c.getId());
		c.setPassword(mdp);
	}


	@Override
	public List<Client> getListeLecteurs() {
		// TODO Auto-generated method stub
		return (List<Client>) em.createQuery("Select c from Client c").getResultList();
	}

}
