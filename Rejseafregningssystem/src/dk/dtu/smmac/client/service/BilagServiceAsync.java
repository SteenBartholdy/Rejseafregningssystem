package dk.dtu.smmac.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.smmac.shared.BilagDTO;

public interface BilagServiceAsync {
	
	void createBilag(BilagDTO bilag, AsyncCallback<Void> callback);
	void deleteBilag(BilagDTO bilag, AsyncCallback<Void> callback);
	void getBilag(int id, AsyncCallback<List<BilagDTO>> callback);
	void getSize(AsyncCallback<Integer> callback);

}
