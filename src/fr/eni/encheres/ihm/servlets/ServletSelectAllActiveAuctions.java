package fr.eni.encheres.ihm.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.CategoryManager;
import fr.eni.encheres.bll.bo.Article;
import fr.eni.encheres.bll.bo.Category;

/**
 * Servlet implementation class ServletSelectAllActiveAuctions
 */
@WebServlet("/index")
public class ServletSelectAllActiveAuctions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ArticleManager am = new ArticleManager();
	CategoryManager cm = new CategoryManager();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Article> artList = null;
		List<Category> catList = null;
		
		try {
			artList = am.getArticlesInProgress();
			catList = cm.getListCategories();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("artList", artList);
		this.getServletContext().setAttribute("catList", catList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
