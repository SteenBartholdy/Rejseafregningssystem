package dk.dtu.smmac.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import brugerautorisation.data.Bruger;

public interface LoginServiceAsync {

	void logIn(String brugernavn, String kode, AsyncCallback<Bruger> callback);
	void changePassword(String brugernavn, String kode, String nyKode, AsyncCallback<Boolean> callback);
	void forgotPassword(String brugernavn, String nyKode, AsyncCallback<Boolean> callback);
	
}
