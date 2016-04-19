package dk.dtu.smmac.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dk.dtu.smmac.shared.AfdelingDTO;
import dk.dtu.smmac.shared.AnsatDTO;

public class Oplysninger extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable fTable;
	private Label lName, lSurname, lZipcode, lCity, lCityName, lDepartment, lTelephone, lEmail, lRoad, lHouseNr, lFloor, lDoor;
	private TextBox name, surname, zipcode, telephone, email, road, houseNr, floor, door;
	private ListBox department;
	private AnsatDTO ansat;
	private List<AfdelingDTO> afdelinger;
	private String width = "200px";
	private String height = "20px";
	
	public Oplysninger() {
		initWidget(this.vPanel);
		fTable = new FlexTable();
		
		lName = new Label("Navn:");
		name = new TextBox();
		name.setWidth(width);
		name.setHeight(height);
		
		lSurname = new Label("Efternavn:");
		surname = new TextBox();
		surname.setWidth(width);
		surname.setHeight(height);
		
		lZipcode = new Label("Postnr:");
		zipcode = new TextBox();
		zipcode.setWidth(width);
		zipcode.setHeight(height);
		
		lCity = new Label("By:");
		lCityName = new Label();
		lCityName.setWidth(width);
		lCityName.setHeight(height);
		
		lDepartment = new Label("Afdeling:");
		department = new ListBox();
		department.setWidth(width);
		department.setHeight(height);
		
		lTelephone = new Label("Telefon:");
		telephone = new TextBox();
		telephone.setWidth(width);
		telephone.setHeight(height);
		
		lEmail = new Label("Email:");
		email = new TextBox();
		email.setWidth(width);
		email.setHeight(height);
		
		lRoad = new Label("Vejnavn");
		road = new TextBox();
		road.setWidth(width);
		road.setHeight(height);
		
		lHouseNr = new Label("Hus nr.:");
		houseNr = new TextBox();
		houseNr.setWidth(width);
		houseNr.setHeight(height);
		
		lFloor = new Label("Etage:");
		floor = new TextBox();
		floor.setWidth(width);
		floor.setHeight(height);
		
		lDoor = new Label("Dør:");
		door = new TextBox();
		door.setWidth(width);
		door.setHeight(height);
		
		//Table
		fTable.setWidget(0, 0, lName);
		fTable.setWidget(0, 1, name);
		fTable.setWidget(1, 0, lSurname);
		fTable.setWidget(1, 1, surname);
		fTable.setWidget(2, 0, lDepartment);
		fTable.setWidget(2, 1, department);
		fTable.setWidget(3, 0, lTelephone);
		fTable.setWidget(3, 1, telephone);
		fTable.setWidget(4, 0, lEmail);
		fTable.setWidget(4, 1, email);
		fTable.setWidget(5, 0, lZipcode);
		fTable.setWidget(5, 1, zipcode);
		fTable.setWidget(6, 0, lCity);
		fTable.setWidget(6, 1, lCityName);
		fTable.setWidget(7, 0, lRoad);
		fTable.setWidget(7, 1, road);
		fTable.setWidget(8, 0, lHouseNr);
		fTable.setWidget(8, 1, houseNr);
		fTable.setWidget(9, 0, lFloor);
		fTable.setWidget(9, 1, floor);
		fTable.setWidget(10, 0, lDoor);
		fTable.setWidget(10, 1, door);
		
		vPanel.setStyleName("margin");
		vPanel.add(fTable);
	}
	
	public void setAnsat(AnsatDTO ansat)
	{
		this.ansat = ansat;
		
		name.setText(ansat.getFornavn());
		surname.setText(ansat.getEfternavn());
		zipcode.setText(ansat.getPostnr()+"");
		department.setItemSelected(getDepartmentIndex(ansat.getAfdeling()), true);
		telephone.setText(ansat.getTlf()+"");
		email.setText(ansat.getEmail());
		road.setText(ansat.getVejnavn());
		houseNr.setText(ansat.gethusnr());
		floor.setText(ansat.getEtage());
		door.setText(ansat.getDoer());
	}
	
	public AnsatDTO getAnsat() 
	{
		ansat.setFornavn(name.getText());
		ansat.setEfternavn(surname.getText());
		ansat.setPostnr(Integer.parseInt(zipcode.getText()));
		ansat.setAfdeling(department.getValue(department.getSelectedIndex()));
		ansat.setTlf(Integer.parseInt(telephone.getText()));
		ansat.setEmail(email.getText());
		ansat.setVejnavn(road.getText());
		ansat.setHusnr(houseNr.getText());
		ansat.setEtage(floor.getText());
		ansat.setDoer(door.getText());
		
		return ansat;
	}
	
	public void setAfdelinger(List<AfdelingDTO> afdelinger)
	{
		this.afdelinger = afdelinger;
	}
	
	public void setDepartmentItems() {
		for(int i = 0; i < this.afdelinger.size(); i++)
		{
			department.addItem(this.afdelinger.get(i).getAfdeling());
		}
	}
	
	public int getDepartmentIndex(String afdeling)
	{
		for(int i = 0; i < afdelinger.size(); i++)
		{
			if(afdelinger.get(i).getAfdeling().equals(afdeling))
			{
				return i;
			}
		}
		return 0;
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

	public void setlCityName(String lCityName) {
		this.lCityName.setText(lCityName);
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

	public ListBox getDepartment() {
		return department;
	}

	public void setDepartment(ListBox department) {
		this.department = department;
	}

	public TextBox getTelephone() {
		return telephone;
	}
	
	public TextBox getEmail() {
		return email;
	}
	
	public TextBox getRoad() {
		return road;
	}
	
	public TextBox getHouseNr() {
		return houseNr;
	}
	
	public TextBox getFloor() {
		return floor;
	}
	
	public TextBox getDoor() {
		return door;
	}
	
}
