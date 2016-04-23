package dk.dtu.smmac.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import brugerautorisation.data.Bruger;
import dk.dtu.smmac.shared.AnsatDTO;
import dk.dtu.smmac.shared.DageInfoDTO;

public interface DageInfoServiceAsync 
{
	void getDageInfo(AsyncCallback<List<DageInfoDTO>> callback);
	void updateDageInfo(DageInfoDTO dag, AsyncCallback<Void> callback);
	void createDageInfo(DageInfoDTO dag, AsyncCallback<Void> callback);
	void deleteDageInfo(DageInfoDTO dag, AsyncCallback<Void> callback);
	void getSize(AsyncCallback<Integer> callback);
}
