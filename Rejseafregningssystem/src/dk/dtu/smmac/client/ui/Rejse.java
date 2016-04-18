package dk.dtu.smmac.client.ui;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

public class Rejse extends Composite {
	
	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable fTable;
	private Label countryL, dateL, dateToL, projectL, assignmentL;
	private ListBox country, project, assignment;
	private DateBox date, dateTo;
	private DateTimeFormat dateFormat;
	private Anchor addProject;
	
	public Rejse()
	{
		initWidget(vPanel);
		
		fTable = new FlexTable();
		
		countryL = new Label("Land:");
		dateL = new Label("Dato:");
		dateToL = new Label("til");
		dateToL.setStyleName("center");
		
		projectL = new Label("Projekt");
		assignmentL = new Label("Opgave");
		
		addProject = new Anchor();
		addProject.setText("Tilføj flere opgaver/projekter");
		
		date = new DateBox();
		date.setPixelSize(80, 15);
		dateTo = new DateBox();
		dateTo.setPixelSize(80, 15);
		
		country = new ListBox();
		country.addItem("Danmark");
		
		project = new ListBox();
		assignment = new ListBox();
		
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
		
		vPanel.add(addProject);
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
	
	public Anchor getAddProjectAnchor()
	{
		return addProject;
	}
	
	public String getDate()
	{
		return dateFormat.format(date.getValue());
	}
	
	public String getDateTo()
	{
		return dateFormat.format(dateTo.getValue());
	}

}
