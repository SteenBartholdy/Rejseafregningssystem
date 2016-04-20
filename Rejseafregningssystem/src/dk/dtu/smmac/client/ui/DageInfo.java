package dk.dtu.smmac.client.ui;

import java.util.List;
import java.util.Arrays;

import com.google.gwt.user.client.ui.Composite;

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
	@SuppressWarnings("deprecation")
	private static final List<RejseDag> REJSEDAG = Array.asList(
			)
	
}
