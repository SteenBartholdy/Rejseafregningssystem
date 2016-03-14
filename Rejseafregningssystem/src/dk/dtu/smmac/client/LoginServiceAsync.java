package dk.dtu.smmac.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

	void logIn(String brugernavn, String kode, AsyncCallback<Boolean> callback);
	void changePassword(String brugernavn, String kode, String nyKode, AsyncCallback<Boolean> callback);
	void forgotPassword(String brugernavn, String nyKode, AsyncCallback<Boolean> callback);
	
}
