package dk.dtu.smmac.client.ui;

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
	private Button delete, edit, upload;
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
		
		edit = new Button();
		edit.setText("Redigér");
		
		upload = new Button();
		upload.setText("Tilføj fil");
		
		addBilag = new Anchor();
		addBilag.setText("Tilføj bilag");
		
		fTable.setStyleName("flextable");
		
		vPanel.setStyleName("margin");
		
		vPanel.add(fTable);
		vPanel.add(addBilag);			
	}
	
	public void addNewBilag(FlexTable flextable)
	{
		int numRows = flextable.getRowCount();
		
		fTable.setWidget(numRows, 0, bilagName);
		fTable.setWidget(numRows, 1, info);
		fTable.setWidget(numRows, 2, upload);
		fTable.setWidget(numRows, 3, edit);
		fTable.setWidget(numRows, 4, delete);
	}
	
	public void deleteNewBilag(FlexTable flextable)
	{
		flextable.removeRow(flextable.getRowCount() - 1);
		// this.addBilag.setVisible(true);
	}
	
	public Anchor getAddBilag()
	{
		return addBilag;
	}

	public FlexTable getFlexTable()
	{
		return fTable;
	}
	
	public Button getDelete()
	{
		return delete;
	}
	
}
