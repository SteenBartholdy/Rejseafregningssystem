package dk.dtu.smmac.shared;

import java.io.Serializable;
import java.sql.Date;

public class RejseDTO implements Serializable
{
	private static final long serialVersionUID = 6L;

	private int RejseID;
	private int Nummer;
	private String Land;
	private Date DatoFra;
	private Date DatoTil;
	private String projekt;
	private String opgave;
	
	public RejseDTO() {}
	
	public RejseDTO(int RejseID, int Nummer, String Land, Date DatoFra, Date DatoTil, String Projekt, String Opgave)
	{
		this.RejseID = RejseID;
		this.Nummer = Nummer;
		this.Land = Land;
		this.DatoFra = DatoFra;
		this.DatoTil = DatoTil;
		this.projekt = Projekt;
		this.opgave = Opgave;
	}
	
	public RejseDTO(int id, int ansatId) {
		this.RejseID = id;
		this.Nummer = ansatId;
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
	
	public void setLand(String land) {
		this.Land = land;
	}
	
	public Date getDatoFra()
	{
		return this.DatoFra;
	}
	
	public void setDatoFra(Date dato) {
		this.DatoFra = dato;
	}

	public Date getDatoTil()
	{
		return this.DatoTil;
	}
	
	public void setDatoTil(Date dato) {
		this.DatoTil = dato;
	}
	
	public String getProjekt() {
		return this.projekt;
	}
	
	public void setProjekt(String projekt) {
		this.projekt = projekt;
	}
	
	public String getOpgave() {
		return this.opgave;
	}
	
	public void setOpgave(String opgave) {
		this.opgave = opgave;
	}
}
