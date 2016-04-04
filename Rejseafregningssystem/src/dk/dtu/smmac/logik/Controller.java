package dk.dtu.smmac.logik;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import dk.dtu.smmac.client.service.LoginService;
import dk.dtu.smmac.client.service.LoginServiceAsync;
import dk.dtu.smmac.client.ui.LoginPage;
import dk.dtu.smmac.client.ui.LoginTopView;
import dk.dtu.smmac.client.ui.MainView;
import dk.dtu.smmac.shared.Bruger;

public class Controller {

	private MainView mainView;
	
	private LoginTopView loginTopView;
	
	private LoginPage loginPage;
	
	private LoginServiceAsync loginService = GWT.create(LoginService.class);
	
	public Controller()
	{
		mainView = new MainView();
		
		loginTopView = mainView.getLoginTopView();
		
		loginPage = mainView.getLoginPage();
		
		// Laver handler
		loginTopView.getLoginAnchor().addClickHandler(new ShowLoginHandler());
		loginPage.getLoginButton().addClickHandler(new LoginHandler());
		loginPage.getGlemtPasswordButton().addClickHandler(new GlemtLoginHandler());
		
		RootLayoutPanel.get().add(mainView);
	}
	
	
	private class ShowLoginHandler implements ClickHandler 
	{

		@Override
		public void onClick(ClickEvent event) {
			
			mainView.showLoginPage();
			
		}
		
	}
	
	private class LoginHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			
			loginService.logIn(loginPage.getBrugernavn(), loginPage.getPassword(), new AsyncCallback<Bruger>(){

				@Override
				public void onFailure(Throwable caught) {
					System.out.println("An error has occured");
				}

				@Override
				public void onSuccess(Bruger result) {
					
					Window.alert("Velkommen " + result.efternavn + ". Din mail er " + result.email);
					
				}
				
			});
			
			//Her skal der være interaktion med Jakobs loginhaløj
			Window.alert("Dit indtastede brugernavn var: " + loginPage.getBrugernavn()
					+ "\n" + "Dit indtastede password er: " + loginPage.getPassword());
			
		}
	}
	
	private class GlemtLoginHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			//Her skal vi bruge serveren
			Window.alert("Ej hvor er du bare dum at du har glemt dit password!!!!");
			
		}
		
	}
}
