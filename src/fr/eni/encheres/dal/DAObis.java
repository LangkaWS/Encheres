package fr.eni.encheres.dal;

import java.util.List;

public interface DAObis<T> {
	
	public void insert(T data) throws DALException;
	
	public void update(T data) throws DALException;
	
	public void delete(T data) throws DALException;
	
	public List<T> selectAll() throws DALException;
	
	public T selectById(int firstId, int secondId) throws DALException;

}
