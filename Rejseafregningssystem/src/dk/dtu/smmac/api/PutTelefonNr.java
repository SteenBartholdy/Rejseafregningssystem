package dk.dtu.smmac.api;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import brugerautorisation.data.Bruger;
import dk.dtu.smmac.server.dal.AnsatteDAO;
import dk.dtu.smmac.server.dal.Login;
import dk.dtu.smmac.shared.AnsatDTO;

@Path("telefon")
public class PutTelefonNr {
	
	private Login login;
	private Bruger bruger;
	private AnsatteDAO ansatte;
	private AnsatDTO ansat;
	private String gammeltTlfNr;
	
	@PUT
	@Produces("text/plain")
	public String updateTlfNR(@QueryParam("user") String username, @QueryParam("pass") String password, @QueryParam("telefon") int telefon)
	{
		login = null;
		ansatte = null;
		bruger = null;
		ansat = null;
		gammeltTlfNr = null;
	
		try {
			login = new Login();
			ansatte = new AnsatteDAO();
			bruger = login.logIn(username, password);
			ansat = ansatte.getAnsat(bruger);
			gammeltTlfNr = "" + ansat.getTlf();
			
			ansat.setTlf(telefon);
			
		} catch (Exception e) {
			return "Der skete en fejl. Tjek brugernavn og kodeord. " + e.getMessage();
		}
		return bruger.fornavn + " " + bruger.efternavn +  "'s telefonnummer er blevet ændret fra " + gammeltTlfNr + " til " + telefon + ".";
	}

}
