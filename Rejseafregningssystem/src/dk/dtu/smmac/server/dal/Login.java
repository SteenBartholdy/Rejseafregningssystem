package dk.dtu.smmac.server.dal;

import java.rmi.Naming;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import brugerautorisation.data.Bruger;
import dk.dtu.smmac.client.service.LoginService;
import dk.dtu.smmac.server.BrugerAdminServer;
import dk.dtu.smmac.server.logik.LoginLogikI;

public class Login extends RemoteServiceServlet implements LoginService {

	public Login() throws Exception {
		super();
	}

	@Override
	public Bruger logIn(String brugernavn, String kode) throws Exception {
		BrugerAdminServer server = new BrugerAdminServer();
		Bruger b = null;
		
		try {
			server.start();
			LoginLogikI login = (LoginLogikI) Naming.lookup("rmi://localhost/Login");
			
			b = login.logIn(brugernavn, kode);
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
			try {
				server.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return b;
	}

	@Override
	public boolean changePassword(String brugernavn, String kode, String nyKode) throws Exception {
		BrugerAdminServer server = new BrugerAdminServer();
		
		try {
			server.start();
			LoginLogikI login = (LoginLogikI) Naming.lookup("rmi://localhost/Login");
			
			login.changePassword(brugernavn, kode, nyKode);

            System.out.println("Koden er ændret til " + nyKode + " hos bruger = " + brugernavn);
            return true;
		} catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
			try {
				server.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

	@Override
	public boolean forgotPassword(String brugernavn) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
