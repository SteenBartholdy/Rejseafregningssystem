package dk.dtu.smmac.shared;

import java.io.Serializable;

public class BankDTO implements Serializable {

	private static final long serialVersionUID = 5L;
	private int id;
	private int kontoNo;
	private int regNo;
	
	public BankDTO() {}
	
	public BankDTO(int id, int regNo, int kontoNo) {
		super();
		this.id = id;
		this.kontoNo = kontoNo;
		this.regNo = regNo;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getKontoNo() {
		return this.kontoNo;
	}
	
	public int getRegNo() {
		return this.regNo;
	}
	
}
