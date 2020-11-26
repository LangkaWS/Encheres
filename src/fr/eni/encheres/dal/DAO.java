package fr.eni.encheres.dal;

import java.util.List;

public interface DAO<T> {
	
	public void insert(T data) throws DALException;
	
	public void update(T data) throws DALException;
	
	public void delete(T data) throws DALException;
	
	public List<T> selectAll() throws DALException;
	
	public T selectByIds(int id1, int id2) throws DALException;
	
	public T selectById(int id) throws DALException;

}
