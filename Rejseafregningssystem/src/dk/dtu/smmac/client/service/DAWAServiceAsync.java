package dk.dtu.smmac.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.smmac.shared.PostNrDTO;

public interface DAWAServiceAsync {
	
	void getZip(AsyncCallback<List<PostNrDTO>> callback);
	void getCity(String zipCode, AsyncCallback<String> callback);
	void getRoad(String zip, AsyncCallback<List<String>> callback);
	void getHouseNo(String zip, String road, AsyncCallback<List<String>> callback);
	void getFloor(String zip, String road, String houseNo, AsyncCallback<List<String>> callback);
	void getDoor(String zip, String road, String houseNo, String floor, AsyncCallback<List<String>> callback);
	
}
