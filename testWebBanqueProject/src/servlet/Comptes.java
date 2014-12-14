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
 * Servlet implementation class Compte
 */
@WebServlet("/comptes")
public class Comptes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB(name = "GestionCompte")
	GestionCompteRemote gestionCompte;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Comptes() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("gestionClientsBean") == null) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			return;
		}
		if (request.getParameter("choix").equals("0")) {
			GestionClientsRemote gestionClient = (GestionClientsRemote) request.getSession().getAttribute("gestionClientsBean");
			Client client = new Client();
			client.setLogin(gestionClient.getLogin());

			request.setAttribute("client", client);
			ArrayList<Compte> listeCompte = gestionCompte.recupererCompteClient(gestionClient.getId());

			request.setAttribute("listeCompte", listeCompte);

			this.getServletContext().getRequestDispatcher("/WEB-INF/selectionCompte.jsp").forward(request, response);
		}

		else if (request.getParameter("choix").equals("1")) {
			Compte comte = gestionCompte.getCompteById(Integer.parseInt(request.getParameter("selectedCompte")));
			request.setAttribute("compteSelectionne", comte);
			this.getServletContext().getRequestDispatcher("/WEB-INF/detailsCompte.jsp").forward(request, response);
			
		} else if (request.getParameter("choix").equals("2")) {
			gestionCompte.supprimerCompte(Integer.parseInt(request.getParameter("compteID")));
			this.getServletContext().getRequestDispatcher("/accueil").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);

	}

}
