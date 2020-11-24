package fr.eni.encheres.dal;

import fr.eni.encheres.bll.bo.Article;
import fr.eni.encheres.bll.bo.User;

public abstract class DAOFactory {
	
	public static DAO<User> getUserDAO() {
		return new UserDAOJDBCImpl();
	}
	
	public static DAO<Article> getArticleDAO() {
		return new ArticleDAOJDBCImpl();
	}

}
