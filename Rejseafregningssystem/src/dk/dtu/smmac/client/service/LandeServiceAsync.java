package dk.dtu.smmac.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.smmac.shared.LandDTO;

public interface LandeServiceAsync {

	void getLandDTO(int nummer, AsyncCallback<List<LandDTO>> callback);
	void setLand(String land,String newLand, AsyncCallback<Void> callback);
	void setTakst(int takst, String land, AsyncCallback<Void> callback);
	void getAllLande(AsyncCallback<List<String>> callback);
	void createLand(LandDTO land, AsyncCallback<Void> callback);
	void getAllLandeDTO(AsyncCallback<List<LandDTO>> callback);
	
}
