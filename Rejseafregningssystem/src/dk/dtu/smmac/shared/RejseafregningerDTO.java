package dk.dtu.smmac.shared;

import java.io.Serializable;
import java.sql.Date;

public class RejseafregningerDTO implements Serializable {

	private static final long serialVersionUID = 11L;

	private int nr, startTid, slutTid;
	private String land;
	private Date datoFra, datoTil;
	private String status;
	
	public RejseafregningerDTO() {
		super();
	}
	
	public RejseafregningerDTO(int nummer, int startTid, int sluttid, String land, Date datoFra, Date datoTil, String status) {
		super();
		this.nr = nummer;
		this.startTid = startTid;
		this.slutTid = sluttid;
		this.land = land;
		this.datoFra = datoFra;
		this.datoTil = datoTil;
		this.status = status;
	}
	
	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public int getStartTid() {
		return startTid;
	}

	public void setStartTid(int startTid) {
		this.startTid = startTid;
	}

	public int getSlutTid() {
		return slutTid;
	}

	public void setSlutTid(int slutTid) {
		this.slutTid = slutTid;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public Date getDatoFra() {
		return datoFra;
	}

	public void setDatoFra(Date datoFra) {
		this.datoFra = datoFra;
	}

	public Date getDatoTil() {
		return datoTil;
	}

	public void setDatoTil(Date datoTil) {
		this.datoTil = datoTil;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
