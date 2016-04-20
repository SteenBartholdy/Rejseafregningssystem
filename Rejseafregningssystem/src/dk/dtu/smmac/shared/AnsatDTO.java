package dk.dtu.smmac.shared;

import java.io.Serializable;

public class AnsatDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int ID, postnummer, telefon;
	private String fornavn, efternavn, afdeling, vejnavn, husnr, etage, doer, email;
	private boolean anviser, godkender;
	
	public AnsatDTO(){
		
	}
	
	public AnsatDTO(int ID, String fornavn, String efternavn, String email, boolean anviser, boolean godkender){
		super();
		this.ID = ID;
		this.fornavn = fornavn;
		this.efternavn = efternavn;
		this.email = email;
		this.anviser = anviser;
		this.godkender = godkender;
	}
	
	public AnsatDTO(int ID, int postnr, int tlf, String fornavn, String efternavn, String afd, String vejnavn, String husnr, String etage, String doer, String email, boolean anviser, boolean godkender){
		super();
		this.ID = ID;
		this.postnummer = postnr;
		this.telefon = tlf;
		this.fornavn = fornavn;
		this.efternavn = efternavn;
		this.afdeling = afd;
		this.vejnavn = vejnavn;
		this.husnr = husnr;
		this.etage = etage;
		this.doer = doer;
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
	
	public void setTlf(int tlf){
		this.telefon = tlf;
	}
	
	public int getTlf(){
		return this.telefon;
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
	
	public void setAfdeling(String afd){
		this.afdeling = afd;
	}
	
	public String getAfdeling(){
		return this.afdeling;
	}
	
	public void setVejnavn(String vej){
		this.vejnavn = vej;
	}
	
	public String getVejnavn(){
		return this.vejnavn;
	}
	
	public void setHusnr(String husnr){
		this.husnr = husnr;
	}
	
	public String gethusnr(){
		return this.husnr;
	}
	
	public void setEtage(String etage){
		this.etage = etage;
	}
	
	public String getEtage(){
		return this.etage;
	}
	
	public void setDoer(String doer){
		this.doer = doer;
	}
	
	public String getDoer(){
		return this.doer;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public void setAnviser(boolean anviser){
		this.anviser = anviser;
	}
	
	public boolean getAnviser(){
		return this.anviser;
	}
	
	public void setGodkender(boolean godkender){
		this.godkender = godkender;
	}
	
	public boolean getGodkender(){
		return this.godkender;
	}
	
	public int getID() {
		return this.ID;
	}
	
	
	
	
	
	
	
	
	
}
