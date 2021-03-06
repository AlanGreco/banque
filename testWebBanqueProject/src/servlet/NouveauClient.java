package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.GestionClientsRemote;
import entities.Client;

/**
 * Servlet implementation class Bonjour
 */
@WebServlet("/NouveauClient")
public class NouveauClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB(name = "GestionClients")
	GestionClientsRemote gestionclient;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NouveauClient() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/nouveauClient.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Client c = new Client();
		c.setNom(request.getParameter("prenomClient"));
		c.setPrenom(request.getParameter("nomClient"));
		c.setPassword(request.getParameter("motDePasseClient"));
		c.setLogin(c.getPrenom() + "." + c.getNom());

		c = gestionclient.ajouterClient(c);
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

}
