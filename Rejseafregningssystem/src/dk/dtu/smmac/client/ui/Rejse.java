package dk.dtu.smmac.client.ui;

import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import dk.dtu.smmac.shared.RejseDTO;

public class Rejse extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable fTable;
	private Label countryL, dateL, dateToL, projectL, assignmentL, shareL;
	private ListBox country, project, assignment, shareList;
	private DateBox date, dateTo;
	private DateTimeFormat dateFormat;
	private TextBox shareT;
	private Button save;
	private List<String> projekt, opgave;
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
		country.addItem("Danmark");

		project = new ListBox();
		assignment = new ListBox();
		shareL = new Label("Andel: ");
		shareT = new TextBox();
		shareT.setPixelSize(60, 15);
		shareList = new ListBox();
		shareList.addItem("%");
		shareList.addItem("kr");
		
		save = new Button("Fortsæt");

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
		fTable.setWidget(2, 4, shareL);
		fTable.setWidget(2, 5, shareT);
		fTable.setWidget(2, 6, shareList);

		setShareForProject("100", "%");

		fTable.setStyleName("flextable");

		vPanel.setStyleName("margin");
		vPanel.add(fTable);
		save.addStyleName("alignButtomRight");
		vPanel.add(save);
	}
	
	public void setRejse(RejseDTO rejse) {
		
	}
	
	public RejseDTO getRejse() {
		
		
		
		return this.rejse;
	}

	public void addCountry(String country)
	{
		this.country.addItem(country);
	}

	public void addProject (String project)
	{
		this.project.addItem(project);
	}

	public void addAssignment (String assignment)
	{
		this.assignment.addItem(assignment);
	}

	public String getDate()
	{
		return dateFormat.format(date.getValue());
	}

	public String getDateTo()
	{
		return dateFormat.format(dateTo.getValue());
	}
	
	public void setProjekt(List<String> list) {
		this.projekt = list;
		
		for(int i = 0; i < this.projekt.size(); i++)
		{
			project.addItem(this.projekt.get(i));
		}
	}
	
	public ListBox getProject() {
		return project;
	}
	
	public int getProjectIndex(String pk)
	{
		for(int i = 0; i < projekt.size(); i++)
		{
			if(projekt.get(i).equals(pk))
			{
				return i;
			}
		}
		return 0;
	}

	public void setShareForProject(String share, String type)
	{
		shareT.setText(share);

		if(type.equals("kr"))
		{
			shareList.setItemSelected(1, true);
		}
		else if(type.equals("%"))
		{
			shareList.setItemSelected(0, true);
		}
	}

	public FlexTable getFlexTable()
	{
		return fTable;
	}

	public Button getSaveButton()
	{
		return save;
	}
	
	public String getCountry() {
		return country.getValue(country.getSelectedIndex());
	}

}
