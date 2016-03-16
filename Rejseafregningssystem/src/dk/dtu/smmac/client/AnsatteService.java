package dk.dtu.smmac.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.dtu.smmac.shared.AnsatDTO;

@RemoteServiceRelativePath("ansatteService")
public interface AnsatteService {

	public List<AnsatDTO> getAnsatte() throws Exception;
	public void updateAnsat(AnsatDTO ansat) throws Exception;
	public void createAnsat(AnsatDTO ansat) throws Exception;
	public void deleteAnsat(AnsatDTO ansat) throws Exception;
	
}
