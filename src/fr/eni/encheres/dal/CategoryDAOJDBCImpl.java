package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.bo.Category;

public class CategoryDAOJDBCImpl implements DAO<Category> {
	
	private static final String INSERT = "INSERT INTO CATEGORIES(name) VALUES (?);";
	private static final String UPDATE = "UPDATE CATEGORIES SET"
			+ "name = ?,"
			+ "WHERE categoryId = ?;";
	private static final String DELETE = "DELETE FROM CATEGORIES WHERE categoryId = ?;";
	private static final String SELECT_ALL = "SELECT * FROM CATEGORIES;";
	private static final String SELECT_BY_ID = "SELECT * FROM CATEGORIES WHERE categoryId = ?;";
	
	@Override
	public void insert(Category data) throws DALException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, data.getName());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				data.setCategoryId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Category insertion into database failed - ", e);
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
	public void update(Category data) throws DALException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, data.getName());
			pstmt.setInt(2, data.getCategoryId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Category update in database failed - ", e);
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
	public void delete(Category data) throws DALException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, data.getCategoryId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Category deletion from database failed - ", e);
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
	public List<Category> selectAll() throws DALException {
		List<Category> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Category c = new Category(rs.getInt(1), 
						rs.getString(2));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Selection of all categories from database failed - ", e);
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
	public Category selectById(int id) throws DALException {
		Category c = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				c = new Category(rs.getInt(1),
						rs.getString(2));
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
		
		return c;
	}

}
