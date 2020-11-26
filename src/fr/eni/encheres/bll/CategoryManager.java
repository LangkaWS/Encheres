package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.bo.Category;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAO;
import fr.eni.encheres.dal.DAOFactory;

public class CategoryManager {
	
	private DAO<Category> categoryDAO;
	
	public CategoryManager() {
		this.categoryDAO = DAOFactory.getCategoryDAO();
	}
	
	public void addCategory(Category c) throws BLLException {
		if (c.getCategoryId() != null) {
			throw new BLLException("Category already exists - ");
		}
		try {
			if (!this.validateCategory(c.getName())) {
				throw new BLLException("Category adding failed - name can't be empty.");
			}
			categoryDAO.insert(c);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Category adding failed - ", e);
		}
	}
	
	public List<Category> getListCategories() throws BLLException {
		List<Category> categories = new ArrayList<Category>();
		try {
			categories = categoryDAO.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Category listing failed - ", e);
		}
		return categories;
	}
	
	public void updateCategory(Category c) throws BLLException {
		try {
			if (!this.validateCategory(c.getName())) {
				throw new BLLException("Category updating failed - name can't be empty.");
			}
			categoryDAO.update(c);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Category updating failed - ", e);
		}
	}
	
	public void removeCategory(Category c) throws BLLException {
		try {
			categoryDAO.delete(c);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Category deleting failed - ", e);
		}
	}
	
	public void selectCategory(int id) throws BLLException {
		try {
			categoryDAO.selectById(id);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Category selecting failed - ", e);
		}
	}
	
	private boolean validateCategory(String name) throws BLLException {
		boolean isValid = true;
		if(name.equals("")) {
			isValid = false;
			throw new BLLException("The name can't be empty.");
		}
		if(name == null) {
			isValid = false;
			throw new BLLException("The name can't be null.");
		}
		if(name.length() > 30) {
			isValid = false;
			throw new BLLException("The name can't be longer than 30 characters.");
		}
		return isValid;
	}

}
