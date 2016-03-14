package dk.dtu.smmac.shared;

import java.io.Serializable;

public class AnsatteDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int ID, postnummer, telefon;
	private String fornavn, efternavn, afdeling, adresse, email;
	private boolean anviser, godkender;
	
	public AnsatteDTO(){
		
	}
	
	public AnsatteDTO(int ID, String fornavn, String efternavn, String email, boolean anviser, boolean godkender){
		super();
		this.ID = ID;
		this.fornavn = fornavn;
		this.efternavn = efternavn;
		this.email = email;
		this.anviser = anviser;
		this.godkender = godkender;
	}
	
	public AnsatteDTO(int ID, int postnr, int tlf, String fornavn, String efternavn, String afd, String add, String email, boolean anviser, boolean godkender){
		super();
		this.ID = ID;
		this.postnummer = postnr;
		this.telefon = tlf;
		this.fornavn = fornavn;
		this.efternavn = efternavn;
		this.afdeling = afd;
		this.adresse = add;
		this.email = email;
		this.anviser = anviser;
		this.godkender = godkender;
	}
	
	public void setPostnr(int postnr){
		this.postnummer = postnr;
	}
	
	public int getPostnr(){
		return this.postnummer;
	}

	public void setFornavn(String fornavn){
		this.fornavn = fornavn;
	}
	
	public String getFornavn(){
		return this.fornavn;
	}
	
	public void setEfternavn(String efternavn){
		this.efternavn = efternavn;
	}
	
	public String getEfternavn(){
		return this.efternavn;
	}
	
	
	
	
	
	
	
}
