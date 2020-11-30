package fr.eni.encheres.ihm.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.bo.Article;

/**
 * Servlet implementation class ServletFilterArticles
 */
@WebServlet("/filter")
public class ServletFilterArticles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("selectCategory");
		String contains = request.getParameter("filterSearch");
		//List<Category> catList = (List<Category>) this.getServletContext().getAttribute("catList");
		
		ArticleManager am = new ArticleManager();
		
		List<Article> artList = new ArrayList<>();
		List<Article> tmpList = new ArrayList<>();
		List<Article> filteredList = new ArrayList<>();
		try {
			artList = am.getArticlesInProgress();
			for(Article a : artList) {
				if(a.getName().contains(contains)) {
					tmpList.add(a);
				}
			}
			if(!category.equals("all")) {
				for(Article a : tmpList) {
					if(a.getCategoryId() == Integer.parseInt(category)) {
						filteredList.add(a);
					}
				}
			} else {
				filteredList = tmpList;
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("artList", filteredList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
