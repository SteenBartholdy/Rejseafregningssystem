package dk.dtu.smmac.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.dtu.smmac.shared.AnsatteDTO;

@RemoteServiceRelativePath("ansatteService")
public interface AnsatteService {

	public List<AnsatteDTO> getAnsatte() throws Exception;
	public void updateAnsat(AnsatteDTO ansat) throws Exception;
	public void createAnsat(AnsatteDTO ansat) throws Exception;
	
}
