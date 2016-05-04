package dk.dtu.smmac.client.service;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import dk.dtu.smmac.shared.RejseDTO;

public interface RejseServiceAsync 
{
	void getRejse(AsyncCallback<List<RejseDTO>> callback);
	void updateRejse(RejseDTO rejse, AsyncCallback<Void> callback);
	void createRejse(RejseDTO rejse, AsyncCallback<Void> callback);
	void deleteRejse(RejseDTO rejse, AsyncCallback<Void> callback);
	void getSize(AsyncCallback<Integer> callback);
	void getRejser(int nr, AsyncCallback<List<RejseDTO>> callback);
	void getLast(AsyncCallback<Integer> callback);
}
