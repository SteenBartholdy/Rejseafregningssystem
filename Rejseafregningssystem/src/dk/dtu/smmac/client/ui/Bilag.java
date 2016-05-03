package dk.dtu.smmac.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.thirdparty.javascript.rhino.head.ast.FunctionNode.Form;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class Bilag extends Composite {
	
	private final FormPanel form = new FormPanel();
	private VerticalPanel vPanel = new VerticalPanel();
	private static FlexTable fTable;
	private Button cont;
	private Anchor addBilag;
	private static List<Button> bList;
	private static List<HandlerRegistration> hList;
	private static List<FileUpload> fList;
	private static List<TextBox> tList;
	private static List<Label> lList;
	
	public Bilag()
	{
		initWidget(vPanel);
		
		fTable = new FlexTable();

		form.setEncoding(FormPanel.ENCODING_MULTIPART);
	    form.setMethod(FormPanel.METHOD_POST);
	    form.setSize("100%", "100%");
	    
	    form.setWidget(fTable);
		
		bList = new ArrayList<Button>();
		hList = new ArrayList<HandlerRegistration>();
		fList = new ArrayList<FileUpload>();
		tList = new ArrayList<TextBox>();
		lList = new ArrayList<Label>();

		addBilag = new Anchor();
		addBilag.setText("Tilføj bilag");
		
		cont = new Button();
		cont.setText("Forstæt");
		cont.setStyleName("marginTop");
		
		fTable.setStyleName("flextable");
		
		vPanel.setStyleName("margin");
		
		vPanel.add(fTable);
		vPanel.add(addBilag);
		vPanel.add(cont);
	}
	
	public void addNewBilag(FlexTable flexTable)
	{
		int numRows = flexTable.getRowCount();

		flexTable.setWidget(numRows, 0, addLabel());
		flexTable.setWidget(numRows, 1, addTextBox());
		flexTable.setWidget(numRows, 2, addFileButton());
		flexTable.setWidget(numRows, 4, addSletButton(numRows));
	}
	
	public void addBilagRow(FlexTable flexTable, String s)
	{
		int numRows = flexTable.getRowCount();
		
		TextBox tx = new TextBox();
		tx.setText(s);

		flexTable.setWidget(numRows, 0, addLabel());
		flexTable.setWidget(numRows, 1, tx);
		flexTable.setWidget(numRows, 2, addFileButton());
		flexTable.setWidget(numRows, 4, addSletButton(numRows));
	}
	
	public static void deleteNewBilag(FlexTable flextable)
	{
		flextable.removeRow(flextable.getRowCount());
	}
	
	public static void deleteNewBilag(FlexTable flexTable, int i)
	{
		flexTable.removeRow(i);
		bList.remove(i);
		lList.remove(i);
		updateLabels(lList);
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
	
	public static List<TextBox> getTList()
	{
		return tList;
	}
	
	public Button getCont()
	{
		return cont;
	}
	
	public Label addLabel()
	{
		Label l = new Label();
		lList.add(l);
		l.setText("Bilag: " + lList.size());
		
		return l;
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
	
	public TextBox addTextBox()
	{
		TextBox t = new TextBox();
		tList.add(t);
		
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
	
	public static void updateLabels(List<Label> l)
	{
		int x;
		for (int i = 0; i < l.size(); i++){
			x = i+1;
			l.get(i).setText("Bilag: " + x);
		}
	}
}