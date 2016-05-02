package dk.dtu.smmac.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MainPage extends Composite {

	private static MainPageUiBinder uiBinder = GWT.create(MainPageUiBinder.class);
	
	interface MainPageUiBinder extends UiBinder<Widget, MainPage> {
	}

	@UiField
	Button opret;
	@UiField
	Button godkendelser;
	@UiField
	Button anvisning;
	@UiField
	Button beskeder;
	
	public MainPage() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Button getOpret() {
		return opret;
	}

	public void setOpret(Button opret) {
		this.opret = opret;
	}

	public Button getGodkendelser() {
		return godkendelser;
	}

	public void setGodkendelser(Button godkendelser) {
		this.godkendelser = godkendelser;
	}

	public Button getAnvisning() {
		return anvisning;
	}

	public void setAnvisning(Button anvisning) {
		this.anvisning = anvisning;
	}

	public Button getBeskeder() {
		return beskeder;
	}

	public void setBeskeder(Button beskeder) {
		this.beskeder = beskeder;
	}

}