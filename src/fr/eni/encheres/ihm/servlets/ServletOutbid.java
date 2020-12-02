package fr.eni.encheres.ihm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.BidManager;
import fr.eni.encheres.bll.bo.Bid;
import fr.eni.encheres.bll.bo.User;

/**
 * Servlet implementation class ServletOutbid
 */
@WebServlet("/ServletOutbid")
public class ServletOutbid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Integer userId = ((User) session.getAttribute("currentUser")).getUserId();
		
		Integer articleId = Integer.parseInt(request.getParameter("submitButton"));
		Integer amount = Integer.parseInt(request.getParameter("newBidAmount"));
		
		BidManager bm = new BidManager();
		
		try {
			if(bm.getBid(articleId, userId) == null) {
				bm.addBid(new Bid(userId, articleId, amount));
			} else {
				bm.updateBid(new Bid(userId, articleId, amount));
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/auctionDetail?id=" + articleId);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
