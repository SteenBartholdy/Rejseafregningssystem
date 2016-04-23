package dk.dtu.smmac.shared;

import java.io.Serializable;

public class PostNrDTO implements Serializable {

	private static final long serialVersionUID = 3L;
	private String no;
	private String name;
	
	public PostNrDTO() {
		
	}
	
	public PostNrDTO(String no, String name) {
		super();
		this.no = no;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getNo() {
		return this.no;
	}
	
}
