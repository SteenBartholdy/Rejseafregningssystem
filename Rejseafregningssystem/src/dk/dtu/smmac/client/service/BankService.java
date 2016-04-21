package dk.dtu.smmac.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.dtu.smmac.shared.BankDTO;

@RemoteServiceRelativePath("bankservice")
public interface BankService extends RemoteService {

	public void updateBank(BankDTO bank) throws Exception;
	public void createBank(BankDTO bank) throws Exception;
	public BankDTO getBank(int id) throws Exception;
	
}
