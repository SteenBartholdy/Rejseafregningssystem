package dk.dtu.smmac.api;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import brugerautorisation.data.Bruger;
import dk.dtu.smmac.server.dal.AnsatteDAO;
import dk.dtu.smmac.server.dal.Login;
import dk.dtu.smmac.shared.AnsatDTO;

@Path("/telefon")
public class PutTelefonNr {
	
	private Login login;
	private Bruger bruger;
	private AnsatteDAO ansatte;
	private AnsatDTO ansat;
	private String gammeltTlfNr;
	
	@PUT
	@Path("{user}/{pass}/{telefon}")
	@Consumes("application/x-www-form-urlencoded")
	public String updateTlfNR(@PathParam("user") String username, @PathParam("pass") String password, 
	@PathParam("telefon") int telefon) throws IOException 
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
			ansatte.updateAnsat(ansat);
			
		} catch (Exception e) {
			String result = "Der skete en fejl. Tjek brugernavn og kodeord. ";
			return result;
		}
		String result = bruger.fornavn + " " + bruger.efternavn +  "'s telefonnummer er blevet ændret fra " + gammeltTlfNr + " til " + telefon + ".";
		return result;
	}

}
