package dk.dtu.smmac.server.logik;

import java.net.MalformedURLException;
import java.net.URL;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import brugerautorisation.transport.soap.Brugeradmin;

@WebService(endpointInterface = "dk.dtu.smmac.server.logik.PasswordLogikI")
public class PasswordLogik implements PasswordLogikI {

	@Override
	public boolean forgotPassword(String brugernavn) {
		try {
			URL url = new URL("http://javabog.dk:9901/brugeradmin?wsdl");
			QName qname = new QName("http://soap.transport.brugerautorisation/", "BrugeradminImplService");
			Service service = Service.create(url, qname);
			
			Brugeradmin ba = service.getPort(Brugeradmin.class);
			ba.sendGlemtAdgangskodeEmail(brugernavn, "");
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
