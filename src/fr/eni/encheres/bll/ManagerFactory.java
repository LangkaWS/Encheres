package fr.eni.encheres.bll;

public class ManagerFactory {
	
	public static UserManager getUserManager() {
		return new UserManager();
	}

}
