package dk.dtu.smmac.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.smmac.shared.BilagDTO;

public interface BilagServiceAsync {
	
	void createBilag(BilagDTO bilag, AsyncCallback<BilagDTO> callback);
	void getBilag(AsyncCallback<BilagDTO> callback);
	void getId(AsyncCallback<Integer> callback);
	void getForklaring(AsyncCallback<String> callback);

}
