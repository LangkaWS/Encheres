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
			request.setAttribute("info", "Votre compte a bien été supprimé");
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		} catch (BLLException e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/ServletShowUser?userId=\" + currentUser.getUserId()");
			rd.forward(request, response);
		}
		
	}

}