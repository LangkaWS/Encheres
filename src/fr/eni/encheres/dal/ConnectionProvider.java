package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class ConnectionProvider {
	
	private static DataSource dataSource;
	
	static {
		Context context;
		try {
			System.out.println("Initializing datasource...");
			context = new InitialContext();
			System.out.println("Context acquired");
			ConnectionProvider.dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
			System.out.println("Success!");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("Impossible to access database.");
		}
	}
	
	public static Connection getConnection() throws SQLException {
		System.out.println("Getting connection...");
		return ConnectionProvider.dataSource.getConnection();
	}

}
