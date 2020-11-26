package fr.eni.encheres.bll;

import java.time.LocalDateTime;

import fr.eni.encheres.bll.bo.Article;
import fr.eni.encheres.dal.DAO;
import fr.eni.encheres.dal.DAOFactory;

public class ArticleManager {
	
	private DAO<Article> articleDAO;

	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}
	
	public void addArticle(String name, String description, LocalDateTime auctionStartDate, LocalDateTime auctionEndDate,
			int startPrice, String state, int sellerId, int categoryId, int pickUpId) {
		
	}
}
