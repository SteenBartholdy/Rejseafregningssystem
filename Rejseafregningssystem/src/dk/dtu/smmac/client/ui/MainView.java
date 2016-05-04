package dk.dtu.smmac.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckLayoutPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class MainView extends Composite {

	private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);

	@UiField
	DockLayoutPanel layoutPanel;
	@UiField
	DeckLayoutPanel contentPanel;
	@UiField
	DeckLayoutPanel navigationPanel;
	@UiField
	DeckLayoutPanel topPanel;
	
	private LoginTopView loginTopView;
	
	private NavigationView navPage;
	
	private LoginPage loginPage;
	
	private Rejseafregning rejseafregningPage;
	
	private MainPage mainPage;
	
	private Bilag bilagPage;
	
	private Oplysninger oplysningerPage;
	
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

	interface MainViewUiBinder extends UiBinder<Widget, MainView> {
	}

	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		layoutPanel.getWidgetContainerElement(contentPanel).getStyle().setOverflowY(Overflow.AUTO);
		
		emptyView = new HTML("");
		emptyTopView = new HTML("");
		emptyNavView = new HTML("");

		loginTopView = new LoginTopView();
		navPage = new NavigationView();
		loginPage = new LoginPage();
		rejseafregningPage = new Rejseafregning();
		mainPage = new MainPage();
		bilagPage = new Bilag();
		oplysningerPage = new Oplysninger();
		rejsePage = new Rejse();
		glemtPasswordPage = new GlemtPassword();
		dageInfoPage = new DageInfo();
		rejseafregningerPage = new Rejseafregninger();
		udgifterPage = new Udgifter();
		afslutningPage = new AfslutningsInfo();
		nyKodePage = new NyKode();
		
		//Tilføjer til top bunken
		topPanel.add(emptyTopView);
		topPanel.add(loginTopView);
		
		//Tilføjer til navigation bunken
		navigationPanel.add(emptyNavView);
		navigationPanel.add(navPage);
		
		//Tilføjer til content bunken
		contentPanel.add(emptyView);
		contentPanel.add(loginPage);
		contentPanel.add(rejseafregningPage);
		contentPanel.add(mainPage);
		contentPanel.add(oplysningerPage);
		contentPanel.add(rejsePage);
		contentPanel.add(glemtPasswordPage);
		contentPanel.add(bilagPage);
		contentPanel.add(dageInfoPage);
		contentPanel.add(rejseafregningerPage);
		contentPanel.add(udgifterPage);
		contentPanel.add(afslutningPage);
		contentPanel.add(nyKodePage);
		
		//Load
		showContentWidget(loginPage);
		showNavWidget(emptyNavView);
		showTopWidget(emptyTopView);
	}

	public void showContentWidget(Widget w) {
		contentPanel.showWidget(w);
	}
	
	public void showTopWidget(Widget w) {
		topPanel.showWidget(w);
	}
	
	public void showNavWidget(Widget w) {
		navigationPanel.showWidget(w);
	}
	
	public void showLoginPage()
	{
		contentPanel.showWidget(loginPage);	
	}

	public LoginTopView getLoginTopView()
	{
		return loginTopView;
	}
	
	public LoginPage getLoginPage()
	{
		return loginPage;
	}

	public Rejseafregning getRejseafregningPage()
	{
		return rejseafregningPage;
	}
	
	public MainPage getMainPage()
	{
		return mainPage;
	}
	
	public Bilag getBilagPage()
	{
		return bilagPage;
	}
	
	public Udgifter getUdgifterPage()
	{
		return udgifterPage;
	}
	
	public NyKode getNykodePage()
	{
		return nyKodePage;
	}
	
	public NavigationView getNavPage()
	{
		return navPage;
	}

	public HTML getEmptyView()
	{
		return emptyView;
	}
	
	public HTML getEmptyTopView()
	{
		return emptyTopView;
	}
	
	public HTML getEmptyNavView()
	{
		return emptyNavView;
	}
	
	public Oplysninger getOplysninger()
	{
		return oplysningerPage;
	}
	
	public Rejse getRejsePage()
	{
		return rejsePage;
	}
	
	public GlemtPassword getGlemtPasswordPage()
	{
		return glemtPasswordPage;
	}

	public DageInfo getDageInfoPage()
	{
		return dageInfoPage;
	}
	
	public Rejseafregninger getRejseafregningerPage() {
		return rejseafregningerPage;
	}
	
	public AfslutningsInfo getAfslutningsInfoPage()
	{
		return afslutningPage;
	}
}
