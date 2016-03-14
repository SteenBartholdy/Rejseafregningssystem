package dk.dtu.smmac.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.smmac.shared.AnsatteDTO;

public interface AnsatteServiceAsync {

	void getAnsatte(AsyncCallback<List<AnsatteDTO>> callback);
	void updateAnsat(AnsatteDTO ansat, AsyncCallback<Void> callback);
	void createAnsat(AnsatteDTO ansat, AsyncCallback<Void> callback);
	
}
