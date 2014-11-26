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
	private String login;
	private int id;
	/**
	 * Default constructor. 
	 */
	public GestionClients() {
		// TODO Auto-generated constructor stub
	}

	public GestionClients(String login) {
		this.login = login;
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
	public List<Client> getListeClients() {
		// TODO Auto-generated method stub
		return (List<Client>) em.createQuery("Select c from Client c").getResultList();
	}

	@Override
	public boolean authenthifierClient(String login, String mdp) {
		String request = "Select c from Client c Where login = '"+login+"'";
		List<Client> listClient = (List<Client>) em.createQuery(request).getResultList();
		if (listClient.size() == 0){
			return false;
		}
		if(!listClient.get(0).getPassword().equals(mdp)){
			return false;
		}
		id = listClient.get(0).getId();
		return true;
	}

	public void setLogin(String login){
		this.login = login;
	}
	public String getLogin(){
		return login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public Client getClientById(int id) {
		Client client = new Client();
		client = em.find(Client.class,id);
		return client;
	}

}
