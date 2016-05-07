package dk.dtu.smmac.api;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import brugerautorisation.data.Bruger;
import dk.dtu.smmac.server.dal.LandDAO;
import dk.dtu.smmac.server.dal.Login;
import dk.dtu.smmac.shared.LandDTO;

@Path("/land")
public class PostLand {

	private Login login;
	private Bruger bruger;
	private LandDAO lande;

	@POST
	@Path("{user}/{pass}/{land}/{takst}")
	@Consumes("application/x-www-form-urlencoded")
	public String addLand(@PathParam("user") String username, @PathParam("pass") String password, 
			@PathParam("land") String land, @PathParam("takst") int takst) throws IOException 
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
			String result = "Der skete en fejl. Tjek brugernavn og kodeord. ";

			return result;
		}
		String result = bruger.fornavn + " " + bruger.efternavn + " har oprettet " + land + " med en takst på " + takst + " kr.";

		return result;
	}

}
