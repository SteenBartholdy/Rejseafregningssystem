package dk.dtu.smmac.api;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

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
	@Produces("text/plain")
	@Consumes("text/plain")
	public Response addLand(@FormParam("user") String username, @FormParam("pass") String password, 
	@FormParam("land") String land, @FormParam("takst") String takst) throws IOException 
	{
		login = null;
		bruger = null;
		lande = null;
		
		try {
			login = new Login();
			lande = new LandDAO();
			bruger = login.logIn(username, password);
			int takstInt = Integer.parseInt(takst);
			lande.createLand(new LandDTO(land, takstInt));
			
		} catch (Exception e) {
			String result = "Der skete en fejl. Tjek brugernavn og kodeord. " + e.getMessage();
			return Response.status(500).entity(result).build();
		}
		String result = bruger.fornavn + " " + bruger.efternavn + " har oprettet " + land + " med en takst på " + takst + " kr.";
		return Response.status(200).entity(result).build();
	}

}
