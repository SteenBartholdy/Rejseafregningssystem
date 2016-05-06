package dk.dtu.smmac.client.service;

import java.sql.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import dk.dtu.smmac.shared.DageInfoDTO;

@RemoteServiceRelativePath("dageinfoservice")

public interface DageInfoService extends RemoteService
{
	public List<DageInfoDTO> getDageInfo(int nummer) throws Exception;
	public void updateDageInfo(DageInfoDTO dag) throws Exception;
	public void createDageInfo(DageInfoDTO dag) throws Exception;
	public void deleteDageInfo(DageInfoDTO dag) throws Exception;
	public int getSize() throws Exception;
	public DageInfoDTO getDageInfo(Date dato, int nummer) throws Exception;
	public double getDagpenge(int nummer) throws Exception;
	public double getRefundering(int nummer) throws Exception;
}
