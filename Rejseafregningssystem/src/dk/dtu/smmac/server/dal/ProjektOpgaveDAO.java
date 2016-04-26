package dk.dtu.smmac.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.smmac.client.service.ProjektOpgaveService;
import dk.dtu.smmac.shared.AfdelingDTO;


public class ProjektOpgaveDAO extends RemoteServiceServlet implements ProjektOpgaveService 
{
	private Connection connection = null;

	private PreparedStatement getProjektStmt = null;
	private PreparedStatement getOpgaveStmt = null;
	
	public ProjektOpgaveDAO() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DAO.URL, DAO.USERNAME, DAO.PASSWORD);

			//Laver query, der henter all projekter
			getProjektStmt = connection.prepareStatement("SELECT * FROM Projekt;");
			
			//Laver query, der henter opgaver i forhold til valgt projekt
			getOpgaveStmt = connection.prepareStatement("SELECT * FROM Opgave WHERE Projekt LIKE ?;");

		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}
	
	
	@Override
	public List<String> getProjekt() throws Exception 
	{
		List<String> list = null;
		ResultSet resultSet = null;
		
		try {
			resultSet = getProjektStmt.executeQuery(); 
			list = new ArrayList<String>();
			

			while(resultSet.next())
			{
				//Tilføjer projekt til listen
				list.add(resultSet.getString("Projekt"));
			}

		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} finally {
			try {
				resultSet.close();
			} 
			catch (SQLException sqlE) {
				System.out.println(sqlE.getMessage());
				connection.close();
			}
		} 

		return list;
	}

	@Override
	public List<String> getOpgave(String projekt) throws Exception 
	{
		List<String> list = null;
		ResultSet resultSet = null;
		
		try {
			getOpgaveStmt.setString(1, projekt);
			resultSet = getOpgaveStmt.executeQuery(); 
			list = new ArrayList<String>();
			

			while(resultSet.next())
			{
				//Tilføjer opgaven til listen
				list.add(resultSet.getString("Opgave"));
			}

		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} finally {
			try {
				resultSet.close();
			} 
			catch (SQLException sqlE) {
				System.out.println(sqlE.getMessage());
				connection.close();
			}
		} 

		return list;
	}

}
