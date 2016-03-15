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
import dk.dtu.smmac.shared.AnsatteDTO;

public class AnsatteDAO extends RemoteServiceServlet implements AnsatteService {

	private static final String URL = "jdbc:mysql://mysql-tem.c7btx7pemwav.eu-central-1.rds.amazonaws.com:3306/TEM";
	private static final String USERNAME = "SMMAC";
	private static final String PASSWORD = "MsU-7dH-ZHQ-KyQ";

	private Connection connection = null;
	
	private PreparedStatement getAnsatteStmt = null;
	private PreparedStatement updateAnsatStmt = null;
	private PreparedStatement createAnsatStmt = null;
	
	public AnsatteDAO() throws Exception {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			//Laver query, der henter all ansatte
			getAnsatteStmt = connection.prepareStatement("SELECT * FROM Ansatte;");
			
			//Laver query, der opdaterer en ansat
			updateAnsatStmt = connection.prepareStatement("");
			
			//Laver query, der opretter en ansat
			createAnsatStmt = connection.prepareStatement("");
			
		} catch (SQLException sqlE) {
			
		}
	}
	
	@Override
	public List<AnsatteDTO> getAnsatte() throws Exception {
		List<AnsatteDTO> list = null;
		ResultSet resultSet = null;
		
		try {
			resultSet = getAnsatteStmt.executeQuery(); 
			list = new ArrayList<AnsatteDTO>();
			
			while(resultSet.next())
			{
				//Tilføjer ansat til listen
				list.add(new AnsatteDTO(
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
			
		} finally {
			try {
				resultSet.close();
			} 
			catch (SQLException sqlE) {

			} 
		} 
		
		return list;
	}

	@Override
	public void updateAnsat(AnsatteDTO ansat) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createAnsat(AnsatteDTO ansat) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
