package fr.eni.encheres.bll;

import java.time.LocalDateTime;

import fr.eni.encheres.bll.bo.Article;
import fr.eni.encheres.bll.bo.Category;
import fr.eni.encheres.bll.bo.PickUp;
import fr.eni.encheres.bll.bo.User;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.SingleIdDAO;

public class ArticleManager {
	
	/* Validates :
	 * 
	 * [v] name <= 30, not null
	 * [v] description varchar(300) not null
	 * [v] auctionStartDate datetime not null, not passed
	 * [v] auctionEndDate datetime not null, after startDate
	 * [v] startPrice int not null,
	 * [v] sellingPrice int
	 * [v] state varchar(30)
	 * [v] sellerId int not null
	 * [v] buyerId int no need of validate at article instantiation, only for update with bid
	 * [v] categoryId int not null
	 * [v] pickUpId int not null
	 * 
	 * */
	
	private ArticleDAO articleDAO;
	private SingleIdDAO<User> userDAO;
	private SingleIdDAO<Category> categoryDAO;
	private SingleIdDAO<PickUp> pickUpDAO;
	
	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
		this.userDAO = DAOFactory.getUserDAO();
		this.categoryDAO = DAOFactory.getCategoryDAO();
		this.pickUpDAO = DAOFactory.getPickUpDAO();
	}
	
	public void addArticle(Article a) throws BLLException {
		try {
			if (a.getArticleId() != null) {
				throw new BLLException("Inserting article failed, article already exists.");
			}
			if (!this.validateArticleName(a.getName())) {
				throw new BLLException("Article invalid.");
			}
			if (!this.validateArticleDescription(a.getDescription())) {
				throw new BLLException("Article invalid.");
			}
			if (!this.validateArticleStartDate(a.getAuctionStartDate())) {
				throw new BLLException("Article invalid.");
			}
			if (!this.validateArticleEndDate(a.getAuctionEndDate(), a.getAuctionStartDate())) {
				throw new BLLException("Article invalid.");
			}
			if (!this.validateArticleStartPrice(a.getStartPrice())) {
				throw new BLLException("Article invalid.");
			}
			if (!this.validateArticleState(a.getState())) {
				throw new BLLException("Article invalid.");
			}
			if (!this.validateArticleSellerId(a.getSellerId())) {
				throw new BLLException("Article invalid.");
			}
			if (!this.validateArticleBuyerId(a.getBuyerId())) {
				throw new BLLException("Article invalid.");
			}
			if (!this.validateArticleCategoryId(a.getCategoryId())) {
				throw new BLLException("Article invalid.");
			}
			if (!this.validateArticlePickUpId(a.getPickUpId())) {
				throw new BLLException("Article invalid.");
			}
			articleDAO.insert(a);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Inserting article failed.", e);
		}
	}
	
	private boolean validateArticleName(String name) throws BLLException {
		boolean isValid = true;
		if(name.equals("")) {
			isValid = false;
			throw new BLLException("The name can't be empty.");
		}
		if(name == null) {
			isValid = false;
			throw new BLLException("The name can't be null.");
		}
		if(name.length() > 30) {
			isValid = false;
			throw new BLLException("The name can't be longer than 30 characters.");
		}
		return isValid;
	}
	
	private boolean validateArticleDescription(String description) throws BLLException {
		boolean isValid = true;
		if(description.equals("")) {
			isValid = false;
			throw new BLLException("The description can't be empty.");
		}
		if(description == null) {
			isValid = false;
			throw new BLLException("The description can't be null.");
		}
		if(description.length() > 300) {
			isValid = false;
			throw new BLLException("The description can't be longer than 300 characters.");
		}
		return isValid;
	}
	
	private boolean validateArticleStartDate(LocalDateTime startDate) throws BLLException {
		boolean isValid = true;
		if (startDate.isBefore(LocalDateTime.now())) {
			isValid = false;
			throw new BLLException("The start date of the auction can't be passed.");
		}
		if (startDate == null) {
			isValid = false;
			throw new BLLException("The start date of the auction can't be null.");
		}
		return isValid;
	}
	
	private boolean validateArticleEndDate(LocalDateTime endDate, LocalDateTime startDate) throws BLLException {
		boolean isValid = true;
		if (endDate.isBefore(startDate)) {
			isValid = false;
			throw new BLLException("The end date of the auction can't be before start date.");
		}
		if (endDate == null) {
			isValid = false;
			throw new BLLException("The end date of the auction can't be null.");
		}
		return isValid;
	}
	
	private boolean validateArticleStartPrice(Integer price) throws BLLException {
		boolean isValid = true;
		if (price < 0) {
			isValid = false;
			throw new BLLException("The price can't be negative.");
		}
		if (price == null) {
			isValid = false;
			throw new BLLException("The price can't be null.");
		}
		return isValid;
	}
	
	private boolean validateArticleState(String state) throws BLLException {
		boolean isValid = true;
		if (state != "created" || state != "in progress" || state != "ended") {
			isValid = false;
			throw new BLLException("The state isn't valid.");
		}
		return isValid;
	}
	
	private boolean validateArticleSellerId(Integer sellerId) throws BLLException {
		boolean isValid = true;
		if (sellerId == null) {
			isValid = false;
			throw new BLLException("Seller can't be null.");
		} else {
			try {
				this.userDAO.selectById(sellerId);
			} catch (DALException e) {
				e.printStackTrace();
				throw new BLLException("Wrong id of seller.");
			}
		}
		return isValid;
	}
	
	private boolean validateArticleBuyerId(Integer buyerId) throws BLLException {
		boolean isValid = true;
		if (buyerId == null) {
			isValid = false;
			throw new BLLException("Buyer can't be null.");
		} else {
			try {
				this.userDAO.selectById(buyerId);
			} catch (DALException e) {
				e.printStackTrace();
				throw new BLLException("Wrong id of buyer.");
			}
		}
		return isValid;
	}
	
	private boolean validateArticleCategoryId(Integer categoryId) throws BLLException {
		boolean isValid = true;
		if (categoryId == null) {
			isValid = false;
			throw new BLLException("Category can't be null.");
		} else {
			try {
				this.categoryDAO.selectById(categoryId);
			} catch (DALException e) {
				e.printStackTrace();
				throw new BLLException("Wrong id of category.");
			}
		}
		return isValid;
	}
	
	private boolean validateArticlePickUpId(Integer pickUpId) throws BLLException {
		boolean isValid = true;
		if (pickUpId == null) {
			isValid = false;
			throw new BLLException("Pick-up adress can't be null.");
		} else {
			try {
				this.pickUpDAO.selectById(pickUpId);
			} catch (DALException e) {
				e.printStackTrace();
				throw new BLLException("Wrong id of pick-up adress.");
			}
		}
		return isValid;
	}
}
