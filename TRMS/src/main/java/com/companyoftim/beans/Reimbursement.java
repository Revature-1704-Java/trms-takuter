package com.companyoftim.beans;

import java.math.BigDecimal;
import java.sql.Date;

public class Reimbursement{
	public int id;
	private int empid;
	
	private String evtype;
	private Date enterdate;
	private String reason;
	private String workmissed;
	private String grdForm;
	private int passingGrade;
	
	private BigDecimal amount;
	private BigDecimal predictedReim;
	private BigDecimal benCoReim;
	
	private String sprvapproval;
	private String headapproval;
	private boolean complete;
	private boolean urgent;
	
	private String desc;
	private String loc;
	private Date evttime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	
	public String getEvtype() {
		return evtype;
	}
	public void setEvtype(String evtype) {
		this.evtype = evtype;
	}
	public Date getEnterdate() {
		return enterdate;
	}
	public void setEnterdate(Date enterdate) {
		this.enterdate = enterdate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getWorkmissed() {
		return workmissed;
	}
	public void setWorkmissed(String workmissed) {
		this.workmissed = workmissed;
	}
	
	public String getGrdForm() {
		return grdForm;
	}
	public void setGrdForm(String grdForm) {
		this.grdForm = grdForm;
	}
	
	public int getPassingGrade() {
		return passingGrade;
	}
	public void setPassingGrade(int passingGrade) {
		this.passingGrade = passingGrade;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getPredictedReim() {
		return predictedReim;
	}
	public void setPredictedReim(BigDecimal predictedReim) {
		this.predictedReim = predictedReim;
	}
	public BigDecimal getBenCoReim() {
		return benCoReim;
	}
	public void setBenCoReim(BigDecimal benCoReim) {
		this.benCoReim = benCoReim;
	}
	public String getSprvapproval() {
		return sprvapproval;
	}
	public void setSprvapproval(String sprvapproval) {
		this.sprvapproval = sprvapproval;
	}
	public String getHeadapproval() {
		return headapproval;
	}
	public void setHeadapproval(String headapproval) {
		this.headapproval = headapproval;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	public boolean isUrgent() {
		return urgent;
	}
	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public Date getEvttime() {
		return evttime;
	}
	public void setEvttime(Date evttime) {
		this.evttime = evttime;
	}
	
	
}
