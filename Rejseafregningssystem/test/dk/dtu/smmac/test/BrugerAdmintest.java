package dk.dtu.smmac.test;

import brugerautorisation.data.Bruger;
import dk.dtu.smmac.server.dal.Login;

public class BrugerAdmintest {

	public static void main(String[] args) {
		
		try {
			Login login = new Login();
			
			Bruger b = login.logIn("s145094", "1q2w3e4r");
			System.out.println(b.brugernavn);
			System.out.println(b.email);
			
			System.out.println(login.forgotPassword("s145094"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
