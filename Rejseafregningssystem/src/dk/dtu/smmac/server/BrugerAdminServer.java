package dk.dtu.smmac.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import dk.dtu.smmac.server.logik.LoginLogik;
import dk.dtu.smmac.server.logik.LoginLogikI;

public class BrugerAdminServer {

	private final String rmi = "rmi://localhost/Login";
	private Registry registry;
	
	public void start() throws Exception {
		registry = LocateRegistry.createRegistry(1099); 
        LoginLogikI login = new LoginLogik();
        
        Naming.rebind(rmi, login);
        System.out.println("BrugerAdminServer registreret.");
	}
	
	public void close() throws Exception {
		Naming.unbind(rmi);
        UnicastRemoteObject.unexportObject(registry, true);
        System.out.println("BrugerAdminServer lukket.");
	}
	
}
