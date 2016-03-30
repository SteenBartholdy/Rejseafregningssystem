package dk.dtu.smmac.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import dk.dtu.smmac.client.service.LoginService;
import dk.dtu.smmac.server.dal.Login;

public class BrugerAdminServer {

	private final String rmi = "rmi://localhost/Login";
	private Registry registry;
	
	public void start() throws Exception {
		registry = LocateRegistry.createRegistry(1099); 
        LoginService login = new Login();
        
        Naming.rebind(rmi, login);
        System.out.println("BrugerAdminServer registreret.");
	}
	
	public void close() throws Exception {
		Naming.unbind(rmi);
        UnicastRemoteObject.unexportObject(registry, true);
	}
	
}
