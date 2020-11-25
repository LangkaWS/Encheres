package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.bo.Article;

public class ArticleDAOJDBCImpl implements ArticleDAO {
	
	private static final String INSERT ="INSERT INTO ARTICLES(name, description, auctionStartDate, auctionEndDate, startPrice, state, sellerId, categoryId, pickUpId) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String UPDATE ="UPDATE ARTICLES SET "
			+ "name = ?, "
			+ "description = ?, "
			+ "auctionStartDate = ?, "
			+ "auctionEndDate = ?, "
			+ "startPrice = ?, "
			+ "sellingPrice = ?, "
			+ "state = ?, "
			+ "sellerId = ?, "
			+ "buyerId = ?, "
			+ "categoryId = ?, "
			+ "pickUpId = ?"
			+ "WHERE articleId = ?;";
	private static final String DELETE ="DELETE FROM ARTICLES WHERE articleId = ?;";
	private static final String SELECT_ALL ="SELECT * FROM ARTICLES;";
	private static final String SELECT_BY_ID ="SELECT * FROM ARTICLES WHERE articleId = ?;";

	@Override
	public void insert(Article data) throws DALException {
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, data.getName());
			pstmt.setString(2, data.getDescription());
			pstmt.setDate(3, Date.valueOf(data.getAuctionStartDate()));
			pstmt.setDate(4, Date.valueOf(data.getAuctionEndDate()));
			pstmt.setInt(5, data.getStartPrice());
			pstmt.setString(6, data.getState());
			pstmt.setInt(7, data.getSellerId());
			pstmt.setInt(8, data.getCategoryId());
			pstmt.setInt(9, data.getPickUpId());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				data.setArticleId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Article insertion into database failed - ", e);
		}
	}

	@Override
	public void update(Article data) throws DALException {
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, data.getName());
			pstmt.setString(2, data.getDescription());
			pstmt.setDate(3, Date.valueOf(data.getAuctionStartDate()));
			pstmt.setDate(4, Date.valueOf(data.getAuctionEndDate()));
			pstmt.setInt(5, data.getStartPrice());
			pstmt.setInt(6, data.getSellingPrice());
			pstmt.setString(7, data.getState());
			pstmt.setInt(8, data.getSellerId());
			pstmt.setInt(9, data.getBuyerId());
			pstmt.setInt(10, data.getCategoryId());
			pstmt.setInt(11, data.getPickUpId());
			pstmt.setInt(12, data.getArticleId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Article update in database failed - ", e);
		}
	}

	@Override
	public void delete(Article data) throws DALException {
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, data.getArticleId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Article deletion from database failed - ", e);
		}
	}

	@Override
	public List<Article> selectAll() throws DALException {
		List<Article> list = new ArrayList<>();
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Article a = new Article(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getDate(4).toLocalDate(),
						rs.getDate(5).toLocalDate(),
						rs.getInt(6),
						rs.getInt(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12)
						);
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : All articles selection from database failed - ", e);
		}
		return list;
	}

	@Override
	public Article selectById(Article data) throws DALException {
		Article article = null;
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, data.getArticleId());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				article = new Article(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getDate(4).toLocalDate(),
						rs.getDate(5).toLocalDate(),
						rs.getInt(6),
						rs.getInt(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12)
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Article selection by ID from database failed - ", e);
		}
		return article;
	}

}
