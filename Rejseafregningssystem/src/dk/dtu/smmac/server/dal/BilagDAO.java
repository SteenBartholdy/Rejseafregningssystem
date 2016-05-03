package dk.dtu.smmac.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	private PreparedStatement getSizeStmt = null;
	private PreparedStatement updateBilagStmt = null;
	
	public BilagDAO() throws Exception
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DAO.URL, DAO.USERNAME, DAO.PASSWORD);

		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}

	@Override
	public void createBilag(BilagDTO bilag) throws Exception {
		
		createBilagStmt = connection.prepareStatement("INSERT INTO Bilag " + "( BilagsNO, Nummer, Forklaring ) " + "VALUES ( ?, ?, ? );");
		
		try {
			createBilagStmt.setInt(1, bilag.getID());
			createBilagStmt.setInt(2, bilag.getNr());
			createBilagStmt.setString(3, bilag.getForklaring());
			createBilagStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}

	@Override
	public List<BilagDTO> getBilag(int id) throws Exception {
		
		getBilagStmt = connection.prepareStatement("SELECT * FROM Bilag WHERE Nummer = ?;");
		List<BilagDTO> list = null;
		ResultSet resultSet = null;
		
		try {
			getBilagStmt.setInt(1, id);
			resultSet = getBilagStmt.executeQuery();
			list = new ArrayList<BilagDTO>();
			
			while(resultSet.next())
			{
				list.add(new BilagDTO(
						resultSet.getInt("Id"),
						resultSet.getInt("Nummer"),
						resultSet.getString("Forklaring")
						));
			}
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} finally {
			try {
				resultSet.close();
			} 
			
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
			} 
		}
		return list;
	}

	@Override
	public void deleteBilag(BilagDTO bilag) throws Exception {
		
		deleteBilagStmt = connection.prepareStatement("DELETE FROM Bilag WHERE Nummer = ?;");
		
		try {
			deleteBilagStmt.setInt(1, bilag.getNr());

			deleteBilagStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
	}

	@Override
	public int getSize() throws Exception {
		
		getSizeStmt = connection.prepareStatement("SELECT COUNT(*) FROM Bilag;");
		ResultSet resultSet = null;

		try {
			resultSet = getSizeStmt.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}

		return 0;
	}

	@Override
	public void updateBilag(BilagDTO bilag) throws Exception {
		
		updateBilagStmt = connection.prepareStatement("UPDATE Bilag SET Nummer = ?, Forklaring = ? WHERE BilagsNo = ?;");
		
		try{
			updateBilagStmt.setInt(1, bilag.getNr());
			updateBilagStmt.setString(2, bilag.getForklaring());
			updateBilagStmt.setInt(3, bilag.getID());
			
			updateBilagStmt.executeUpdate();
		}
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 	
	}
}
