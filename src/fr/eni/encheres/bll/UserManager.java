package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bll.bo.User;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.SingleIdDAO;
import fr.eni.encheres.dal.UserDAO;

public class UserManager {
	
	private UserDAO userDAO;
	
	public UserManager() {
		this.userDAO = DAOFactory.getUserDAO();
	}
	
	public void addUser(User user) throws BLLException {
		if(this.validateUser(user)) {
			try {
				userDAO.insert(user);
			} catch (DALException e) {
				e.printStackTrace();
				throw new BLLException("User adding failed - ", e);
			}
		} else {
			throw new BLLException("Invalid user. Please check again data.");
		}
	}
	
	public void updateUser(User user) throws BLLException {
		if(this.validateUser(user)) {
			try {
				userDAO.update(user);
			} catch (DALException e) {
				e.printStackTrace();
				throw new BLLException("User update failed - ", e);
			}
		} else {
			throw new BLLException("Invalid user. Please check again data.");
		}
	}
	
	public void deleteUser(int id) throws BLLException {
		try {
			//Get All articles, if buyerId = userId, throw exception "Cannot delete account if best bidder"
			userDAO.delete(id);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("User deletion failed - ", e);
		}
	}
	
	public User getUser(int id) throws BLLException {
		User u = null;
		try {
			u = userDAO.selectById(id);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("User selection failed - ", e);
		}
		return u;
	}
	
	public User getUser(String email) throws BLLException {
		User u = null;
		try {
			u = userDAO.selectUserByEmail(email);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("User selection failed - ", e);
		}
		return u;
	}
	
	private boolean validateUser(User user) throws BLLException {
		boolean isValid = true;
		List<User> list;
		try {
			list = userDAO.selectAll();
			if(!this.validateUserName(user, list)) {
				isValid=false;
				throw new BLLException("The user name is invalid.");
			}
			if(!this.validateLastName(user.getLastName())) {
				isValid=false;
				throw new BLLException("The last name is invalid.");
			}
			if(!this.validateFirstName(user.getFirstName())) {
				isValid=false;
				throw new BLLException("The first name is invalid.");
			}
			if(!this.validateEmail(user, list)) {
				isValid=false;
				throw new BLLException("The user name is invalid.");
			}
			if(!this.validatePhone(user.getPhone())) {
				isValid=false;
				throw new BLLException("The phone number is invalid.");
			}
			if(!this.validateStreet(user.getStreet())) {
				isValid=false;
				throw new BLLException("The street is invalid.");
			}
			if(!this.validateZipCode(user.getZipCode())) {
				isValid=false;
				throw new BLLException("The zip code is invalid.");
			}
			if(!this.validateTown(user.getTown())) {
				isValid=false;
				throw new BLLException("The town is invalid.");
			}
			if(!this.validatePassword(user.getPassword())) {
				throw new BLLException("The password is invalid.");
			}
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("All users selection failed - ", e);
		}
		return isValid;
	}
	
	public boolean validateUserName(User user, List<User> listAllUsers) throws BLLException {
		boolean isValid = true;
		
		//User name test : maximum length = 30
		if(!(user.getUserName().length() <= 30)) {
			isValid = false;
			throw new BLLException("The user name must be up to 30 characters long.");
		}
		
		//User name test : only alphanumerical characters
		if(!user.getUserName().matches("^[A-Za-zÀ-ÖØ-öø-ÿ0-9]+$")) {
			isValid = false;
			throw new BLLException("The user name must contain only letters and numbers");
		}
		
		//User name test : must be unique
		for(User u : listAllUsers) {
			if (user.getUserName().equals(u.getUserName())) {
				if(user.getUserId() != null && user.getUserId() == u.getUserId()) {
					continue;
				}
				isValid = false;
				throw new BLLException("The user name must be unique.");
			}
		}
		return isValid;
	}

	private boolean validateLastName(String lastName) throws BLLException {
		boolean isValid = true;
		
		if(!(lastName.length() <= 30)) {
			isValid = false;
			throw new BLLException("The last name must be up to 30 characters long.");
		}
		
		if(!lastName.matches("(?i)^[A-Za-zÀ-ÖØ-öø-ÿ .'-]+$")) {
			isValid = false;
			throw new BLLException("The last name must contain only letters, white spaces and some special characters (.'-)");
		}
		return isValid;
	}
	
	private boolean validateFirstName(String firstName) throws BLLException {
		boolean isValid = true;
		
		if(!(firstName.length() <= 30)) {
			isValid = false;
			throw new BLLException("The first name must be up to 30 characters long.");
		}
		
		if(!firstName.matches("(?i)^[A-Za-zÀ-ÖØ-öø-ÿ .'-]+$")) {
			isValid = false;
			throw new BLLException("The first name must contain only letters, white spaces and some special characters (.'-)");
		}
		return isValid;
	}

	public boolean validateEmail(User user, List<User> listAllUsers) throws BLLException {
		boolean isValid = true;
		
		if(!(user.getEmail().length() <= 50)) {
			isValid = false;
			throw new BLLException("The email must be up to 50 characters long.");
		}
		
		//User email test : must be something like xxx@xxx.xx
		if(!user.getEmail().matches("\\A(?=[a-z0-9@.!#$%&'*+/=?^_‘{|}~-]{6,254}\\z)(?=[a-z0-9.!#$%&'*+/=?^_‘{|}~-]{1,64}@)[a-z0-9!#$%&'*+/=?^_‘{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_‘{|}~-]+)*@(?:(?=[a-z0-9-]{1,63}\\.)[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+(?=[a-z0-9-]{1,63}\\z)[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\z")) {
			isValid = false;
			throw new BLLException("The email address must be valid.");
		}
		
		//User name test : must be unique
		for(User u : listAllUsers) {
			if (user.getUserId() != null && user.getEmail().equals(u.getEmail())) {
				if(user.getUserId() == u.getUserId()) {
					continue;
				}
				isValid = false;
				throw new BLLException("The email must be unique.");
			}
		}
		return isValid;
	}
	
	private boolean validatePhone(String phone) throws BLLException {
		boolean isValid = true;
		
		if(phone.isEmpty()) {
			return isValid;
		}
		
		if(!(phone.length() <= 20)) {
			isValid = false;
			throw new BLLException("The phone must be up to 20 characters long.");
		}
		
		if(!phone.matches("^\\d{10}$")) {
			isValid = false;
			throw new BLLException("The phone number must be 10 digits long.");
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
	
	private boolean validatePassword(String password) throws BLLException {
		boolean isValid = true;
		
		if(!(password.length() <= 30)) {
			isValid = false;
			throw new BLLException("The password must be up to 30 characters long.");
		}
		
		// TODO Auto-generated method stub
		return isValid;
	}


}
