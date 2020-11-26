package fr.eni.encheres.bll;

import fr.eni.encheres.bll.bo.Article;
import fr.eni.encheres.bll.bo.Bid;
import fr.eni.encheres.bll.bo.User;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAO;
import fr.eni.encheres.dal.DAOFactory;

public class BidManager {
	
	private DAO<Bid> bidDAO;
	private ArticleDAO articleDAO;
	private DAO<User> userDAO;
	
	public BidManager() {
		this.bidDAO = DAOFactory.getBidDAO();
		this.articleDAO = DAOFactory.getArticleDAO();
		this.userDAO = DAOFactory.getUserDAO();
	}
	
	/* Bid is valid if :
	 * [v] amount > current max
	 * [v] buyerCredit >= amount
	 * [v] article is in-progress
	 * [v] buyerId exists in USERS and is a number
	 * [v] ArticleID exists in ARTICLES and is a number
	 * [v] amount is a number
	 * [v] bidDate is a LocalDateTime
	 * 
	 * [] On add : remove amount credit / add credit to previous max buyer
	 * [] On update : remove difference between previous and current amount / add credit to previous max buyer (if different)
	 * [] On delete : add amount credit
	 */
	
	public void addBid(Bid bid) {
		
	}
	
	private boolean validateBid(Bid bid) throws BLLException {
		boolean isValid = true;
		Article a = new Article();
		a.setArticleId(bid.getArticleId());
		Article article = null;
		try {
			article = articleDAO.selectById(a);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Article selection failed - ", e);
		}
		if(!validateBidArticle(article)) {
			isValid = false;
			throw new BLLException("Article is invalid.");
		}
		if(!validateBidAmount(bid, article)) {
			isValid = false;
			throw new BLLException("Amount is invalid.");
		}
		if(!validateBidBuyer(bid.getBuyerId(), bid)) {
			isValid = false;
			throw new BLLException("Buyer is invalid.");
		}
		return isValid;
	}
	
	private boolean validateBidArticle(Article a) throws BLLException {
		boolean isValid = false;
		if(a != null) {
			if(a.getState().equals("in-progress")) {
				isValid = true;
			} else if(a.getState().equals("created")){
				throw new BLLException("Auction has not started yet.");
			} else if(a.getState().equals("ended")) {
				throw new BLLException("Auction is over. You can't participate anymore.");
			} else {
				throw new BLLException("Error in auction state : " + a.getState() + ". Should be created, in-progress or created.");
			}
		} else {
			throw new BLLException("Article doesn't exist.");
		}
		return isValid;
	}
	
	private boolean validateBidAmount(Bid bid, Article a) throws BLLException {
		boolean isValid = false;
		if(bid.getAmount() > a.getSellingPrice()) {
			isValid = true;
		} else {
			throw new BLLException("Bid amoount must be higher than current selling price.");
		}
		return isValid;
	}
	
	private boolean validateBidBuyer(int buyerId, Bid bid) throws BLLException {
		boolean isValid = false;
		User u = new User();
		u.setUserId(buyerId);
		try {
			u = userDAO.selectById(u);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Buyer selection failed - ", e);
		}
		if(u != null) {
			if(u.getCredit() >= bid.getAmount()) {
				isValid = true;
			} else {
				throw new BLLException("Buyer's credit is insufficient.");
			}
		} else {
			throw new BLLException("Buyer doesn't exist in database. Create user first.");
		}
		return isValid;
	}

}
