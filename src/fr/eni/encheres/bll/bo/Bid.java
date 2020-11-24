package fr.eni.encheres.bll.bo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Bid {
	
	private String bidDate;
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
		this.bidDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").format(new Date());
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
	public Bid(int buyerId, int articleId, int amount, String bidDate) {
		super();
		this.bidDate = bidDate;
		this.amount = amount;
		this.buyerId = buyerId;
		this.articleId = articleId;
	}

	/**
	 * @return the bidDate
	 */
	public String getBidDate() {
		return bidDate;
	}

	/**
	 * @param bidDate the bidDate to set
	 */
	public void setBidDate(LocalDate bidDate) {
		this.bidDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").format(new Date());
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
