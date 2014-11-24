package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Client;
import entities.Compte;
import beans.GestionClientsRemote;
import beans.GestionCompteRemote;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB(name = "GestionClients")
	GestionClientsRemote gestionclient;
	
	@EJB(name = "GestionCompte")
	GestionCompteRemote gestionCompte;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
		.forward(request, response);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("loginClient");
		String mdp = request.getParameter("motDePasseClient");
		boolean authentification = gestionclient.authenthifierClient(login, mdp);	
		
		if (authentification){
			request.setAttribute("login", login);
			request.setAttribute("client", gestionclient.getId());
			
			gestionclient.setLogin(login);
			request.getSession().setAttribute("gestionClientsBean", gestionclient);

			this.getServletContext().getRequestDispatcher("/accueil")
			.forward(request, response);
		}else{
			this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
			.forward(request, response);
		}
	}

}
