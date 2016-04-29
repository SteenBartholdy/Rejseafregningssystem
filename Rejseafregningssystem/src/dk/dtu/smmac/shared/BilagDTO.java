package dk.dtu.smmac.shared;

import java.io.Serializable;

public class BilagDTO implements Serializable {

	private static final long serialVersionUID = 19L;
	private int id;
	private String forklaring;
	
	public BilagDTO() {
		super();
	}
	
	public BilagDTO(int id)
	{
		super();
		this.id = id;
	}
	
	public BilagDTO(int id, String forklaring)
	{
		super();
		this.id = id;
		this.forklaring = forklaring;
	}
	
	public int getID()
	{
		return id;
	}
	
	public String getForklaring()
	{
		return forklaring;
	}

}
