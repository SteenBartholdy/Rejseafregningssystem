package dk.dtu.smmac.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import dk.dtu.smmac.shared.UdgifterDTO;

@RemoteServiceRelativePath("udgifterservice")

public interface UdgifterService 
{
	public List<UdgifterDTO> getUdgifter() throws Exception;
	public void updateUdgifter(UdgifterDTO udgift) throws Exception;
	public void createUdgifter(UdgifterDTO udgift) throws Exception;
	public void deleteUdgifter(UdgifterDTO udgift) throws Exception;
	public int getSize() throws Exception;	
}
