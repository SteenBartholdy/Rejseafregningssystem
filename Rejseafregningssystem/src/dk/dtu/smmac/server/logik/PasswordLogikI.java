package dk.dtu.smmac.server.logik;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface PasswordLogikI {

	@WebMethod boolean forgotPassword(String brugernavn);
	
}
