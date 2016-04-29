package dk.dtu.smmac.shared;

import java.io.Serializable;

public class UdgifterDTO implements Serializable {

	private static final long serialVersionUID = 9L;

	private String bilagsNummer;
	private int nummer;
	private String udgiftType;
	private String udgiftDato;
	private String udgiftBeloeb;
	
	public UdgifterDTO() {}
	
	public UdgifterDTO(String bilagsNummer, int nummer, String udgiftType, String udgiftDato, String udgiftBeloeb)
	{
		this.bilagsNummer = bilagsNummer;
		this.nummer = nummer;
		this.udgiftType = udgiftType;
		this.udgiftDato = udgiftDato;
		this.udgiftBeloeb = udgiftBeloeb;
	}

	public String getBilagsNummer() 
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

	public String getUdgiftBeloeb() 
	{
		return udgiftBeloeb;
	}

	public void setBilagsNummer(String bilagsNummer) 
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

	public void setUdgiftBeloeb(String udgiftBeloeb) 
	{
		this.udgiftBeloeb = udgiftBeloeb;
	}
	
	
	
}
