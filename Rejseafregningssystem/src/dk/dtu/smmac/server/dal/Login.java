package dk.dtu.smmac.server.dal;

import java.net.URL;
import java.rmi.Naming;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import brugerautorisation.data.Bruger;
import dk.dtu.smmac.client.service.LoginService;
import dk.dtu.smmac.server.BrugerAdminRMI;
import dk.dtu.smmac.server.BrugerAdminSOAP;
import dk.dtu.smmac.server.logik.LoginLogikI;
import dk.dtu.smmac.server.logik.PasswordLogikI;

public class Login extends RemoteServiceServlet implements LoginService {

	public Login() throws Exception {
		super();
	}

	@Override
	public Bruger logIn(String brugernavn, String kode) throws Exception {
		BrugerAdminRMI server = new BrugerAdminRMI();
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
		BrugerAdminRMI server = new BrugerAdminRMI();
		
		try {
			server.start();
			LoginLogikI login = (LoginLogikI) Naming.lookup("rmi://localhost/Login");
			
			return login.changePassword(brugernavn, kode, nyKode);
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
		BrugerAdminSOAP server = new BrugerAdminSOAP();

		try {
			server.start();
			URL url = new URL("http://localhost:9901/password?wsdl");
			QName qname = new QName("http://logik.server.smmac.dtu.dk/", "PasswordLogikService"); 
			Service service = Service.create(url, qname);
			PasswordLogikI pass = service.getPort(PasswordLogikI.class);

			pass.forgotPassword(brugernavn);
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
	
	

}
