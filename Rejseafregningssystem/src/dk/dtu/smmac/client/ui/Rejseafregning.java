package dk.dtu.smmac.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Rejseafregning extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable fTable;
	private Label date, dateTo;
	private DateLabel dateLabel;
	
	public Rejseafregning()
	{
		initWidget(this.vPanel);
		
		fTable = new FlexTable();
		
		date = new Label("Dato:");
		
		dateLabel = new DateLabel();
		
		dateTo = new Label("Til");
		
		fTable.setWidget(0, 0, date);
		fTable.setWidget(0, 1, dateLabel);
		fTable.setWidget(0, 2, dateTo);
		
		vPanel.add(fTable);
	}
}
