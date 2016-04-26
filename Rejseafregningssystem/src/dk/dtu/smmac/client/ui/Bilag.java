package dk.dtu.smmac.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class Bilag extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private static FlexTable fTable;
	private Button delete, upload, cont;
	private Anchor addBilag;
	private int count = 0;
	private static List<Button> bList;
	private static List<HandlerRegistration> hList;
	
	public Bilag()
	{
		initWidget(vPanel);
		
		fTable = new FlexTable();
		
		bList = new ArrayList<Button>();
		hList = new ArrayList<HandlerRegistration>();
		
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
	
	public static void deleteNewBilag(FlexTable flexTable, int i)
	{
		flexTable.removeRow(i);
		bList.remove(i);
		hList.remove(i);
		clickHandler(bList, getFlexTable());
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
		bList.add(b);
		hList.add(addClickHandler(b, row));
		
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
	
	public List<Button> getBList()
	{
		return bList;
	}
	
	public static void clickHandler(List<Button> b, FlexTable fTable)
	{
		for (int i = 0; i < b.size(); i++){
			hList.get(i).removeHandler();
		}
		for (int i = 0; i < b.size(); i++){
			final int x = i;
			b.get(i).addClickHandler(new ClickHandler(){
				@Override
				public void onClick(ClickEvent event) {
					deleteNewBilag(getFlexTable(), x);
			}
			});
		}
	}
	public static HandlerRegistration addClickHandler(Button b, int i)
	{
		HandlerRegistration handler;
		final int x = i;
		handler = b.addClickHandler(new ClickHandler(){
					@Override
					public void onClick(ClickEvent event) {
						deleteNewBilag(getFlexTable(), x);
				}
		});
		return handler;
	}
}

	
