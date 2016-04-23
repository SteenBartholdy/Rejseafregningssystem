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

public class RejseDAO extends RemoteServiceServlet implements RejseService
{

	private Connection connection = null;

	private PreparedStatement getRejseStmt = null;
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

			//Laver query, der opdaterer en rejse
			updateRejseStmt = connection.prepareStatement("UPDATE Rejse "
					+ "SET Land = ?, Byen = ?, DatoFra = ?, DatoTil = ? "
					+ "WHERE RejseID = ? AND Nummer = ?;");

			//Laver query, der opretter en rejse
			createRejseStmt = connection.prepareStatement("INSERT INTO Rejse "
					+ "( RejseID, Nummer, Land, Byen, DatoFra, DatoTil) "
					+ "VALUES ( ?, ?, ?, ?, ?, ? );");

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
						resultSet.getString("Byen"),
						resultSet.getDate("DatoFra"),
						resultSet.getDate("DatoTil")
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
			updateRejseStmt.setInt(1, rejse.getRejseID());
			updateRejseStmt.setInt(2, rejse.getNummer());
			updateRejseStmt.setString(3, rejse.getLand());
			updateRejseStmt.setString(4, rejse.getBy());
			updateRejseStmt.setDate(5, rejse.getDatoFra());
			updateRejseStmt.setDate(6, rejse.getDatoTil());

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
			createRejseStmt.setString(4, rejse.getBy());
			createRejseStmt.setDate(5, rejse.getDatoFra());
			createRejseStmt.setDate(6, rejse.getDatoTil());

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
	
}
