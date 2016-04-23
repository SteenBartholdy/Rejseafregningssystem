package dk.dtu.smmac.shared;

import java.io.Serializable;

public class DageInfoDTO implements Serializable {

	private static final long serialVersionUID = 7L;
	
	private int dagID;
	private int nummer;
	private boolean morgenmad;
	private boolean frokost;
	private boolean aftensmad;
	private boolean nattill;
	private boolean rejseAfbrudt;
	private boolean udokNat;
	private boolean refunderes;
	
	public DageInfoDTO() {}
	
	public DageInfoDTO(int dagID, int nummer, boolean morgenmad, boolean frokost, boolean aftensmad, boolean nattill, boolean rejseAfbrudt, boolean udokNat, boolean refunderes)
	{
		this.dagID = dagID;
		this.nummer = nummer;
		this.morgenmad = morgenmad;
		this.frokost = frokost;
		this.aftensmad = aftensmad;
		this.nattill = nattill;
		this.rejseAfbrudt = rejseAfbrudt;
		this.udokNat = udokNat;
		this.refunderes = refunderes;
	}

	public int getDagID()
	{
		return this.dagID;
	}
	
	public int getNummer()
	{
		return this.nummer;
	}
	
	public boolean getMorgenmad()
	{
		return this.morgenmad;
	}
	
	public boolean getFrokost()
	{
		return this.frokost;
	}
	
	public boolean getAftensmad()
	{
		return this.aftensmad;
	}

	public boolean getNattill()
	{
		return this.nattill;
	}

	public boolean getRejseAfbrudt()
	{
		return this.rejseAfbrudt;
	}

	public boolean getUdokNat()
	{
		return this.udokNat;
	}

	public boolean getRefunderes()
	{
		return this.refunderes;
	}
}
