package dk.dtu.smmac.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RejseafregningDAO {

	private Connection connection = null;

	private PreparedStatement getRejsStmt = null;
	private PreparedStatement updateRejsStmt = null;
	private PreparedStatement createRejsStmt = null;
	
	public RejseafregningDAO() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DAO.URL, DAO.USERNAME, DAO.PASSWORD);
			
			
			
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}
	
}
