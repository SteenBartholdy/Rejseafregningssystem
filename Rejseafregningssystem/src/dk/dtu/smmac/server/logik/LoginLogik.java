package dk.dtu.smmac.server.logik;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;

public class LoginLogik extends UnicastRemoteObject implements LoginLogikI {

	public LoginLogik() throws RemoteException {
		
	}

	@Override
	public Bruger logIn(String studieNr, String kode) throws RemoteException, MalformedURLException, NotBoundException {
			Brugeradmin ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
			Bruger b = ba.hentBruger(studieNr, kode);
            System.out.println("Fik bruger = " + b);
            return b;
	}

	@Override
	public boolean changePassword(String studieNr, String kode, String nyKode) throws RemoteException {
		try {
            Brugeradmin ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
            ba.ændrAdgangskode(studieNr, kode, nyKode);
            Bruger b = ba.hentBruger(studieNr, nyKode);
            System.out.println("Koden er ændret til " + nyKode + " hos bruger = " + b);
            return true;
		} catch (Exception e) {
            e.printStackTrace(System.out);
        }
		return false;
	}

	
	
}
