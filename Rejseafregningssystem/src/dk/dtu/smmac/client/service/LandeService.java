package dk.dtu.smmac.client.service;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("landeservice")
public interface LandeService {
	
	public String getLand() throws Exception;
	public int getTakst() throws Exception;
	public void setLand(String land) throws Exception;
	public void setTakst(int takst) throws Exception;

}
