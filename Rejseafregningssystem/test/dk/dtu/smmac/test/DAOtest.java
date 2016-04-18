package dk.dtu.smmac.test;

import java.util.List;

import dk.dtu.smmac.server.dal.AfdelingerDAO;
import dk.dtu.smmac.server.dal.AnsatteDAO;
import dk.dtu.smmac.server.dal.ByerDAO;
import dk.dtu.smmac.shared.AfdelingDTO;
import dk.dtu.smmac.shared.AnsatDTO;

public class DAOtest {

	public static void main(String[] args) throws Exception {
		AnsatteDAO aDAO = new AnsatteDAO();
		ByerDAO bDAO = new ByerDAO();
		AfdelingerDAO afDAO = new AfdelingerDAO();
		
		//aDAO.createAnsat(new AnsatDTO(aDAO.getSize() + 1, 2300, "Steen", "Bartholdy", "s145090@student.dtu.dk", true, true));
		
		//List<AnsatDTO> list = aDAO.getAnsatte();
		
		//System.out.println(list.get(2).getEfternavn());
		
		//bDAO.createBy(2300, "København S");
		
		//System.out.println(bDAO.getBy(2300));
		
		//AfdelingDTO afdeling = new AfdelingDTO("Civil Engineering");
		
		//afDAO.createAfdeling(afdeling);
		
		List<AfdelingDTO> list = afDAO.getAfdelinger();
		
		System.out.println(list.get(14).getAfdeling());
	}
	
}
