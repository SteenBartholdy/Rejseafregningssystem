package dk.dtu.smmac.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.smmac.client.service.BankService;
import dk.dtu.smmac.shared.BankDTO;

public class BankDAO extends RemoteServiceServlet implements BankService {

	private Connection connection = null;

	private PreparedStatement getBankStmt = null;
	private PreparedStatement updateBankStmt = null;
	private PreparedStatement createBankStmt = null;
	
	public BankDAO() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DAO.URL, DAO.USERNAME, DAO.PASSWORD);
			
			//Laver query, der henter bankoplysninger
			getBankStmt = connection.prepareStatement("SELECT * FROM Konto WHERE Id = ?;");
			
			//Laver query, der opdaterer en konto
			updateBankStmt = connection.prepareStatement("UPDATE Konto "
					+ "SET RegNo = ?, KontoNo = ? "
					+ "WHERE Id = ?;");
			
			//Laver query, der opretter en konto
			createBankStmt = connection.prepareStatement("INSERT INTO Konto "
					+ "( Id, RegNo, KonoNot ) "
					+ "VALUES ( ?, ?, ? );");
			
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}

	@Override
	public void updateBank(BankDTO bank) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createBank(BankDTO bank) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BankDTO getBank(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
