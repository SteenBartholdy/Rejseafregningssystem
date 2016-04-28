package dk.dtu.smmac.shared;

import java.io.Serializable;

public class BilagDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String forklaring;
	
	public BilagDTO(int id)
	{
		this.id = id;
	}
	
	public BilagDTO(int id, String forklaring)
	{
		this.id = id;
		this.forklaring = forklaring;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getForklaring()
	{
		return forklaring;
	}

}
