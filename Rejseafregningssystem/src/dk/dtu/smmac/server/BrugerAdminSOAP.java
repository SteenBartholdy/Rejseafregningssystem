package dk.dtu.smmac.server;

import javax.xml.ws.Endpoint;

import dk.dtu.smmac.server.logik.PasswordLogik;
import dk.dtu.smmac.server.logik.PasswordLogikI;

public class BrugerAdminSOAP {

	private final String soap = "http://[::]:9901/password";
	private Endpoint ep;
	
	public void start() throws Exception {
		PasswordLogikI password = new PasswordLogik();
		
		ep = Endpoint.publish(soap, password);
		System.out.println("BrugerAdmin soap server registreret.");
	}
	
	public void close() throws Exception {
		ep.stop();
		System.out.println("BrugerAdmin soap server lukket.");
	}
	
}
