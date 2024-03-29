package fr.eni.encheres.ihm.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.ManagerFactory;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bll.bo.User;
import fr.eni.encheres.ihm.IHMException;

/**
 * Servlet implementation class ServletSignUp
 */
@WebServlet("/signUp")
public class ServletSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserManager um = ManagerFactory.getUserManager();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/user/signUp.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userName = request.getParameter("userNameInput");
		String lastName = request.getParameter("lastNameInput");
		String firstName = request.getParameter("firstNameInput");
		String email = request.getParameter("emailInput");
		String phone = request.getParameter("phoneInput");
		String street = request.getParameter("streetInput");
		String zipCode = request.getParameter("zipCodeInput");
		String town = request.getParameter("townInput");
		String password = request.getParameter("passwordInput");
		String confirmPassword = request.getParameter("confirmPasswordInput");
		
		User newUser = new User(userName, lastName, firstName, email, phone, street, zipCode, town, password);
		
		try {
			if(!password.equals(confirmPassword)) {
				throw new IHMException("Error - The password and the confirmation are different. Please check again.");
			}
			um.addUser(newUser);
			
			request.getSession().setAttribute("currentUser", newUser);
			
			RequestDispatcher rd = request.getRequestDispatcher("/index");
			rd.forward(request, response);
		} catch (BLLException | IHMException e) {
			e.printStackTrace();
			
			request.setAttribute("exception", "An error occured : " + e.getMessage());
			request.setAttribute("userName", userName);
			request.setAttribute("lastName", lastName);
			request.setAttribute("firstName", firstName);
			request.setAttribute("email", email);
			request.setAttribute("phone", phone);
			request.setAttribute("street", street);
			request.setAttribute("zipCode", zipCode);
			request.setAttribute("town", town);
			request.setAttribute("password", password);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/user/signUp.jsp");
			rd.forward(request, response);
		}
	}

}
