package beans;


import javax.ejb.Remote;




import java.util.List;

import entities.Client;

@Remote
public interface GestionClientsRemote {
	public Client ajouterClient (Client c);
	public void supprimerClient (Client c);
	public void modifierMotDePasse (String mdp, Client c);
	public List<Client> getListeClients();
	public boolean authenthifierClient(String login, String mdp);
	public void setLogin(String login);
	public String getLogin();
	public int getId();
	public Client getClientById (int id);


}
