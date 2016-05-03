package dk.dtu.smmac.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import brugerautorisation.data.Bruger;
import dk.dtu.smmac.server.dal.LandDAO;
import dk.dtu.smmac.server.dal.Login;
import dk.dtu.smmac.shared.LandDTO;

@Path("land")
public class PostLand {
	
	private Login login;
	private Bruger bruger;
	private LandDAO lande;
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addLand(@QueryParam("user") String username, @QueryParam("pass") String password, @QueryParam("land") String land, @QueryParam("takst") int takst)
	{
		login = null;
		bruger = null;
		lande = null;
		
		try {
			login = new Login();
			lande = new LandDAO();
			bruger = login.logIn(username, password);
			lande.createLand(new LandDTO(land, takst));
			
		} catch (Exception e) {
			return "Der skete en fejl. Tjek brugernavn og kodeord.";
		}
		
		return bruger.fornavn + " " + bruger.efternavn + " har oprettet " + land + " med en takst på " + takst + " kr.";
	}

}
