package dk.dtu.smmac.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LandeServiceAsync {

	void getLand(String land, AsyncCallback<String> callback);
	void getTakst(int takst, AsyncCallback<Integer> callback);
	void setLand(String land, AsyncCallback<Void> callback);
	void setTakst(int takst, AsyncCallback<Void> callback);
	void getAllLande(List<String> land, AsyncCallback<List<String>> callback);
	
}
