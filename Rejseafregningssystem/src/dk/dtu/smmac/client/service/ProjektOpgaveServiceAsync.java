package dk.dtu.smmac.client.service;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface ProjektOpgaveServiceAsync 
{
	void getProjekt(AsyncCallback<List<String>> callback);
	void getOpgave(String projekt, AsyncCallback<List<String>> callback);
}
