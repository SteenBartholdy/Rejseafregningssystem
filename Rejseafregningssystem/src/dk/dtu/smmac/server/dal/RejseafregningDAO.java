package dk.dtu.smmac.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.smmac.client.service.DAWAService;
import dk.dtu.smmac.client.service.RejseafregningService;
import dk.dtu.smmac.shared.RejseafregningDTO;

public class RejseafregningDAO extends RemoteServiceServlet implements RejseafregningService {

	private Connection connection = null;

	private PreparedStatement getRejsStmt = null;
	private PreparedStatement getRejserStmt = null;
	private PreparedStatement updateRejsStmt = null;
	private PreparedStatement createRejsStmt = null;
	
	public RejseafregningDAO() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DAO.URL, DAO.USERNAME, DAO.PASSWORD);
			
			//Laver query, der henter en rejseafregning
			getRejsStmt = connection.prepareStatement("SELECT * FROM Rejseafregning WHERE Nummer = ? AND Id = ?;");
			
			//Laver query, der henter alle den ansattes rejseafregninger
			getRejserStmt = connection.prepareStatement("SELECT * FROM Rejseafregning WHERE Id = ?;");
			
			//Laver query, der opdaterer en rejse
			updateRejsStmt = connection.prepareStatement("UPDATE Rejseafregning "
					+ "SET Starttid = ?, Sluttid = ? "
					+ "WHERE Nummer = ? AND Id = ?;");
			
			//Laver query, der opretter en rejse
			createRejsStmt = connection.prepareStatement("INSERT INTO Rejseafregning "
					+ "( Nummer, Id, Starttid, Sluttid ) "
					+ "VALUES ( ?, ?, ?, ? );");
			
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}

	@Override
	public void updateRejse(RejseafregningDTO rejse) throws Exception {
		try {
			updateRejsStmt.setInt(1, rejse.getStartTid());
			updateRejsStmt.setInt(2, rejse.getSlutTid());
			updateRejsStmt.setInt(3, rejse.getId());
			updateRejsStmt.setInt(4, rejse.getAnsatId());
			updateRejsStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}

	@Override
	public void createRejse(RejseafregningDTO rejse) throws Exception {
		try {
			createRejsStmt.setInt(1, rejse.getId());
			createRejsStmt.setInt(2, rejse.getAnsatId());
			createRejsStmt.setInt(3, rejse.getStartTid());
			createRejsStmt.setInt(4, rejse.getSlutTid());
			createRejsStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
	}

	@Override
	public RejseafregningDTO getRejse(int id, int ansatId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RejseafregningDTO> getRejser(int ansatId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
