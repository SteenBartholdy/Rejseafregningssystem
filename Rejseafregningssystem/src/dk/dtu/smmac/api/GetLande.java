package dk.dtu.smmac.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import dk.dtu.smmac.server.dal.LandDAO;
import dk.dtu.smmac.shared.LandDTO;



@Path("alle-lande")
public class GetLande {
	
	private LandDAO land;

	@GET
	@Produces("application/json")
	public List<LandDTO> getAlleLande()
	{
		List<LandDTO> landeList = new ArrayList<LandDTO>();
		
		try {
			land = new LandDAO();
			landeList = land.getAllLandeDTO();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return landeList;
	}
	
}
