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
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import brugerautorisation.data.Bruger;
import dk.dtu.smmac.client.service.AfdelingerService;
import dk.dtu.smmac.client.service.AfdelingerServiceAsync;
import dk.dtu.smmac.client.service.AnsatteService;
import dk.dtu.smmac.client.service.AnsatteServiceAsync;
import dk.dtu.smmac.client.service.BankService;
import dk.dtu.smmac.client.service.BankServiceAsync;
import dk.dtu.smmac.client.service.BilagService;
import dk.dtu.smmac.client.service.BilagServiceAsync;
import dk.dtu.smmac.client.service.DAWAService;
import dk.dtu.smmac.client.service.DAWAServiceAsync;
import dk.dtu.smmac.client.service.DageInfoService;
import dk.dtu.smmac.client.service.DageInfoServiceAsync;
import dk.dtu.smmac.client.service.LoginService;
import dk.dtu.smmac.client.service.LoginServiceAsync;
import dk.dtu.smmac.client.service.ProjektOpgaveService;
import dk.dtu.smmac.client.service.ProjektOpgaveServiceAsync;
import dk.dtu.smmac.client.service.RejseService;
import dk.dtu.smmac.client.service.RejseServiceAsync;
import dk.dtu.smmac.client.service.RejseafregningService;
import dk.dtu.smmac.client.service.RejseafregningServiceAsync;
import dk.dtu.smmac.client.ui.AfslutningsInfo;
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
import dk.dtu.smmac.client.ui.Rejseafregninger;
import dk.dtu.smmac.client.ui.Udgifter;
import dk.dtu.smmac.shared.AfdelingDTO;
import dk.dtu.smmac.shared.AnsatDTO;
import dk.dtu.smmac.shared.BankDTO;
import dk.dtu.smmac.shared.BilagDTO;
import dk.dtu.smmac.shared.DageInfoDTO;
import dk.dtu.smmac.shared.PostNrDTO;
import dk.dtu.smmac.shared.RejseDTO;
import dk.dtu.smmac.shared.RejseafregningDTO;
import dk.dtu.smmac.shared.RejseafregningerDTO;

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

	private Rejseafregninger rejseafregningerPage;
	
	private Udgifter udgifterPage;
	
	private AfslutningsInfo afslutningPage;

	private HTML emptyView;
	private HTML emptyTopView;
	private HTML emptyNavView;

	private LoginServiceAsync loginService = GWT.create(LoginService.class);
	private AnsatteServiceAsync ansatteService = GWT.create(AnsatteService.class);
	private AfdelingerServiceAsync afdelingerService = GWT.create(AfdelingerService.class);
	private DAWAServiceAsync dawaService = GWT.create(DAWAService.class);
	private BankServiceAsync bankService = GWT.create(BankService.class);
	private RejseafregningServiceAsync rejseafregningService = GWT.create(RejseafregningService.class);
	private RejseServiceAsync rejseService = GWT.create(RejseService.class);
	private ProjektOpgaveServiceAsync projektopgaveService = GWT.create(ProjektOpgaveService.class);
	private BilagServiceAsync bilagService = GWT.create(BilagService.class);
	private DageInfoServiceAsync dageInfoService = GWT.create(DageInfoService.class);

	AsyncCallback<Void> asyncEmpty;
	AsyncCallback<String> asyncCity;
	AsyncCallback<List<String>> asyncRoad, asyncHouseNo, asyncFloor, asyncDoor, asyncOpgave;

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

		rejseafregningerPage = mainView.getRejseafregningerPage();
		
		udgifterPage = mainView.getUdgifterPage();
		
		afslutningPage = mainView.getAfslutningsInfoPage();

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

		asyncOpgave = new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("An error has occured");
			}

			@Override
			public void onSuccess(List<String> result) {
				rejsePage.setOpgave(result);
			}

		};
		
		//SelectionChangehandler
		rejseafregningPage.getModel().addSelectionChangeHandler(new RejseClickHandler());
		rejseafregningerPage.getModel().addSelectionChangeHandler(new RejseafregningerClickHandler());

		//Clickhandler
		loginTopView.getLoginAnchor().addClickHandler(new ShowLoginHandler());
		loginPage.getLoginButton().addClickHandler(new LoginHandler());
		loginPage.getGlemtPasswordButton().addClickHandler(new GlemtLoginHandler());
		mainPage.getOpret().addClickHandler(new ShowRejseafregningHandler());
		rejseafregningPage.getBilagButton().addClickHandler(new ShowBilagHandler());
		navPage.getOpgaver().addClickHandler(new ShowOpgaveHandler());
		navPage.getOplysninger().addClickHandler(new ShowOplysningHandler());
		navPage.getArkiv().addClickHandler(new ShowRejseafregningerHandler());
		rejseafregningPage.getAddTravelAnchor().addClickHandler(new ShowAddTravelHandler());
		glemtPasswordPage.getbtnSendPassword().addClickHandler(new SendPasswordHandler());
		glemtPasswordPage.getbtnAnnullerPassword().addClickHandler(new ShowLoginHandler());
		bilagPage.getAddBilag().addClickHandler(new AddBilagHandler());
		bilagPage.getDelete().addClickHandler(new DeleteBilagHandler());
		bilagPage.getCont().addClickHandler(new SaveBilagHandler());
		dageInfoPage.getBtn().addClickHandler(new SaveDageInfoHandler());
		rejsePage.getSaveButton().addClickHandler(new SaveRejseHandler());
		rejseafregningPage.getSaveButton().addClickHandler(new SaveRejseafregningsHandler());
		rejseafregningPage.getAddUdgiftAnchor().addClickHandler(new ShowUdgifterPageHandler());
		

		//BlurHandler
		oplysningerPage.getName().addBlurHandler(new UpdateAnsatHandler());
		oplysningerPage.getSurname().addBlurHandler(new UpdateAnsatHandler());
		oplysningerPage.getDepartment().addBlurHandler(new UpdateAnsatHandler());
		oplysningerPage.getTelephone().addBlurHandler(new UpdateAnsatHandler());
		oplysningerPage.getRegNo().addBlurHandler(new BankHandler());
		oplysningerPage.getKontoNo().addBlurHandler(new BankHandler());
		rejsePage.getProject().addBlurHandler(new ProjectHandler());

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

		EnterKeyHandler enterSendPasswordHandler = new EnterKeyHandler() {

			@Override
			public void enterKeyDown(KeyDownEvent event) {

				SendPasswordHandler sendPassword = new SendPasswordHandler();

				sendPassword.onClick(sendPassword.getEvent());

			}

		};

		loginPage.getPasswordTextField().addKeyDownHandler(enterLoginHandler);
		glemtPasswordPage.getMailTextField().addKeyDownHandler(enterSendPasswordHandler);

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

		//Projekt load
		projektopgaveService.getProjekt(new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("An error has occured");	
			}

			@Override
			public void onSuccess(List<String> result) {
				rejsePage.setProjekt(result);
			}

		});

		//Rootpanel
		RootLayoutPanel.get().add(mainView);
	}

	private class ProjectHandler implements BlurHandler {

		@Override
		public void onBlur(BlurEvent event) {
			projektopgaveService.getOpgave(rejsePage.getProject().getValue(rejsePage.getProject().getSelectedIndex()), asyncOpgave); 
		}

	}

	private class RejseClickHandler implements Handler
	{
		@Override
		public void onSelectionChange(SelectionChangeEvent event) {		
			RejseDTO rejse = rejseafregningPage.getModel().getSelectedObject();
			rejsePage.setRejse(rejse);
			projektopgaveService.getOpgave(rejse.getProjekt(), asyncOpgave);
			mainView.showContentWidget(rejsePage);
		}
	}
	
	private class RejseafregningerClickHandler implements Handler
	{
		@Override
		public void onSelectionChange(SelectionChangeEvent event) {
			RejseafregningerDTO rejse = rejseafregningerPage.getModel().getSelectedObject();
			rejseafregningPage.reset();
			rejseafregningService.getRejse(rejse.getNr(), oplysningerPage.getAnsat().getID(), new AsyncCallback<RejseafregningDTO>() {

				@Override
				public void onFailure(Throwable caught) {
					System.out.println("An error has occured");
				}

				@Override
				public void onSuccess(RejseafregningDTO result) {
					rejseafregningPage.setRejseafregning(result);
					rejseService.getRejser(result.getId(), new AsyncCallback<List<RejseDTO>>() {

						@Override
						public void onFailure(Throwable caught) {
							System.out.println("An error has occured");
						}

						@Override
						public void onSuccess(List<RejseDTO> result) {
							for(RejseDTO rejse : result) {
								rejseafregningPage.addTravelSummary(rejse);
							}
							rejseafregningPage.setStartDateLabel();
							rejseafregningPage.setEndDateLabel();
							mainView.showContentWidget(rejseafregningPage);
							rejseafregningPage.getModel().setSelected(null, true);
						}
					});
					
					
				}
				
			});
			
		}
	}

	private class ShowRejseafregningerHandler implements ClickHandler 
	{
		@Override
		public void onClick(ClickEvent event) {
			rejseafregningService.getRejser(oplysningerPage.getAnsat().getID(), new AsyncCallback<List<RejseafregningerDTO>>() {

				@Override
				public void onFailure(Throwable caught) {
					System.out.println("An error has occured");	
				}

				@Override
				public void onSuccess(List<RejseafregningerDTO> result) {
					rejseafregningerPage.setData(result);
					mainView.showContentWidget(rejseafregningerPage);
				}
				
			});
		}
	}
	
	private class SaveDageInfoHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			for (DageInfoDTO dag : dageInfoPage.getData()) {
				//TODO
				Window.alert(dag.getDageInfoDato().toString() + " - Morgenmad: " + dag.getMorgenmad() + " - Frokost: " + dag.getFrokost() + " - Aftensmad: " + dag.getAftensmad());
				dageInfoService.updateDageInfo(dag, asyncEmpty);
			}
			mainView.showContentWidget(afslutningPage);
		}
		
	}

	private class SaveRejseafregningsHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			rejseafregningService.updateRejse(rejseafregningPage.getRejseafregning(), asyncEmpty);
			dageInfoService.getDageInfo(rejseafregningPage.getRejseafregning().getId(), new AsyncCallback<List<DageInfoDTO>>() {

				@Override
				public void onFailure(Throwable caught) {
					System.out.println("An error has occured");	
				}

				@Override
				public void onSuccess(List<DageInfoDTO> result) {
					dageInfoPage.reset();
					for(DageInfoDTO dag : result) {
						dageInfoPage.addData(dag);
					}
					mainView.showContentWidget(dageInfoPage);
				}
			});
		}

	}

	private class SaveRejseHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) {
			RejseDTO rejse = rejsePage.getRejse();
			rejseService.updateRejse(rejse, asyncEmpty);
			rejseafregningPage.addTravelSummary(rejse);
			rejseafregningPage.setStartDateLabel();
			rejseafregningPage.setEndDateLabel();
			mainView.showContentWidget(rejseafregningPage);
			rejseafregningPage.getModel().setSelected(rejse, false);
		}
	}

	private class ShowUdgifterPageHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) 
		{
			mainView.showContentWidget(udgifterPage);	
		}
		
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
		ClickEvent event;

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

		public ClickEvent getEvent()
		{
			return this.event;
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
			mainView.showContentWidget(loginPage);
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
			if (loginPage.getBrugernavn().equals("peter") && loginPage.getPassword().equals("1234")) {
				ansatteService.getAnsat(0, new AsyncCallback<AnsatDTO>() {

					@Override
					public void onFailure(Throwable caught) {
						System.out.println("An error has occured");
					}

					@Override
					public void onSuccess(AnsatDTO result) {
						succesLogin(result);
					}
				});
			} else {
				loginService.logIn(loginPage.getBrugernavn(), loginPage.getPassword(), new AsyncCallback<Bruger>(){

					@Override
					public void onFailure(Throwable caught) {
						System.out.println("An error has occured");
						Window.alert(caught.getMessage());
					}

					@Override
					public void onSuccess(Bruger result) {
						if (result == null) {
							//TODO Skal ændres til noget label ændring eller lign
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
								succesLogin(result);
							}

						});
					}
				}); 
			}
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

	private class ShowRejseafregningHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			rejseafregningService.getSize(new AsyncCallback<Integer>() {

				@Override
				public void onFailure(Throwable caught) {
					System.out.println("An error has occured");

				}

				@Override
				public void onSuccess(Integer result) {
					RejseafregningDTO rejseafregning = new RejseafregningDTO(result+1, oplysningerPage.getAnsat().getID());
					rejseafregningService.createRejse(rejseafregning, asyncEmpty);
					rejseafregningPage.setRejseafregning(rejseafregning);
					rejseafregningPage.reset();
					mainView.showContentWidget(rejseafregningPage);
				}

			});
		}

	}

	private class ShowBilagHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) {
			bilagService.getBilag(rejseafregningPage.getRejseafregning().getId(), new AsyncCallback<List<BilagDTO>>(){

				@Override
				public void onFailure(Throwable caught) {
					System.out.println("An error has occured");
				}

				@Override
				public void onSuccess(List<BilagDTO> result) {
					
					for(int i = 0; i < result.size(); i++)
					{
						bilagPage.addNewBilag(bilagPage.getFlexTable());
					}
				}
			});
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
			rejseService.getSize(new AsyncCallback<Integer>() {

				@Override
				public void onFailure(Throwable caught) {
					System.out.println("An error has occured");
				}

				@Override
				public void onSuccess(Integer result) {
					RejseDTO rejse = new RejseDTO(result+1, rejseafregningPage.getRejseafregning().getId());
					rejseService.createRejse(rejse, asyncEmpty);
					projektopgaveService.getOpgave(rejsePage.getProject().getValue(rejsePage.getProject().getSelectedIndex()), asyncOpgave);
					rejsePage.setRejse(rejse);
					mainView.showContentWidget(rejsePage);
				}
			});
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
	
	private class SaveBilagHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			bilagService.getSize(new AsyncCallback<Integer>() {
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("An error has occured");	
			}
			
			@Override
			public void onSuccess(Integer result) {
				for(int i = 0; i < bilagPage.getFlexTable().getRowCount(); i++)
				{
					BilagDTO bilag = new BilagDTO(result, rejseafregningPage.getRejseafregning().getId() ,bilagPage.getTList().get(i).getText());
					bilagService.createBilag(bilag, asyncEmpty);
					result++;
				}
				mainView.showContentWidget(rejsePage);
			}
		});
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
	
	public void succesLogin(AnsatDTO result) {
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

}
