package dk.dtu.smmac.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dk.dtu.smmac.shared.AfdelingDTO;
import dk.dtu.smmac.shared.AnsatDTO;
import dk.dtu.smmac.shared.PostNrDTO;

public class Oplysninger extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable fTable;
	private Label h1, h2, h3, lName, lSurname, lZipcode, lCity, lCityName, lDepartment, lTelephone, lEmail, lRoad, lHouseNr, lFloor, lDoor, email, lKontoNo, lRegNo;
	private TextBox name, surname, telephone, regNo, kontoNo;
	private ListBox department;
	private SuggestBox zip, road, houseNo, floor, door;
	private AnsatDTO ansat;
	private Button btnNyKode;
	private List<AfdelingDTO> afdelinger;
	private String width = "200px";
	private String lwidth = "65px";
	private String height = "20px";

	public Oplysninger() {
		initWidget(this.vPanel);
		fTable = new FlexTable();

		h1 = new Label("Info");
		h1.setStyleName("boldText");
		h2 = new Label("Adresse");
		h2.setStyleName("boldText");
		h3 = new Label("Bank");
		h3.setStyleName("boldText");
		
		lName = new Label("Navn:");
		lName.setWidth(lwidth);
		lName.setHeight(height);
		name = new TextBox();
		name.setWidth(width);
		name.setHeight(height);

		lSurname = new Label("Efternavn:");
		lSurname.setWidth(lwidth);
		lSurname.setHeight(height);
		surname = new TextBox();
		surname.setWidth(width);
		surname.setHeight(height);

		lZipcode = new Label("Postnr:");
		lZipcode.setWidth(lwidth);
		lZipcode.setHeight(height);
		zip = new SuggestBox(new MultiWordSuggestOracle());
		zip.setWidth(width);
		zip.setHeight(height);

		lCity = new Label("By:");
		lCity.setWidth(lwidth);
		lCity.setHeight(height);
		lCityName = new Label();
		lCityName.setWidth(width);
		lCityName.setHeight(height);

		lDepartment = new Label("Afdeling:");
		lDepartment.setWidth(lwidth);
		lDepartment.setHeight(height);
		department = new ListBox();
		department.setWidth(width);
		department.setHeight(height);

		lTelephone = new Label("Telefon:");
		lTelephone.setWidth(lwidth);
		lTelephone.setHeight(height);
		telephone = new TextBox();
		telephone.setWidth(width);
		telephone.setHeight(height);

		lEmail = new Label("Email:");
		lEmail.setWidth(lwidth);
		lEmail.setHeight(height);
		email = new Label();
		email.setWidth(width);
		email.setHeight(height);

		lRoad = new Label("Vejnavn:");
		lRoad.setWidth(lwidth);
		lRoad.setHeight(height);
		road = new SuggestBox(new MultiWordSuggestOracle());
		road.setWidth(width);
		road.setHeight(height);

		lHouseNr = new Label("Hus nr.:");
		lHouseNr.setWidth(lwidth);
		lHouseNr.setHeight(height);
		houseNo = new SuggestBox(new MultiWordSuggestOracle());
		houseNo.setWidth(width);
		houseNo.setHeight(height);

		lFloor = new Label("Etage:");
		lFloor.setWidth(lwidth);
		lFloor.setHeight(height);
		floor = new SuggestBox(new MultiWordSuggestOracle());
		floor.setWidth(width);
		floor.setHeight(height);

		lDoor = new Label("Dør:");
		lDoor.setWidth(lwidth);
		lDoor.setHeight(height);
		door = new SuggestBox(new MultiWordSuggestOracle());
		door.setWidth(width);
		door.setHeight(height);
		
		lRegNo = new Label("Reg nr.:");
		lRegNo.setWidth(lwidth);
		lRegNo.setHeight(height);
		regNo = new TextBox();
		regNo.setWidth(width);
		regNo.setHeight(height);
		
		lKontoNo = new Label("Kontonr.:");
		lKontoNo.setWidth(lwidth);
		lKontoNo.setHeight(height);
		kontoNo = new TextBox();
		kontoNo.setWidth(width);
		kontoNo.setHeight(height);
		
		btnNyKode = new Button("Ændre adgangskode");
		
		//Table
		fTable.setWidget(0, 1, h1);
		fTable.setWidget(1, 0, lName);
		fTable.setWidget(1, 1, name);
		fTable.setWidget(2, 0, lSurname);
		fTable.setWidget(2, 1, surname);
		fTable.setWidget(3, 0, lDepartment);
		fTable.setWidget(3, 1, department);
		fTable.setWidget(4, 0, lTelephone);
		fTable.setWidget(4, 1, telephone);
		fTable.setWidget(5, 0, lEmail);
		fTable.setWidget(5, 1, email);
		fTable.setWidget(6, 1, btnNyKode);
		
		btnNyKode.setStyleName("marginRightLeft");
		
		fTable.setWidget(0, 4, h2);
		fTable.setWidget(1, 3, lZipcode);
		fTable.setWidget(1, 4, zip);
		fTable.setWidget(2, 3, lCity);
		fTable.setWidget(2, 4, lCityName);
		fTable.setWidget(3, 3, lRoad);
		fTable.setWidget(3, 4, road);
		fTable.setWidget(4, 3, lHouseNr);
		fTable.setWidget(4, 4, houseNo);
		fTable.setWidget(5, 3, lFloor);
		fTable.setWidget(5, 4, floor);
		fTable.setWidget(6, 3, lDoor);
		fTable.setWidget(6, 4, door);

		fTable.setWidget(0, 7, h3);
		fTable.setWidget(1, 6, lRegNo);
		fTable.setWidget(1, 7, regNo);
		fTable.setWidget(2, 6, lKontoNo);
		fTable.setWidget(2, 7, kontoNo);
		
		vPanel.setStyleName("margin");
		vPanel.add(fTable);
	}

	public void setZip(List<PostNrDTO> list) {
		MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) zip.getSuggestOracle();
		
		for(int i = 0; i < list.size(); i++) {
			oracle.add(list.get(i).getNo());
		}
	}
	
	public void setRoad(List<String> list) {
		MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) road.getSuggestOracle();
		
		for(int i = 0; i < list.size(); i++) {
			oracle.add(list.get(i));
		}
	}
	
	public void setHouseNo(List<String> list) {
		MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) houseNo.getSuggestOracle();
		
		for(int i = 0; i < list.size(); i++) {
			oracle.add(list.get(i));
		}
	}
	
	public void setFloor(List<String> list) {
		MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) floor.getSuggestOracle();
		
		for(int i = 0; i < list.size(); i++) {
			oracle.add(list.get(i));
		}
	}
	
	public void setDoor(List<String> list) {
		MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) door.getSuggestOracle();
		
		for(int i = 0; i < list.size(); i++) {
			oracle.add(list.get(i));
		}
	}
	
	public void resetRoad() {
		MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) road.getSuggestOracle();
		oracle.clear();
		road.setText("");
	}
	
	public void resetHouseNo() {
		MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) houseNo.getSuggestOracle();
		oracle.clear();
		houseNo.setText("");
	}
	
	public void resetFloor() {
		MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) floor.getSuggestOracle();
		oracle.clear();
		floor.setText("");
	}
	
	public void resetDoor() {
		MultiWordSuggestOracle oracle = (MultiWordSuggestOracle) door.getSuggestOracle();
		oracle.clear();
		door.setText("");
	}
	
	public SuggestBox getZip() {
		return zip;
	}

	public void setAnsat(AnsatDTO ansat)
	{
		this.ansat = ansat;

		name.setText(ansat.getFornavn());
		surname.setText(ansat.getEfternavn());
		zip.setText(ansat.getPostnr()+"");
		telephone.setText(ansat.getTlf()+"");
		email.setText(ansat.getEmail());
		road.setText(ansat.getVejnavn());
		houseNo.setText(ansat.gethusnr());
		floor.setText(ansat.getEtage());
		door.setText(ansat.getDoer());
		department.setItemSelected(getDepartmentIndex(ansat.getAfdeling()), true);
	}

	public AnsatDTO getAnsat() 
	{
		ansat.setFornavn(name.getText());
		ansat.setEfternavn(surname.getText());
		ansat.setPostnr(Integer.parseInt(zip.getText()));
		ansat.setTlf(Integer.parseInt(telephone.getText()));
		ansat.setEmail(email.getText());
		ansat.setVejnavn(road.getText());
		ansat.setHusnr(houseNo.getText());
		ansat.setEtage(floor.getText());
		ansat.setDoer(door.getText());
		ansat.setAfdeling(department.getValue(department.getSelectedIndex()));

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

	public ListBox getDepartment() {
		return department;
	}

	public void setDepartment(ListBox department) {
		this.department = department;
	}

	public TextBox getTelephone() {
		return telephone;
	}

	public Label getEmail() {
		return email;
	}

	public SuggestBox getRoad() {
		return road;
	}

	public SuggestBox getHouseNr() {
		return houseNo;
	}

	public SuggestBox getFloor() {
		return floor;
	}

	public SuggestBox getDoor() {
		return door;
	}
	
	public TextBox getKontoNo() {
		return kontoNo;
	}
	
	public TextBox getRegNo() {
		return regNo;
	}
	
	public void setKontoNo(String no) {
		kontoNo.setText(no);
	}

	public void setRegNo(String no) {
		regNo.setText(no);
	}

	public Button getBtnNyKode()
	{
		return btnNyKode;
	}
}
