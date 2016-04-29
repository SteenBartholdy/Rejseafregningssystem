package dk.dtu.smmac.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.dtu.smmac.shared.BilagDTO;

@RemoteServiceRelativePath("bilagservice")
public interface BilagService extends RemoteService{
	
	public void createBilag(BilagDTO bilag) throws Exception;
	public BilagDTO getBilag(int id) throws Exception;
	public void deleteBilag(BilagDTO bilag) throws Exception;
	
}
