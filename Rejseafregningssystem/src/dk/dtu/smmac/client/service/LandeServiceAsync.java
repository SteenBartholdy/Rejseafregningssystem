package dk.dtu.smmac.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LandeServiceAsync {

	void getLand(AsyncCallback<String> callback);
	void getTakst(AsyncCallback<Integer> callback);
	void setLand(String land, AsyncCallback<Void> callback);
	void setTakst(int takst, AsyncCallback<Void> callback);
	void getAllLande(AsyncCallback<List<String>> callback);
	
}
