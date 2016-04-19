package dk.dtu.smmac.client.ui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Bilag extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private FlexTable fTable;
	private Button delete, upload;
	private Anchor addBilag;
	private Button[] bList, cList;
	private TextBox[] tList;
	
	public Bilag()
	{
		initWidget(vPanel);
		
		fTable = new FlexTable();
		
		delete = new Button();
		delete.setText("Slet");

		upload = new Button();
		upload.setText("Tilføj fil");
		
		bList = new Button[]{};
		cList = new Button[]{};
		tList = new TextBox[]{};
		
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
		
		fTable.setWidget(numRows, 0, addLabel("Bilag " + numRows));
		fTable.setWidget(numRows, 1, addTextBox());
		fTable.setWidget(numRows, 2, addFilButton());
		fTable.setWidget(numRows, 3, addSletButton());
		
		Window.alert("tList: " + tList.length);
		Window.alert("cList: " + tList.length);
		Window.alert("bList: " + tList.length);
	}
	
	public void deleteNewBilag(FlexTable flextable)
	{
		flextable.removeRow(flextable.getRowCount() - 1);
	}
	
	public Anchor getAddBilag()
	{
		return addBilag;
	}

	public FlexTable getFlexTable()
	{
		return fTable;
	}
	
	public Button[] getBList()
	{
		return bList;
	}
	
	public Button getDelete()
	{
		return delete;
	}
	
	public Button addSletButton()
	{
		Button b = new Button();
		b.setText("Slet");
		bList[bList.length] = b;
		return b;
	}
	
	public Button addFilButton()
	{
		Button c = new Button();
		c.setText("Tilføj fil");
		cList[cList.length] = c;
		return c;
	}
	
	public Label addLabel(String name)
	{
		Label l = new Label();
		l.setText(name);
		l.setSize("150px", "20px");
		return l;
	}
	
	public TextBox addTextBox()
	{
		TextBox t = new TextBox();
		t.setSize("500px", "20px");
		tList[tList.length] = t;
		return t;
	}
	
}
