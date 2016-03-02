package dk.dtu.smmac.logik;

import com.google.gwt.user.client.ui.RootLayoutPanel;

import dk.dtu.smmac.client.ui.MainView;

public class Controller {

	private MainView mainView;
	
	public Controller()
	{
		mainView = new MainView();
		
		
		
		
		
		RootLayoutPanel.get().add(mainView);
	}
}
