package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bll.bo.Article;

public interface ArticleDAO extends SingleIdDAO<Article> {
	
	public List<Article> selectArticlesByCategory(int categoryId) throws DALException;
	
	public List<Article> selectArticlesByName(String name) throws DALException;
	
	public List<Article> selectArticlesInProgress(String state) throws DALException;
	
	public List<Article> selectArticlesByParticipatingBuyer(int buyerId) throws DALException;
	
	public List<Article> selectWonArticles(int buyerId) throws DALException;
	
	public List<Article> selectArticlesOfSeller(int sellerId) throws DALException;

	public List<Article> selectArticlesOfSellerByState(int sellerId, String state) throws DALException;

}
