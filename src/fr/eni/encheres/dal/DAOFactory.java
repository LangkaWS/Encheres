package fr.eni.encheres.dal;

import fr.eni.encheres.bll.bo.PickUp;
import fr.eni.encheres.bll.bo.Category;
import fr.eni.encheres.bll.bo.User;

public abstract class DAOFactory {
	
	public static SingleIdDAO<User> getUserDAO() {
		return new UserDAOJDBCImpl();
	}
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJDBCImpl();
	}
	
	public static SingleIdDAO<PickUp> getPickUpDAO() {
		return new PickUpDAOJDBCImpl();
	}
		
	public static SingleIdDAO<Category> getCategoryDAO() {
		return new CategoryDAOJDBCImpl();

	}
	
	public static BidDAO getBidDAO() {
		return new BidDAOJDBCImpl();

	}

}
