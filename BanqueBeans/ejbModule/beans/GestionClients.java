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
		em.persist(c);
		return c;
	}

	@Override
	public void supprimerClient(Client c) {
		c=em.find(Client.class,c.getId());
		em.remove(c);

	}

	@Override
	public void modifierMotDePasse(String mdp, Client c) {
		c=em.find(Client.class,c.getId());
		c.setPassword(mdp);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getListeClients() {
		return (List<Client>) em.createQuery("Select c from Client c").getResultList();
	}

	/* 
	 * @see beans.GestionClientsRemote#authenthifierClient(java.lang.String, java.lang.String)
	 * Méthode permettant de vérifier si un compte appartient bien à un client.
	 */
	@SuppressWarnings("unchecked")
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
