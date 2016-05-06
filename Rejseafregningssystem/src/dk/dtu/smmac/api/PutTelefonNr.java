package dk.dtu.smmac.api;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

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
	@Consumes("text/plain")
	public Response updateTlfNR(@FormParam("user") String username, @FormParam("pass") String password, 
	@FormParam("telefon") String telefon) throws IOException 
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
			int telefonInt = Integer.parseInt(telefon);
			ansat.setTlf(telefonInt);
			
		} catch (Exception e) {
			String result = "Der skete en fejl. Tjek brugernavn og kodeord. " + e.getMessage();
			return Response.status(201).entity(result).build();
		}
		String result = bruger.fornavn + " " + bruger.efternavn +  "'s telefonnummer er blevet ændret fra " + gammeltTlfNr + " til " + telefon + ".";
		return Response.status(201).entity(result).build();
	}

}
