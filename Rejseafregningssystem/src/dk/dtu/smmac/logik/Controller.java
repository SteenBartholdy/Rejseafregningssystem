package dk.dtu.smmac.logik;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import dk.dtu.smmac.client.ui.LoginTopView;
import dk.dtu.smmac.client.ui.MainView;

public class Controller {

	private MainView mainView;
	
	private LoginTopView loginTopView;
	
	public Controller()
	{
		mainView = new MainView();
		
		loginTopView = mainView.getLoginTopView();
		

		loginTopView.getLoginAnchor().addClickHandler(new ShowLoginHandler());
		
		RootLayoutPanel.get().add(mainView);
	}
	
	
	private class ShowLoginHandler implements ClickHandler 
	{

		@Override
		public void onClick(ClickEvent event) {
			
			mainView.showLoginPage();
			
		}
		
	}
}
