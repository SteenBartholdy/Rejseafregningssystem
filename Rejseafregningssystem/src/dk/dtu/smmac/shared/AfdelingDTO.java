package dk.dtu.smmac.shared;

public class AfdelingDTO {

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
