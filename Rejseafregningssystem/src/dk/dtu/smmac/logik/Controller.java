package dk.dtu.smmac.logik;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import brugerautorisation.data.Bruger;
import dk.dtu.smmac.client.service.AnsatteService;
import dk.dtu.smmac.client.service.AnsatteServiceAsync;
import dk.dtu.smmac.client.service.LoginService;
import dk.dtu.smmac.client.service.LoginServiceAsync;
import dk.dtu.smmac.client.ui.Bilag;
import dk.dtu.smmac.client.ui.LoginPage;
import dk.dtu.smmac.client.ui.LoginTopView;
import dk.dtu.smmac.client.ui.MainPage;
import dk.dtu.smmac.client.ui.MainView;
import dk.dtu.smmac.client.ui.NavigationView;
import dk.dtu.smmac.client.ui.Oplysninger;
import dk.dtu.smmac.client.ui.Rejseafregning;
import dk.dtu.smmac.shared.AnsatDTO;

public class Controller {

	private MainView mainView;

	private LoginTopView loginTopView;

	private LoginPage loginPage;

	private Rejseafregning rejseafregningPage;

	private MainPage mainPage;

	private Bilag bilagPage;
	
	private Oplysninger oplysningerPage;

	private NavigationView navPage;
	
	private HTML emptyView;
	private HTML emptyTopView;
	private HTML emptyNavView;

	private LoginServiceAsync loginService = GWT.create(LoginService.class);
	private AnsatteServiceAsync ansatteService = GWT.create(AnsatteService.class);
	
	public Controller()
	{
		mainView = new MainView();
		
		emptyView = mainView.getEmptyView();
		emptyTopView = mainView.getEmptyTopView();
		emptyNavView = mainView.getEmptyNavView();

		loginTopView = mainView.getLoginTopView();

		loginPage = mainView.getLoginPage();

		mainPage = mainView.getMainPage();

		rejseafregningPage = mainView.getRejseafregningPage();
		
		oplysningerPage = mainView.getOplysninger();

		navPage = mainView.getNavPage();

		// Laver handler
		loginTopView.getLoginAnchor().addClickHandler(new ShowLoginHandler());
		loginPage.getLoginButton().addClickHandler(new LoginHandler());
		loginPage.getGlemtPasswordButton().addClickHandler(new GlemtLoginHandler());
		mainPage.getOpret().addClickHandler(new ShowRejseafregningHandler());
		rejseafregningPage.getBilagButton().addClickHandler(new ShowBilagHandler());
		navPage.getOpgaver().addClickHandler(new ShowOpgaveHandler());
		navPage.getOplysninger().addClickHandler(new ShowOplysningHandler());

		RootLayoutPanel.get().add(mainView);
	}

	private class ShowLoginHandler implements ClickHandler 
	{
		@Override
		public void onClick(ClickEvent event) {
			mainView.showLoginPage();
			mainView.showNavWidget(emptyNavView);
			mainView.showTopWidget(emptyTopView);
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
					if (result == null) {
						//Skal ændres til noget label ændring eller lign
						Window.alert("Forkert brugernavn eller kodeord");
					}

					ansatteService.getAnsat(result.email, new AsyncCallback<AnsatDTO>() {

						@Override
						public void onFailure(Throwable caught) {
							System.out.println("An error has occured");
						}

						@Override
						public void onSuccess(AnsatDTO result) {
							if (result == null) {
								//Skal ændres til noget label ændring eller lign
								Window.alert("Brugeren er ikke oprettet korrekt i systemet");
							} else {
								//Mangler at bruge result 
								Window.alert(result.getFornavn() + " " + result.getEfternavn());
								
								mainView.showContentWidget(mainPage);
								mainView.showNavWidget(navPage);
								mainView.showTopWidget(loginTopView);
								
								oplysningerPage.setAnsat(result);
							}
						}
						
					});
				}
			});
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

	private class ShowRejseafregningHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			mainView.showContentWidget(rejseafregningPage);
		}

	}

	private class ShowBilagHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) {
			mainView.showContentWidget(bilagPage);
		}
	}

	private class ShowOpgaveHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) {
			mainView.showContentWidget(mainPage);

		}
	}
	
	private class ShowOplysningHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) {
			mainView.showContentWidget(oplysningerPage);
			
		}
	}
	
}
