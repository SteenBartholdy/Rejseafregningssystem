package dk.dtu.smmac.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dk.dtu.smmac.client.service.BilagService;
import dk.dtu.smmac.shared.BilagDTO;
import dk.dtu.smmac.shared.RejseafregningDTO;

public class BilagDAO extends RemoteServiceServlet implements BilagService
{
	private Connection connection = null;
	
	private PreparedStatement createBilagStmt = null; 
	private PreparedStatement getBilagStmt = null;
	private PreparedStatement deleteBilagStmt = null;
	
	public BilagDAO() throws Exception
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DAO.URL, DAO.USERNAME, DAO.PASSWORD);

			//Laver query, der opretter et bilag
			createBilagStmt = connection.prepareStatement("INSERT INTO Bilag " + "( Bilag ) " + "VALUES ( ? );");

			//Laver query, der finder et bilag
			getBilagStmt = connection.prepareStatement("SELECT * FROM Bilag Where Id = ?");

			//Laver query, der sletter et bilag
			deleteBilagStmt = connection.prepareStatement("DELETE FROM Bilag WHERE Id = ?;");

		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}

	@Override
	public void createBilag(BilagDTO bilag) throws Exception {
		try {
			createBilagStmt.setInt(1, bilag.getID());
			createBilagStmt.setString(2, bilag.getForklaring());
			createBilagStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}

	@Override
	public BilagDTO getBilag(int id) throws Exception {
		BilagDTO bilag = null;
		ResultSet resultSet = null;
		
		try {
			getBilagStmt.setInt(1, id);
			resultSet = getBilagStmt.executeQuery();
			resultSet.next();
			
			bilag = new BilagDTO(
					resultSet.getInt("Id"),
					resultSet.getString("Forklaring")
					);
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
		return bilag;
	}

	@Override
	public void deleteBilag(BilagDTO bilag) throws Exception {
		try {
			deleteBilagStmt.setInt(1, bilag.getID());

			deleteBilagStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
	}
}