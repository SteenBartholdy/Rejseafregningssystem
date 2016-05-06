package dk.dtu.smmac.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import dk.dtu.smmac.shared.UdgifterDTO;

@RemoteServiceRelativePath("udgifterservice")

public interface UdgifterService extends RemoteService
{
	public List<UdgifterDTO> getUdgifter(int nr) throws Exception;
	public void updateUdgifter(UdgifterDTO udgift) throws Exception;
	public void createUdgifter(UdgifterDTO udgift) throws Exception;
	public void deleteUdgifter(UdgifterDTO udgift) throws Exception;
	public int getSize() throws Exception;	
	public int getLast() throws Exception;
	public double getUdgifterSum(int nr) throws Exception;
}
