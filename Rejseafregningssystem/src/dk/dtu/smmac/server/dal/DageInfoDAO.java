package dk.dtu.smmac.server.dal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
	private PreparedStatement getRejseStmt = null;
	private PreparedStatement getDagpengeStmt = null;
	private PreparedStatement getTakstStmt = null;
	private PreparedStatement getRefunderingStmt = null;

	public DageInfoDAO() throws Exception 
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DAO.URL, DAO.USERNAME, DAO.PASSWORD);
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}

	@Override
	public List<DageInfoDTO> getDageInfo(int nummer) throws Exception 
	{
		List<DageInfoDTO> list = null;
		List<DageInfoDTO> fulllist = null;
		ResultSet resultSet = null;
		DageInfoDTO dag = null;
		Date fra = null;
		Date til = null;

		try {
			//Laver query, der finder alle rejserne
			getRejseStmt = connection.prepareStatement("SELECT * FROM RejseDag WHERE Nummer = ?");
			getRejseStmt.setInt(1, nummer);
			resultSet = getRejseStmt.executeQuery();
			fulllist = new ArrayList<DageInfoDTO>();

			while(resultSet.next()) {
				fulllist.add(new DageInfoDTO(
						resultSet.getDate("Dato"),
						resultSet.getInt("Nummer"),
						resultSet.getBoolean("Morgenmad"),
						resultSet.getBoolean("Frokost"),
						resultSet.getBoolean("Aftensmad"),
						resultSet.getBoolean("Nattilaeg"),
						resultSet.getBoolean("RejseAfbrudt"),
						resultSet.getBoolean("UdokNat"),
						resultSet.getBoolean("Refunderes"),
						resultSet.getString("Land")
						));
			}

			//Laver query, der finder alle rejserne
			getRejseStmt = connection.prepareStatement("SELECT * FROM Rejse WHERE Nummer = ?");
			getRejseStmt.setInt(1, nummer);
			resultSet = getRejseStmt.executeQuery();
			list = new ArrayList<DageInfoDTO>();

			while(resultSet.next()) {
				fra = resultSet.getDate("DatoFra");
				til = resultSet.getDate("DatoTil");

				Calendar cal = Calendar.getInstance();
				cal.setTime(fra);

				dag = getDageInfo(fra, nummer);

				if (dag == null || dag.getCountry().equals(resultSet.getString("Land")) == false) {
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
				}

				list.add(dag);

				while (cal.getTime().before(til)) {
					cal.add(Calendar.DATE, 1);

					dag = getDageInfo(new java.sql.Date(cal.getTime().getTime()), nummer);

					if (dag == null || dag.getCountry().equals(resultSet.getString("Land")) == false) {
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
					}

					list.add(dag);
				}
			}

		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
		
		for (DageInfoDTO fDag : fulllist) {
			if(checkDay(fDag, list)) {
				deleteDageInfo(fDag);
			}
		}

		return list;
	}
	
	public boolean checkDay(DageInfoDTO dag, List<DageInfoDTO> list) {
		for (DageInfoDTO lDag : list) {
			if (dag.getDageInfoDato().compareTo(lDag.getDageInfoDato()) == 0) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void updateDageInfo(DageInfoDTO dag) throws Exception 
	{
		//Laver query, der opdaterer en rejsedag
		updateDageInfoStmt = connection.prepareStatement("UPDATE RejseDag "
				+ "SET Morgenmad = ?, Frokost = ?, Aftensmad = ?, Nattilaeg = ?, RejseAfbrudt = ?, UdokNat = ?, Refunderes = ?, Land = ? "
				+ "WHERE Dato = ? AND Nummer = ?;");

		try {
			updateDageInfoStmt.setBoolean(1, dag.getMorgenmad());
			updateDageInfoStmt.setBoolean(2, dag.getFrokost());
			updateDageInfoStmt.setBoolean(3, dag.getAftensmad());
			updateDageInfoStmt.setBoolean(4, dag.getNattill());
			updateDageInfoStmt.setBoolean(5, dag.getRejseAfbrudt());
			updateDageInfoStmt.setBoolean(6, dag.getUdokNat());
			updateDageInfoStmt.setBoolean(7, dag.getRefunderes());
			updateDageInfoStmt.setString(8, dag.getCountry());
			updateDageInfoStmt.setDate(9, dag.getDageInfoDato());
			updateDageInfoStmt.setInt(10, dag.getNummer());

			updateDageInfoStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}

	}

	@Override
	public void createDageInfo(DageInfoDTO dag) throws Exception 
	{
		//Laver query, der opretter en rejsedag
		createDageInfoStmt = connection.prepareStatement("INSERT INTO RejseDag "
				+ "( Dato, Nummer, Morgenmad, Frokost, Aftensmad, Nattilaeg, RejseAfbrudt, UdokNat, Refunderes, Land) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );");

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
			createDageInfoStmt.setString(10, dag.getCountry());

			createDageInfoStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 

	}

	@Override
	public void deleteDageInfo(DageInfoDTO dag) throws Exception 
	{
		//Laver query, der sletter en rejsedag
		deleteDageInfoStmt = connection.prepareStatement("DELETE FROM RejseDag WHERE Dato = ? AND Nummer = ?;");

		try {
			deleteDageInfoStmt.setDate(1, dag.getDageInfoDato());
			deleteDageInfoStmt.setInt(2, dag.getNummer());

			deleteDageInfoStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 

	}

	@Override
	public int getSize() throws Exception 
	{
		//Laver query, der finder størrelsen på tabellen
		getSizeStmt = connection.prepareStatement("SELECT COUNT(*) FROM RejseDag;");

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
	public DageInfoDTO getDageInfo(Date dato, int nummer) throws Exception {
		//Laver query, der henter alle rejsedage
		getDageInfoStmt = connection.prepareStatement("SELECT * FROM RejseDag WHERE Dato = ? AND Nummer = ?;");

		DageInfoDTO dag = null;
		ResultSet resultSet = null;

		try {
			getDageInfoStmt.setDate(1, dato);
			getDageInfoStmt.setInt(2, nummer);
			resultSet = getDageInfoStmt.executeQuery(); 

			while(resultSet.next())
			{
				dag = new DageInfoDTO(
						resultSet.getDate("Dato"),
						resultSet.getInt("Nummer"),
						resultSet.getBoolean("Morgenmad"),
						resultSet.getBoolean("Frokost"),
						resultSet.getBoolean("Aftensmad"),
						resultSet.getBoolean("Nattilaeg"),
						resultSet.getBoolean("RejseAfbrudt"),
						resultSet.getBoolean("UdokNat"),
						resultSet.getBoolean("Refunderes"),
						resultSet.getString("Land")
						);
			}

		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}

		return dag;
	}

	@Override
	public double getDagpenge(int nummer) throws Exception {
		getDagpengeStmt = connection.prepareStatement("SELECT * FROM RejseDag WHERE Nummer = ?;");

		ResultSet resultSet = null;
		ResultSet landSet = null;
		double dagpenge = 0;
		int takst = 0;

		try {
			getDagpengeStmt.setInt(1, nummer);
			resultSet = getDagpengeStmt.executeQuery();

			while(resultSet.next())
			{
				getTakstStmt = connection.prepareStatement("SELECT Takst FROM Lande WHERE Land LIKE ?;");
				getTakstStmt.setString(1, resultSet.getString("Land"));
				landSet = getTakstStmt.executeQuery();

				while(landSet.next())
				{
					takst = landSet.getInt("Takst"); 
				}

				boolean morgenmad = resultSet.getBoolean("Morgenmad");
				boolean frokost = resultSet.getBoolean("Frokost");
				boolean aftensmad = resultSet.getBoolean("Aftensmad");

				double trækPro = 1;
				if(morgenmad) {
					trækPro = trækPro - 0.15;
				}
				if(frokost) {
					trækPro = trækPro - 0.30;
				}
				if(aftensmad) {
					trækPro = trækPro - 0.30;
				}

				dagpenge = dagpenge + (takst*trækPro);
			}

		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}

		return round(dagpenge, 2);
	}

	@Override
	public double getRefundering(int nummer) throws Exception {
		getDagpengeStmt = connection.prepareStatement("SELECT * FROM RejseDag WHERE Nummer = ?;");

		ResultSet resultSet = null;
		ResultSet landSet = null;
		double dagpenge = 0;
		int takst = 0;

		try {
			getDagpengeStmt.setInt(1, nummer);
			resultSet = getDagpengeStmt.executeQuery();

			while(resultSet.next())
			{
				if (resultSet.getBoolean("Refunderes")) {
					getTakstStmt = connection.prepareStatement("SELECT Takst FROM Lande WHERE Land LIKE ?;");
					getTakstStmt.setString(1, resultSet.getString("Land"));
					landSet = getTakstStmt.executeQuery();

					while(landSet.next())
					{
						takst = landSet.getInt("Takst"); 
					}

					boolean morgenmad = resultSet.getBoolean("Morgenmad");
					boolean frokost = resultSet.getBoolean("Frokost");
					boolean aftensmad = resultSet.getBoolean("Aftensmad");

					double trækPro = 1;
					if(morgenmad) {
						trækPro = trækPro - 0.15;
					}
					if(frokost) {
						trækPro = trækPro - 0.30;
					}
					if(aftensmad) {
						trækPro = trækPro - 0.30;
					}

					dagpenge = dagpenge + (takst*trækPro);
				}
			}

		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}

		return round(dagpenge, 2);
	}

	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

}
