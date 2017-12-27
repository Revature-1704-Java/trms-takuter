package com.companyoftim.beans;

import java.math.BigDecimal;
import java.sql.Date;

public class Reimbursement {
	public int id;
	private int empid;
	
	private Event evt;
	private String evtype;
	private Date enterdate;
	private String reason;
	private String workmissed;
	private int grdForm;
	
	private BigDecimal amount;
	private BigDecimal predictedReim;
	private BigDecimal benCoReim;
	
	private String sprvapproval;
	private String headapproval;
	private boolean complete;
	private boolean urgent;
	
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
	public Event getEvt() {
		return evt;
	}
	public void setEvt(Event evt) {
		this.evt = evt;
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
	public int getGrdForm() {
		return grdForm;
	}
	public void setGrdForm(int grdForm) {
		this.grdForm = grdForm;
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
	
	
}
