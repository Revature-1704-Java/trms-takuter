package com.companyoftim.beans;

public class ReimbursmentCompletion {
	String filename;
	int reimID;
	int grduplID;
	int minPass;
	
	boolean SPVR;
	boolean BenCo;
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getReimID() {
		return reimID;
	}
	public void setReimID(int reimID) {
		this.reimID = reimID;
	}
	public int getGrduplID() {
		return grduplID;
	}
	public void setGrduplID(int grduplID) {
		this.grduplID = grduplID;
	}
	public boolean isSPVR() {
		return SPVR;
	}
	public void setSPVR(boolean sPVR) {
		SPVR = sPVR;
	}
	public boolean isBenCo() {
		return BenCo;
	}
	public void setBenCo(boolean benCo) {
		BenCo = benCo;
	}
	
	
}
