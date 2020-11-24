package fr.eni.encheres.dal;

import fr.eni.encheres.bll.bo.Article;
import fr.eni.encheres.bll.bo.PickUp;
import fr.eni.encheres.bll.bo.Category;
import fr.eni.encheres.bll.bo.User;

public abstract class DAOFactory {
	
	public static DAO<User> getUserDAO() {
		return new UserDAOJDBCImpl();
	}
	
	public static DAO<Article> getArticleDAO() {
		return new ArticleDAOJDBCImpl();
	}
	
	public static DAO<PickUp> getPickUpDAO() {
		return new PickUpDAOJDBCImpl();
	}
		
	public static DAO<Category> getCategoryDAO() {
		return new CategoryDAOJDBCImpl();

	}

}
