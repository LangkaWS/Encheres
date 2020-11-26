package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bll.bo.User;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAO;
import fr.eni.encheres.dal.DAOFactory;

public class UserManager {
	
	private DAO<User> userDAO;
	
	public UserManager() {
		this.userDAO = DAOFactory.getUserDAO();
	}
	
	public void addUser(String userName, String lastName, String firstName, String email, String phone, String street, String zipCode, String town, String password) throws BLLException {
		User user = new User(userName, lastName, firstName, email, phone, street, zipCode, town, password);
		try {
			List<User> list = userDAO.selectAll();
			if(!this.validateUserName(userName, list)) {
				throw new BLLException("The user name is invalid.");
			}
			if(!this.validateLastName(lastName)) {
				throw new BLLException("The last name is invalid.");
			}
			if(!this.validateFirstName(firstName)) {
				throw new BLLException("The first name is invalid.");
			}
			if(!this.validateEmail(email, list)) {
				throw new BLLException("The user name is invalid.");
			}
			if(!this.validatePhone(phone)) {
				throw new BLLException("The phone number is invalid.");
			}
			if(!this.validateStreet(street)) {
				throw new BLLException("The street is invalid.");
			}
			if(!this.validateZipCode(zipCode)) {
				throw new BLLException("The zip code is invalid.");
			}
			if(!this.validateTown(town)) {
				throw new BLLException("The town is invalid.");
			}
			/*
			if(!this.validatePassword(password)) {
				throw new BLLException("The password is invalid.");
			}
			*/
			try {
				userDAO.insert(user);
			} catch (DALException e) {
				e.printStackTrace();
				throw new BLLException("User adding failed - ", e);
			}
		} catch (DALException e1) {
			e1.printStackTrace();
			throw new BLLException("All users selection failed - ", e1);
		}


	}
	
	private boolean validatePassword(String password) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean validateTown(String town) throws BLLException {
		boolean isValid = true;
		if(!town.matches("(?i)^[a-z0-9 ,.'-]+$")) {
			isValid = false;
			throw new BLLException("The town must contain only letters, numbers, white spaces and some special characters (,.'-)");
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

	private boolean validateStreet(String street) throws BLLException {
		boolean isValid = true;
		if(!street.matches("(?i)^[a-z0-9 ,.'-]+$")) {
			isValid = false;
			throw new BLLException("The street must contain only letters, numbers, white spaces and some special characters (,.'-)");
		}
		return isValid;
	}

	private boolean validatePhone(String phone) throws BLLException {
		boolean isValid = true;
		if(!phone.matches("^\\d{10}$")) {
			isValid = false;
			throw new BLLException("The phone number must be 10 digits long.");
		}
		return isValid;
	}

	private boolean validateLastName(String lastName) throws BLLException {
		boolean isValid = true;
		if(!lastName.matches("(?i)^[a-z .'-]+$")) {
			isValid = false;
			throw new BLLException("The last name must contain only letters, white spaces and some special characters (.'-)");
		}
		return isValid;
	}
	
	private boolean validateFirstName(String firstName) throws BLLException {
		boolean isValid = true;
		if(!firstName.matches("(?i)^[a-z .'-]+$")) {
			isValid = false;
			throw new BLLException("The first name must contain only letters, white spaces and some special characters (.'-)");
		}
		return isValid;
	}


	public boolean validateUserName(String userName, List<User> listAllUsers) throws BLLException {
		boolean isValid = true;
		//User name test : must be unique
		for(User u : listAllUsers) {
			if (userName.equals(u.getUserName())) {
				isValid = false;
				throw new BLLException("The user name must be unique.");
			}
		}
		
		//User name test : only alphanumerical characters
		if(!userName.matches("^\\w+$")) {
			isValid = false;
			throw new BLLException("The user name must contain only letters, numbers and _");
		}
		return isValid;
	}
	
	public boolean validateEmail(String email, List<User> listAllUsers) throws BLLException {
		boolean isValid = true;
		//User email test : must be something like xxx@xxx.xx
		if(!email.matches("\\A(?=[a-z0-9@.!#$%&'*+/=?^_‘{|}~-]{6,254}\\z)(?=[a-z0-9.!#$%&'*+/=?^_‘{|}~-]{1,64}@)[a-z0-9!#$%&'*+/=?^_‘{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_‘{|}~-]+)*@(?:(?=[a-z0-9-]{1,63}\\.)[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+(?=[a-z0-9-]{1,63}\\z)[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\z")) {
			isValid = false;
			throw new BLLException("The email address must be valid.");
		}
		
		//User name test : must be unique
		for(User u : listAllUsers) {
			if (email.equals(u.getEmail())) {
				isValid = false;
				throw new BLLException("The email must be unique.");
			}
		}
		return isValid;
	}

}