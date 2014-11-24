package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.GestionClientsRemote;
import beans.GestionCompteRemote;
import entities.Client;
import entities.Compte;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB(name = "GestionCompte")
	GestionCompteRemote gestionCompte;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Accueil() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		GestionClientsRemote gestionClient = (GestionClientsRemote) request
				.getSession().getAttribute("gestionClientsBean");
		Client client = new Client();
		client.setLogin(gestionClient.getLogin());

		request.setAttribute("client", client);
		ArrayList<Compte> listeCompte = gestionCompte.recupererCompteClient(gestionClient.getId());

		request.setAttribute("listeCompte", listeCompte);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp")
				.forward(request, response);		}

}
