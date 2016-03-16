package dk.dtu.smmac.test;

import java.util.List;

import dk.dtu.smmac.server.dal.AnsatteDAO;
import dk.dtu.smmac.shared.AnsatDTO;

public class DAOtest {

	public static void main(String[] args) throws Exception {
		AnsatteDAO DAO = new AnsatteDAO();
		
		//DAO.createAnsat(new AnsatDTO(1, 2650, "Martin", "Roos", "s145094@student.dtu.dk", true, true));
		
		List<AnsatDTO> list = DAO.getAnsatte();
		
		System.out.println(list.get(0).getEfternavn());
	}
	
}
