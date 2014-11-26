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
import entities.Compte;

/**
 * Servlet implementation class Operation
 */
@WebServlet("/operation")
public class Operation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name = "GestionCompte")
	GestionCompteRemote gestionCompte;
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
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/operation").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
