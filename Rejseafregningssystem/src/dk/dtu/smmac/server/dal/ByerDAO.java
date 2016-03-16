package dk.dtu.smmac.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.smmac.client.service.ByerService;

public class ByerDAO extends RemoteServiceServlet implements ByerService {

	private static final String URL = "jdbc:mysql://mysql-tem.cxjp73j45toh.eu-west-1.rds.amazonaws.com:3306/TEM";
	private static final String USERNAME = "SMMAC";
	private static final String PASSWORD = "MsU-7dH-ZHQ-KyQ";

	private Connection connection = null;
	
	private PreparedStatement createByStmt = null;
	private PreparedStatement getByStmt = null;
	
	public ByerDAO() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			//Laver query, der opretter en by
			createByStmt = connection.prepareStatement("INSERT INTO Byer ( Postnummer, Byen ) VALUES ( ?, ? );");
			
			//Laver query, der opretter en by
			getByStmt = connection.prepareStatement("SELECT * FROM Byer WHERE Postnummer = ?;");
			
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}
	
	@Override
	public void createBy(int postnr, String navn) throws Exception {
		try {
		createByStmt.setInt(1, postnr);
		createByStmt.setString(2, navn);
		
		createByStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
	}

	@Override
	public String getBy(int postnr) throws Exception {
		ResultSet resultSet = null;
		
		try {
			getByStmt.setInt(1, postnr);
			
			resultSet = getByStmt.executeQuery();
			resultSet.next();
			return resultSet.getString("Byen");
			} 
			catch (SQLException sqlE) {
				System.out.println(sqlE.getMessage());
			} 
		
		return null;
	}

}
