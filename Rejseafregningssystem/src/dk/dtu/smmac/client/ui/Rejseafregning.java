package dk.dtu.smmac.client.ui;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Rejseafregning extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable fTable;
	private Label date, dateTo, startTimeLabel, endTimeLabel;
	private DateLabel startDateLabel, endDateLabel;
	private ListBox startTime, endTime;
	private Anchor bilag;
	
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
		fTable.setWidget(2, 0, bilag);
		
		vPanel.setStyleName("margin");
		vPanel.add(fTable);
	}
	
	public Anchor getBilagButton()
	{
		return bilag;
	}
}
