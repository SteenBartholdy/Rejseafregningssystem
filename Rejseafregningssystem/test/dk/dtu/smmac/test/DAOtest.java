package dk.dtu.smmac.test;

import java.util.List;

import dk.dtu.smmac.server.dal.AnsatteDAO;
import dk.dtu.smmac.shared.AnsatteDTO;

public class DAOtest {

	public static void main(String[] args) throws Exception {
		AnsatteDAO DAO = new AnsatteDAO();
		List<AnsatteDTO> list = DAO.getAnsatte();
		
		System.out.println(list.get(0).getEfternavn()); 
		
		
	}
	
}
