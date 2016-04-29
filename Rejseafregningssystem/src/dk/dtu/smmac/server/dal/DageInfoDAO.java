package dk.dtu.smmac.server.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibm.icu.util.Calendar;

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
	private PreparedStatement getRejseStmt = null;
	
	public DageInfoDAO() throws Exception 
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DAO.URL, DAO.USERNAME, DAO.PASSWORD);

			//Laver query, der henter alle rejsedage
			getDageInfoStmt = connection.prepareStatement("SELECT * FROM RejseDag;");

			//Laver query, der opdaterer en rejsedag
			updateDageInfoStmt = connection.prepareStatement("UPDATE RejseDag "
					+ "SET Morgenmad = ?, Frokost = ?, Aftensmad = ?, Nattil = ?, RejseAfbrudt = ?, UdokNat = ?, Refunderes = ? "
					+ "WHERE DagID = ? AND Nummer = ?;");

			//Laver query, der opretter en rejsedag
			createDageInfoStmt = connection.prepareStatement("INSERT INTO RejseDag "
					+ "( Dato, Nummer, Morgenmad, Frokost, Aftensmad, Nattil, RejseAfbrudt, UdokNat, Refunderes) "
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? );");

			//Laver query, der sletter en rejsedag
			deleteDageInfoStmt = connection.prepareStatement("DELETE FROM RejseDag WHERE DagID = ? AND Nummer = ?;");

			//Laver query, der finder størrelsen på tabellen
			getSizeStmt = connection.prepareStatement("SELECT COUNT(*) FROM RejseDag;");

			//Laver query, der finder alle rejserne
			getRejseStmt = connection.prepareStatement("SELECT * FROM Rejse WHERE Nummer = ?");
			
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}
		
	@Override
	public List<DageInfoDTO> getDageInfo(int nummer) throws Exception 
	{
		//TODO skal kunne hente og ikke kun create, hvis det eksisterer
		List<DageInfoDTO> list = null;
		ResultSet resultSet = null;
		DageInfoDTO dag = null;
		Date fra = null;
		Date til = null;
		
		try {
			getRejseStmt.setInt(1, nummer);
			resultSet = getRejseStmt.executeQuery();
			list = new ArrayList<DageInfoDTO>();
			
			while(resultSet.next()) {
				fra = resultSet.getDate("DatoFra");
				til = resultSet.getDate("DatoTil");
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(fra);
				
				dag = new DageInfoDTO(
						fra,
						nummer,
						false,
						false,
						false,
						false,
						false,
						false,
						false,
						resultSet.getString("Land")
						);
				
				createDageInfo(dag);
				list.add(dag);
				
				while (cal.getTime().before(til)) {
				    cal.add(Calendar.DATE, 1);
				    dag = new DageInfoDTO(
				    		new java.sql.Date(cal.getTime().getTime()),
							nummer,
							false,
							false,
							false,
							false,
							false,
							false,
							false,
							resultSet.getString("Land")
							);
				    
				    createDageInfo(dag);
				    list.add(dag);
				}
			}
			
//			resultSet = getDageInfoStmt.executeQuery(); 
//			list = new ArrayList<DageInfoDTO>();
//
//			while(resultSet.next())
//			{
//				//Tilføjer rejsedag til listen
//				list.add(new DageInfoDTO(
//						resultSet.getDate("Dato"),
//						resultSet.getInt("Nummer"),
//						resultSet.getBoolean("Morgenmad"),
//						resultSet.getBoolean("Frokost"),
//						resultSet.getBoolean("Aftensmad"),
//						resultSet.getBoolean("Nattill"),
//						resultSet.getBoolean("RejseAfbrudt"),
//						resultSet.getBoolean("UdokNat"),
//						resultSet.getBoolean("Refunderes"),
//						resultSet.getString("")
//						));
//			}

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
			updateDageInfoStmt.setDate(1, dag.getDageInfoDato());
			updateDageInfoStmt.setInt(2, dag.getNummer());
			updateDageInfoStmt.setBoolean(3, dag.getMorgenmad());
			updateDageInfoStmt.setBoolean(4, dag.getFrokost());
			updateDageInfoStmt.setBoolean(5, dag.getAftensmad());
			updateDageInfoStmt.setBoolean(6, dag.getNattill());
			updateDageInfoStmt.setBoolean(7, dag.getRejseAfbrudt());
			updateDageInfoStmt.setBoolean(8, dag.getUdokNat());
			updateDageInfoStmt.setBoolean(9, dag.getRefunderes());

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
			createDageInfoStmt.setDate(1, dag.getDageInfoDato());
			createDageInfoStmt.setInt(2, dag.getNummer());
			createDageInfoStmt.setBoolean(3, dag.getMorgenmad());
			createDageInfoStmt.setBoolean(4, dag.getFrokost());
			createDageInfoStmt.setBoolean(5, dag.getAftensmad());
			createDageInfoStmt.setBoolean(6, dag.getNattill());
			createDageInfoStmt.setBoolean(7, dag.getRejseAfbrudt());
			createDageInfoStmt.setBoolean(8, dag.getUdokNat());
			createDageInfoStmt.setBoolean(9, dag.getRefunderes());
			
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
			deleteDageInfoStmt.setDate(1, dag.getDageInfoDato());

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
