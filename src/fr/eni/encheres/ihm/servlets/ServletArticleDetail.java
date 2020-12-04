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
import fr.eni.encheres.bll.CategoryManager;
import fr.eni.encheres.bll.PickUpManager;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bll.bo.Article;
import fr.eni.encheres.bll.bo.Category;
import fr.eni.encheres.bll.bo.PickUp;
import fr.eni.encheres.bll.bo.User;

/**
 * Servlet implementation class ServletArticleDetail
 */
@WebServlet("/auctionDetail")
public class ServletArticleDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer articleId = Integer.parseInt(request.getParameter("id"));
		
		Article art = null;
		Category cat = null;
		PickUp pu = null;
		User seller = null;
		User buyer = null;
		
		ArticleManager am = new ArticleManager();
		CategoryManager cm = new CategoryManager();
		PickUpManager pm = new PickUpManager();
		UserManager um = new UserManager();
		
		try {
			art = am.getArticleById(articleId);
			cat = cm.getCategory(art.getCategoryId());
			pu = pm.getPickUp(art.getPickUpId());
			seller = um.getUser(art.getSellerId());
			buyer = um.getUser(art.getBuyerId());
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("art", art);
		request.setAttribute("category", cat.getName());
		request.setAttribute("pu", pu);
		request.setAttribute("seller", seller.getUserName());
		request.setAttribute("buyer", buyer == null ? "noBuyer" : buyer.getUserName());
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/auction/auctionDetail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
