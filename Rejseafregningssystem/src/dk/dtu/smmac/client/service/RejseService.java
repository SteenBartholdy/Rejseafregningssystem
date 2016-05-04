package dk.dtu.smmac.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import dk.dtu.smmac.shared.RejseDTO;

@RemoteServiceRelativePath("rejseservice")
public interface RejseService extends RemoteService
{
	public List<RejseDTO> getRejse() throws Exception;
	public void updateRejse(RejseDTO rejse) throws Exception;
	public void createRejse(RejseDTO rejse) throws Exception;
	public void deleteRejse(RejseDTO rejse) throws Exception;
	public int getSize() throws Exception;
	public List<RejseDTO> getRejser(int nr) throws Exception;
	public int getLast() throws Exception;
}
