package dk.dtu.smmac.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import brugerautorisation.data.Bruger;
import dk.dtu.smmac.shared.AnsatDTO;

public interface AnsatteServiceAsync {

	void getAnsatte(AsyncCallback<List<AnsatDTO>> callback);
	void updateAnsat(AnsatDTO ansat, AsyncCallback<Void> callback);
	void createAnsat(AnsatDTO ansat, AsyncCallback<Void> callback);
	void deleteAnsat(AnsatDTO ansat, AsyncCallback<Void> callback);
	void getSize(AsyncCallback<Integer> callback);
	void getAnsat(Bruger b, AsyncCallback<AnsatDTO> callback);
	void getAnsat(int id, AsyncCallback<AnsatDTO> callback);
	
}
