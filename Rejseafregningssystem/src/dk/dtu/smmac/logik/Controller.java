package dk.dtu.smmac.logik;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
import dk.dtu.smmac.client.ui.Rejseafregning;
import dk.dtu.smmac.server.dal.AnsatteDAO;
import dk.dtu.smmac.shared.AnsatDTO;

public class Controller {

	private MainView mainView;

	private LoginTopView loginTopView;

	private LoginPage loginPage;

	private Rejseafregning rejseafregningPage;

	private MainPage mainPage;

	private Bilag bilagPage;

	private NavigationView navPage;

	private LoginServiceAsync loginService = GWT.create(LoginService.class);
	private AnsatteServiceAsync ansatteService = GWT.create(AnsatteService.class);
	
	public Controller()
	{
		mainView = new MainView();

		loginTopView = mainView.getLoginTopView();

		loginPage = mainView.getLoginPage();

		mainPage = mainView.getMainPage();

		rejseafregningPage = mainView.getRejseafregningPage();

		navPage = mainView.getNavPage();

		// Laver handler
		loginTopView.getLoginAnchor().addClickHandler(new ShowLoginHandler());
		loginPage.getLoginButton().addClickHandler(new LoginHandler());
		loginPage.getGlemtPasswordButton().addClickHandler(new GlemtLoginHandler());
		mainPage.getOpret().addClickHandler(new ShowRejseafregningHandler());
		rejseafregningPage.getBilagButton().addClickHandler(new ShowBilagHandler());
		navPage.getOpgaver().addClickHandler(new ShowOpgaveHandler());

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
							mainView.showContentWidget(mainPage);
							//Mangler at bruge result 
						}
						
					});


					//Window.alert("Velkommen " + result.fornavn + " " + result.efternavn + ". Din mail er: " + result.email);
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
}
