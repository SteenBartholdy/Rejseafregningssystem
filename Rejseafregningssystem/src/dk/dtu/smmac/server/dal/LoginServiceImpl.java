package dk.dtu.smmac.server.dal;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.smmac.client.service.Bruger;
import dk.dtu.smmac.client.service.Brugeradmin;
import dk.dtu.smmac.client.service.LoginService;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {


	private static final long serialVersionUID = 1L;
	
	
	private Brugeradmin ba;
	private Bruger bruger;


	public LoginServiceImpl(){
		
	}


	@Override
	public Bruger logIn(String brugernavn, String kode) throws Exception {
		try {
			ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bruger = ba.hentBruger(brugernavn, kode);

		return bruger;
	}

	@Override
	public boolean changePassword(String brugernavn, String kode, String nyKode) throws Exception {

//		ba.ændrAdgangskode(brugernavn, kode, nyKode);

		return false;
	}

	@Override
	public boolean forgotPassword(String brugernavn) throws Exception {

//		ba.sendGlemtAdgangskodeEmail(brugernavn, "Dit password er: ");

		return false;
	}

}
