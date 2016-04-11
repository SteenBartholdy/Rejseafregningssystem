package dk.dtu.smmac.server.dal;

import java.rmi.Naming;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;
import dk.dtu.smmac.client.service.LoginService;

public class Login extends RemoteServiceServlet implements LoginService {

	public Login() throws Exception {
		super();
	}

//	@Override
//	public Bruger logIn(String brugernavn, String kode) throws Exception {
//		try {
//            Brugeradmin ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
//            Bruger b = ba.hentBruger(brugernavn, kode);
//            System.out.println("Fik bruger = " + b);
//            return b;
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
//		return null;
//	}

	@Override
	public boolean changePassword(String brugernavn, String kode, String nyKode) throws Exception {
		try {
            Brugeradmin ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
            ba.ændrAdgangskode(brugernavn, kode, nyKode);
            Bruger b = ba.hentBruger(brugernavn, nyKode);
            System.out.println("Koden er ændret til " + nyKode + " hos bruger = " + b);
            return true;
		} catch (Exception e) {
            e.printStackTrace(System.out);
        }
		return false;
	}

	@Override
	public boolean forgotPassword(String brugernavn) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
