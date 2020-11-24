package fr.eni.encheres.servlets;

import fr.eni.encheres.bll.bo.Category;
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
import fr.eni.encheres.bll.bo.PickUp;
import fr.eni.encheres.bll.bo.User;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.bll.bo.*;
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
		
		//Connection testing
		try {
			Connection con = ConnectionProvider.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DAO<User> userDAO = DAOFactory.getUserDAO();
		DAO<Article> articleDAO = DAOFactory.getArticleDAO();
		DAO<PickUp> pickUpDAO = DAOFactory.getPickUpDAO();
		DAO<Category> categoryDAO = DAOFactory.getCategoryDAO();
		
		try {
			//User data access testing
			System.out.println("Users list : ");
			List<User> usersList = userDAO.selectAll();
			for(User u : usersList) {
				System.out.println(u.toString());
				System.out.println();
			}
			
			//Article data access testing
			System.out.println("Selection of the article with id = 2 : ");
			Article a = articleDAO.selectById(2);
			System.out.println(a.toString());
			
			//Pick-up data access testing
			System.out.println("Selection of all pick-ups : ");
			List<PickUp> pickUpslist = pickUpDAO.selectAll();
			for(PickUp pu : pickUpslist) {
				System.out.println(pu.toString());
			}

			//Category data acess testing
			System.out.println("Selection of the category with id = 1 : ");
			Category c = categoryDAO.selectById(1);
			System.out.println(c.toString());
			
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
