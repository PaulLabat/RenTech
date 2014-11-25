package servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet permettant � un utilisateur du portail de se d�connecter
 * @author Paul Mariage
 */
@WebServlet("/LogoutServlet/*")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
    }

	/**
	 * Methode de traitement des requ�tes de type GET
	 * <br>invalide la session de l'utilisateur et renvoi sur la page d'accueil
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @param request la requ�te re�u par la servlet
	 * @param response la r�ponse envoy� par la servlet
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * On supprime la session courante puis on redirige vers la page d'accueil.
		 */
		request.getSession().invalidate();
		response.sendRedirect("index.jsp");
	}

	/**
	 * Methode de traitement des requ�tes de type POST
	 * <br>Pas d'inter�t � utiliser la methode POST pour le logout
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) 
	 * @param request la requ�te re�u par la servlet
	 * @param response la r�ponse envoy� par la servlet
	 * @exception javax.servlet.ServletException
	 * @exception java.io.IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
