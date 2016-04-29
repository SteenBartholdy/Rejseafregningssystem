package dk.dtu.smmac.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.smmac.client.service.RejseService;
import dk.dtu.smmac.shared.RejseDTO;
import dk.dtu.smmac.shared.RejseafregningerDTO;

public class RejseDAO extends RemoteServiceServlet implements RejseService
{

	private Connection connection = null;

	private PreparedStatement getRejseStmt = null;
	private PreparedStatement getRejserStmt = null;
	private PreparedStatement updateRejseStmt = null;
	private PreparedStatement createRejseStmt = null;
	private PreparedStatement deleteRejseStmt = null;
	private PreparedStatement getSizeStmt = null;
	
	public RejseDAO() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DAO.URL, DAO.USERNAME, DAO.PASSWORD);

			//Laver query, der henter alle rejser
			getRejseStmt = connection.prepareStatement("SELECT * FROM Rejse;");
			
			//Laver query, der henter alle rejserne fra en rejseafregning
			getRejserStmt = connection.prepareStatement("SELECT * FROM Rejse WHERE Nummer = ?;");

			//Laver query, der opdaterer en rejse
			updateRejseStmt = connection.prepareStatement("UPDATE Rejse "
					+ "SET Land = ?, DatoFra = ?, DatoTil = ?, Projekt = ?, Opgave = ? "
					+ "WHERE RejseID = ? AND Nummer = ?;");

			//Laver query, der opretter en rejse
			createRejseStmt = connection.prepareStatement("INSERT INTO Rejse "
					+ "( RejseID, Nummer, Land, DatoFra, DatoTil, Projekt, Opgave) "
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ? );");

			//Laver query, der sletter en rejse
			deleteRejseStmt = connection.prepareStatement("DELETE FROM Rejse WHERE RejseID = ? AND Nummer = ?;");

			//Laver query, der finder størrelsen på tabellen
			getSizeStmt = connection.prepareStatement("SELECT COUNT(*) FROM Rejse;");

		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}
	
	@Override
	public List<RejseDTO> getRejse() throws Exception {
		List<RejseDTO> list = null;
		ResultSet resultSet = null;
		
		try {
			resultSet = getRejseStmt.executeQuery(); 
			list = new ArrayList<RejseDTO>();

			while(resultSet.next())
			{
				//Tilføjer rejsedag til listen
				list.add(new RejseDTO(
						resultSet.getInt("RejseID"),
						resultSet.getInt("Nummer"),
						resultSet.getString("Land"),
						resultSet.getDate("DatoFra"),
						resultSet.getDate("DatoTil"), 
						resultSet.getString("Projekt"),
						resultSet.getString("Opgave")
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
				connection.close();
			}
		} 

		return list;
	}

	@Override
	public void updateRejse(RejseDTO rejse) throws Exception 
	{
		try {
			updateRejseStmt.setString(1, rejse.getLand());
			updateRejseStmt.setDate(2, rejse.getDatoFra());
			updateRejseStmt.setDate(3, rejse.getDatoTil());
			updateRejseStmt.setString(4, rejse.getProjekt());
			updateRejseStmt.setString(5, rejse.getOpgave());
			updateRejseStmt.setInt(6, rejse.getRejseID());
			updateRejseStmt.setInt(7, rejse.getNummer());

			updateRejseStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
		
	}

	@Override
	public void createRejse(RejseDTO rejse) throws Exception 
	{
		try {
			createRejseStmt.setInt(1, rejse.getRejseID());
			createRejseStmt.setInt(2, rejse.getNummer());
			createRejseStmt.setString(3, rejse.getLand());
			createRejseStmt.setDate(4, rejse.getDatoFra());
			createRejseStmt.setDate(5, rejse.getDatoTil());
			createRejseStmt.setString(6, rejse.getProjekt());
			createRejseStmt.setString(7, rejse.getOpgave());

			createRejseStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
		
	}

	@Override
	public void deleteRejse(RejseDTO rejse) throws Exception 
	{
		try {
			deleteRejseStmt.setInt(1, rejse.getRejseID());

			deleteRejseStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
		
	}

	@Override
	public int getSize() throws Exception 
	{
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
	public List<RejseDTO> getRejser(int nr) throws Exception {
		List<RejseDTO> list = null;
		ResultSet resultSet = null;
		
		try {
			getRejserStmt.setInt(1, nr);
			resultSet = getRejserStmt.executeQuery();
			list = new ArrayList<RejseDTO>();
			
			while(resultSet.next()) {
				list.add(new RejseDTO(
						resultSet.getInt("RejseID"),
						resultSet.getInt("Nummer"),
						resultSet.getString("Land"),
						resultSet.getDate("DatoFra"),
						resultSet.getDate("DatoTil"),
						resultSet.getString("Projekt"),
						resultSet.getString("Opgave")
						));
			}
			
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
		
		return list;
	}
	
}
