package servlet;

import java.io.IOException;

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
import entities.CompteEpargne;
import entities.ComptePlatine;
import entities.CompteStandard;

/**
 * Servlet implementation class NouveauCompte
 */
@WebServlet("/nouveaucompte")
public class NouveauCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB(name = "GestionCompteRemote")
	GestionCompteRemote gestionCompteBean;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NouveauCompte() {
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
		GestionClientsRemote gestionClient = (GestionClientsRemote) request.getSession().getAttribute("gestionClientsBean");
		Client client = new Client();
		client = gestionClient.getClientById(gestionClient.getId());
		Compte compte = null;
		String typeDeCompte = (String) request.getParameter("type");
		if ("simple".equals(typeDeCompte)) {
			compte = new CompteStandard();
			((CompteStandard) compte).setPenalite(20);
		} else if ("premium".equals(typeDeCompte)) {
			compte = new ComptePlatine();
			((ComptePlatine) compte).setDecouvertAutorise(500);
			((ComptePlatine) compte).setPenalite(10);
		} else if ("epargne".equals(typeDeCompte)) {
			compte = new CompteEpargne();
			((CompteEpargne) compte).setTauxInteret(5);
		}
		compte.setClient(client);
		gestionCompteBean.ajouterCompte(compte);

		this.getServletContext().getRequestDispatcher("/accueil").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
