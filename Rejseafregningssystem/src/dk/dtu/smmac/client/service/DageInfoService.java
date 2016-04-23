package dk.dtu.smmac.client.service;

import java.util.List;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import dk.dtu.smmac.shared.DageInfoDTO;

@RemoteServiceRelativePath("dageinfoservice")

public interface DageInfoService 
{
	public List<DageInfoDTO> getDageInfo() throws Exception;
	public void updateDageInfo(DageInfoDTO ansat) throws Exception;
	public void createDageInfo(DageInfoDTO ansat) throws Exception;
	public void deleteDageInfo(DageInfoDTO ansat) throws Exception;
	public int getSize() throws Exception;
}
