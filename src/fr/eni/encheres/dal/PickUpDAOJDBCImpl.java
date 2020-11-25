package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.bo.PickUp;

public class PickUpDAOJDBCImpl implements DAO<PickUp> {
	
	private static final String INSERT ="INSERT INTO PICKUPS(street, zipCode, town) VALUES (?, ?, ?);";
	private static final String UPDATE ="UPDATE PICKUPS SET "
			+ "street = ?, "
			+ "zipCode = ?, "
			+ "town = ? "
			+ "WHERE pickUpId = ?;";
	private static final String DELETE ="DELETE FROM PICKUPS WHERE pickUpId = ?;";
	private static final String SELECT_ALL ="SELECT * FROM PICKUPS";
	private static final String SELECT_BY_ID ="SELECT * FROM PICKUPS WHERE pickUpId = ?;";

	@Override
	public void insert(PickUp data) throws DALException {
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, data.getStreet());
			pstmt.setString(2, data.getZipCode());
			pstmt.setString(3, data.getTown());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				data.setPickUpId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Pick-up insertion into database failed - ", e);
		}
	}

	@Override
	public void update(PickUp data) throws DALException {
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, data.getStreet());
			pstmt.setString(2, data.getZipCode());
			pstmt.setString(3, data.getTown());
			pstmt.setInt(4, data.getPickUpId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Pick-up update in database failed - ", e);
		}
	}

	@Override
	public void delete(PickUp data) throws DALException {
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, data.getPickUpId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Pick-up deletion from database failed - ", e);
		}
	}

	@Override
	public List<PickUp> selectAll() throws DALException {
		List<PickUp> list = new ArrayList<>();
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				PickUp pu = new PickUp(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4)
						);
				list.add(pu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : All pick-ups selection from database failed - ", e);
		}
		return list;
	}

	@Override
	public PickUp selectById(PickUp data) throws DALException {
		PickUp pu = null;
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, data.getPickUpId());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				pu = new PickUp(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4)
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("DATA ACCESS LAYER EXCEPTION : Pick-up selection by id from database failed - ", e);
		}
		return pu;
	}

}
