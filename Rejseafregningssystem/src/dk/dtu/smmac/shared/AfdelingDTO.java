package dk.dtu.smmac.shared;

import java.io.Serializable;

public class AfdelingDTO implements Serializable {
	
	private static final long serialVersionUID = 2L;
	private String navn;
	
	public AfdelingDTO() {
		
	}
	
	public AfdelingDTO(String afdeling) {
		this.navn = afdeling;
	}

	public String getAfdeling() {
		return this.navn;
	}
	
	public void setAfdeling(String afdeling) {
		this.navn = afdeling;
	}
	
}
