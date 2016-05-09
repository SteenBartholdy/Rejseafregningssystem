package dk.dtu.smmac.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.smmac.shared.RejseafregningDTO;
import dk.dtu.smmac.shared.RejseafregningerDTO;

public interface RejseafregningServiceAsync {

	void updateRejse(RejseafregningDTO rejse, AsyncCallback<Void> callback);
	void createRejse(RejseafregningDTO rejse, AsyncCallback<Void> callback);
	void getRejse(int id, int ansatId, AsyncCallback<RejseafregningDTO> callback);
	void getRejser(int ansatId, AsyncCallback<List<RejseafregningerDTO>> callback);
	void getSize(AsyncCallback<Integer> callback);
	void getLast(AsyncCallback<Integer> callback);
	void deleteRejse(RejseafregningDTO rejse, AsyncCallback<Void> callback);
	void getGodkend(AsyncCallback<List<RejseafregningerDTO>> callback);
	void getAnvis(AsyncCallback<List<RejseafregningerDTO>> callback);
	void updateRejsen(RejseafregningerDTO rejse, AsyncCallback<Void> callback);
}
