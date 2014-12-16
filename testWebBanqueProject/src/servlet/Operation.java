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
import beans.GestionHistoriqueRemote;
import entities.Compte;

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

		ArrayList<Compte> listeCompte = gestionCompte.recupererCompteClient(gestionClient.getId());
		request.setAttribute("listeCompte", listeCompte);

		this.getServletContext().getRequestDispatcher("/WEB-INF/operation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("gestionClientsBean") == null) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			return;
		}

		GestionClientsRemote gestionClient = (GestionClientsRemote) request.getSession().getAttribute("gestionClientsBean");

		String type = request.getParameter("type");
		if ("operation".equals(type)) {
			int id = Integer.parseInt(request.getParameter("selectedCompte"));
			Double montant = Double.parseDouble(request.getParameter("montant"));
			gestionCompte.modifierSolde(id, montant);

			this.getServletContext().getRequestDispatcher("/comptes").forward(request, response);

		} else if ("virement".equals(type)) {
			
			//On vérifie les arguments passé en paramètre par le client
			String pMontant = request.getParameter("montant");
			String pIdCompte1 = request.getParameter("selectedCompte");
			String pIdCompte2 = request.getParameter("selectedCompte2");

			boolean parsable = util.isParsableDouble(pMontant) && util.isParsableInt(pIdCompte1) && util.isParsableInt(pIdCompte2);

			if (parsable) {
				Double iMontant = Double.parseDouble(pMontant);
				int idCompte1 = Integer.parseInt(pIdCompte1);
				int idCompte2 = Integer.parseInt(pIdCompte2);

				boolean authentifier = gestionCompte.verificationAppartenanceCompte(gestionClient.getId(), idCompte1);
				authentifier &= gestionCompte.verificationAppartenanceCompte(gestionClient.getId(), idCompte2);  // On vérifie si les comptes appartienne bien au client connecté

				if (iMontant > 0 && authentifier) {
					gestionCompte.effectuerVirement(idCompte1, idCompte2, iMontant);

					
					this.getServletContext().getRequestDispatcher("/comptes").forward(request, response);
				} else {
					doGet(request, response);
				}

			} else {
				doGet(request, response);
			}

		}

	}

}
