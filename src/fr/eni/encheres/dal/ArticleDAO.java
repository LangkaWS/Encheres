package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bll.bo.Article;
import fr.eni.encheres.bll.bo.User;

public interface ArticleDAO extends DAO<Article> {
	
	//Select by category
	
	//Select by article name
	
	//Select by "en cours"
	
	//Select auctions to which a buyer participate
	//SELECT * FROM BIDS, ARTICLES WHERE bids.buyerId = userId INNER JOIN bids.articleId = articles.articleId INNER JOIN bids.buyerId = users.userID
	//date - montant - nom article - catégorie
	
	//Select won auctions by a user
	public List<Article> selectWonArticles(User buyer) throws DALException;
	
	//Select auctions by sellerId
	public List<Article> selectArticlesOfSeller(User seller) throws DALException;
	
	/*
	 * REMOVED AND REPLACED BY selectArticleOfSellerByState(User seller, String state)
	 * 
	//Select created auctions of an user
	public List<Article> selectCreatedArticles(User seller) throws DALException;
	
	//Select in-progress auctions of an user
	public List<Article> selectInProgressArticles(User seller) throws DALException;
	
	//Select ended auctions of an user
	
	public List<Article> selectEndedArticles(User seller) throws DALException;
	*/
	public List<Article> selectArticlesOfSellerByState(User seller, String state) throws DALException;

}
