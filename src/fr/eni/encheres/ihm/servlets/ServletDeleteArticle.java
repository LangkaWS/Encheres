package fr.eni.encheres.ihm.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.bo.Article;
import fr.eni.encheres.bll.bo.User;

/**
 * Servlet implementation class ServletDeleteArticle
 */
@WebServlet("/delete/auction")
public class ServletDeleteArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ArticleManager am = new ArticleManager();
       
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
					
					response.sendRedirect("/index");
				} else if(!art.getState().equals("created")) {
					
					request.setAttribute("exception", "This article cannot be deleted anymore (auction has begun or ended)");
					response.sendRedirect("/auction?id="+articleId);
				} else {
					
					am.deleteArticle(articleId);
					
					response.sendRedirect(request.getContextPath());
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
		doGet(request, response);
	}

}
