package fr.eni.encheres.dal;

import fr.eni.encheres.bll.bo.Bid;

public interface BidDAO extends DAO<Bid> {
	
	public Bid selectByIds(int articleId, int buyerId) throws DALException;
	
}
