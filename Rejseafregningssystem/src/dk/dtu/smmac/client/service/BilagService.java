package dk.dtu.smmac.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.dtu.smmac.shared.BilagDTO;

@RemoteServiceRelativePath("bilagservice")
public interface BilagService extends RemoteService{
	
	public BilagDTO createBilag(BilagDTO bilag) throws Exception;
	public BilagDTO getBilag() throws Exception;
	public int getId() throws Exception;
	public String getForklaring() throws Exception;
	
}
