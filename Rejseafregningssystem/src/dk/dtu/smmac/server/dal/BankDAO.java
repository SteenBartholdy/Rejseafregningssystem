package dk.dtu.smmac.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
					+ "( Id, RegNo, KontoNo ) "
					+ "VALUES ( ?, ?, ? );");
			
		} catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}

	@Override
	public void updateBank(BankDTO bank) throws Exception {
		try {
			updateBankStmt.setInt(1, bank.getRegNo());
			updateBankStmt.setInt(2, bank.getKontoNo());
			updateBankStmt.setInt(3, bank.getId());
			updateBankStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		}
	}

	@Override
	public void createBank(BankDTO bank) throws Exception {
		try {
			createBankStmt.setInt(1, bank.getId());
			createBankStmt.setInt(2, bank.getRegNo());
			createBankStmt.setInt(3, bank.getKontoNo());
			createBankStmt.executeUpdate();
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
	}

	@Override
	public BankDTO getBank(int id) throws Exception {
		BankDTO bank = null;
		ResultSet resultSet = null;

		try {
			getBankStmt.setInt(1, id);
			resultSet = getBankStmt.executeQuery(); 
			resultSet.next();
			bank = new BankDTO(
					id,
					resultSet.getInt("RegNo"),
					resultSet.getInt("KontoNo")
					);
		
		} 
		catch (SQLException sqlE) {
			System.out.println(sqlE.getMessage());
		} 
		
		if(bank == null) {
			bank = new BankDTO(id, 0, 0);
			
			createBank(bank);
		}
		
		return bank;
	}
	
}
