package dk.dtu.smmac.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.smmac.client.service.LandeService;

public class LandDAO extends RemoteServiceServlet implements LandeService {

	private Connection connection = null;
	
	private PreparedStatement getLandStmt = null;
	private PreparedStatement getTakstStmt = null;
	private PreparedStatement setLandStmt = null;
	
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

	

}
