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
import fr.eni.encheres.ihm.IHMException;

/**
 * Servlet implementation class ServletSignIn
 */
@WebServlet("/signIn")
public class ServletSignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserManager um = ManagerFactory.getUserManager();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/user/signIn.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginInput = request.getParameter("loginInput");
		String password = request.getParameter("passwordInput");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("currentUser") == null) {		
			try {
				User user = um.getUserByEmail(loginInput);
				if (user == null) {
					user = um.getUserByUserName(loginInput);
					if (user == null) {
						throw new BLLException("Business Exception - User doesn't seem to exist.");
					}
				}
				if (!user.getPassword().equals(password)) {
					throw new IHMException("IHM Exception - Wrong password.");
				} else {
					session.setAttribute("currentUser", user);
					
					RequestDispatcher rd = request.getRequestDispatcher("/index");
					rd.forward(request, response);
				}
			} catch (BLLException | IHMException e) {
				e.printStackTrace();
				
				request.setAttribute("exception", e);
				request.setAttribute("loginInput", loginInput);
				request.setAttribute("password", password);
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/user/signIn.jsp");
				rd.forward(request, response);
			}
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}

}
