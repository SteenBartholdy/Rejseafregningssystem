package dk.dtu.smmac.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class NavigationView extends Composite {

	private static NavigationViewUiBinder uiBinder = GWT.create(NavigationViewUiBinder.class);

	interface NavigationViewUiBinder extends UiBinder<Widget, NavigationView> {
	}
	
	@UiField
	Button opgaver;
	@UiField
	Button arkiv;
	@UiField
	Button rapporter;
	@UiField
	Button oplysninger;
	@UiField
	Button indstillinger;
	@UiField
	Button links;
	@UiField
	Button hjælp;

	public NavigationView() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	
}
