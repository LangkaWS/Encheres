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
 * Servlet implementation class ServletEditArticle
 */
@WebServlet("/edit/auction")
public class ServletEditArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ArticleManager am = new ArticleManager();
	PickUpManager pm = new PickUpManager();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		
		if (request.getParameter("id") == null) {
			request.setAttribute("warning", "Article not found.");
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
			rd.forward(request, response);
		} else {

			Integer articleId = Integer.parseInt(request.getParameter("id"));
			
			Article art = null;
			
			try {
				art = am.getArticleById(articleId);
				if (currentUser == null) {
					request.setAttribute("exception", "Access denied, you're not logged in.");
					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/user/signIn.jsp");
					rd.forward(request, response);
				} else if (currentUser.getUserId() != art.getSellerId()) {
					request.setAttribute("warning", "Access denied, you're not the owner of the article.");
					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("art", art);
					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/auction/editAuction.jsp");
					rd.forward(request, response);
				}
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		Integer articleId = Integer.parseInt(request.getParameter("id"));
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
		article.setArticleId(articleId);
		
		try {
			pm.addPickUp(pickUp);
			article.setPickUpId(pickUp.getPickUpId());
			am.updateArticle(article);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/auction?id=" + articleId);
		
	}

}
