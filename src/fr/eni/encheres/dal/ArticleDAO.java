package fr.eni.encheres.dal;

import fr.eni.encheres.bll.bo.Article;

public interface ArticleDAO extends DAO<Article>{
	
	//Select by category
	
	//Select by article name
	
	//Select by "en cours"
	
	//Select by buyerId
	//SELECT * FROM BIDS, ARTICLES WHERE bids.buyerId = userId INNER JOIN bids.articleId = articles.articleId INNER JOIN bids.buyerId = users.userID
	//date - montant - nom article - catégorie

}
