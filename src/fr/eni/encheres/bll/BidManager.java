package fr.eni.encheres.bll;

import fr.eni.encheres.bll.bo.Article;
import fr.eni.encheres.bll.bo.Bid;
import fr.eni.encheres.bll.bo.User;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.BidDAO;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UserDAO;

public class BidManager {
	
	private BidDAO bidDAO;
	private ArticleDAO articleDAO;
	private UserDAO userDAO;
	
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
	 * [v] On add : remove amount credit / add credit to previous max buyer
	 * [] On update : remove difference between previous and current amount / add credit to previous max buyer (if different)
	 */
	
	public void addBid(Bid bid) throws BLLException {
		Article article;
		User newBidder;
		try {
			article = articleDAO.selectById(bid.getArticleId());
			newBidder = userDAO.selectById(bid.getBuyerId());
			if(this.validateBid(bid, article, newBidder)) {
				//Give back credits to previous bidder
				User previousBidder = userDAO.selectById(article.getBuyerId());
				if(previousBidder != null) {
					previousBidder.setCredit(previousBidder.getCredit() + article.getSellingPrice());
				userDAO.update(previousBidder);
				}
				
				//Take credits from new bidder
				newBidder.setCredit(newBidder.getCredit() - bid.getAmount());
				userDAO.update(newBidder);
				
				//Update article selling price and buyer ID
				article.setSellingPrice(bid.getAmount());
				article.setBuyerId(bid.getBuyerId());
				articleDAO.update(article);
				
				//Insert bid into database
				bidDAO.insert(bid);
			}
		} catch (DALException e2) {
			e2.printStackTrace();
			throw new BLLException("Something went wrong...");
		}
	}
	
	public void updateBid(Bid bid) throws BLLException {
		Article article;
		User newBidder;
		try {
			article = articleDAO.selectById(bid.getArticleId());
			newBidder = userDAO.selectById(bid.getBuyerId());
			if(this.validateBid(bid, article, newBidder)) {
				User previousBidder = userDAO.selectById(article.getBuyerId());
				if(previousBidder.getUserId() == newBidder.getUserId()) {
					newBidder.setCredit(newBidder.getCredit() - (bid.getAmount() - article.getSellingPrice()));
					userDAO.update(newBidder);
				} else {
					//Give back credits to previous bidder
					previousBidder.setCredit(previousBidder.getCredit() + article.getSellingPrice());
					userDAO.update(previousBidder);
					//Take credits from new bidder
					newBidder.setCredit(newBidder.getCredit() - bid.getAmount());
					userDAO.update(newBidder);
				}
				//Update article selling price and buyer ID
				article.setSellingPrice(bid.getAmount());
				article.setBuyerId(bid.getBuyerId());
				articleDAO.update(article);
				
				//Update bid in database
				bidDAO.update(bid);
			}
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Something went wrong...");
		}
	}
	
	public Bid getBid(int articleId, int buyerId) throws BLLException {
		Bid bid = null;
		try {
			bid = bidDAO.selectByIds(articleId, buyerId);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Bid selection (articleId " + articleId + ", buyerId " + buyerId + ") failed - ", e);
		}
		return bid;
	}
	
	private boolean validateBid(Bid bid, Article article, User bidder) throws BLLException {
		boolean isValid = true;
		if(!validateBidArticle(article)) {
			isValid = false;
			throw new BLLException("Article is invalid.");
		}
		if(!validateBidAmount(bid, article)) {
			isValid = false;
			throw new BLLException("Amount is invalid.");
		}
		if(!validateBidBuyer(bid, bidder)) {
			isValid = false;
			throw new BLLException("Buyer is invalid.");
		}
		return isValid;
	}
	
	private boolean validateBidArticle(Article a) throws BLLException {
		boolean isValid = false;
		if(a != null) {
			if(a.getState().equals("in progress")) {
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
	
	private boolean validateBidBuyer(Bid bid, User bidder) throws BLLException {
		boolean isValid = false;
		if(bidder != null) {
			if(bidder.getCredit() >= bid.getAmount()) {
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
