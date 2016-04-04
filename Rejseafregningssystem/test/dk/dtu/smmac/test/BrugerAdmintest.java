package dk.dtu.smmac.test;

import java.rmi.Naming;

import brugerautorisation.data.Bruger;
import dk.dtu.smmac.client.service.LoginService;
import dk.dtu.smmac.server.BrugerAdminServer;

public class BrugerAdmintest {

	public static void main(String[] args) {
		BrugerAdminServer server = new BrugerAdminServer();
		
		try {
			server.start();
			
			LoginService login = (LoginService) Naming.lookup("rmi://localhost/Login");
			
			Bruger b = (Bruger) login.logIn("s145094", "1q2w3e4r");
			
			System.out.println(b.brugernavn);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
