package dk.dtu.smmac.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.dtu.smmac.shared.AfdelingDTO;

@RemoteServiceRelativePath("afdelingerservice")
public interface AfdelingerService extends RemoteService {

	public List<AfdelingDTO> getAfdelinger() throws Exception;
	public void createAfdeling(AfdelingDTO afdeling) throws Exception;
	public void deleteAfdeling(AfdelingDTO afdeling) throws Exception;
	public int getSize() throws Exception;
	
}
