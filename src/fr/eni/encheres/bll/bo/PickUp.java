package fr.eni.encheres.bll.bo;

public class PickUp {
	
	private int pickUpId;
	private String street;
	private String zipCode;
	private String town;
	
	/**
	 * 
	 */
	public PickUp() {
	}
	
	/**
	 * @param street
	 * @param zipCode
	 * @param town
	 */
	public PickUp(String street, String zipCode, String town) {
		this.street = street;
		this.zipCode = zipCode;
		this.town = town;
	}
	
	/**
	 * @param pickUpId
	 * @param street
	 * @param zipCode
	 * @param town
	 */
	public PickUp(int pickUpId, String street, String zipCode, String town) {
		this.pickUpId = pickUpId;
		this.street = street;
		this.zipCode = zipCode;
		this.town = town;
	}

	/**
	 * @return the pickUpId
	 */
	public int getPickUpId() {
		return pickUpId;
	}

	/**
	 * @param pickUpId the pickUpId to set
	 */
	public void setPickUpId(int pickUpId) {
		this.pickUpId = pickUpId;
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
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PickUp [pickUpId=" + pickUpId + ", street=" + street + ", zipCode=" + zipCode + ", town=" + town
				+ "]";
	}
	
	
	
	

}
