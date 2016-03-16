package dk.dtu.smmac.test;

import java.util.List;

import dk.dtu.smmac.server.dal.AnsatteDAO;
import dk.dtu.smmac.server.dal.ByerDAO;
import dk.dtu.smmac.shared.AnsatDTO;

public class DAOtest {

	public static void main(String[] args) throws Exception {
		AnsatteDAO aDAO = new AnsatteDAO();
		ByerDAO bDAO = new ByerDAO();
		
		//DAO.createAnsat(new AnsatDTO(DAO.getSize() + 1, 2400, "Christoffer", "Svendsen", "s145089@student.dtu.dk", true, true));
		
		List<AnsatDTO> list = aDAO.getAnsatte();
		
		System.out.println(list.get(0).getEfternavn());
		
		//bDAO.createBy(2300, "København S");
		
		System.out.println(bDAO.getBy(2650));
	}
	
}
