package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.bo.User;

public class UserDAOJDBCImpl implements DAO<User> {
	
	private static final String INSERT = "INSERT INTO USERS(userName, lastName, firstName, email, phone, street, postalCode, town, password, credit, admin) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String UPDATE = "UPDATE USERS SET "
			+ "userName = ?, "
			+ "lastName = ?, "
			+ "firstName = ?, "
			+ "email = ?, "
			+ "phone = ?, "
			+ "street = ?, "
			+ "postalCode = ?, "
			+ "town = ?, "
			+ "motDePasse = ?, "
			+ "credit = ?, "
			+ "admin = ? "
			+ "WHERE userId = ?;";
	private static final String DELETE = "DELETE FROM USERS WHERE userId = ?;";
	private static final String SELECT_ALL = "SELECT * FROM USERS;";
	private static final String SELECT_BY_ID = "SELECT * FROM USERS WHERE userId = ?;";

	@Override
	public void insert(User data) throws DALException {
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, data.getUserName());
			pstmt.setString(2, data.getLastName());
			pstmt.setString(3, data.getFirstName());
			pstmt.setString(4, data.getEmail());
			pstmt.setString(5, data.getPhone());
			pstmt.setString(6, data.getStreet());
			pstmt.setString(7, data.getZipCode());
			pstmt.setString(8, data.getTown());
			pstmt.setString(9, data.getPassword());
			pstmt.setInt(10, data.getCredit());
			pstmt.setInt(11, data.isAdmin() ? 1 : 0);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				data.setUserId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : User insertion into database failed - ", e);
		}
	}

	@Override
	public void update(User data) throws DALException {
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, data.getUserName());
			pstmt.setString(2, data.getLastName());
			pstmt.setString(3, data.getFirstName());
			pstmt.setString(4, data.getEmail());
			pstmt.setString(5, data.getPhone());
			pstmt.setString(6, data.getStreet());
			pstmt.setString(7, data.getZipCode());
			pstmt.setString(8, data.getTown());
			pstmt.setString(9, data.getPassword());
			pstmt.setInt(10, data.getCredit());
			pstmt.setInt(11, (data.isAdmin() ? 1 : 0));
			pstmt.setInt(12, data.getUserId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : User update in database failed - ", e);
		}
	}

	@Override
	public void delete(User data) throws DALException {
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, data.getUserId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : User deletion from database failed - ", e);
		}
	}

	@Override
	public List<User> selectAll() throws DALException {
		List<User> list = new ArrayList<>();
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				User u = new User(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getInt(11),
						(rs.getInt(12) == 1)	
						);
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : All users selection from database failed - ", e);
		}
		return list;
	}

	@Override
	public User selectById(User data) throws DALException {
		User user = null;
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, data.getUserId());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new User(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getInt(11),
						(rs.getInt(12) == 1)	
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : User selection by ID from database failed - ", e);
		}
		return user;
	}

}
