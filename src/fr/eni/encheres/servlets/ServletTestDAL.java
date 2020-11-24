package fr.eni.encheres.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.bo.Article;
import fr.eni.encheres.bll.bo.User;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAO;
import fr.eni.encheres.dal.DAOFactory;

/**
 * Servlet implementation class ServletTestDAL
 */
@WebServlet("/ServletTestDAL")
public class ServletTestDAL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			Connection con = ConnectionProvider.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//User c = new User("cloclo","Resti","Claude", "cloclo@gmx.com", "0123456789", "52 rue des pious", "68000", "Colmar", "myPassword");
		//User c = new User("bichette","Marou","Marie-Jeanine", "bichette@gmx.com", "0123456789", "52 rue des pious", "68000", "Colmar", "myPassword");
		DAO<User> userDAO = DAOFactory.getUserDAO();
		DAO<Article> articleDAO = DAOFactory.getArticleDAO();
		
		try {
			System.out.println("Users list : ");
			List<User> usersList = userDAO.selectAll();
			for(User u : usersList) {
				System.out.println(u.toString());
				System.out.println();
			}
			Article a = articleDAO.selectById(2);
			System.out.println(a.toString());
			/*
			System.out.println("Inserting...");
			userDAO.insert(c);
			System.out.println("Users list after insertion : ");
			usersList = userDAO.selectAll();
			for(User u : usersList) {
				System.out.println(u.toString());
				System.out.println();
			}
			*/
		} catch (DALException e) {
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
