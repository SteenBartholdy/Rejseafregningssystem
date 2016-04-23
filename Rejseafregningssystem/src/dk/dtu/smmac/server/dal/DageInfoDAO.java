package dk.dtu.smmac.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.smmac.client.service.DageInfoService;
import dk.dtu.smmac.shared.DageInfoDTO;

public class DageInfoDAO extends RemoteServiceServlet implements DageInfoService
{

	private Connection connection = null;

	private PreparedStatement getDageInfoStmt = null;
	private PreparedStatement updateDageInfoStmt = null;
	private PreparedStatement createDageInfoStmt = null;
	private PreparedStatement deleteDageInfoStmt = null;
	private PreparedStatement getSizeStmt = null;
	
	public DageInfoDAO() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DAO.URL, DAO.USERNAME, DAO.PASSWORD);

			//Laver query, der henter alle rejsedage
			getDageInfoStmt = connection.prepareStatement("SELECT * FROM DageInfo;");

			//Laver query, der opdaterer en rejsedag
			updateDageInfoStmt = connection.prepareStatement("UPDATE DageInfo "
					+ "SET Land = ?, Byen = ?, DatoFra = ?, DatoTil = ? "
					+ "WHERE RejseID = ? AND Nummer = ?;");

			//Laver query, der opretter en rejsedag
			createDageInfoStmt = connection.prepareStatement("INSERT INTO DageInfo "
					+ "( RejseID, Nummer, Land, Byen, DatoFra, DatoTil) "
					+ "VALUES ( ?, ?, ?, ?, ?, ? );");

			//Laver query, der sletter en rejsedag
			deleteDageInfoStmt = connection.prepareStatement("DELETE FROM DageInfo WHERE RejseID = ? AND Nummer = ?;");

			//Laver query, der finder størrelsen på tabellen
			getSizeStmt = connection.prepareStatement("SELECT COUNT(*) FROM DageInfo;");

		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}
	
	@Override
	public List<DageInfoDTO> getDageInfo() throws Exception {
		List<DageInfoDTO> list = null;
		ResultSet resultSet = null;
		
		try {
			resultSet = getDageInfoStmt.executeQuery(); 
			list = new ArrayList<DageInfoDTO>();

			while(resultSet.next())
			{
				//Tilføjer rejsedag til listen
				list.add(new DageInfoDTO(
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
	public void updateDageInfo(DageInfoDTO dag) throws Exception 
	{
		try {
			updateDageInfoStmt.setInt(1, dag.getRejseID());
			updateDageInfoStmt.setInt(2, dag.getNummer());
			updateDageInfoStmt.setString(3, dag.getLand());
			updateDageInfoStmt.setString(4, dag.getBy());
			updateDageInfoStmt.setDate(5, dag.getDatoFra());
			updateDageInfoStmt.setDate(6, dag.getDatoTil());

			updateDageInfoStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
		
	}

	@Override
	public void createDageInfo(DageInfoDTO dag) throws Exception 
	{
		try {
			createDageInfoStmt.setInt(1, dag.getRejseID());
			createDageInfoStmt.setInt(2, dag.getNummer());
			createDageInfoStmt.setString(3, dag.getLand());
			createDageInfoStmt.setString(4, dag.getBy());
			createDageInfoStmt.setDate(5, dag.getDatoFra());
			createDageInfoStmt.setDate(6, dag.getDatoTil());

			createDageInfoStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
		
	}

	@Override
	public void deleteDageInfo(DageInfoDTO dag) throws Exception 
	{
		try {
			deleteDageInfoStmt.setInt(1, dag.getRejseID());

			deleteDageInfoStmt.executeUpdate();
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
