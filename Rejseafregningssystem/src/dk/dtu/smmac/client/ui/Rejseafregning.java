package dk.dtu.smmac.client.ui;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dk.dtu.smmac.shared.RejseDTO;
import dk.dtu.smmac.shared.RejseafregningDTO;

public class Rejseafregning extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable fTable;
	private Label date, dateTo, startTimeLabel, endTimeLabel;
	private DateLabel startDateLabel, endDateLabel;
	private ListBox startTime, endTime;
	private Anchor bilag, addTravel;
	private Button edit, delete, save;
	private RejseafregningDTO rejseafregning;

	public Rejseafregning()
	{
		initWidget(this.vPanel);

		fTable = new FlexTable();

		date = new Label("Dato:");

		startDateLabel = new DateLabel();
		endDateLabel = new DateLabel();

		dateTo = new Label("Til");

		startTimeLabel = new Label("Starttid:");
		endTimeLabel = new Label("Sluttid:");

		bilag = new Anchor();
		bilag.setText("Tilføj bilag");
		bilag.setStyleName("anchorStyle");

		addTravel = new Anchor();
		addTravel.setText("Tilføj rejse");
		addTravel.setStyleName("anchorStyle");

		save = new Button();
		save.setText("Fortsæt");
		save.setStyleName("alignButtomRight");

		//Skal have en changeHandler
		startTime = new ListBox();
		endTime = new ListBox();

		for(int i = 0; i<10; i++)
		{
			startTime.addItem("0" + i + ":00");
			endTime.addItem("0" + i + ":00");
		}
		for(int i = 10; i<24; i++)
		{
			startTime.addItem("" + i + ":00");
			endTime.addItem("" + i + ":00");
		}

		fTable.setWidget(0, 0, date);
		fTable.setWidget(0, 1, startDateLabel);
		fTable.setWidget(0, 2, dateTo);
		fTable.setWidget(0, 3, endDateLabel);
		fTable.setWidget(1, 0, startTimeLabel);
		fTable.setWidget(1, 1, startTime);
		fTable.setWidget(1, 2, endTimeLabel);
		fTable.setWidget(1, 3, endTime);
		fTable.setWidget(2, 0, addTravel);
		fTable.setWidget(2, 2, bilag);

		vPanel.setStyleName("margin");
		vPanel.add(fTable);
		vPanel.add(save);
	}

	public void setRejseafregning(RejseafregningDTO rejseafregning) {
		this.rejseafregning = rejseafregning;
	}

	public RejseafregningDTO getRejseafregning() {
		this.rejseafregning.setStartTid(getStartTime());
		this.rejseafregning.setSlutTid(getEndTime());

		return this.rejseafregning;
	}

	public Anchor getBilagButton()
	{
		return bilag;
	}

	public void setStartTime(int starttime)
	{
		startTime.setItemSelected(starttime, true);
	}

	public int getStartTime() {
		String str = startTime.getValue(startTime.getSelectedIndex());
		str = str.substring(0, 2);
		return Integer.parseInt(str);
	}

	public int getEndTime() {
		String str = endTime.getValue(endTime.getSelectedIndex());
		str = str.substring(0, 2);
		return Integer.parseInt(str);
	}

	public void setEndTime(int endtime)
	{
		endTime.setItemSelected(endtime, true);
	}

	public void addTravelSummary(RejseDTO rejse)
	{
		int numRows = fTable.getRowCount();

		Label l = new Label(rejse.getLand() + ", " + rejse.getDatoFra() + " til " + rejse.getDatoTil() + ", " + rejse.getProjekt() + ", " + rejse.getOpgave());

		fTable.setWidget(numRows, 0, l);
	}

	public Anchor getAddTravelAnchor()
	{
		return addTravel;
	}

	public Button getEditButton()
	{
		return edit;
	}

	public Button getDeleteButton()
	{
		return delete;
	}

	public Button getSaveButton() 
	{
		return save;
	}

	public void setStartDateLabel(Date dato) {

		if (dato.compareTo(new java.sql.Date(startDateLabel.getValue().getTime())) < 0) {
			startDateLabel.setValue(new java.util.Date(dato.getTime()));
		}

	}

	public void setEndDateLabel(Date dato) {

	}

}
