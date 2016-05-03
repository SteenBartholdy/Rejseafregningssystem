package dk.dtu.smmac.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.smmac.client.service.LandeService;
import dk.dtu.smmac.shared.AnsatDTO;
import dk.dtu.smmac.shared.DageInfoDTO;
import dk.dtu.smmac.shared.LandDTO;

public class LandDAO extends RemoteServiceServlet implements LandeService {

	private Connection connection = null;
	
	private PreparedStatement getLandStmt = null;
	private PreparedStatement setLandStmt = null;
	private PreparedStatement setTakstStmt = null;
	private PreparedStatement getAllLandeStmt = null;
	
	public LandDAO() throws Exception
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DAO.URL, DAO.USERNAME, DAO.PASSWORD);
			
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
		
	}
	
	@Override
	public List<LandDTO> getLandDTO(int nummer) throws Exception {
		
		List<LandDTO> list = null;
		ResultSet resultSet = null;
		
		getLandStmt = connection.prepareStatement("SELECT * FROM Rejse INNER JOIN Lande ON Rejse.Land=Lande.Land WHERE Rejse.Nummer=?;");
		
		try {
			
			getLandStmt.setInt(1, nummer);
			resultSet = getLandStmt.executeQuery();
			list = new ArrayList<LandDTO>();
			
			while(resultSet.next())
			{
				list.add(new LandDTO(resultSet.getString("Lande.Land"),resultSet.getInt("Lande.Takst")));
			}
			
		}catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
		
		return list;
	}


	@Override
	public void setLand(String land, String newLand) throws Exception {
		
		setLandStmt = connection.prepareStatement("UPDATE Lande SET Land=? WHERE Land=?");
		
		try {
			
			setLandStmt.setString(1, newLand);
			setLandStmt.setString(2, land);
			
			setLandStmt.executeUpdate();
			
		} catch(SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
	}

	@Override
	public void setTakst(int takst, String land) throws Exception {
		
		setTakstStmt = connection.prepareStatement("UPDATE Lande SET Takst=? Where Land=?");
		
		try {
			
			setTakstStmt.setInt(1, takst);
			setTakstStmt.setString(2, land);
			
			setTakstStmt.executeUpdate();
			
		} catch(SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}  
		
	}

	@Override
	public List<String> getAllLande() throws Exception {
		getAllLandeStmt = connection.prepareStatement("SELECT * FROM Lande;");
		
		List<String> land = null;
		ResultSet resultSet = null;


		try {
		
			resultSet = getAllLandeStmt.executeQuery(); 
			land = new ArrayList<String>();
			
			while(resultSet.next())
			{
				land.add(resultSet.getString("Land"));
			}

		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 

		return land;
		
	}

	

}
