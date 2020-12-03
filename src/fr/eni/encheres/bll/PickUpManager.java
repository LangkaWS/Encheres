package fr.eni.encheres.bll;

import fr.eni.encheres.bll.bo.PickUp;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.SingleIdDAO;

public class PickUpManager {
	
	private SingleIdDAO<PickUp> pickUpDAO;
	
	public PickUpManager() {
		this.pickUpDAO = DAOFactory.getPickUpDAO();
	}
	
	public void addPickUp(PickUp pickup) throws BLLException {
		if(this.validatePickUp(pickup)) {
			try {
				pickUpDAO.insert(pickup);
			} catch (DALException e) {
				e.printStackTrace();
				throw new BLLException("Pick-up adding failed - ", e);
			}
		} else {
			throw new BLLException("Invalid pick-up. Please check again data.");
		}
	}
	
	public void updatePickUp(PickUp pickup) throws BLLException {
		if(this.validatePickUp(pickup)) {
			try {
				pickUpDAO.update(pickup);
			} catch (DALException e) {
				e.printStackTrace();
				throw new BLLException("Pick-up update failed - ", e);
			}
		} else {
			throw new BLLException("Invalid pick-up. Please check again data.");
		}
	}
	
	public void deletePickUp(int id) throws BLLException {
		try {
			pickUpDAO.delete(id);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Pick-up deletion failed - ", e);
		}
	}
	
	public PickUp getPickUp(int id) throws BLLException {
		PickUp pu = null;
		try {
			pu = pickUpDAO.selectById(id);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Pick-up selection failed - ", e);
		}
		return pu;
	}
	
	public boolean validatePickUp(PickUp pickup) throws BLLException {
		boolean isValid = true;
		if(!this.validateStreet(pickup.getStreet())) {
			isValid = false;
			throw new BLLException("The street is invalid.");
		}
		if(!this.validateZipCode(pickup.getZipCode())) {
			isValid = false;
			throw new BLLException("The zip code is invalid.");
		}
		if(!this.validateTown(pickup.getTown())) {
			isValid = false;
			throw new BLLException("The town is invalid.");
		}
		return isValid;
	}
	
	private boolean validateStreet(String street) throws BLLException {
		boolean isValid = true;
		
		if(!(street.length() <= 50)) {
			isValid = false;
			throw new BLLException("The street must be up to 50 characters long.");
		}
		
		if(!street.matches("(?i)^[A-Za-zÀ-ÖØ-öø-ÿ0-9 ,.'-]+$")) {
			isValid = false;
			throw new BLLException("The street must contain only letters, numbers, white spaces and some special characters (,.'-)");
		}
		return isValid;
	}
	
	private boolean validateZipCode(String zipCode) throws BLLException {
		boolean isValid = true;
		if(!zipCode.matches("^\\d{5}$")) {
			isValid = false;
			throw new BLLException("The zip code must be 5 digits long.");
		}
		return isValid;
	}
	
	private boolean validateTown(String town) throws BLLException {
		boolean isValid = true;
		
		if(!(town.length() <= 30)) {
			isValid = false;
			throw new BLLException("The town must be up to 30 characters long.");
		}
		
		if(!town.matches("(?i)^[A-Za-zÀ-ÖØ-öø-ÿ ,.'-]+$")) {
			isValid = false;
			throw new BLLException("The town must contain only letters, white spaces and some special characters (,.'-)");
		}
		return isValid;
	}

	
	

}
