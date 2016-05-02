package dk.dtu.smmac.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import dk.dtu.smmac.client.service.UdgifterService;
import dk.dtu.smmac.shared.UdgifterDTO;

public class UdgifterDAO extends RemoteServiceServlet implements UdgifterService
{
	private Connection connection = null;

	private PreparedStatement getUdgifterStmt = null;
	private PreparedStatement updateUdgifterStmt = null;
	private PreparedStatement createUdgifterStmt = null;
	private PreparedStatement deleteUdgifterStmt = null;
	private PreparedStatement getSizeStmt = null;
	
	public UdgifterDAO() throws Exception
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DAO.URL, DAO.USERNAME, DAO.PASSWORD);
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}
	
	
	@Override
	public List<UdgifterDTO> getUdgifter(int nr) throws Exception 
	{
		//Laver query, der henter alle udgifter
		getUdgifterStmt = connection.prepareStatement("SELECT * FROM Udgifter WHERE Nummer = ?;");
		
		List<UdgifterDTO> list = null;
		ResultSet resultSet = null;
		
		try {
			getUdgifterStmt.setInt(1, nr);
			resultSet = getUdgifterStmt.executeQuery(); 
			list = new ArrayList<UdgifterDTO>();

			while(resultSet.next())
			{
				//Tilføjer udgiften til listen
				list.add(new UdgifterDTO(
						resultSet.getInt("Id"),
						resultSet.getString("BilagsNummer"),
						resultSet.getInt("Nummer"),
						resultSet.getString("UdgiftsType"),
						resultSet.getString("Dato"),
						resultSet.getString("Beloeb")						
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
	public void updateUdgifter(UdgifterDTO udgift) throws Exception 
	{
		//Laver query, der opdaterer en udgift
		updateUdgifterStmt = connection.prepareStatement("UPDATE Udgifter "
				+ "SET BilagsNummer = ?, UdgiftsType = ?, Dato = ?, Beloeb = ? "
				+ "WHERE Id = ? AND Nummer = ?;");
		
		try {
			updateUdgifterStmt.setString(1, udgift.getBilagsNummer());
			updateUdgifterStmt.setString(2, udgift.getUdgiftType());
			updateUdgifterStmt.setString(3, udgift.getUdgiftDato());
			updateUdgifterStmt.setString(4, udgift.getUdgiftBeloeb());
			updateUdgifterStmt.setInt(5, udgift.getId());
			updateUdgifterStmt.setInt(6, udgift.getNummer());

			updateUdgifterStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}		
	}

	@Override
	public void createUdgifter(UdgifterDTO udgift) throws Exception 
	{
		//Laver query, der opretter en udgift
		createUdgifterStmt = connection.prepareStatement("INSERT INTO Udgifter "
				+ "( Id, BilagsNummer, Nummer, UdgiftsType, Dato, Beloeb ) "
				+ "VALUES ( ?, ?, ?, ?, ?, ? );");
		
		try {
			createUdgifterStmt.setInt(1, udgift.getId());
			createUdgifterStmt.setString(2, udgift.getBilagsNummer());
			createUdgifterStmt.setInt(3, udgift.getNummer());
			createUdgifterStmt.setString(4, udgift.getUdgiftType());
			createUdgifterStmt.setString(5, udgift.getUdgiftDato());
			createUdgifterStmt.setString(6, udgift.getUdgiftBeloeb());
			
			createUdgifterStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 		
	}

	@Override
	public void deleteUdgifter(UdgifterDTO udgift) throws Exception 
	{
		//Laver query, der sletter en udgift
		deleteUdgifterStmt = connection.prepareStatement("DELETE FROM Udgifter WHERE Nummer = ? AND Id = ? ");
		
		try {
			deleteUdgifterStmt.setInt(1, udgift.getNummer());
			deleteUdgifterStmt.setInt(2, udgift.getId());

			deleteUdgifterStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
	}

	@Override
	public int getSize() throws Exception 
	{
		//Laver query, der finder størrelsen på tabellen
		getSizeStmt = connection.prepareStatement("SELECT COUNT(*) FROM Udgifter;" );
		
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
