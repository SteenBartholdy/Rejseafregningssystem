package dk.dtu.smmac.client;

import com.google.gwt.core.client.EntryPoint;

import dk.dtu.smmac.logik.Controller;


public class Rejseafregningssystem implements EntryPoint {
	
	@Override
	public void onModuleLoad() {
		
		new Controller();
		
	}
}
