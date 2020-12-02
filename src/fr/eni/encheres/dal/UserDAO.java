package fr.eni.encheres.dal;

import fr.eni.encheres.bll.bo.User;

public interface UserDAO extends SingleIdDAO<User> {
	
	public User selectUserByEmail(String email) throws DALException;
	
	public User selectUserByUserName(String userName) throws DALException;

}
