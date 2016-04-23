package dk.dtu.smmac.client.service;

import java.util.List;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import dk.dtu.smmac.shared.DageInfoDTO;

@RemoteServiceRelativePath("dageinfoservice")

public interface DageInfoService 
{
	public List<DageInfoDTO> getDageInfo() throws Exception;
	public void updateAnsat(DageInfoDTO ansat) throws Exception;
	public void createAnsat(DageInfoDTO ansat) throws Exception;
	public void deleteAnsat(DageInfoDTO ansat) throws Exception;
	public int getSize() throws Exception;
}
