package dk.dtu.smmac.shared;

import java.io.Serializable;
import java.sql.Date;

public class RejseDTO implements Serializable
{
	private static final long serialVersionUID = 6L;

	private int RejseID;
	private int Nummer;
	private String Land;
	private String By;
	private Date DatoFra;
	private Date DatoTil;
	
	public RejseDTO() {}
	
	public RejseDTO(int RejseID, int Nummer, String Land, String By, Date DatoFra, Date DatoTil)
	{
		this.RejseID = RejseID;
		this.Nummer = Nummer;
		this.Land = Land;
		this.By = By;
		this.DatoFra = DatoFra;
		this.DatoTil = DatoTil;
	}

	public int getRejseID()
	{
		return this.RejseID;
	}

	public int getNummer()
	{
		return this.Nummer;
	}

	public String getLand()
	{
		return this.Land;
	}

	public String getBy()
	{
		return this.By;
	}
	
	public Date getDatoFra()
	{
		return this.DatoFra;
	}

	public Date getDatoTil()
	{
		return this.DatoTil;
	}
}
