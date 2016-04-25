package dk.dtu.smmac.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.smmac.shared.RejseafregningDTO;

public interface RejseafregningServiceAsync {

	void updateRejse(RejseafregningDTO rejse, AsyncCallback<Void> callback);
	void createRejse(RejseafregningDTO rejse, AsyncCallback<Void> callback);
	void getRejse(int id, int ansatId, AsyncCallback<RejseafregningDTO> callback);
	void getRejser(int ansatId, AsyncCallback<List<RejseafregningDTO>> callback);
	void getSize(AsyncCallback<Integer> callback);
	
}
