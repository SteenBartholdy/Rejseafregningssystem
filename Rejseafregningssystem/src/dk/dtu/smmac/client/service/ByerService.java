package dk.dtu.smmac.client.service;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("byerService")
public interface ByerService {

	public void createBy(int postnr, String navn) throws Exception;
	public String getBy(int postnr) throws Exception;
	
}
