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
	
	private LoginTopView login;
	
	private NavigationView nav;


	private HTML emptyView;

	interface MainViewUiBinder extends UiBinder<Widget, MainView> {
	}

	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));

		layoutPanel.getWidgetContainerElement(contentPanel).getStyle().setOverflowY(Overflow.AUTO);
		
		emptyView = new HTML("looooooool");

		login = new LoginTopView();
		nav = new NavigationView();


		topPanel.add(login);

		navigationPanel.add(nav);
		
		contentPanel.add(emptyView);

		showContentWidget(emptyView);
		navigationPanel.showWidget(nav);
		topPanel.showWidget(login);
	}

	private void showContentWidget(Widget w) {
		contentPanel.showWidget(w);
	}



}
