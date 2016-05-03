package dk.dtu.smmac.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.dtu.smmac.shared.LandDTO;

@RemoteServiceRelativePath("landeservice")
public interface LandeService extends RemoteService {
	
	public List<LandDTO> getLandDTO(int nummer) throws Exception;
	public void setLand(String land, String newLand) throws Exception;
	public void setTakst(int takst, String land) throws Exception;
	public List<String> getAllLande() throws Exception;
	public void createLand(LandDTO land) throws Exception;
	
}
