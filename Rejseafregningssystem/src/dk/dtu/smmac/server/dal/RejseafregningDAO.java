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

import dk.dtu.smmac.client.service.RejseafregningService;
import dk.dtu.smmac.shared.RejseafregningDTO;
import dk.dtu.smmac.shared.RejseafregningerDTO;

public class RejseafregningDAO extends RemoteServiceServlet implements RejseafregningService {

	private Connection connection = null;

	private PreparedStatement getRejsStmt = null;
	private PreparedStatement getRejserStmt = null;
	private PreparedStatement updateRejsStmt = null;
	private PreparedStatement createRejsStmt = null;
	private PreparedStatement getSizeStmt = null;
	private PreparedStatement getLastStmt = null;
	private PreparedStatement deleteRejseStmt = null;

	public RejseafregningDAO() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DAO.URL, DAO.USERNAME, DAO.PASSWORD);
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}

	@Override
	public void updateRejse(RejseafregningDTO rejse) throws Exception {
		//Laver query, der opdaterer en rejse
		updateRejsStmt = connection.prepareStatement("UPDATE Rejseafregning "
				+ "SET Starttid = ?, Sluttid = ?, Befordring = ?, Dagpenge = ?, Udgifter = ?, Afregningstotal = ?, Refundering = ?, Forskud = ?, Afregning = ?, Godkendt = ?, Anvist = ? "
				+ "WHERE Nummer = ? AND Id = ?;");
		
		try {
			updateRejsStmt.setInt(1, rejse.getStartTid());
			updateRejsStmt.setInt(2, rejse.getSlutTid());
			updateRejsStmt.setDouble(3, rejse.getBefordring());
			updateRejsStmt.setDouble(4, rejse.getDagpenge());
			updateRejsStmt.setDouble(5, rejse.getUdgifter());
			updateRejsStmt.setDouble(6, rejse.getAfregningtotal());
			updateRejsStmt.setDouble(7, rejse.getRefundering());
			updateRejsStmt.setDouble(8, rejse.getForskud());
			updateRejsStmt.setDouble(9, rejse.getAfregning());
			updateRejsStmt.setBoolean(10, rejse.isGodkendt());
			updateRejsStmt.setBoolean(11, rejse.isAnvist());
			updateRejsStmt.setInt(12, rejse.getId());
			updateRejsStmt.setInt(13, rejse.getAnsatId());
			updateRejsStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}

	@Override
	public void createRejse(RejseafregningDTO rejse) throws Exception {
		//Laver query, der opretter en rejse
		createRejsStmt = connection.prepareStatement("INSERT INTO Rejseafregning "
				+ "( Nummer, Id, Starttid, Sluttid, Befordring, Dagpenge, Udgifter, Afregningstotal, Refundering, Forskud, Afregning, Godkendt, Anvist ) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );");
		
		try {
			createRejsStmt.setInt(1, rejse.getId());
			createRejsStmt.setInt(2, rejse.getAnsatId());
			createRejsStmt.setInt(3, rejse.getStartTid());
			createRejsStmt.setInt(4, rejse.getSlutTid());
			createRejsStmt.setDouble(5, rejse.getBefordring());
			createRejsStmt.setDouble(6, rejse.getDagpenge());
			createRejsStmt.setDouble(7, rejse.getUdgifter());
			createRejsStmt.setDouble(8, rejse.getAfregningtotal());
			createRejsStmt.setDouble(9, rejse.getRefundering());
			createRejsStmt.setDouble(10, rejse.getForskud());
			createRejsStmt.setDouble(11, rejse.getAfregning());
			createRejsStmt.setBoolean(12, rejse.isGodkendt());
			createRejsStmt.setBoolean(13, rejse.isAnvist());
			createRejsStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
	}

	@Override
	public RejseafregningDTO getRejse(int id, int ansatId) throws Exception {
		//Laver query, der henter en rejseafregning
		getRejsStmt = connection.prepareStatement("SELECT * FROM Rejseafregning WHERE Nummer = ? AND Id = ?;");
		
		RejseafregningDTO rejse = null;
		ResultSet resultSet = null;
		
		try {
			getRejsStmt.setInt(1, id);
			getRejsStmt.setInt(2, ansatId);
			resultSet = getRejsStmt.executeQuery();
			resultSet.next();
			
			rejse = new RejseafregningDTO(
					resultSet.getInt("Nummer"),
					resultSet.getInt("Id"),
					resultSet.getInt("Sluttid"),
					resultSet.getInt("Starttid"),
					resultSet.getDouble("Befordring"),
					resultSet.getDouble("Dagpenge"),
					resultSet.getDouble("Udgifter"),
					resultSet.getDouble("Afregningstotal"),
					resultSet.getDouble("Refundering"),
					resultSet.getDouble("Forskud"),
					resultSet.getDouble("Afregning"),
					resultSet.getBoolean("Godkendt"),
					resultSet.getBoolean("Anvist")
					);
			
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
		
		return rejse;
	}

	@Override
	public List<RejseafregningerDTO> getRejser(int ansatId) throws Exception {
		//Laver query, der henter alle den ansattes rejseafregninger
		getRejserStmt = connection.prepareStatement("SELECT * FROM Rejseafregning INNER JOIN Rejse ON Rejseafregning.Nummer=Rejse.Nummer WHERE Rejseafregning.Id = ?;");
		
		List<RejseafregningerDTO> list = null;
		List<String> lande = new ArrayList<String>();
		List<Date> datoFra = new ArrayList<Date>();
		List<Date> datoTil = new ArrayList<Date>();
		RejseafregningerDTO rejse;
		ResultSet resultSet = null;
		List<RejseafregningerDTO> listen = null;
		int nr = 0;

		try {
			getRejserStmt.setInt(1, ansatId);
			resultSet = getRejserStmt.executeQuery();
			list = new ArrayList<RejseafregningerDTO>();
			listen = new ArrayList<RejseafregningerDTO>();

			while(resultSet.next())
			{
				rejse = new RejseafregningerDTO(
						resultSet.getInt("Rejseafregning.Nummer"),
						resultSet.getInt("Rejseafregning.Starttid"),
						resultSet.getInt("Rejseafregning.Sluttid"),
						resultSet.getString("Rejse.Land"),
						resultSet.getDate("Rejse.DatoFra"),
						resultSet.getDate("Rejse.DatoTil")
						);
				
				if (rejse.getLand() == null) {
					
				} else {
					list.add(rejse);
				}
			}
			
			for (RejseafregningerDTO rejsen : list) {
				if (rejsen.getNr() == nr) {
					for (RejseafregningerDTO rejs : listen) {
						if (rejs.getNr() == nr) {
							rejs.setLand(rejs.getLand() + ", " + rejsen.getLand());
							if (rejs.getDatoFra().compareTo(rejsen.getDatoFra()) > 0) {
								rejs.setDatoFra(rejsen.getDatoFra());
							}
							if (rejs.getDatoTil().compareTo(rejsen.getDatoTil()) < 0) {
								rejs.setDatoTil(rejsen.getDatoTil());
							}
						}
					}
				} else {
					listen.add(rejsen);
				}
				
				nr = rejsen.getNr();
			}
			
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}

		return listen;
	}

	@Override
	public int getSize() throws Exception {
		//Laver query, der finder størrelsen på tabellen
		getSizeStmt = connection.prepareStatement("SELECT COUNT(*) FROM Rejseafregning;");
		
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
	public int getLast() throws Exception {
		getLastStmt = connection.prepareStatement("SELECT Nummer FROM Rejseafregning ORDER BY Nummer DESC LIMIT 1;");
		
		ResultSet resultSet = null;
		
		try {
			resultSet = getLastStmt.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
		
		return 0;
	}

	@Override
	public void deleteRejse(RejseafregningDTO rejse) throws Exception {
		deleteRejseStmt = connection.prepareStatement("DELETE FROM Rejseafregning WHERE Nummer = ?;");
		try {
			deleteRejseStmt.setInt(1, rejse.getId());
			deleteRejseStmt.executeUpdate();
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
	}

}
