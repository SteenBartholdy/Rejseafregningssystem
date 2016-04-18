package dk.dtu.smmac.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.smmac.shared.AfdelingDTO;

public interface AfdelingerServiceAsync {

	void getAfdelinger(AsyncCallback<List<AfdelingDTO>> callback);
	void createAfdeling(AfdelingDTO afdeling, AsyncCallback<Void> callback);
	void deleteAfdeling(AfdelingDTO afdeling, AsyncCallback<Void> callback);
	void getSize(AsyncCallback<Integer> callback);
	
}
