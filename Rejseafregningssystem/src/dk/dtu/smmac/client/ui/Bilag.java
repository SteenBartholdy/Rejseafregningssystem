package dk.dtu.smmac.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.thirdparty.javascript.rhino.head.ast.FunctionNode.Form;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class Bilag extends Composite {
	
	final FormPanel form = new FormPanel();
	private VerticalPanel vPanel = new VerticalPanel();
	private static FlexTable fTable;
	private Button delete, cont;
	private Anchor addBilag;
	private int count = 0;
	private static List<Button> bList;
	private static List<HandlerRegistration> hList;
	private static List<FileUpload> fList;
	
	public Bilag()
	{
		initWidget(vPanel);
		
		fTable = new FlexTable();
		
		form.setAction("/myFormHandler");
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
	    form.setMethod(FormPanel.METHOD_POST);
	    
	    form.setWidget(fTable);
		
		bList = new ArrayList<Button>();
		hList = new ArrayList<HandlerRegistration>();
		fList = new ArrayList<FileUpload>();
		
		delete = new Button();
		delete.setText("Slet");

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
		fTable.setWidget(numRows, 2, addFileButton());
		fTable.setWidget(numRows, 4, addSletButton(numRows));
	}
	
	public static void deleteNewBilag(FlexTable flextable)
	{
		flextable.removeRow(flextable.getRowCount());
	}
	
	public static void deleteNewBilag(FlexTable flexTable, int i)
	{
		flexTable.removeRow(i);
		bList.remove(i);
		clickHandler(bList, hList);
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
	
	public Button getCont()
	{
		return cont;
	}
	
	public Button addSletButton(int row)
	{
		Button b = new Button();
		bList.add(b);
		hList.add(addClickHandlerDelete(b, row));
		
		b.setText("Slet");
		return b;
	}
	
	public FileUpload addFileButton()
	{
		FileUpload file = new FileUpload();
		fList.add(file);
		
		file.setName("Tilføj fil");
		return file;
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
	
	public static void clickHandler(List<Button> b, List<HandlerRegistration> h)
	{
		for (int i = 0; i <= hList.size(); i++){
			final int x = i;
			hList.get(x).removeHandler();
			hList.remove(x);
			hList.add(x, addClickHandlerDelete(b.get(x), x));
		}
	}
	public static HandlerRegistration addClickHandlerDelete(Button b, int i)
	{
		HandlerRegistration handler;
		final int x = i;
		handler = b.addClickHandler(new ClickHandler(){
					@Override
					public void onClick(ClickEvent event) {
						deleteNewBilag(fTable, x);
				}
		});
		return handler;
	}
}

	
