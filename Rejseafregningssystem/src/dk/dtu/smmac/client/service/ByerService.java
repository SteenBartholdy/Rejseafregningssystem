package dk.dtu.smmac.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("byerservice")
public interface ByerService extends RemoteService{

	public void createBy(int postnr, String navn) throws Exception;
	public String getBy(int postnr) throws Exception;
	
}
