package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.bean.UtilisateurFacadeRemote;
import ejb.entity.Utilisateur;

/**
 * Servlet implementation class AddPanierServlet
 */
@WebServlet("/ValidationServlet")
public class ValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	InitialContext ctx;
	UtilisateurFacadeRemote ufi;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidationServlet() {
        super();
         
        try {
			ctx = new InitialContext(System.getProperties());
			ufi = (UtilisateurFacadeRemote)ctx.lookup("UtilisateurFacade");
			System.out.println("ufi initialisé");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String email = request.getParameter("email");
		String function = "validationFAIL";
		request.getSession().setAttribute("email", email);
		
		if (ufi.contains(email))
		{
			
			ufi.edit(email,true);
			function="validationOK";
			ServiceMail.sendMailCompteActive(email);
			System.out.println("Email en parametre : "+email);

		}
		else{
			System.out.println("La bdd ne contient pas l'user "+email);
		}
		request.getSession().setAttribute("function", function);
		response.sendRedirect("index.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Not implemented
		
	}

}
