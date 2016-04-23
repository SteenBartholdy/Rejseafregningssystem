package dk.dtu.smmac.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.dtu.smmac.shared.RejseafregningDTO;

@RemoteServiceRelativePath("rejseafregningservice")
public interface RejseafregningService extends RemoteService {

	public void updateRejse(RejseafregningDTO rejse) throws Exception;
	public void createRejse(RejseafregningDTO rejse) throws Exception;
	public RejseafregningDTO getRejse(int id, int ansatId) throws Exception;
	public List<RejseafregningDTO> getRejser(int ansatId) throws Exception;
	
}