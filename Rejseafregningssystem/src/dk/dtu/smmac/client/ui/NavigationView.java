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

	
	public Anchor getOpgaver() {
		return opgaver;
	}



	public void setOpgaver(Anchor opgaver) {
		this.opgaver = opgaver;
	}



	public Anchor getArkiv() {
		return arkiv;
	}



	public void setArkiv(Anchor arkiv) {
		this.arkiv = arkiv;
	}



	public Anchor getRapporter() {
		return rapporter;
	}



	public void setRapporter(Anchor rapporter) {
		this.rapporter = rapporter;
	}



	public Anchor getOplysninger() {
		return oplysninger;
	}



	public void setOplysninger(Anchor oplysninger) {
		this.oplysninger = oplysninger;
	}



	public Anchor getIndstillinger() {
		return indstillinger;
	}



	public void setIndstillinger(Anchor indstillinger) {
		this.indstillinger = indstillinger;
	}



	public Anchor getLinks() {
		return links;
	}



	public void setLinks(Anchor links) {
		this.links = links;
	}



	public Anchor getHjælp() {
		return hjælp;
	}



	public void setHjælp(Anchor hjælp) {
		this.hjælp = hjælp;
	}
}
