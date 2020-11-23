package fr.eni.encheres.bll.bo;

public class User {
	
	private int userId;
	private String userName;
	private String lastName;
	private String firstName;
	private String email;
	private String phone;
	private String street;
	private String postalCode;
	private String town;
	private String password;
	private int credit;
	private boolean admin;
	
	/**
	 * 
	 */
	public User() {
	}
	
	/**
	 * @param userName
	 * @param lastName
	 * @param firstName
	 * @param email
	 * @param phone
	 * @param street
	 * @param postalCode
	 * @param town
	 * @param password
	 */
	public User(String userName, String lastName, String firstName, String email, String phone, String street,
			String postalCode, String town, String password) {
		this.userName = userName;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phone = phone;
		this.street = street;
		this.postalCode = postalCode;
		this.town = town;
		this.password = password;
	}
	
	/**
	 * @param userId
	 * @param userName
	 * @param lastName
	 * @param firstName
	 * @param email
	 * @param phone
	 * @param street
	 * @param postalCode
	 * @param town
	 * @param password
	 * @param credit
	 * @param admin
	 */
	public User(int userId, String userName, String lastName, String firstName, String email, String phone,
			String street, String postalCode, String town, String password, int credit, boolean admin) {
		this.userId = userId;
		this.userName = userName;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phone = phone;
		this.street = street;
		this.postalCode = postalCode;
		this.town = town;
		this.password = password;
		this.credit = credit;
		this.admin = admin;
	}
	
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	/**
	 * @return the town
	 */
	public String getTown() {
		return town;
	}
	
	/**
	 * @param town the town to set
	 */
	public void setTown(String town) {
		this.town = town;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the credit
	 */
	public int getCredit() {
		return credit;
	}
	
	/**
	 * @param credit the credit to set
	 */
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	/**
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}
	
	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", lastName=" + lastName + ", firstName="
				+ firstName + ", email=" + email + ", phone=" + phone + ", street=" + street + ", postalCode="
				+ postalCode + ", town=" + town + ", password=" + password + ", credit=" + credit + ", admin=" + admin
				+ "]";
	}
	
	

}
