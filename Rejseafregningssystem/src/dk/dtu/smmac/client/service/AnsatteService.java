package dk.dtu.smmac.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import brugerautorisation.data.Bruger;
import dk.dtu.smmac.shared.AnsatDTO;

@RemoteServiceRelativePath("ansatteservice")
public interface AnsatteService extends RemoteService {

	public List<AnsatDTO> getAnsatte() throws Exception;
	public void updateAnsat(AnsatDTO ansat) throws Exception;
	public void createAnsat(AnsatDTO ansat) throws Exception;
	public void deleteAnsat(AnsatDTO ansat) throws Exception;
	public int getSize() throws Exception;
	public AnsatDTO getAnsat(Bruger b) throws Exception;
	public AnsatDTO getAnsat(int id) throws Exception;
	
}
