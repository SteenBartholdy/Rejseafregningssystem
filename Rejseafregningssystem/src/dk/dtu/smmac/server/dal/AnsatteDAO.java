package dk.dtu.smmac.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.smmac.client.AnsatteService;
import dk.dtu.smmac.shared.AnsatDTO;

public class AnsatteDAO extends RemoteServiceServlet implements AnsatteService {

	private static final String URL = "jdbc:mysql://mysql-tem.cxjp73j45toh.eu-west-1.rds.amazonaws.com:3306/TEM";
	private static final String USERNAME = "SMMAC";
	private static final String PASSWORD = "MsU-7dH-ZHQ-KyQ";

	private Connection connection = null;

	private PreparedStatement getAnsatteStmt = null;
	private PreparedStatement updateAnsatStmt = null;
	private PreparedStatement createAnsatStmt = null;
	private PreparedStatement deleteAnsatStmt = null;

	public AnsatteDAO() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			//Laver query, der henter all ansatte
			getAnsatteStmt = connection.prepareStatement("SELECT * FROM Ansatte;");

			//Laver query, der opdaterer en ansat
			updateAnsatStmt = connection.prepareStatement("UPDATE Ansatte "
					+ "SET Postnummer = ?, Telefon = ?, Fornavn = ?, Efternavn = ?, Afdeling = ?, Vejnavn = ?, Husnr = ?, Etage = ?, Doer = ?, Email = ?, Anviser = ?, Godkender = ? "
					+ "WHERE Id = ?;");

			//Laver query, der opretter en ansat
			createAnsatStmt = connection.prepareStatement("INSERT INTO Ansatte "
					+ "( Id, Postnummer, Telefon, Fornavn, Efternavn, Afdeling, Vejnavn, Husnr, Etage, Doer, Email, Anviser, Godkender ) "
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );");

			//Laver query, der sletter en ansat
			deleteAnsatStmt = connection.prepareStatement("DELETE FROM Ansatte WHERE Id = ?;");

		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}

	@Override
	public List<AnsatDTO> getAnsatte() throws Exception {
		List<AnsatDTO> list = null;
		ResultSet resultSet = null;

		try {
			resultSet = getAnsatteStmt.executeQuery(); 
			list = new ArrayList<AnsatDTO>();

			while(resultSet.next())
			{
				//Tilføjer ansat til listen
				list.add(new AnsatDTO(
						resultSet.getInt("Id"),
						resultSet.getInt("Postnummer"),
						resultSet.getInt("Telefon"),
						resultSet.getString("Fornavn"),
						resultSet.getString("Efternavn"),
						resultSet.getString("Afdeling"),
						resultSet.getString("Vejnavn"),
						resultSet.getString("Husnr"),
						resultSet.getString("Etage"),
						resultSet.getString("Doer"),
						resultSet.getString("Email"),
						resultSet.getBoolean("Anviser"),
						resultSet.getBoolean("Godkender")
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
			} 
		} 

		return list;
	}

	@Override
	public void updateAnsat(AnsatDTO ansat) throws Exception {
		try {
			updateAnsatStmt.setInt(1, ansat.getPostnr());
			updateAnsatStmt.setInt(2, ansat.getTlf());
			updateAnsatStmt.setString(3, ansat.getFornavn());
			updateAnsatStmt.setString(4, ansat.getEfternavn());
			updateAnsatStmt.setString(5, ansat.getAfdeling());
			updateAnsatStmt.setString(6, ansat.getVejnavn());
			updateAnsatStmt.setString(7, ansat.gethusnr());
			updateAnsatStmt.setString(8, ansat.getEtage());
			updateAnsatStmt.setString(9, ansat.getDoer());
			updateAnsatStmt.setString(10, ansat.getEmail());
			updateAnsatStmt.setBoolean(11, ansat.getAnviser());
			updateAnsatStmt.setBoolean(12, ansat.getGodkender());
			updateAnsatStmt.setInt(13, ansat.getID());

			updateAnsatStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
	}

	@Override
	public void createAnsat(AnsatDTO ansat) throws Exception {
		try {
			createAnsatStmt.setInt(1, ansat.getID());
			createAnsatStmt.setInt(2, ansat.getPostnr());
			createAnsatStmt.setInt(3, ansat.getTlf());
			createAnsatStmt.setString(4, ansat.getFornavn());
			createAnsatStmt.setString(5, ansat.getEfternavn());
			createAnsatStmt.setString(6, ansat.getAfdeling());
			createAnsatStmt.setString(7, ansat.getVejnavn());
			createAnsatStmt.setString(8, ansat.gethusnr());
			createAnsatStmt.setString(9, ansat.getEtage());
			createAnsatStmt.setString(10, ansat.getDoer());
			createAnsatStmt.setString(11, ansat.getEmail());

			if (ansat.getAnviser())
				createAnsatStmt.setInt(12, 1);
			else
				createAnsatStmt.setInt(12, 0);

			if (ansat.getGodkender())
				createAnsatStmt.setInt(13, 1);
			else
				createAnsatStmt.setInt(13, 0);

			createAnsatStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
	}

	@Override
	public void deleteAnsat(AnsatDTO ansat) throws Exception {	
		try {
			deleteAnsatStmt.setInt(1, ansat.getID());

			deleteAnsatStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
	}

}
