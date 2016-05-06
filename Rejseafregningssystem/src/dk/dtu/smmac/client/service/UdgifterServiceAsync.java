package dk.dtu.smmac.client.service;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import dk.dtu.smmac.shared.UdgifterDTO;

public interface UdgifterServiceAsync 
{
	void getUdgifter(int nr, AsyncCallback<List<UdgifterDTO>> callback);
	void updateUdgifter(UdgifterDTO udgift, AsyncCallback<Void> callback);
	void createUdgifter(UdgifterDTO udgift, AsyncCallback<Void> callback);
	void deleteUdgifter(UdgifterDTO udgift, AsyncCallback<Void> callback);
	void getSize(AsyncCallback<Integer> callback);
	void getLast(AsyncCallback<Integer> callback);
	void getUdgifterSum(int nr, AsyncCallback<Double> callback);
}
