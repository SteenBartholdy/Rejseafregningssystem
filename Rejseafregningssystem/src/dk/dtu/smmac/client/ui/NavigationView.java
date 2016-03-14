package dk.dtu.smmac.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class NavigationView extends Composite {
	
	private static NavigationViewUiBinder uiBinder = GWT.create(NavigationViewUiBinder.class);

	interface NavigationViewUiBinder extends UiBinder<Widget, NavigationView> {
	}
	
	
	@UiField
	Anchor opgaver;
	@UiField
	Anchor arkiv;
	@UiField
	Anchor rapporter;
	@UiField
	Anchor oplysninger;
	@UiField
	Anchor indstillinger;
	@UiField
	Anchor links;
	@UiField
	Anchor hjælp;
	


	public NavigationView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
