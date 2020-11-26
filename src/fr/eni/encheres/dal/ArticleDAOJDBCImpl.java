package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
			+ "pickUpId = ? "
			+ "WHERE articleId = ?;";
	private static final String DELETE ="DELETE FROM ARTICLES WHERE articleId = ?;";
	private static final String SELECT_ALL ="SELECT * FROM ARTICLES;";
	private static final String SELECT_BY_ID ="SELECT * FROM ARTICLES WHERE articleId = ?;";
	private static final String SELECT_BY_SELLER ="SELECT * FROM ARTICLES WHERE sellerId = ?;";
	private static final String SELECT_BY_SELLER_AND_STATE ="SELECT * FROM ARTICLES WHERE sellerId = ? AND state LIKE ?;";
	private static final String SELECT_ENDED_BY_BUYER ="SELECT * FROM ARTICLES WHERE buyerId = ? AND state LIKE 'ended';";
	private static final String SELECT_BY_CATEGORY = "SELECT * FROM ARTICLES WHERE categoryId = ?;";
	private static final String SELECT_BY_NAME = "SELECT * FROM ARTICLES WHERE name LIKE ?;";
	private static final String SELECT_IN_PROGRESS = "SELECT * FROM ARTICLES WHERE state = 'in progress';";
	private static final String SELECT_BY_PARTICIPATING_BUYER = "select a.articleId, a.name, a.description, a.auctionStartDate, a.auctionEndDate, a.startPrice, a.sellingPrice, a.state, a.sellerId, a.buyerId, a.categoryId, a.pickUpId from articles a, users u, bids b where b.buyerId = u.userId and b.articleId = a.articleId and u.userId = ?;";

	@Override
	public void insert(Article data) throws DALException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, data.getName());
			pstmt.setString(2, data.getDescription());
			pstmt.setTimestamp(3, Timestamp.valueOf(data.getAuctionStartDate()));
			pstmt.setTimestamp(4, Timestamp.valueOf(data.getAuctionEndDate()));
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
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Close failed - ", e);
			}
		}
	}

	@Override
	public void update(Article data) throws DALException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, data.getName());
			pstmt.setString(2, data.getDescription());
			pstmt.setTimestamp(3, Timestamp.valueOf(data.getAuctionStartDate()));
			pstmt.setTimestamp(4, Timestamp.valueOf(data.getAuctionEndDate()));
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
		}  finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Close failed - ", e);
			}
		}
	}

	@Override
	public void delete(Article data) throws DALException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, data.getArticleId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Article deletion from database failed - ", e);
		}  finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Close failed - ", e);
			}
		}
	}

	@Override
	public List<Article> selectAll() throws DALException {
		List<Article> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Article a = new Article(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getTimestamp(4).toLocalDateTime(),
						rs.getTimestamp(5).toLocalDateTime(),
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
		}  finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Close failed - ", e);
			}
		}
		return list;
	}

	@Override
	public List<Article> selectWonArticles(int buyerId) throws DALException {
		List<Article> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(SELECT_ENDED_BY_BUYER);
			pstmt.setInt(1, buyerId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Article a = new Article(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getTimestamp(4).toLocalDateTime(),
						rs.getTimestamp(5).toLocalDateTime(),
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
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Selection of articles won by user from database failed - ", e);
		}  finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Close failed - ", e);
			}
		}
		return list;
	}

	@Override
	public List<Article> selectArticlesOfSeller(int sellerId) throws DALException {
		List<Article> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_SELLER);
			pstmt.setInt(1, sellerId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Article a = new Article(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getTimestamp(4).toLocalDateTime(),
						rs.getTimestamp(5).toLocalDateTime(),
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
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Selection of all buyer's articles from database failed - ", e);
		}  finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Close failed - ", e);
			}
		}
		return list;
	}
	
	@Override
	public List<Article> selectArticlesOfSellerByState(int sellerId, String state) throws DALException {
		List<Article> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_SELLER_AND_STATE);
			pstmt.setInt(1, sellerId);
			pstmt.setString(2, state);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Article a = new Article(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getTimestamp(4).toLocalDateTime(),
						rs.getTimestamp(5).toLocalDateTime(),
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
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Selection of seller's articles with the state '" + state + "' from database failed - ", e);
		}  finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Close failed - ", e);
			}
		}
		return list;
	}

	@Override
	public List<Article> selectArticlesByCategory(int categoryId) throws DALException {
		List<Article> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_CATEGORY);
			pstmt.setInt(1, categoryId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Article a = new Article(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getTimestamp(4).toLocalDateTime(),
						rs.getTimestamp(5).toLocalDateTime(),
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
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Selection of articles with the category '" + categoryId + "' from database failed - ", e);
		}  finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Close failed - ", e);
			}
		}
		return list;
	}
	
	@Override
	public List<Article> selectArticlesByName(String name) throws DALException {
		List<Article> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_NAME);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Article a = new Article(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getTimestamp(4).toLocalDateTime(),
						rs.getTimestamp(5).toLocalDateTime(),
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
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Selection of articles with the name '" + name + "' from database failed - ", e);
		}  finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Close failed - ", e);
			}
		}
		return list;
	}

	@Override
	public List<Article> selectArticlesInProgress(String state) throws DALException {
		List<Article> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(SELECT_IN_PROGRESS);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Article a = new Article(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getTimestamp(4).toLocalDateTime(),
						rs.getTimestamp(5).toLocalDateTime(),
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
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Selection of articles with the state '" + state + "' from database failed - ", e);
		}  finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Close failed - ", e);
			}
		}
		return list;
	}

	@Override
	public List<Article> selectArticlesByParticipatingBuyer(int buyerId) throws DALException {
		List<Article> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_PARTICIPATING_BUYER);
			pstmt.setInt(1, buyerId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Article a = new Article(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getTimestamp(4).toLocalDateTime(),
						rs.getTimestamp(5).toLocalDateTime(),
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
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Selection of articles with participating User with id '" + buyerId + "' from database failed - ", e);
		}  finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Close failed - ", e);
			}
		}
		return list;
	}

	@Override
	public Article selectByIds(int id1, int id2) throws DALException {
		// unused method
		return null;
	}

	@Override
	public Article selectById(int id) throws DALException {
		Article article = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				article = new Article(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getTimestamp(4).toLocalDateTime(),
						rs.getTimestamp(5).toLocalDateTime(),
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
		}  finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Close failed - ", e);
			}
		}
		return article;
	}

	

}
