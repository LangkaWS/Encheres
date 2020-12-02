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
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bll.bo.User;

/**
 * Servlet implementation class ServletShowUser
 */
@WebServlet("/ServletShowUser")
public class ServletShowUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserManager um = new UserManager();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = 0;
		if (request.getParameter("userId") == null) {
			userId = (int) request.getAttribute("userId");
		} else {
			userId = Integer.parseInt(request.getParameter("userId"));
		}
		try {
			User u = um.getUser(userId);
			request.setAttribute("user", u);
			RequestDispatcher rd = request.getRequestDispatcher("/users/" + userId);
			rd.forward(request, response);
		} catch (BLLException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
