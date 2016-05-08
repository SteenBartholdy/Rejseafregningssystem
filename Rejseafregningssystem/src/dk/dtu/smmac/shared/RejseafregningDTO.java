package dk.dtu.smmac.shared;

import java.io.Serializable;

public class RejseafregningDTO implements Serializable {

	private static final long serialVersionUID = 7L;

	private int nr;
	private int ansatId;
	private int startTid;
	private int slutTid;
	private double befordring, dagpenge, udgifter, afregningtotal, refundering, forskud, afregning;
	private boolean godkendt, anvist, done;

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public RejseafregningDTO() { }
	
	public RejseafregningDTO(int id, int ansatId, int startTid, int slutTid, double befordring, double dagpenge, double udgifter, double afregningtotal, double refundering, double forskud, double afregning, boolean godkendt, boolean anvist, boolean done) {
		super();
		this.nr = id;
		this.ansatId = ansatId;
		this.startTid = startTid;
		this.slutTid = slutTid;
		this.befordring = befordring;
		this.dagpenge = dagpenge;
		this.udgifter = udgifter;
		this.afregningtotal = afregningtotal;
		this.refundering = refundering;
		this.forskud = forskud;
		this.afregning = afregning;
		this.godkendt = godkendt;
		this.anvist = anvist;
		this.done = done;
	}
	
	public RejseafregningDTO(int id, int ansatId) {
		super();
		this.nr = id;
		this.ansatId = ansatId;
	}
	
	public boolean isGodkendt() {
		return godkendt;
	}

	public void setGodkendt(boolean godkendt) {
		this.godkendt = godkendt;
	}

	public boolean isAnvist() {
		return anvist;
	}

	public void setAnvist(boolean anvist) {
		this.anvist = anvist;
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
	
	public void setStartTid(int tid) {
		this.startTid = tid;
	}
	
	public void setSlutTid(int tid) {
		this.slutTid = tid;
	}
	
	public double getBefordring() {
		return befordring;
	}

	public void setBefordring(double befordring) {
		this.befordring = befordring;
	}

	public double getDagpenge() {
		return dagpenge;
	}

	public void setDagpenge(double dagpenge) {
		this.dagpenge = dagpenge;
	}

	public double getUdgifter() {
		return udgifter;
	}

	public void setUdgifter(double udgifter) {
		this.udgifter = udgifter;
	}

	public double getAfregningtotal() {
		return afregningtotal;
	}

	public void setAfregningtotal(double afregningtotal) {
		this.afregningtotal = afregningtotal;
	}

	public double getRefundering() {
		return refundering;
	}

	public void setRefundering(double refundering) {
		this.refundering = refundering;
	}

	public double getForskud() {
		return forskud;
	}

	public void setForskud(double forskud) {
		this.forskud = forskud;
	}

	public double getAfregning() {
		return afregning;
	}

	public void setAfregning(double afregning) {
		this.afregning = afregning;
	}
	
}
