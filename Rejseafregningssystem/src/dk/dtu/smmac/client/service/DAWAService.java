package dk.dtu.smmac.client.service;

import java.io.IOException;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.dtu.smmac.shared.PostNrDTO;

@RemoteServiceRelativePath("dawaservice")
public interface DAWAService extends RemoteService {

	public List<PostNrDTO> getZip() throws Exception;
	public List<String> getRoad(String zip) throws Exception;
	public List<String> getHouseNo(String zip, String road) throws Exception;
	public List<String> getFloor(String zip, String road, String houseNo) throws Exception;
	public List<String> getDoor(String zip, String road, String houseNo, String floor) throws Exception;
	
}
