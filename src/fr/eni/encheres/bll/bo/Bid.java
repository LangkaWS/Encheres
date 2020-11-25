package fr.eni.encheres.bll.bo;

import java.time.LocalDateTime;

public class Bid {
	
	private LocalDateTime bidDate;
	private int amount;
	private int buyerId;
	private int articleId;
	
	/**
	 * 
	 */
	public Bid() {
		super();
	}

	/**
	 * @param amount
	 * @param buyerId
	 * @param articleId
	 */
	public Bid(int buyerId, int articleId, int amount) {
		super();
		this.bidDate = LocalDateTime.now();
		this.amount = amount;
		this.buyerId = buyerId;
		this.articleId = articleId;
	}
	
	

	/**
	 * @param bidDate
	 * @param amount
	 * @param buyerId
	 * @param articleId
	 */
	public Bid(int buyerId, int articleId, int amount, LocalDateTime bidDate) {
		super();
		this.bidDate = bidDate;
		this.amount = amount;
		this.buyerId = buyerId;
		this.articleId = articleId;
	}

	/**
	 * @return the bidDate
	 */
	public LocalDateTime getBidDate() {
		return bidDate;
	}

	/**
	 * @param bidDate the bidDate to set
	 */
	public void setBidDate(LocalDateTime bidDate) {
		this.bidDate = bidDate;
	}
	
	/**
	 * Set bidDate at the today's date
	 */
	public void setBidDate() {
		this.bidDate = LocalDateTime.now();
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bid [bidDate=" + bidDate + ", amount=" + amount + ", buyerId=" + buyerId + ", articleId=" + articleId
				+ "]";
	}
	
	

}
