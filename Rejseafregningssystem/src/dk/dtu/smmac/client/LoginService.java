package dk.dtu.smmac.client;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("loginService")
public interface LoginService {
	
	public boolean logIn(String brugernavn, String kode) throws Exception;
	public boolean changePassword(String brugernavn, String kode, String nyKode) throws Exception;
	public boolean forgotPassword(String brugernavn) throws Exception;

}
