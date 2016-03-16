package dk.dtu.smmac.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ByerServiceAsync {

	void createBy(int postnr, String navn, AsyncCallback<Void> callback);
	void getBy(int postnr, AsyncCallback<String> callback);
	
}
