package dk.dtu.smmac.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dk.dtu.smmac.shared.AnsatDTO;

public class Oplysninger extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable fTable;
	private Label lName, lSurname, lZipcode, lCity, lCityName, lDepartment;
	private TextBox name, surname, zipcode, department;
	
	public Oplysninger() {
		initWidget(this.vPanel);
		fTable = new FlexTable();
		
		lName = new Label("Navn:");
		lSurname = new Label("Efternavn:");
		
		name = new TextBox();
		surname = new TextBox();
		
		lZipcode = new Label();
		zipcode = new TextBox();
		
		lCity = new Label();
		lCityName = new Label();
		
		lDepartment = new Label();
		department = new TextBox();
		
		fTable.setWidget(0, 0, lName);
		fTable.setWidget(0, 1, name);
		fTable.setWidget(1, 0, lSurname);
		fTable.setWidget(1, 1, surname);
		fTable.setWidget(2, 0, lDepartment);
		fTable.setWidget(2, 1, department);
		
		
		fTable.setWidget(5, 0, lZipcode);
		fTable.setWidget(5, 1, zipcode);
		fTable.setWidget(6, 0, lCity);
		fTable.setWidget(6, 1, lCityName);
		
		vPanel.setStyleName("margin");
		vPanel.add(fTable);
	}
	
	public void setAnsat(AnsatDTO ansat)
	{
		name.setText(ansat.getFornavn());
		surname.setText(ansat.getEfternavn());
	}

	public VerticalPanel getvPanel() {
		return vPanel;
	}

	public void setvPanel(VerticalPanel vPanel) {
		this.vPanel = vPanel;
	}

	public FlexTable getfTable() {
		return fTable;
	}

	public void setfTable(FlexTable fTable) {
		this.fTable = fTable;
	}

	public Label getlName() {
		return lName;
	}

	public void setlName(Label lName) {
		this.lName = lName;
	}

	public Label getlSurname() {
		return lSurname;
	}

	public void setlSurname(Label lSurname) {
		this.lSurname = lSurname;
	}

	public Label getlZipcode() {
		return lZipcode;
	}

	public void setlZipcode(Label lZipcode) {
		this.lZipcode = lZipcode;
	}

	public Label getlCity() {
		return lCity;
	}

	public void setlCity(Label lCity) {
		this.lCity = lCity;
	}

	public Label getlCityName() {
		return lCityName;
	}

	public void setlCityName(Label lCityName) {
		this.lCityName = lCityName;
	}

	public Label getlDepartment() {
		return lDepartment;
	}

	public void setlDepartment(Label lDepartment) {
		this.lDepartment = lDepartment;
	}

	public TextBox getName() {
		return name;
	}

	public void setName(TextBox name) {
		this.name = name;
	}

	public TextBox getSurname() {
		return surname;
	}

	public void setSurname(TextBox surname) {
		this.surname = surname;
	}

	public TextBox getZipcode() {
		return zipcode;
	}

	public void setZipcode(TextBox zipcode) {
		this.zipcode = zipcode;
	}

	public TextBox getDepartment() {
		return department;
	}

	public void setDepartment(TextBox department) {
		this.department = department;
	}

	
	
}
