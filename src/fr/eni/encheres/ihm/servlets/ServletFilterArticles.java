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
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.bo.Article;
import fr.eni.encheres.bll.bo.User;

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
		
		HttpSession session = request.getSession();
		int userId = ((User) session.getAttribute("currentUser")).getUserId();
		
		String category = request.getParameter("selectCategory");
		String contains = request.getParameter("filterSearch");
		String buyOrSell = request.getParameter("filterBuyOrSell");
		String buyInProgressAuctions = request.getParameter("buyInProgressAuctions");
		String buyParticipatingInProgressAuctions = request.getParameter("buyParticipatingInProgressAuctions");
		String buyParticipatingEnded = request.getParameter("buyParticipatingEnded");
		String sellInProgress = request.getParameter("sellInProgress");
		String sellCreated = request.getParameter("sellCreated");
		String sellEnded = request.getParameter("sellEnded");
		
		ArticleManager am = new ArticleManager();
		
		List<Article> requestList = new ArrayList<>();
		List<Article> tmpList = new ArrayList<>();
		List<Article> tmp2List = new ArrayList<>();
		List<Article> filteredList = new ArrayList<>();
		try {
			//If the user is a buyer
			if(buyOrSell.equals("buy")) {
				
				//Listing in progress auctions
				if(buyInProgressAuctions != null && buyInProgressAuctions.equals("c")) {
					requestList = am.getArticlesInProgress();
					for(Article a : requestList) {
						if(!tmpList.contains(a)) {
							tmpList.add(a);
						}
					}
				}
				
				//Listing in progress auctions to which the user participates
				if(buyParticipatingInProgressAuctions != null && buyParticipatingInProgressAuctions.equals("c")) {
					requestList = am.getInProgressArticlesByParticipatingBuyer(userId);
					for(Article a : requestList) {
						if(!tmpList.contains(a)) {
							tmpList.add(a);
						}
					}
				}
				
				//Listing ended auctions where the user is the final buyer
				if(buyParticipatingEnded != null && buyParticipatingEnded.equals("c")) {
					requestList = am.getWonArticles(userId);
					for(Article a : requestList) {
						if(!tmpList.contains(a)) {
							tmpList.add(a);
						}
					}
				}
			}
			
			//If the user is a seller
			if(buyOrSell.equals("sell")) {
				
				//Listing seller's current selling list
				if(sellInProgress != null && sellInProgress.equals("c")) {
					requestList = am.getArticlesOfSellerByState(userId, "in progress");
					for(Article a : requestList) {
						if(!tmpList.contains(a)) {
							tmpList.add(a);
						}
					}
				}
				
				//Listing seller's created articles (but not in progress)
				if(sellCreated != null && sellCreated.equals("c")) {
					requestList = am.getArticlesOfSellerByState(userId, "created");
					for(Article a : requestList) {
						if(!tmpList.contains(a)) {
							tmpList.add(a);
						}
					}
				}
				
				//Listing seller's ended articles
				if(sellEnded != null && sellEnded.equals("c")) {
					requestList = am.getArticlesOfSellerByState(userId, "ended");
					for(Article a : requestList) {
						if(!tmpList.contains(a)) {
							tmpList.add(a);
						}
					}
				}
			}
			
			//Filter by name
			if(!contains.equals("")) {
				for(Article a : tmpList) {
					if(a.getName().contains(contains)) {
						tmp2List.add(a);
					}
				}
			} else {
				tmp2List = tmpList;
			}
			
			//Filter by category
			if(!category.equals("all")) {
				for(Article a : tmp2List) {
					if(a.getCategoryId() == Integer.parseInt(category)) {
						filteredList.add(a);
					}
				}
			} else {
				filteredList = tmp2List;
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
