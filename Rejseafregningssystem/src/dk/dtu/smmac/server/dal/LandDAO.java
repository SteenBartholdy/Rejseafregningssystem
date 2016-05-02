package dk.dtu.smmac.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.smmac.client.service.LandeService;
import dk.dtu.smmac.shared.AnsatDTO;
import dk.dtu.smmac.shared.DageInfoDTO;

public class LandDAO extends RemoteServiceServlet implements LandeService {

	private Connection connection = null;
	
	private PreparedStatement getLandStmt = null;
	private PreparedStatement getTakstStmt = null;
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
	public String getLand() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTakst() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLand(String land) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTakst(int takst) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getAllLande() throws Exception {
		getLandStmt = connection.prepareStatement("SELECT * From Lande;");
		
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
