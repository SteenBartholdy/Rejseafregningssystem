package dk.dtu.smmac.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("projektopgaveservice")

public interface ProjektOpgaveService extends RemoteService
{
	public List<String> getProjekt() throws Exception;
	public List<String> getOpgave(String projekt) throws Exception;
}
