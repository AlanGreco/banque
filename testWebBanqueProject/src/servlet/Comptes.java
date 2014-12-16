package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.util;
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

		String sChoix = request.getParameter("choix");
		int choix = 10;

		if (util.isParsableInt(sChoix)) {
			choix = Integer.parseInt(sChoix);
		}

		switch (choix) {
		case 0:
			GestionClientsRemote gestionClient = (GestionClientsRemote) request.getSession().getAttribute("gestionClientsBean");
			Client client = new Client();
			client.setLogin(gestionClient.getLogin());

			request.setAttribute("client", client);
			ArrayList<Compte> listeCompte = gestionCompte.recupererCompteClient(gestionClient.getId());

			request.setAttribute("listeCompte", listeCompte);

			this.getServletContext().getRequestDispatcher("/WEB-INF/selectionCompte.jsp").forward(request, response);
			break;

		case 1:
			Compte comte = gestionCompte.getCompteById(Integer.parseInt(request.getParameter("selectedCompte")));
			request.setAttribute("compteSelectionne", comte);
			this.getServletContext().getRequestDispatcher("/WEB-INF/detailsCompte.jsp").forward(request, response);
			break;

		case 2:
			gestionCompte.supprimerCompte(Integer.parseInt(request.getParameter("compteID")));
			this.getServletContext().getRequestDispatcher("/accueil").forward(request, response);

			break;
		default:
			this.getServletContext().getRequestDispatcher("/accueil").forward(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
