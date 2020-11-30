package fr.eni.encheres.ihm.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.ManagerFactory;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bll.bo.User;

/**
 * Servlet implementation class ServletSignIn
 */
@WebServlet("/ServletSignIn")
public class ServletSignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserManager um = ManagerFactory.getUserManager();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("emailInput");
		String password = request.getParameter("passwordInput");
		
		HttpSession session = request.getSession();
		
		try {
			User user = um.getUser(email);
			if (user == null) {
				throw new BLLException("Error - User seems to not exist.");
			} else if (!user.getPassword().equals(password)) {
				throw new BLLException("Error - Wrong password.");
			} else {
				session.setAttribute("currentUser", user);
				
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}
		} catch (BLLException e) {
			e.printStackTrace();
			request.setAttribute("exception", e);
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			RequestDispatcher rd = request.getRequestDispatcher("/signIn.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
