package dk.dtu.smmac.logik;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import brugerautorisation.data.Bruger;
import dk.dtu.smmac.client.service.AfdelingerService;
import dk.dtu.smmac.client.service.AfdelingerServiceAsync;
import dk.dtu.smmac.client.service.AnsatteService;
import dk.dtu.smmac.client.service.AnsatteServiceAsync;
import dk.dtu.smmac.client.service.BankService;
import dk.dtu.smmac.client.service.BankServiceAsync;
import dk.dtu.smmac.client.service.DAWAService;
import dk.dtu.smmac.client.service.DAWAServiceAsync;
import dk.dtu.smmac.client.service.LoginService;
import dk.dtu.smmac.client.service.LoginServiceAsync;
import dk.dtu.smmac.client.ui.Bilag;
import dk.dtu.smmac.client.ui.DageInfo;
import dk.dtu.smmac.client.ui.GlemtPassword;
import dk.dtu.smmac.client.ui.LoginPage;
import dk.dtu.smmac.client.ui.LoginTopView;
import dk.dtu.smmac.client.ui.MainPage;
import dk.dtu.smmac.client.ui.MainView;
import dk.dtu.smmac.client.ui.NavigationView;
import dk.dtu.smmac.client.ui.Oplysninger;
import dk.dtu.smmac.client.ui.Rejse;
import dk.dtu.smmac.client.ui.Rejseafregning;
import dk.dtu.smmac.shared.AfdelingDTO;
import dk.dtu.smmac.shared.AnsatDTO;
import dk.dtu.smmac.shared.BankDTO;
import dk.dtu.smmac.shared.PostNrDTO;

@SuppressWarnings("deprecation")
public class Controller {

	private MainView mainView;

	private LoginTopView loginTopView;

	private LoginPage loginPage;

	private Rejseafregning rejseafregningPage;

	private MainPage mainPage;

	private Bilag bilagPage;

	private Oplysninger oplysningerPage;

	private NavigationView navPage;

	private Rejse rejsePage;

	private GlemtPassword glemtPasswordPage;
	
	private DageInfo dageInfoPage;

	private HTML emptyView;
	private HTML emptyTopView;
	private HTML emptyNavView;

	private LoginServiceAsync loginService = GWT.create(LoginService.class);
	private AnsatteServiceAsync ansatteService = GWT.create(AnsatteService.class);
	private AfdelingerServiceAsync afdelingerService = GWT.create(AfdelingerService.class);
	private DAWAServiceAsync dawaService = GWT.create(DAWAService.class);
	private BankServiceAsync bankService = GWT.create(BankService.class);

	AsyncCallback<Void> asyncEmpty;
	AsyncCallback<String> asyncCity;
	AsyncCallback<List<String>> asyncRoad, asyncHouseNo, asyncFloor, asyncDoor;

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

		rejsePage = mainView.getRejsePage();

		glemtPasswordPage = mainView.getGlemtPasswordPage();

		bilagPage = mainView.getBilagPage();
		
		dageInfoPage = mainView.getDageInfoPage();

		//Async
		asyncEmpty = new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("An error has occured");	
			}

			@Override
			public void onSuccess(Void result) {
			}
		};

		asyncCity = new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("An error has occured");		
			}

			@Override
			public void onSuccess(String result) {
				oplysningerPage.setlCityName(result);
			}
		};

		asyncRoad = new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("An error has occured");
			}

			@Override
			public void onSuccess(List<String> result) {
				oplysningerPage.setRoad(result);
			}
		};

		asyncHouseNo = new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("An error has occured");
			}

			@Override
			public void onSuccess(List<String> result) {
				oplysningerPage.setHouseNo(result);
			}
		};

		asyncFloor = new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("An error has occured");
			}

			@Override
			public void onSuccess(List<String> result) {
				oplysningerPage.setFloor(result);
			}
		};

		asyncDoor = new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("An error has occured");
			}

			@Override
			public void onSuccess(List<String> result) {
				oplysningerPage.setDoor(result);
			}
		};

		//Clickhandler
		loginTopView.getLoginAnchor().addClickHandler(new ShowLoginHandler());
		loginPage.getLoginButton().addClickHandler(new LoginHandler());
		loginPage.getGlemtPasswordButton().addClickHandler(new GlemtLoginHandler());
		mainPage.getOpret().addClickHandler(new ShowRejseafregningHandler());
		rejseafregningPage.getBilagButton().addClickHandler(new ShowBilagHandler());
		navPage.getOpgaver().addClickHandler(new ShowOpgaveHandler());
		navPage.getOplysninger().addClickHandler(new ShowOplysningHandler());
		rejseafregningPage.getAddTravelAnchor().addClickHandler(new ShowAddTravelHandler());
		rejsePage.getAddProjectAnchor().addClickHandler(new AddProjectHandler());
		rejsePage.getDeleteProjectButton().addClickHandler(new DeleteProjectHandler());
		glemtPasswordPage.getbtnSendPassword().addClickHandler(new SendPasswordHandler());
		glemtPasswordPage.getbtnAnnullerPassword().addClickHandler(new ShowLoginHandler());
		glemtPasswordPage.getbtnDageInfoPassword().addClickHandler(new DageInfoHandler());
		bilagPage.getAddBilag().addClickHandler(new AddBilagHandler());
		bilagPage.getDelete().addClickHandler(new DeleteBilagHandler());
		dageInfoPage.getbtnAnnullerDageInfo().addClickHandler(new ShowLoginHandler());

		//BlurHandler
		oplysningerPage.getName().addBlurHandler(new UpdateAnsatHandler());
		oplysningerPage.getSurname().addBlurHandler(new UpdateAnsatHandler());
		oplysningerPage.getDepartment().addBlurHandler(new UpdateAnsatHandler());
		oplysningerPage.getTelephone().addBlurHandler(new UpdateAnsatHandler());
		oplysningerPage.getRegNo().addBlurHandler(new BankHandler());
		oplysningerPage.getKontoNo().addBlurHandler(new BankHandler());

		//FocusListener
		oplysningerPage.getZip().addFocusListener(new ZipCodeListener());
		oplysningerPage.getRoad().addFocusListener(new RoadListener());
		oplysningerPage.getHouseNr().addFocusListener(new HouseNoListener());
		oplysningerPage.getFloor().addFocusListener(new FloorListener());
		oplysningerPage.getDoor().addFocusListener(new DoorListener());

		//Enter Handler
		EnterKeyHandler enterLoginHandler = new EnterKeyHandler() {
			@Override
			public void enterKeyDown(KeyDownEvent event) {

				LoginHandler login = new LoginHandler(); 
				login.onClick(login.getEvent());
			}
		};
		loginPage.getPasswordTextField().addKeyDownHandler(enterLoginHandler);

		//Afdeling load
		afdelingerService.getAfdelinger(new AsyncCallback<List<AfdelingDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("An error has occured");	
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<AfdelingDTO> result) {
				oplysningerPage.setAfdelinger(result);
				oplysningerPage.setDepartmentItems();
			}

		});

		//Postnr load
		dawaService.getZip(new AsyncCallback<List<PostNrDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("An error has occured");	
			}

			@Override
			public void onSuccess(List<PostNrDTO> result) {
				oplysningerPage.setZip(result);
			}

		});

		//Rootpanel
		RootLayoutPanel.get().add(mainView);
	}

	private class BankHandler implements BlurHandler
	{
		@Override
		public void onBlur(BlurEvent event) {
			BankDTO konto = new BankDTO(oplysningerPage.getAnsat().getID(), Integer.parseInt(oplysningerPage.getRegNo().getText()), Integer.parseInt(oplysningerPage.getKontoNo().getText()));
			
			bankService.updateBank(konto, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					System.out.println("An error has occured");		
				}

				@Override
				public void onSuccess(Void result) {
					
				}
				
			});
		}
	}
	
	private class SendPasswordHandler implements ClickHandler 
	{
		@Override
		public void onClick(ClickEvent event) {
			loginService.forgotPassword(glemtPasswordPage.getMailPassword(), new AsyncCallback<Boolean>() {

				@Override
				public void onFailure(Throwable caught) {
					System.out.println("An error has occured");
					Window.alert(caught.getMessage());
				}

				@Override
				public void onSuccess(Boolean result) {
					if (result == true) {
						Window.alert("Der er sendt en email med din kode i.");
						mainView.showLoginPage();
					} else if (result == false) {
						Window.alert("Brugeren eksisterer ikke!");
					}
				}

			});

		}
	}

	private class UpdateAnsatHandler implements BlurHandler
	{
		@Override
		public void onBlur(BlurEvent event) {
			ansatteService.updateAnsat(oplysningerPage.getAnsat(), asyncEmpty);
		}
	}

	private class ZipCodeListener implements FocusListener
	{

		@Override
		public void onFocus(Widget sender) {

		}

		@Override
		public void onLostFocus(Widget sender) {
			dawaService.getCity(oplysningerPage.getZip().getText(), asyncCity);
			oplysningerPage.resetRoad();
			oplysningerPage.resetHouseNo();
			oplysningerPage.resetFloor();
			oplysningerPage.resetDoor();
			dawaService.getRoad(oplysningerPage.getZip().getText(), asyncRoad);

			ansatteService.updateAnsat(oplysningerPage.getAnsat(), asyncEmpty);
		}
	}

	private class RoadListener implements FocusListener
	{
		@Override
		public void onFocus(Widget sender) {

		}

		@Override
		public void onLostFocus(Widget sender) {
			oplysningerPage.resetHouseNo();
			oplysningerPage.resetFloor();
			oplysningerPage.resetDoor();
			dawaService.getHouseNo(oplysningerPage.getZip().getText(), oplysningerPage.getRoad().getText(), asyncHouseNo);

			ansatteService.updateAnsat(oplysningerPage.getAnsat(), asyncEmpty);
		}
	}

	private class HouseNoListener implements FocusListener
	{
		@Override
		public void onFocus(Widget sender) {

		}

		@Override
		public void onLostFocus(Widget sender) {
			oplysningerPage.resetFloor();
			oplysningerPage.resetDoor();
			dawaService.getFloor(oplysningerPage.getZip().getText(), oplysningerPage.getRoad().getText(), oplysningerPage.getHouseNr().getText(), asyncFloor);

			ansatteService.updateAnsat(oplysningerPage.getAnsat(), asyncEmpty);
		}
	}

	private class FloorListener implements FocusListener
	{
		@Override
		public void onFocus(Widget sender) {

		}

		@Override
		public void onLostFocus(Widget sender) {
			oplysningerPage.resetDoor();
			dawaService.getDoor(oplysningerPage.getZip().getText(), oplysningerPage.getRoad().getText(), oplysningerPage.getHouseNr().getText(), oplysningerPage.getFloor().getText(), asyncDoor);

			ansatteService.updateAnsat(oplysningerPage.getAnsat(), asyncEmpty);
		}
	}

	private class DoorListener implements FocusListener
	{
		@Override
		public void onFocus(Widget sender) {

		}

		@Override
		public void onLostFocus(Widget sender) {
			ansatteService.updateAnsat(oplysningerPage.getAnsat(), asyncEmpty);
		}
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

		ClickEvent event;
		@Override
		public void onClick(ClickEvent event) {
			this.event = event;
			loginService.logIn(loginPage.getBrugernavn(), loginPage.getPassword(), new AsyncCallback<Bruger>(){

				@Override
				public void onFailure(Throwable caught) {
					System.out.println("An error has occured");
					Window.alert(caught.getMessage());
				}

				@Override
				public void onSuccess(Bruger result) {
					if (result == null) {
						//Skal ændres til noget label ændring eller lign
						Window.alert("Forkert brugernavn eller kodeord");
					}

					ansatteService.getAnsat(result, new AsyncCallback<AnsatDTO>() {

						@Override
						public void onFailure(Throwable caught) {
							System.out.println("An error has occured");
							Window.alert(caught.getMessage());
						}

						@Override
						public void onSuccess(AnsatDTO result) {
							mainView.showContentWidget(mainPage);
							mainView.showNavWidget(navPage);
							mainView.showTopWidget(loginTopView);

							oplysningerPage.setAnsat(result);
							dawaService.getCity(""+result.getPostnr(), asyncCity);
							dawaService.getRoad(""+result.getPostnr(), asyncRoad);
							dawaService.getHouseNo(""+result.getPostnr(), result.getVejnavn(), asyncHouseNo);
							dawaService.getFloor(""+result.getPostnr(), result.getVejnavn(), result.gethusnr(), asyncFloor);
							dawaService.getDoor(""+result.getPostnr(), result.getVejnavn(), result.gethusnr(), result.getEtage(), asyncDoor);
							bankService.getBank(result.getID(), new AsyncCallback<BankDTO>() {

								@Override
								public void onFailure(Throwable caught) {
									System.out.println("An error has occured");
								}

								@Override
								public void onSuccess(BankDTO result) {
									oplysningerPage.setRegNo(""+result.getRegNo());
									oplysningerPage.setKontoNo(""+result.getKontoNo());
								}
							});
							
						}

					});
				}
			});
		}

		public ClickEvent getEvent()
		{
			return this.event;
		}
	}

	private class GlemtLoginHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			//Her skal vi bruge serveren
			mainView.showContentWidget(glemtPasswordPage);

		}

	}

	private class DageInfoHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event)
		{
			mainView.showContentWidget(dageInfoPage);
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

	private class ShowAddTravelHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			mainView.showContentWidget(rejsePage);

		}

	}

	private class AddProjectHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			rejsePage.addNewProject(rejsePage.getFlexTable());

		}

	}

	private class DeleteProjectHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			rejsePage.deleteNewProject(rejsePage.getFlexTable());

		}

	}

	private class AddBilagHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			bilagPage.addNewBilag(bilagPage.getFlexTable());

		}

	}

	private class DeleteBilagHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			bilagPage.deleteNewBilag(bilagPage.getFlexTable());

		}

	}

	private abstract class EnterKeyHandler implements KeyDownHandler {
		@Override
		public void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
				enterKeyDown(event);
		}
		public abstract void enterKeyDown(KeyDownEvent event);
	}

}
