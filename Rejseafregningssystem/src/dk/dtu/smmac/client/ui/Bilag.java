package dk.dtu.smmac.client.ui;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Bilag extends Composite {

	// private CellTable<Bilag> table;
	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable fTable;
	private Button delete;
	private Anchor addBilag;
	private Label bilagName;
	private TextBox info;
	
	public Bilag()
	{
		initWidget(vPanel);
		
		fTable = new FlexTable();
		
		bilagName = new Label("Bilag 1");
		
		info = new TextBox();
		
		delete = new Button();
		delete.setText("Slet");
		
		addBilag = new Anchor();
		addBilag.setText("Tilføj bilag");
		
		fTable.setWidget(0, 0, bilagName);
		fTable.setWidget(0, 1, info);
		fTable.setWidget(0, 2, delete);
		
		fTable.setStyleName("flextable");
		
		vPanel.setStyleName("margin");
		
		vPanel.add(fTable);
		vPanel.add(addBilag);			
	}
	
}
