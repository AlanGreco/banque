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
import beans.GestionCompte;
import beans.GestionCompteRemote;
import beans.GestionHistoriqueRemote;
import entities.Compte;
import entities.Mouvement;

/**
 * Servlet implementation class Operation
 */
@WebServlet("/operation")
public class Operation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name = "GestionCompte")
	GestionCompteRemote gestionCompte;
	
	@EJB(name = "GestionMouvement")
	GestionHistoriqueRemote gestionMouvement;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Operation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GestionClientsRemote gestionClient = (GestionClientsRemote) request.getSession().getAttribute("gestionClientsBean");

		ArrayList<Compte> listeCompte = gestionCompte.recupererCompteClient(gestionClient.getId());
		request.setAttribute("listeCompte", listeCompte);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/operation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int id = Integer.parseInt(request.getParameter("choix"));	
		Double montant = Double.parseDouble(request.getParameter("montant"));
		gestionCompte.modifierSolde(id, montant);
		this.getServletContext().getRequestDispatcher("/WEB-INF/detailsCompte.jsp").forward(request, response);
		//ArrayList<Mouvement> listeMouvement = gestionMouvement.ajouterHistorique(historique);
		
		
	}

}
