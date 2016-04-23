package dk.dtu.smmac.shared;

import java.io.Serializable;

public class RejseafregningDTO implements Serializable {

	private static final long serialVersionUID = 7L;

	private int nr;
	private int ansatId;
	private int startTid;
	private int slutTid;
	
	public RejseafregningDTO() { }
	
	public RejseafregningDTO(int id, int ansatId, int startTid, int slutTid) {
		super();
		this.nr = id;
		this.ansatId = ansatId;
		this.startTid = startTid;
		this.slutTid = slutTid;
	}
	
	public int getId() {
		return this.nr;
	}
	
	public int getAnsatId() {
		return this.ansatId;
	}
	
	public int getStartTid() {
		return this.startTid;
	}
	
	public int getSlutTid() {
		return this.slutTid;
	}
	
}
