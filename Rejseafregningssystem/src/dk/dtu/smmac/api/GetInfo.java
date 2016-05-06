package dk.dtu.smmac.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import brugerautorisation.data.Bruger;
import dk.dtu.smmac.server.dal.AnsatteDAO;
import dk.dtu.smmac.server.dal.Login;
import dk.dtu.smmac.shared.AnsatDTO;

@Path("info")
public class GetInfo {
	
	private Login login;
	private Bruger bruger;
	private AnsatteDAO ansatte;
	private AnsatDTO ansat;
	private String navn, efternavn, afdeling, telefon, email;

	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@QueryParam("user") String username, @QueryParam("pass") String password) {
		
		login = null;
		ansatte = null;
		bruger = null;
		ansat = null;
	
		try {
			login = new Login();
			ansatte = new AnsatteDAO();
			bruger = login.logIn(username, password);
			ansat = ansatte.getAnsat(bruger);
			
			navn = ansat.getFornavn();
			efternavn = ansat.getEfternavn();
			afdeling = ansat.getAfdeling();
			telefon = "" + ansat.getTlf();
			email = ansat.getEmail();
			
		} catch (Exception e) {
			return "Der skete en fejl. Tjek brugernavn og kodeord. " + e.getMessage();
		}
		
		
        
		return "Velkommen " + navn + " " + efternavn + ". " + "\n" + "Afdeling: " + afdeling + "\n" +  "Telefonnummer: " + telefon + "\n" + "Email: " + email;
    }
	
}
