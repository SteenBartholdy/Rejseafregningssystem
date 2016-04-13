package dk.dtu.smmac.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import dk.dtu.smmac.shared.AnsatDTO;

public class Oplysninger extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable fTable;
	private Label lName, lSurname;
	private Label name, surname;
	
	public Oplysninger() {
		initWidget(this.vPanel);
		fTable = new FlexTable();
		
		lName = new Label("Navn:");
		lSurname = new Label("Efternavn:");
		
		name = new Label();
		surname = new Label();
		
		fTable.setWidget(0, 0, lName);
		fTable.setWidget(0, 1, lSurname);
		fTable.setWidget(1, 0, name);
		fTable.setWidget(1, 1, surname);
	}
	
	public void setAnsat(AnsatDTO ansat)
	{
		name.setText(ansat.getFornavn());
		surname.setText(ansat.getEfternavn());
	}

}
