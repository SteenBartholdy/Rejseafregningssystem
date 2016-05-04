package dk.dtu.smmac.client.ui;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import dk.dtu.smmac.shared.RejseDTO;

public class Rejse extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private FlexTable fTable;
	private Label countryL, dateL, dateToL, projectL, assignmentL;
	private ListBox country, project, assignment;
	private DateBox date, dateTo;
	private DateTimeFormat dateFormat;
	private Button save, back;
	private List<String> projekt, opgave, land;
	private RejseDTO rejse;

	public Rejse()
	{
		initWidget(vPanel);

		fTable = new FlexTable();

		countryL = new Label("Land:");
		dateL = new Label("Dato:");
		dateToL = new Label("til:");
		dateToL.setStyleName("center");

		projectL = new Label("Projekt: ");
		assignmentL = new Label("Opgave: ");

		date = new DateBox();
		date.setPixelSize(80, 15);
		dateTo = new DateBox();
		dateTo.setPixelSize(80, 15);

		country = new ListBox();
		project = new ListBox();
		assignment = new ListBox();
		
		save = new Button("Gem");
		back = new Button("Slet");

		dateFormat = DateTimeFormat.getFormat("dd/MM-yyyy");

		date.setFormat(new DateBox.DefaultFormat(dateFormat));
		dateTo.setFormat(new DateBox.DefaultFormat(dateFormat));

		fTable.setWidget(0, 0, countryL);
		fTable.setWidget(0, 1, country);
		fTable.setWidget(1, 0, dateL);
		fTable.setWidget(1, 1, date);
		fTable.setWidget(1, 2, dateToL);
		fTable.setWidget(1, 3, dateTo);
		fTable.setWidget(2, 0, projectL);
		fTable.setWidget(2, 1, project);
		fTable.setWidget(2, 2, assignmentL);
		fTable.setWidget(2, 3, assignment);

		fTable.setStyleName("flextable");

		vPanel.setStyleName("margin");
		vPanel.add(fTable);
		vPanel.add(hPanel);
		hPanel.add(back);
		hPanel.add(save);
		save.setStyleName("marginLeft");
		back.setStyleName("marginRight");
	}
	
	public void setRejse(RejseDTO rejse) {
		this.rejse = rejse;
		
		date.setValue(rejse.getDatoFra());
		dateTo.setValue(rejse.getDatoTil());
		country.setItemSelected(getCountryIndex(rejse.getLand()), true);
		project.setItemSelected(getProjectIndex(rejse.getProjekt()), true);
		//assignment.setItemSelected(getAssignmentIndex(rejse.getOpgave()), true);
	}
	
	public RejseDTO getRejse() {
		try {
		this.rejse.setDatoFra(getDate());
		this.rejse.setDatoTil(getDateTo());
		this.rejse.setLand(country.getValue(country.getSelectedIndex()));
		this.rejse.setOpgave(assignment.getValue(assignment.getSelectedIndex()));
		this.rejse.setProjekt(project.getValue(project.getSelectedIndex()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return this.rejse;
	}

	public int getCountryIndex(String land)
	{
		for(int i = 0; i < this.land.size(); i++)
		{
			if(this.land.get(i).equals(land))
			{
				return i;
			}
		}
		return 0;
	}
	
	public int getProjectIndex(String projekt)
	{
		for(int i = 0; i < this.projekt.size(); i++)
		{
			if(this.projekt.get(i).equals(projekt))
			{
				return i;
			}
		}
		return 0;
	}
	
	public int getAssignmentIndex(String opgave)
	{
		for(int i = 0; i < this.opgave.size(); i++)
		{
			if(this.opgave.get(i).equals(opgave))
			{
				return i;
			}
		}
		return 0;
	}
	
	public Date getDate()
	{
		return new java.sql.Date(date.getValue().getTime());
	}

	public Date getDateTo()
	{
		return new java.sql.Date(dateTo.getValue().getTime());
	}
	
	public void setProjekt(List<String> list) {
		this.projekt = list;
		
		for(int i = 0; i < this.projekt.size(); i++)
		{
			project.addItem(this.projekt.get(i));
		}
	}
	
	public void setOpgave(List<String> list) {
		this.opgave = list;
		
		assignment.clear();
		
		for(int i = 0; i < this.opgave.size(); i++)
		{
			assignment.addItem(this.opgave.get(i));
		}
	}
	
	public void setLand(List<String> list) {
		this.land = list;
		
		country.clear();
		
		for(int i = 0; i < this.land.size(); i++)
		{
			country.addItem(this.land.get(i));
		}
	}
	
	public ListBox getProject() {
		return project;
	}

	public FlexTable getFlexTable()
	{
		return fTable;
	}

	public Button getSaveButton()
	{
		return save;
	}
	
	public Button getBackButton() {
		return back;
	}
	
	public String getCountry() {
		return country.getValue(country.getSelectedIndex());
	}

}
