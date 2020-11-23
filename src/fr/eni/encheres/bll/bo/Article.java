package fr.eni.encheres.bll.bo;

import java.time.LocalDate;

public class Article {
	
	private int articleId;
	private String name;
	private String description;
	private LocalDate bidStartDate;
	private LocalDate bidEndDate;
	private int startPrice;
	private int sellingPrice;
	private String state;
	private int sellerId;
	private int buyerId;
	private int categoryId;
	private int pickUpId;

}
