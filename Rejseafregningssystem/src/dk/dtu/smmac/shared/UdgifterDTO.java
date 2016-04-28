package dk.dtu.smmac.shared;

import java.io.Serializable;

public class UdgifterDTO implements Serializable {

	private static final long serialVersionUID = 9L;

	private int bilagsNummer;
	private int nummer;
	private String udgiftType;
	private String udgiftDato;
	private int udgiftBeloeb;
	
	public UdgifterDTO() {}
	
	public UdgifterDTO(int bilagsNummer, int nummer, String udgiftType, String udgiftDato, int udgiftBeloeb)
	{
		this.bilagsNummer = bilagsNummer;
		this.nummer = nummer;
		this.udgiftType = udgiftType;
		this.udgiftDato = udgiftDato;
		this.udgiftBeloeb = udgiftBeloeb;
	}

	public int getBilagsNummer() 
	{
		return bilagsNummer;
	}

	public int getNummer() 
	{
		return nummer;
	}

	public String getUdgiftType() 
	{
		return udgiftType;
	}

	public String getUdgiftDato() 
	{
		return udgiftDato;
	}

	public int getUdgiftBeloeb() 
	{
		return udgiftBeloeb;
	}

	public void setBilagsNummer(int bilagsNummer) 
	{
		this.bilagsNummer = bilagsNummer;
	}

	public void setNummer(int nummer) 
	{
		this.nummer = nummer;
	}

	public void setUdgiftType(String udgiftType) 
	{
		this.udgiftType = udgiftType;
	}

	public void setUdgiftDato(String udgiftDato) 
	{
		this.udgiftDato = udgiftDato;
	}

	public void setUdgiftBeloeb(int udgiftBeloeb) 
	{
		this.udgiftBeloeb = udgiftBeloeb;
	}
	
	
	
}
