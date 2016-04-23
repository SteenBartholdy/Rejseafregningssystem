package dk.dtu.smmac.client.ui;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

public class Rejse extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable fTable;
	private Label countryL, dateL, dateToL, projectL, assignmentL, projectL2, assignmentL2, shareL, shareL2;
	private ListBox country, project, assignment, project2, assignment2, shareList, shareList2;
	private DateBox date, dateTo;
	private DateTimeFormat dateFormat;
	private Anchor addProject;
	private TextBox shareT, shareT2;
	private Button save, deleteProject;

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
		projectL2 = new Label("Projekt: ");
		assignmentL2 = new Label("Opgave: ");

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
		project2 = new ListBox();
		assignment2 = new ListBox();

		shareL = new Label("Andel: ");
		shareT = new TextBox();
		shareT.setPixelSize(60, 15);
		shareList = new ListBox();
		shareList.addItem("%");
		shareList.addItem("kr");

		shareL2 = new Label("Andel: ");
		shareT2 = new TextBox();
		shareT2.setPixelSize(60, 15);
		shareList2 = new ListBox();
		shareList2.addItem("%");
		shareList2.addItem("kr");
		
		deleteProject = new Button();
		deleteProject.setText("Slet projekt");
		
		save = new Button("Fortsæt");

		dateFormat = DateTimeFormat.getFormat("dd/MM-yyyy");

		date.setFormat(new DateBox.DefaultFormat(dateFormat));
		dateTo.setFormat(new DateBox.DefaultFormat(dateFormat));

		fTable.setWidget(0, 7, save);
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

		//SKAL IKKE GØRE SÅDAN HER. HENTES FRA DATABASE HVIS DER ER LAVET NOGET ANDET
		setShareForProject1("100", "%");

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

	public void addNewProject(FlexTable flextable)
	{
		int numRows = flextable.getRowCount();

		fTable.setWidget(numRows, 0, projectL2);
		fTable.setWidget(numRows, 1, project2);
		fTable.setWidget(numRows, 2, assignmentL2);
		fTable.setWidget(numRows, 3, assignment2);
		fTable.setWidget(numRows, 4, shareL2);
		fTable.setWidget(numRows, 5, shareT2);
		fTable.setWidget(numRows, 6, shareList2);
		fTable.setWidget(numRows, 7, deleteProject);
		this.addProject.setVisible(false);
	}

	public void deleteNewProject(FlexTable flextable)
	{
		int numRows = flextable.getRowCount();

		if (numRows > 1) {
			flextable.removeRow(numRows - 1);
		}
		
		this.addProject.setVisible(true);
	}

	public void setShareForProject1(String share, String type)
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

	public void setShareForProject2(String share, String type)
	{
		shareT2.setText(share);
		if(type.equals("kr"))
		{
			shareList2.setItemSelected(1, true);
		}
		else if (type.equals("%"))
		{
			shareList2.setItemSelected(0, true);
		}
	}

	public FlexTable getFlexTable()
	{
		return fTable;
	}
	
	public Button getDeleteProjectButton()
	{
		return deleteProject;
	}

	public Button getSaveButton()
	{
		return save;
	}

}
