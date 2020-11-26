package fr.eni.encheres.bll.bo;

import java.time.LocalDateTime;

public class Article {
	
	private int articleId;
	private String name;
	private String description;
	private LocalDateTime auctionStartDate;
	private LocalDateTime auctionEndDate;
	private int startPrice;
	private int sellingPrice;
	private String state = "created"; // DEFAULT "created"
	private int sellerId;
	private int buyerId;
	private int categoryId;
	private int pickUpId;
	
	/**
	 * 
	 */
	public Article() {
	}
	
	/**
	 * @param articleId
	 */
	public Article(int articleId) {
		this.articleId = articleId;
	}
	
	/**
	 * @param name
	 * @param description
	 * @param auctionStartDate
	 * @param auctionEndDate
	 * @param startPrice
	 * @param state
	 * @param sellerId
	 * @param categoryId
	 * @param pickUpId
	 */
	public Article(String name, String description, LocalDateTime auctionStartDate, LocalDateTime auctionEndDate,
			int startPrice, String state, int sellerId, int categoryId, int pickUpId) {
		this.name = name;
		this.description = description;
		this.auctionStartDate = auctionStartDate;
		this.auctionEndDate = auctionEndDate;
		this.startPrice = startPrice;
		this.state = state;
		this.sellerId = sellerId;
		this.categoryId = categoryId;
		this.pickUpId = pickUpId;
	}
	
	/**
	 * @param articleId
	 * @param name
	 * @param description
	 * @param auctionStartDate
	 * @param auctionEndDate
	 * @param startPrice
	 * @param sellingPrice
	 * @param state
	 * @param sellerId
	 * @param buyerId
	 * @param categoryId
	 * @param pickUpId
	 */
	public Article(int articleId, String name, String description, LocalDateTime auctionStartDate, LocalDateTime auctionEndDate,
			int startPrice, int sellingPrice, String state, int sellerId, int buyerId, int categoryId, int pickUpId) {
		this.articleId = articleId;
		this.name = name;
		this.description = description;
		this.auctionStartDate = auctionStartDate;
		this.auctionEndDate = auctionEndDate;
		this.startPrice = startPrice;
		this.sellingPrice = sellingPrice;
		this.state = state;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.categoryId = categoryId;
		this.pickUpId = pickUpId;
	}
	
	/**
	 * @return the articleId
	 */
	public int getArticleId() {
		return articleId;
	}
	
	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the auctionStartDate
	 */
	public LocalDateTime getAuctionStartDate() {
		return auctionStartDate;
	}
	
	/**
	 * @param auctionStartDate the auctionStartDate to set
	 */
	public void setAuctionStartDate(LocalDateTime auctionStartDate) {
		this.auctionStartDate = auctionStartDate;
	}
	
	/**
	 * @return the auctionEndDate
	 */
	public LocalDateTime getAuctionEndDate() {
		return auctionEndDate;
	}
	
	/**
	 * @param auctionEndDate the auctionEndDate to set
	 */
	public void setAuctionEndDate(LocalDateTime auctionEndDate) {
		this.auctionEndDate = auctionEndDate;
	}
	
	/**
	 * @return the startPrice
	 */
	public int getStartPrice() {
		return startPrice;
	}
	
	/**
	 * @param startPrice the startPrice to set
	 */
	public void setStartPrice(int startPrice) {
		this.startPrice = startPrice;
	}
	
	/**
	 * @return the sellingPrice
	 */
	public int getSellingPrice() {
		return sellingPrice;
	}
	
	/**
	 * @param sellingPrice the sellingPrice to set
	 */
	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * @return the sellerId
	 */
	public int getSellerId() {
		return sellerId;
	}
	
	/**
	 * @param sellerId the sellerId to set
	 */
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	
	/**
	 * @return the buyerId
	 */
	public int getBuyerId() {
		return buyerId;
	}
	
	/**
	 * @param buyerId the buyerId to set
	 */
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	
	/**
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}
	
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", name=" + name + ", description=" + description
				+ ", auctionStartDate=" + auctionStartDate + ", auctionEndDate=" + auctionEndDate + ", startPrice="
				+ startPrice + ", sellingPrice=" + sellingPrice + ", state=" + state + ", sellerId=" + sellerId
				+ ", buyerId=" + buyerId + ", categoryId=" + categoryId + ", pickUpId=" + pickUpId + "]";
	}
	
	

}
