package dk.dtu.smmac.client.ui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
	private int count = 0;
	
	public Bilag()
	{
		initWidget(vPanel);
		
		fTable = new FlexTable();
		
		delete = new Button();
		delete.setText("Slet");

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
		
		count ++;

		fTable.setWidget(numRows, 0, addLabel("Bilag: " + count));
		fTable.setWidget(numRows, 1, addTextBox());
		fTable.setWidget(numRows, 2, addFilButton());
		fTable.setWidget(numRows, 3, addSletButton(numRows));
	}
	
	public void deleteNewBilag(FlexTable flextable)
	{
		flextable.removeRow(flextable.getRowCount());
	}
	
	public void deleteNewBilag(FlexTable flextable, int row)
	{
		Window.alert(" " + row);
		
		if (row == flextable.getRowCount())
		{
			flextable.removeRow(row);
		}
		if (row == flextable.getRowCount()+1)
		{
			flextable.removeRow(row-1);
		}
		else flextable.removeRow(row);
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
	
	public Button addSletButton(int row)
	{
		Button b = new Button();
		final int a = row;
		
		b.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				deleteNewBilag(getFlexTable(), a);
			}
		});
		
		b.setText("Slet");
		return b;
	}
	
	public Button addFilButton()
	{
		Button c = new Button();
		c.setText("Tilføj fil");
		return c;
	}
	
	public Label addLabel(String name)
	{
		Label l = new Label();
		l.setText(name);
		return l;
	}
	
	public TextBox addTextBox()
	{
		TextBox t = new TextBox();
		t.setSize("500px", "20px");
		return t;
	}
	
}
