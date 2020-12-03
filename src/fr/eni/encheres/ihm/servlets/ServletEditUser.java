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
 * Servlet implementation class ServletEditUser
 */
@WebServlet("/ServletEditUser")
public class ServletEditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("currentUser") == null) {
			request.setAttribute("exception", "Vous devez être connecté pour accéder à cette page.");
			RequestDispatcher rd = request.getRequestDispatcher("/signIn.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/user/editUser.jsp");
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
		
		request.setCharacterEncoding("UTF-8");
		
		int userId = currentUser.getUserId();
		String userName = request.getParameter("userNameInput");
		String lastName = request.getParameter("lastNameInput");
		String firstName = request.getParameter("firstNameInput");
		String email = request.getParameter("emailInput");
		String phone = request.getParameter("phoneInput");
		String street = request.getParameter("streetInput");
		String zipCode = request.getParameter("zipCodeInput");
		String town = request.getParameter("townInput");
		String newPassword = request.getParameter("newPasswordInput");
		String confirmPassword = request.getParameter("confirmPasswordInput");
		String currentPassword = request.getParameter("currentPasswordInput");
		int credit = currentUser.getCredit();
		boolean admin = currentUser.isAdmin();
		
		if (userName.equals("")) {
			userName = currentUser.getUserName();
		}
		if (lastName.equals("")) {
			lastName = currentUser.getLastName();
		}
		if (firstName.equals("")) {
			firstName = currentUser.getFirstName();
		}
		if (email.equals("")) {
			email = currentUser.getEmail();
		}
		if (phone.equals("")) {
			phone = currentUser.getPhone();
		}
		if (street.equals("")) {
			street = currentUser.getStreet();
		}
		if (zipCode.equals("")) {
			zipCode = currentUser.getZipCode();
		}
		if (town.equals("")) {
			town = currentUser.getTown();
		}
		if (newPassword.equals(confirmPassword) && newPassword.equals("")) {
			newPassword = currentUser.getPassword();
			confirmPassword = currentUser.getPassword();
		}
		
		User editUser = new User(userId, userName, lastName, firstName, email, phone, street, zipCode, town, newPassword, credit, admin);
		
		System.out.println(editUser.toString());
		
		try {
			if(!currentPassword.equals(currentUser.getPassword())) {
				throw new IHMException("IHM Error - The password seems wrong. Please check again.");
			}
			if(!newPassword.equals(confirmPassword)) {
				throw new IHMException("IHM Error - The new password does not match confirmation password. Please check again.");
			}
			um.updateUser(editUser);
			System.out.println(editUser.toString());
			request.setAttribute("info", "Your account has been updated.");
			session.setAttribute("currentUser", editUser);
			RequestDispatcher rd = request.getRequestDispatcher("/ServletShowUser?userId=" + currentUser.getUserId());
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
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/user/editUser.jsp");
			rd.forward(request, response);
		} 
	}

}
