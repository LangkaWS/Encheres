package fr.eni.encheres.ihm.servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.PickUpManager;
import fr.eni.encheres.bll.bo.Article;
import fr.eni.encheres.bll.bo.PickUp;
import fr.eni.encheres.bll.bo.User;

/**
 * Servlet implementation class ServletNewAuction
 */
@WebServlet("/newAuction")
public class ServletNewAuction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PickUpManager pm = new PickUpManager();
	ArticleManager am = new ArticleManager();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/auction/newAuction.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String articleName = request.getParameter("articleName");
		String articleDescription = request.getParameter("articleDescription");
		Integer category = Integer.parseInt(request.getParameter("category"));
		Integer articleStartPrice = Integer.parseInt(request.getParameter("articleStartPrice"));
		LocalDateTime startDate = LocalDateTime.parse(request.getParameter("startDate"));
		LocalDateTime endDate = LocalDateTime.parse(request.getParameter("endDate"));
		String street = request.getParameter("street");
		String zipCode = request.getParameter("zipCode");
		String town = request.getParameter("town");
		
		Integer sellerId = ((User) request.getSession().getAttribute("currentUser")).getUserId();
		
		PickUp pickUp = new PickUp(street, zipCode, town);
		
		Article article = new Article(articleName, articleDescription, startDate, endDate, articleStartPrice, "created", sellerId, category, 0);
		
		try {
			pm.addPickUp(pickUp);
			article.setPickUpId(pickUp.getPickUpId());
			am.addArticle(article);
			
			response.sendRedirect(request.getContextPath() + "/auction?id=" + article.getArticleId());
		} catch (BLLException e) {
			e.printStackTrace();
			
			request.setAttribute("art", article);
			request.setAttribute("pickUp", pickUp);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/auction/newAuction.jsp");
			rd.forward(request, response);
		}
		
		
	}

}
