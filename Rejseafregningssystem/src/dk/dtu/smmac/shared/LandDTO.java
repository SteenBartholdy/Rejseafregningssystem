package dk.dtu.smmac.shared;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LandDTO implements Serializable {
	
	private static final long serialVersionUID = 30L;
	private String land;
	private int takst;
	
	public LandDTO(){
		super();
	}
	
	public LandDTO(String land, int takst)
	{
		super();
		this.land = land;
		this.takst = takst;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public int getTakst() {
		return takst;
	}

	public void setTakst(int takst) {
		this.takst = takst;
	}
	
	

}
