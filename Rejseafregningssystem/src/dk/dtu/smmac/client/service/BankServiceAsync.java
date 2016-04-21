package dk.dtu.smmac.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.smmac.shared.BankDTO;

public interface BankServiceAsync {

	void updateBank(BankDTO bank, AsyncCallback<Void> callback);
	void createBank(BankDTO bank, AsyncCallback<Void> callback);
	void getBank(int id, AsyncCallback<BankDTO> callback);
	
}
