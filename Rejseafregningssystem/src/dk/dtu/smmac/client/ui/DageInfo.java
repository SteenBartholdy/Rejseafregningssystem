package dk.dtu.smmac.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
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
	private boolean aftensmad;
	private boolean nattill;
	private boolean refunderes;
	private boolean afbrudtRejse;
	
	public void RejseDag(String dato, String land, boolean morgenmad, boolean frokost, boolean aftensmad, boolean nattill, boolean refunderes, boolean afbrudtRejse)
	{
		this.dato = dato;
		this.land = land;
		this.morgenmad = morgenmad;
		this.frokost = frokost;
		this.aftensmad = aftensmad;
		this.nattill = nattill;
		this.refunderes = refunderes;
		this.afbrudtRejse = afbrudtRejse;
	}
	
		public String getRejseDagDato()
		{
			return dato;
		}
	
		public String getRejseDagLand()
		{
			return land;
		}
	}
	
	ArrayList<RejseDag> REJSEDAG = new ArrayList<RejseDag>();
	
//	RejseDag dag = new RejseDag(dato, land, morgenmad, frokost, aftensmad, nattill, refunderes, afbrudtRejse);
//	REJSEDAG.add(new RejseDag(dato, land, morgenmad, frokost, aftensmad, nattill, refunderes, afbrudtRejse));
	
//	@SuppressWarnings("deprecation")
//	private static final List<RejseDag> REJSEDAG = Array.asList(
//		new RejseDag("13-05-2015", "Burkinafaso", true, true, true, false, false, false));
		
		//new RejseDag("14-05-2015", "Burkinafaso", true, false, true, false, false, false),
		//new RejseDag("15-05-2015", "Burkinafaso", false, false, true, false, false, false),
		//new RejseDag("16-05-2015", "Burkinafaso", true, true, true, false, false, false));
	public DageInfo()
	{
		initWidget(this.vPanel);
		
		table = new CellTable();
		table.setPageSize(2);
		
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
		
		vPanel.add(table);
		vPanel.add(btnAnnuller);
	
	}

	public Button getbtnAnnullerDageInfo()
	{
		return btnAnnuller;
	}
}
