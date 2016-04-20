package dk.dtu.smmac.client.ui;

import java.util.ArrayList;
import java.util.List;


import com.google.gwt.user.client.ui.Composite;

//http://www.mytechtip.com/2010/11/gwt-celltable-example-using_8168.html

public class DageInfo extends Composite 
{
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
	
}
