package dk.dtu.smmac.client.service;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import brugerautorisation.data.Bruger;

@RemoteServiceRelativePath("loginservice")
public interface LoginService extends java.rmi.Remote {
	
	public Bruger logIn(String brugernavn, String kode) throws Exception;
	public boolean changePassword(String brugernavn, String kode, String nyKode) throws Exception;
	public boolean forgotPassword(String brugernavn) throws Exception;

}
