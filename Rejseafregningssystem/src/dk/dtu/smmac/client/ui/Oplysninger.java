package dk.dtu.smmac.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dk.dtu.smmac.shared.AfdelingDTO;
import dk.dtu.smmac.shared.AnsatDTO;
import dk.dtu.smmac.shared.PostNrDTO;

public class Oplysninger extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable fTable;
	private Label lName, lSurname, lZipcode, lCity, lCityName, lDepartment, lTelephone, lEmail, lRoad, lHouseNr, lFloor, lDoor, email;
	private TextBox name, surname, telephone;
	private ListBox department;
	private SuggestBox zip, road, houseNo, floor, door;
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
		zip = new SuggestBox(new MultiWordSuggestOracle());
		zip.setWidth(width);
		zip.setHeight(height);

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
		email = new Label();
		email.setWidth(width);
		email.setHeight(height);

		lRoad = new Label("Vejnavn");
		road = new SuggestBox(new MultiWordSuggestOracle());
		road.setWidth(width);
		road.setHeight(height);

		lHouseNr = new Label("Hus nr.:");
		houseNo = new SuggestBox(new MultiWordSuggestOracle());
		houseNo.setWidth(width);
		houseNo.setHeight(height);

		lFloor = new Label("Etage:");
		floor = new SuggestBox(new MultiWordSuggestOracle());
		floor.setWidth(width);
		floor.setHeight(height);

		lDoor = new Label("Dør:");
		door = new SuggestBox(new MultiWordSuggestOracle());
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
		fTable.setWidget(5, 1, zip);
		fTable.setWidget(6, 0, lCity);
		fTable.setWidget(6, 1, lCityName);
		fTable.setWidget(7, 0, lRoad);
		fTable.setWidget(7, 1, road);
		fTable.setWidget(8, 0, lHouseNr);
		fTable.setWidget(8, 1, houseNo);
		fTable.setWidget(9, 0, lFloor);
		fTable.setWidget(9, 1, floor);
		fTable.setWidget(10, 0, lDoor);
		fTable.setWidget(10, 1, door);

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

}
