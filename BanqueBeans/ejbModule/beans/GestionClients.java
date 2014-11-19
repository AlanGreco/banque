package beans;

import javax.ejb.Stateful;

/**
 * Session Bean implementation class GestionClients
 */
@Stateful
public class GestionClients implements GestionClientsRemote, GestionClientsLocal {

    /**
     * Default constructor. 
     */
    public GestionClients() {
        // TODO Auto-generated constructor stub
    }

}
