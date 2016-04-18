package dk.dtu.smmac.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.smmac.client.service.AfdelingerService;
import dk.dtu.smmac.client.service.AnsatteService;
import dk.dtu.smmac.shared.AfdelingDTO;
import dk.dtu.smmac.shared.AnsatDTO;

public class AfdelingerDAO extends RemoteServiceServlet implements AfdelingerService {

	private Connection connection = null;

	private PreparedStatement getAfdelingerStmt = null;
	private PreparedStatement createAfdelingStmt = null;
	private PreparedStatement deleteAfdelingStmt = null;
	private PreparedStatement getSizeStmt = null;

	public AfdelingerDAO() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DAO.URL, DAO.USERNAME, DAO.PASSWORD);

			//Laver query, der henter all afdelinger
			getAfdelingerStmt = connection.prepareStatement("SELECT * FROM Afdeling;");

			//Laver query, der opretter en afdeling
			createAfdelingStmt = connection.prepareStatement("INSERT INTO Afdeling "
					+ "( Afdeling ) "
					+ "VALUES ( ? );");

			//Laver query, der sletter en ansat
			deleteAfdelingStmt = connection.prepareStatement("DELETE FROM Afdeling WHERE Afdeling LIKE ?;");

			//Laver query, der finder størrelsen på tabellen
			getSizeStmt = connection.prepareStatement("SELECT COUNT(*) FROM Afdeling;");

		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}

	@Override
	public List<AfdelingDTO> getAfdelinger() throws Exception {
		List<AfdelingDTO> list = null;
		ResultSet resultSet = null;

		try {
			resultSet = getAfdelingerStmt.executeQuery(); 
			list = new ArrayList<AfdelingDTO>();

			while(resultSet.next())
			{
				//Tilføjer ansat til listen
				list.add(new AfdelingDTO(
						resultSet.getString("Afdeling")
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
	public void createAfdeling(AfdelingDTO afdeling) throws Exception {
		try {
			createAfdelingStmt.setString(1, afdeling.getAfdeling());
			createAfdelingStmt.executeUpdate();
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
	}

	@Override
	public void deleteAfdeling(AfdelingDTO afdeling) throws Exception {
		try {
			deleteAfdelingStmt.setString(1, afdeling.getAfdeling());
			deleteAfdelingStmt.executeUpdate();
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
	}

	@Override
	public int getSize() throws Exception {
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
