package fr.eni.encheres.servlets;

import fr.eni.encheres.bll.bo.Category;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.bo.Article;
import fr.eni.encheres.bll.bo.PickUp;
import fr.eni.encheres.bll.bo.User;
import fr.eni.encheres.dal.ArticleDAO;
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
		
		DAO<User> userDAO = DAOFactory.getUserDAO();
		ArticleDAO articleDAO = DAOFactory.getArticleDAO();
		DAO<PickUp> pickUpDAO = DAOFactory.getPickUpDAO();
		DAO<Category> categoryDAO = DAOFactory.getCategoryDAO();
		DAO<Bid> bidDAO = DAOFactory.getBidDAO();
		
		
		try {
			//User data access testing
			System.out.println("Users list : ");
			List<User> usersList = userDAO.selectAll();
			for(User u : usersList) {
				System.out.println(u.toString());
				System.out.println();
			}
			
			/*
			//Article inserting testing
			System.out.println("Insertion of a new article : ");
			Article a2 = new Article("Karcher", "Karcher 3200 très peu servi, parfait état de fonctionnement", LocalDateTime.parse("2020-11-30T15:30:45.157"), LocalDateTime.parse("2020-12-15T15:30:05.520"), 100, "created", 3, 1, 1);
			articleDAO.insert(a2);
			System.out.println(a2.toString());
			*/
			
			//Article data access testing
			System.out.println("Selection of the article with id = 2 : ");
			Article a = new Article();
			a.setArticleId(2);
			 a = articleDAO.selectById(a);
			System.out.println(a.toString());

			//Pick-up data access testing
			System.out.println("Selection of all pick-ups : ");
			List<PickUp> pickUpslist = pickUpDAO.selectAll();
			for(PickUp pu : pickUpslist) {
				System.out.println(pu.toString());
			}
			
			//Category data access testing
			System.out.println("Selection of the category with id = 1 : ");
			Category c = new Category();
			c.setCategoryId(1);
			c = categoryDAO.selectById(c);
			System.out.println(c.toString());
			
			//Bid data access testing
			System.out.println("Selection of all bids : ");
			List<Bid> bidsList = bidDAO.selectAll();
			for (Bid bid : bidsList) {
				System.out.println(bid.toString());
			}
			System.out.println("Selection by id : ");
			Bid b = new Bid();
			b.setBuyerId(3);
			b.setArticleId(2);
			b = bidDAO.selectById(b);
			System.out.println(b.toString());
			
			//Precise article selection
			List<Article> list = new ArrayList<>();
			User user = new User();
			user.setUserId(2);
			list = articleDAO.selectArticlesOfSeller(user.getUserId());
			for(Article article : list) {
				System.out.println(article.toString());
			}
			list = articleDAO.selectArticlesOfSellerByState(user.getUserId(), "ended");
			for(Article article : list) {
				System.out.println(article.toString());
			}
			
			//Articles selection by category
			System.out.println("Selection by category : ");
			list = articleDAO.selectArticlesByCategory(1);
			for (Article article : list) {
				System.out.println(article.toString());
			}
			
			//Articles selection by name
			System.out.println("Selection by name : ");
			list = articleDAO.selectArticlesByName("Horloge");
			for (Article article : list) {
				System.out.println(article.toString());
			}
			
			//Articles selection by state in progress
			System.out.println("Selection by state in progress : ");
			list = articleDAO.selectArticlesInProgress("in progress");
			for (Article article : list) {
				System.out.println(article.toString());
			}
			
			//Articles selection by participating buyer
			System.out.println("Selection by participating buyer : ");
			list = articleDAO.selectArticlesByParticipatingBuyer(2);
			for (Article article : list) {
				System.out.println(article.toString());
			}

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
