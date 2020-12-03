package fr.eni.encheres.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.bo.Article;
import fr.eni.encheres.bll.bo.Category;
import fr.eni.encheres.bll.bo.PickUp;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.SingleIdDAO;
import fr.eni.encheres.dal.UserDAO;

public class ArticleManager {
	
	private ArticleDAO articleDAO;
	private UserDAO userDAO;
	private SingleIdDAO<Category> categoryDAO;
	private SingleIdDAO<PickUp> pickUpDAO;
	
	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
		this.userDAO = DAOFactory.getUserDAO();
		this.categoryDAO = DAOFactory.getCategoryDAO();
		this.pickUpDAO = DAOFactory.getPickUpDAO();
	}
	
	public void addArticle(Article a) throws BLLException {
		if (this.validateArticle(a)) {
			try {
				articleDAO.insert(a);
			} catch (DALException e) {
				e.printStackTrace();
				throw new BLLException("Inserting article failed.", e);
			}
		} else {
			throw new BLLException("Article invalid. Please check again data.");
		}
	}
	
	public void updateArticle(Article a) throws BLLException {
		if (this.validateArticle(a)) {
			try {
				articleDAO.update(a);
			} catch (DALException e) {
				e.printStackTrace();
				throw new BLLException("Updating article failed.", e);
			}
		} else {
			throw new BLLException("Article invalid. Please check again data.");
		}
	}
	
	public void deleteArticle(int id) throws BLLException {
		try {
			articleDAO.delete(id);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Article deletion failed - ", e);
		}
	}
	
	public List<Article> getAllArticles() throws BLLException {
		List<Article> list = new ArrayList<>();
		try {
			list = articleDAO.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Selection of all articles failed - ", e);
		}
		return list;
	}
	
	
	public Article getArticleById(int id) throws BLLException {
		Article article = null;
		try {
			article = articleDAO.selectById(id);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Article selection by ID (id: " + id + ") failed - ", e);
		}
		return article;
	}
	
	public List<Article> getArticlesByCategory(int id) throws BLLException {
		List<Article> list = new ArrayList<>();
		try {
			list = articleDAO.selectArticlesByCategory(id);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Article selection by category (category ID: " + id + ") failed - ", e);
		}
		return list;
	}
	
	public List<Article> getArticlesByName(String name) throws BLLException {
		List<Article> list = new ArrayList<>();
		try {
			list = articleDAO.selectArticlesByName(name);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Article selection by name (name: " + name + ") failed - ", e);
		}
		return list;
	}
	
	public List<Article> getArticlesInProgress() throws BLLException {
		List<Article> list = new ArrayList<>();
		try {
			list = articleDAO.selectArticlesInProgress();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Selection of in progress articles failed - ", e);
		}
		return list;
	}
	
	public List<Article> getArticlesByParticipatingBuyer(int buyerId) throws BLLException {
		List<Article> list = new ArrayList<>();
		try {
			list = articleDAO.selectArticlesByParticipatingBuyer(buyerId);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Selection of articles where user " + buyerId + " participates failed - ", e);
		}
		return list;
	}
	
	public List<Article> getInProgressArticlesByParticipatingBuyer(int buyerId) throws BLLException {
		List<Article> list = new ArrayList<>();
		try {
			list = articleDAO.selectArticlesInProgressByParticipatingBuyer(buyerId);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Selection of in progress articles where user " + buyerId + " participates failedd - ", e);
		}
		return list;
	}
	
	public List<Article> getWonArticles(int buyerId) throws BLLException {
		List<Article> list = new ArrayList<>();
		try {
			list = articleDAO.selectWonArticles(buyerId);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Selection of articles which user " + buyerId + " have won failed - ", e);
		}
		return list;
	}
	
	public List<Article> getArticlesOfSeller(int sellerId) throws BLLException {
		List<Article> list = new ArrayList<>();
		try {
			list = articleDAO.selectArticlesOfSeller(sellerId);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Selection of articles of seller " + sellerId + " failed - ", e);
		}
		return list;
	}
	
	public List<Article> getArticlesOfSellerByState(int sellerId, String state) throws BLLException {
		List<Article> list = new ArrayList<>();
		try {
			list = articleDAO.selectArticlesOfSellerByState(sellerId, state);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Selection of " + state + " articles of seller " + sellerId + " failed - ", e);
		}
		return list;
	}
		
	private boolean validateArticle(Article a) throws BLLException {
		boolean isValid = true;
		if (!this.validateArticleName(a.getName())) {
			isValid = false;
			throw new BLLException("Article name is invalid.");
		}
		if (!this.validateArticleDescription(a.getDescription())) {
			isValid = false;
			throw new BLLException("Article description is invalid.");
		}
		if (!this.validateArticleStartDate(a.getAuctionStartDate())) {
			isValid = false;
			throw new BLLException("Article start date is invalid.");
		}
		if (!this.validateArticleEndDate(a.getAuctionEndDate(), a.getAuctionStartDate())) {
			isValid = false;
			throw new BLLException("Article end date is invalid.");
		}
		if (!this.validateArticleStartPrice(a.getStartPrice())) {
			isValid = false;
			throw new BLLException("Article start price is invalid.");
		}
		if (!this.validateArticleState(a.getState())) {
			isValid = false;
			throw new BLLException("Article state is invalid.");
		}
		if (!this.validateArticleSellerId(a.getSellerId())) {
			isValid = false;
			throw new BLLException("Article seller id is invalid.");
		}
		/*
		if (!this.validateArticleBuyerId(a.getBuyerId())) {
			isValid = false;
			throw new BLLException("Article buyer id is invalid.");
		}
		*/
		if (!this.validateArticleCategoryId(a.getCategoryId())) {
			isValid = false;
			throw new BLLException("Article category id is invalid.");
		}
		if (!this.validateArticlePickUpId(a.getPickUpId())) {
			isValid = false;
			throw new BLLException("Article pick-up id is invalid.");
		}
		return isValid;
	}
	
	private boolean validateArticleName(String name) throws BLLException {
		boolean isValid = true;
		if(name.equals("")) {
			isValid = false;
			throw new BLLException("The name can't be empty.");
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
		return isValid;
	}
	
	private boolean validateArticleEndDate(LocalDateTime endDate, LocalDateTime startDate) throws BLLException {
		boolean isValid = true;
		if (endDate.isBefore(startDate)) {
			isValid = false;
			throw new BLLException("The end date of the auction can't be before start date.");
		}
		return isValid;
	}
	
	private boolean validateArticleStartPrice(Integer price) throws BLLException {
		boolean isValid = true;
		if (price < 0) {
			isValid = false;
			throw new BLLException("The price can't be negative.");
		}
		return isValid;
	}
	
	private boolean validateArticleState(String state) throws BLLException {
		boolean isValid = true;
		if (!state.equals("created") && !state.equals("in progress") && !state.equals("ended")) {
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
	
	/*
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
	*/
	
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
