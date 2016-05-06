package dk.dtu.smmac.client.service;

import java.sql.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.smmac.shared.DageInfoDTO;

public interface DageInfoServiceAsync 
{
	void getDageInfo(int nummer, AsyncCallback<List<DageInfoDTO>> callback);
	void updateDageInfo(DageInfoDTO dag, AsyncCallback<Void> callback);
	void createDageInfo(DageInfoDTO dag, AsyncCallback<Void> callback);
	void deleteDageInfo(DageInfoDTO dag, AsyncCallback<Void> callback);
	void getSize(AsyncCallback<Integer> callback);
	void getDageInfo(Date dato, int nummer, AsyncCallback<DageInfoDTO> callback);
	void getDagpenge(int nummer, AsyncCallback<Double> callback);
	void getRefundering(int nummer, AsyncCallback<Double> callback);
}
