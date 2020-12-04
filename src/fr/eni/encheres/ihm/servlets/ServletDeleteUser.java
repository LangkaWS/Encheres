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
 * Servlet implementation class ServletDeleteUser
 */
@WebServlet("/ServletDeleteUser")
public class ServletDeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if (currentUser == null) {
			request.setAttribute("exception", "You have to be logged in if you want to access this page.");
			RequestDispatcher rd = request.getRequestDispatcher("/signIn.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("userId", currentUser.getUserId());
			RequestDispatcher rd = request.getRequestDispatcher("/ServletShowUser");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserManager um = ManagerFactory.getUserManager();
		
		HttpSession session = request.getSession();
		
		User currentUser = (User) session.getAttribute("currentUser");
		
		try {
			um.deleteUser(currentUser.getUserId());
			session.invalidate();
			request.setAttribute("info", "Account deleted.");
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		} catch (BLLException e) {
			e.printStackTrace();
			request.setAttribute("warning", "We couldn't delete your account : " + e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/ServletShowUser?userId=\" + currentUser.getUserId()");
			rd.forward(request, response);
		}
		
	}

}
