package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.bo.Bid;

public class BidDAOJDBCImpl implements BidDAO {
	private static final String INSERT = "INSERT INTO BIDS(buyerId, articleId, amount, bidDate) VALUES (?, ?, ?, ?);";
	private static final String UPDATE = "UPDATE BIDS SET "
			+ "amount = ?, "
			+ "bidDate = ? "
			+ "WHERE buyerId = ? "
			+ "AND articleId = ?;";
	//private static final String DELETE = "DELETE FROM BIDS WHERE articleId = ? AND buyerId = ?;";
	private static final String SELECT_ALL = "SELECT * FROM BIDS;";
	private static final String SELECT_BY_ID = "SELECT * FROM BIDS WHERE articleId = ? AND buyerId = ?;";
	
	@Override
	public void insert(Bid data) throws DALException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(INSERT);
			pstmt.setInt(1, data.getBuyerId());
			pstmt.setInt(2, data.getArticleId());
			pstmt.setInt(3, data.getAmount());
			pstmt.setTimestamp(4, Timestamp.valueOf(data.getBidDate()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Bid insertion into database failed - ", e);
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
	public void update(Bid data) throws DALException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, data.getAmount());
			pstmt.setTimestamp(2, Timestamp.valueOf(data.getBidDate()));
			pstmt.setInt(3, data.getBuyerId());
			pstmt.setInt(4, data.getArticleId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Bid update in database failed - ", e);
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
	public List<Bid> selectAll() throws DALException {
		List<Bid> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Bid b = new Bid(rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getTimestamp(4).toLocalDateTime());
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Selection of all bids from database failed - ", e);
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
		return list;
	}

	@Override
	public Bid selectByIds(int articleId, int buyerId) throws DALException {
		Bid b = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, articleId);
			pstmt.setInt(2, buyerId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				b = new Bid(rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getTimestamp(4).toLocalDateTime());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Selection by id from database failed - ", e);
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
		
		return b;
	}

}
