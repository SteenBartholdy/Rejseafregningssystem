package dk.dtu.smmac.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;





//http://www.mytechtip.com/2010/11/gwt-celltable-example-using_8168.html

public class DageInfo extends Composite 
{
	private CellTable<RejseDag> table;
	private VerticalPanel vPanel = new VerticalPanel();
	private Button btnAnnuller;


	private class RejseDag
	{
		private String dato;
		private String land;
		private boolean morgenmad;
		private boolean frokost;
		//private boolean aftensmad;
		//private boolean nattill;
		//private boolean refunderes;
		//private boolean afbrudtRejse;

		@SuppressWarnings("unused")
		public RejseDag(String dato, String land)
		{
			this.dato = dato;
			this.land = land;
		}

		public String getRejseDagDato()
		{
			return dato;
		}

		public String getRejseDagLand()
		{
			return land;
		}

		public Boolean getRejseDagMorgenmad()
		{
			return morgenmad;
		}

		public void setRejseDagMorgenmad(boolean morgenmad)
		{
			this.morgenmad = morgenmad; 
		}

		public Boolean getRejseDagFrokost()
		{
			return frokost;
		}

		public void setRejseDagFrokost(boolean frokost)
		{
			this.frokost = frokost; 
		}
	}

	public void rejsedagetest()
	{
	List<RejseDag> rejsedage = new ArrayList<RejseDag>();
	RejseDag r1 = new RejseDag("14-03-2015", "Holland");
	RejseDag r2 = new RejseDag("15-03-2015", "Holland");
	
	rejsedage.add(r1);
	rejsedage.add(r2);	
	}


	//	ArrayList<RejseDag> REJSEDAG = new ArrayList<RejseDag>();

	//	RejseDag dag = new RejseDag(dato, land, morgenmad, frokost, aftensmad, nattill, refunderes, afbrudtRejse);
	//	REJSEDAG.add(new RejseDag(dato, land, morgenmad, frokost, aftensmad, nattill, refunderes, afbrudtRejse));

	/*	@SuppressWarnings("deprecation")
	private static final List<RejseDag> REJSEDAG = Array.asList(
		new RejseDag("13-05-2015", "Burkinafaso"),
		new RejseDag("14-05-2015", "Burkinafaso"),
		new RejseDag("15-05-2015", "Burkinafaso"),
		new RejseDag("16-05-2015", "Burkinafaso"));
	 */

	public DageInfo()
	{
		initWidget(this.vPanel);

		table = new CellTable<RejseDag>();
		table.setPageSize(4);

		rejsedagetest();
		
		btnAnnuller = new Button("Annuller");

		TextColumn<RejseDag> datoColumn = new TextColumn<RejseDag>()
		{
			@Override
			public String getValue(RejseDag object) 
			{
				return object.getRejseDagDato();
			}
		};
		table.addColumn(datoColumn, "Dato:");

		TextColumn<RejseDag> landColumn = new TextColumn<RejseDag>()
		{
			@Override
			public String getValue(RejseDag object) 
			{
				return object.getRejseDagLand();
			}
		};
		table.addColumn(landColumn, "Land:");

		Column<RejseDag, Boolean> morgenmadColumn = new Column<RejseDag, Boolean>(new CheckboxCell()) { 
			@Override 
			public Boolean getValue(RejseDag object) { 
				return object.getRejseDagMorgenmad(); 
			} 
		}; 

		morgenmadColumn.setFieldUpdater(new FieldUpdater<RejseDag, Boolean>() { 
			@Override
			public void update(int index, RejseDag object, Boolean value) {
				object.setRejseDagMorgenmad(value); 
			} 
		}); 

		table.addColumn(morgenmadColumn, "Morgenmad:");

		Column<RejseDag, Boolean> frokostColumn = new Column<RejseDag, Boolean>(new CheckboxCell()) { 
			@Override 
			public Boolean getValue(RejseDag object) { 
				return object.getRejseDagFrokost(); 
			} 
		}; 

		frokostColumn.setFieldUpdater(new FieldUpdater<RejseDag, Boolean>() { 
			@Override
			public void update(int index, RejseDag object, Boolean value) {
				object.setRejseDagFrokost(value); 
			} 
		}); 

		table.addColumn(frokostColumn, "Frokost:");

		vPanel.add(table);
		vPanel.add(btnAnnuller);

		vPanel.setStyleName("margin");
	

	}

	public Button getbtnAnnullerDageInfo()
	{
		return btnAnnuller;
	}
}
