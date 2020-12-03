package fr.eni.encheres.bll.bo;

import java.time.LocalDateTime;

public class Article {
	
	private Integer articleId;
	private String name;
	private String description;
	private LocalDateTime auctionStartDate;
	private LocalDateTime auctionEndDate;
	private Integer startPrice;
	private Integer sellingPrice;
	private String state = "created"; // DEFAULT "created"
	private Integer sellerId;
	private Integer buyerId;
	private Integer categoryId;
	private Integer pickUpId;
	
	/**
	 * 
	 */
	public Article() {
	}
	
	/**
	 * @param articleId
	 */
	public Article(Integer articleId) {
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
			Integer startPrice, String state, Integer sellerId, Integer categoryId, Integer pickUpId) {
		this.name = name;
		this.description = description;
		this.auctionStartDate = auctionStartDate;
		this.auctionEndDate = auctionEndDate;
		this.startPrice = startPrice;
		this.sellingPrice = startPrice;
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
	public Article(Integer articleId, String name, String description, LocalDateTime auctionStartDate, LocalDateTime auctionEndDate,
			Integer startPrice, Integer sellingPrice, String state, Integer sellerId, Integer buyerId, Integer categoryId, Integer pickUpId) {
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
	public Integer getArticleId() {
		return articleId;
	}
	
	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(Integer articleId) {
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
	public Integer getStartPrice() {
		return startPrice;
	}
	
	/**
	 * @param startPrice the startPrice to set
	 */
	public void setStartPrice(Integer startPrice) {
		this.startPrice = startPrice;
	}
	
	/**
	 * @return the sellingPrice
	 */
	public Integer getSellingPrice() {
		return sellingPrice;
	}
	
	/**
	 * @param sellingPrice the sellingPrice to set
	 */
	public void setSellingPrice(Integer sellingPrice) {
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
	public Integer getSellerId() {
		return sellerId;
	}
	
	/**
	 * @param sellerId the sellerId to set
	 */
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	
	/**
	 * @return the buyerId
	 */
	public Integer getBuyerId() {
		return buyerId;
	}
	
	/**
	 * @param buyerId the buyerId to set
	 */
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	
	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}
	
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * @return the pickUpId
	 */
	public Integer getPickUpId() {
		return pickUpId;
	}
	
	/**
	 * @param pickUpId the pickUpId to set
	 */
	public void setPickUpId(Integer pickUpId) {
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
	
	@Override
	public boolean equals(Object obj) {
		
		//If the object is compared with itself then return true
		if(obj == this) {
			return true;
		}
		
		//If the object is not an article return false
		if(!(obj instanceof Article)) {
			return false;
		}
		
		//Now we can cast object to Article so we can compare data members
		Article a = (Article) obj;
		
		//Compare the data members and return true or false
		return (this.articleId.equals(a.articleId))
				 && (this.name.equals(a.name))
				 && (this.description.equals(a.description))
				 && (this.auctionStartDate.equals(a.auctionStartDate))
				 && (this.auctionEndDate.equals(a.auctionEndDate))
				 && (this.startPrice.equals(a.startPrice))
				 && (this.sellingPrice.equals(a.sellingPrice))
				 && (this.state.equals(a.state))
				 && (this.sellerId.equals(a.sellerId))
				 && (this.buyerId.equals(a.buyerId))
				 && (this.categoryId.equals(a.categoryId))
				 && (this.pickUpId.equals(a.pickUpId));
	}

}
