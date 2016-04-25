package dk.dtu.smmac.client.ui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import java.util.ArrayList;
import java.util.List;

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
	private static FlexTable fTable;
	private Button delete, upload, cont;
	private Anchor addBilag;
	private int count = 0;
	private static Button[] bList;
	
	public Bilag()
	{
		initWidget(vPanel);
		
		fTable = new FlexTable();
		
		bList = new Button[]{};
		
		delete = new Button();
		delete.setText("Slet");

		upload = new Button();
		upload.setText("Tilføj fil");

		addBilag = new Anchor();
		addBilag.setText("Tilføj bilag");
		
		cont = new Button();
		cont.setText("Forstæt");
		
		fTable.setStyleName("flextable");
		
		vPanel.setStyleName("margin");
		
		vPanel.add(fTable);
		vPanel.add(addBilag);
		vPanel.add(cont);
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
	
	public static void deleteNewBilag(FlexTable flextable)
	{
		flextable.removeRow(flextable.getRowCount());
	}
	
	public static void deleteNewBilag(FlexTable flextable, Button[] b)
	{
		for (int i = 0; i < fTable.getRowCount(); i++){
			final int x = i;
				b[i].addClickHandler(new ClickHandler(){
					public void onClick(ClickEvent event) {
						deleteNewBilag(getFlexTable());
					}
				});
		}
	}
	
	public Anchor getAddBilag()
	{
		return addBilag;
	}

	public static FlexTable getFlexTable()
	{
		return fTable;
	}
	
	public Button getDelete()
	{
		return delete;
	}
	
	public Button getCont()
	{
		return cont;
	}
	
	public Button addSletButton(int row)
	{
		Button b = new Button();
		bList[bList.length] = b;

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
	
	public Button[] getBList()
	{
		return bList;
	}
}
	
