package dk.dtu.smmac.shared;

import java.io.Serializable;

public class BilagDTO implements Serializable {

	private static final long serialVersionUID = 19L;
	private int id, nr;
	private String forklaring;
	
	public BilagDTO() {
		super();
	}
	
	public BilagDTO(int id, int nr)
	{
		super();
		this.id = id;
		this.nr = nr;
	}
	
	public BilagDTO(int id, int nr, String forklaring)
	{
		super();
		this.id = id;
		this.nr = nr;
		this.forklaring = forklaring;
	}
	
	public int getNr()
	{
		return nr;
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
