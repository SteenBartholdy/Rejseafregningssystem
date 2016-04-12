package dk.dtu.smmac.server.logik;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;

import brugerautorisation.data.Bruger;

public interface LoginLogikI extends java.rmi.Remote 
{

	public Bruger logIn(String studieNr, String kode) throws java.rmi.RemoteException, MalformedURLException, NotBoundException;
	public boolean changePassword(String studieNr, String kode, String nyKode) throws java.rmi.RemoteException;
	
}
