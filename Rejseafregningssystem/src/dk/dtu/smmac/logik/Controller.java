package dk.dtu.smmac.logik;

import java.util.List;

import com.google.gwt.cell.client.FieldUpdater;
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
import dk.dtu.smmac.client.service.LandeService;
import dk.dtu.smmac.client.service.LandeServiceAsync;
import dk.dtu.smmac.client.service.LoginService;
import dk.dtu.smmac.client.service.LoginServiceAsync;
import dk.dtu.smmac.client.service.ProjektOpgaveService;
import dk.dtu.smmac.client.service.ProjektOpgaveServiceAsync;
import dk.dtu.smmac.client.service.RejseService;
import dk.dtu.smmac.client.service.RejseServiceAsync;
import dk.dtu.smmac.client.service.RejseafregningService;
import dk.dtu.smmac.client.service.RejseafregningServiceAsync;
import dk.dtu.smmac.client.service.UdgifterService;
import dk.dtu.smmac.client.service.UdgifterServiceAsync;
import dk.dtu.smmac.client.ui.AfslutningsInfo;
import dk.dtu.smmac.client.ui.Bilag;
import dk.dtu.smmac.client.ui.DageInfo;
import dk.dtu.smmac.client.ui.GlemtPassword;
import dk.dtu.smmac.client.ui.LoginPage;
import dk.dtu.smmac.client.ui.LoginTopView;
import dk.dtu.smmac.client.ui.MainPage;
import dk.dtu.smmac.client.ui.MainView;
import dk.dtu.smmac.client.ui.NavigationView;
import dk.dtu.smmac.client.ui.NyKode;
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
import dk.dtu.smmac.shared.UdgifterDTO;

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

	private NyKode nyKodePage;

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
	private UdgifterServiceAsync udgifterService = GWT.create(UdgifterService.class);
	private LandeServiceAsync landeService = GWT.create(LandeService.class);

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

		nyKodePage = mainView.getNykodePage();

		//Async
		asyncEmpty = new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());	
			}

			@Override
			public void onSuccess(Void result) {
			}
		};

		asyncCity = new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {	
			}

			@Override
			public void onSuccess(String result) {
				oplysningerPage.setlCityName(result);
			}
		};

		asyncRoad = new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(List<String> result) {
				oplysningerPage.setRoad(result);
			}
		};

		asyncHouseNo = new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(List<String> result) {
				oplysningerPage.setHouseNo(result);
			}
		};

		asyncFloor = new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(List<String> result) {
				oplysningerPage.setFloor(result);
			}
		};

		asyncDoor = new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(List<String> result) {
				oplysningerPage.setDoor(result);
			}
		};

		asyncOpgave = new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<String> result) {
				rejsePage.setOpgave(result);
			}

		};

		//ButtonColumn
		udgifterPage.getButtonColumn().setFieldUpdater(new FieldUpdater<UdgifterDTO, String>() {
			public void update(int index, UdgifterDTO object, String value) {
				udgifterService.deleteUdgifter(object, asyncEmpty);
				udgifterPage.getData().remove(index);
			}
		});
		bilagPage.getButtonColumn().setFieldUpdater(new FieldUpdater<BilagDTO, String>() {
			public void update(int index, BilagDTO object, String value) {
				bilagService.deleteBilag(object, asyncEmpty);
				bilagPage.getData().remove(index);
			}
		});
		bilagPage.getFileColumn().setFieldUpdater(new FieldUpdater<BilagDTO, String>() {
			public void update(int index, BilagDTO object, String value) {
				//show upload dialog
			}
		});

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
		bilagPage.getSaveButton().addClickHandler(new SaveBilagHandler());
		dageInfoPage.getBtn().addClickHandler(new SaveDageInfoHandler());
		rejsePage.getSaveButton().addClickHandler(new SaveRejseHandler());
		rejseafregningPage.getSaveButton().addClickHandler(new SaveRejseafregningsHandler());
		rejseafregningPage.getAddUdgiftAnchor().addClickHandler(new ShowUdgifterPageHandler());
		udgifterPage.getBtnTilbage().addClickHandler(new saveUdgifterHandler());
		udgifterPage.getBtnNyUdgift().addClickHandler(new addUdgiftHandler());
		oplysningerPage.getBtnNyKode().addClickHandler(new ShowNyKodeHandler());
		nyKodePage.getBtnNyKodeTilbage().addClickHandler(new ShowOplysningHandler());
		nyKodePage.getBtnNyKodeUdfoer().addClickHandler(new ChangePasswordHandler());
		afslutningPage.getGodkendButton().addClickHandler(new GodkendHandler());
		afslutningPage.getAfvisButton().addClickHandler(new AfvisHandler());
		rejsePage.getBackButton().addClickHandler(new RejseBackHandler());
		dageInfoPage.getBack().addClickHandler(new backToRejseafregning());
		afslutningPage.getBackButton().addClickHandler(new backToDageInfo());

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
				Window.alert(caught.getMessage());	
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
				Window.alert(caught.getMessage());	
			}

			@Override
			public void onSuccess(List<String> result) {
				rejsePage.setProjekt(result);
			}

		});

		//Land load
		landeService.getAllLande(new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<String> result) {
				rejsePage.setLand(result);
			}
		});

		//Rootpanel
		RootLayoutPanel.get().add(mainView);
	}

	private class GodkendHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			RejseafregningDTO rejse = rejseafregningPage.getRejseafregning();
			rejse.setBefordring(afslutningPage.getBef());
			rejse.setDagpenge(afslutningPage.getDag());
			rejse.setUdgifter(afslutningPage.getUd());
			rejse.setAfregningtotal(afslutningPage.getAfto());
			rejse.setRefundering(afslutningPage.getRef());
			rejse.setForskud(afslutningPage.getFo());
			rejse.setAfregning(afslutningPage.getAf());
			rejse.setDone(true);
			rejse.setAnvist(false);
			rejse.setGodkendt(false);
			rejseafregningService.updateRejse(rejse, asyncEmpty);
			showRejseafregninger();
		}
	}

	private class AfvisHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			//Sletter rejsedage
			for (DageInfoDTO dag : dageInfoPage.getData()) {
				dageInfoService.deleteDageInfo(dag, asyncEmpty);
			}
			//Sletter udgifter
			for (UdgifterDTO udgift : udgifterPage.getData()) {
				udgifterService.deleteUdgifter(udgift, asyncEmpty);
			}
			//Sletter rejser
			for (RejseDTO rejse : rejseafregningPage.getData()) {
				rejseService.deleteRejse(rejse, asyncEmpty);
			}
			//Sletter bilag
			for (BilagDTO bilag : bilagPage.getData()) {
				bilagService.deleteBilag(bilag, asyncEmpty);
			}
			//Sletter rejseafregning
			rejseafregningService.deleteRejse(rejseafregningPage.getRejseafregning(), asyncEmpty);

			Window.alert("Din rejseafregning er blevet slettet!");

			mainView.showContentWidget(mainPage);
		}
	}

	private class backToRejseafregning implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			mainView.showContentWidget(rejseafregningPage);
		}
	}

	private class backToDageInfo implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			mainView.showContentWidget(dageInfoPage);
		}
	}

	private class RejseBackHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			RejseDTO rejse = rejsePage.getRejse();
			rejseService.deleteRejse(rejse, asyncEmpty);
			rejseafregningPage.reset();
			rejseService.getRejser(rejse.getNummer(), new AsyncCallback<List<RejseDTO>>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
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
	}

	private class ChangePasswordHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			if (nyKodePage.getNyKodeTB() == nyKodePage.getNyKodeVeriTB()) {
				loginService.changePassword(nyKodePage.getBrugernavnTB(), nyKodePage.getGammelKodeTB(), nyKodePage.getNyKodeTB(), new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}

					@Override
					public void onSuccess(Boolean result) {
						if (result == true) {
							Window.alert("Dit kodeord er blevet ændret.");
						} else if (result == false) {
							Window.alert("Forkert brugernavn eller kodeord.");
						}
					}

				});
			} else {
				Window.alert("De to kodeord er ikke ens!");
			}
			nyKodePage.reset();
		}
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

	private class saveUdgifterHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			for (UdgifterDTO udgift : udgifterPage.getData()) {
				udgifterService.updateUdgifter(udgift, asyncEmpty);
			}
			mainView.showContentWidget(rejseafregningPage);
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
					Window.alert(caught.getMessage());
				}

				@Override
				public void onSuccess(RejseafregningDTO result) {
					rejseafregningPage.setRejseafregning(result);
					rejseService.getRejser(result.getId(), new AsyncCallback<List<RejseDTO>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.getMessage());
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
			showRejseafregninger();
		}
	}

	private class SaveDageInfoHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			for (DageInfoDTO dag : dageInfoPage.getData()) {
				dageInfoService.updateDageInfo(dag, asyncEmpty);
			}
			final int nummer = rejseafregningPage.getRejseafregning().getId();

			double befordring = 0;
			afslutningPage.setBefordring(befordring);

			dageInfoService.getDagpenge(nummer, new AsyncCallback<Double>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
				}

				@Override
				public void onSuccess(Double dagpenge) {
					afslutningPage.setDagpenge(dagpenge);

					udgifterService.getUdgifterSum(nummer, new AsyncCallback<Double>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.getMessage());
						}

						@Override
						public void onSuccess(Double udgifter) {
							afslutningPage.setUdgifter(udgifter);

							afslutningPage.setAfregningTotal();

							dageInfoService.getRefundering(nummer, new AsyncCallback<Double>() {

								@Override
								public void onFailure(Throwable caught) {
									Window.alert(caught.getMessage());
								}

								@Override
								public void onSuccess(Double refundering) {
									afslutningPage.setRefundering(refundering);

									double forskud = 0;
									afslutningPage.setForskud(forskud);

									afslutningPage.setAfregning();

									mainView.showContentWidget(afslutningPage);
								} 
							});
						}
					});
				}
			});
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
					Window.alert(caught.getMessage());	
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

	private class addUdgiftHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			addUdgift();
		}
	}

	private class ShowUdgifterPageHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) 
		{
			udgifterService.getUdgifter(rejseafregningPage.getRejseafregning().getId(), new AsyncCallback<List<UdgifterDTO>>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());	
				}

				@Override
				public void onSuccess(List<UdgifterDTO> result) {
					udgifterPage.reset();
					if (result.isEmpty()) {
						addUdgift();
					} else {
						for (UdgifterDTO udgift : result) {
							udgifterPage.addUdgiftPost(udgift);
						}
					}
					mainView.showContentWidget(udgifterPage);
				}
			});	
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
					Window.alert(caught.getMessage());		
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

	private class ShowNyKodeHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event)
		{
			nyKodePage.reset();
			mainView.showContentWidget(nyKodePage);
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
						Window.alert(caught.getMessage());
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
						Window.alert(caught.getMessage());
					}

					@Override
					public void onSuccess(Bruger result) {
						if (result == null) {
							Window.alert("Forkert brugernavn eller kodeord.");
						}

						ansatteService.getAnsat(result, new AsyncCallback<AnsatDTO>() {

							@Override
							public void onFailure(Throwable caught) {
							}

							@Override
							public void onSuccess(AnsatDTO result) {
								succesLogin(result);
							}
						});
						nyKodePage.setBrugernavnL(result.brugernavn);
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
			rejseafregningService.getLast(new AsyncCallback<Integer>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());

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
			bilagService.getBilag(rejseafregningPage.getRejseafregning().getId(), new AsyncCallback<List<BilagDTO>>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
				}

				@Override
				public void onSuccess(List<BilagDTO> result) {
					bilagPage.reset();
					if (result.isEmpty()) {
						addBilag();
					} else {
						for (BilagDTO bilag : result) {
							bilagPage.addBilag(bilag);
						} 
					}
					mainView.showContentWidget(bilagPage);
				}
			});
		}
	}


	private class SaveBilagHandler implements ClickHandler
	{
		public void onClick(ClickEvent event) {
			for (BilagDTO bilag : bilagPage.getData()) {
				bilagService.updateBilag(bilag, asyncEmpty);
			}
			mainView.showContentWidget(rejseafregningPage);
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
			rejseService.getLast(new AsyncCallback<Integer>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
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
			addBilag();
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
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(BankDTO result) {
				oplysningerPage.setRegNo(""+result.getRegNo());
				oplysningerPage.setKontoNo(""+result.getKontoNo());
			}
		});
	}

	public void addUdgift() {
		udgifterService.getLast(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Integer result) {
				UdgifterDTO udgift = new UdgifterDTO(result+1, rejseafregningPage.getRejseafregning().getId());
				udgifterService.createUdgifter(udgift, asyncEmpty);
				udgifterPage.addUdgiftPost(udgift);
			}
		});
	}

	public void addBilag() {
		bilagService.getLast(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Integer result) {
				BilagDTO bilag = new BilagDTO(result+1, rejseafregningPage.getRejseafregning().getId());
				bilagService.createBilag(bilag, asyncEmpty);
				bilagPage.addBilag(bilag);
			}
		});
	}

	public void showRejseafregninger() {
		rejseafregningService.getRejser(oplysningerPage.getAnsat().getID(), new AsyncCallback<List<RejseafregningerDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<RejseafregningerDTO> result) {
				rejseafregningerPage.setData(result);
				mainView.showContentWidget(rejseafregningerPage);
			}
		});
	}

}
