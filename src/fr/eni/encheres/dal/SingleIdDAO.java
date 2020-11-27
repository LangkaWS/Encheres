package fr.eni.encheres.dal;

public interface SingleIdDAO<T> extends DAO<T> {
	
	public void delete(int id) throws DALException;
	
	public T selectById(int id) throws DALException;

}
