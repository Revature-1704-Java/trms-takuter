package com.companyoftim.beans;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;

public class Employee {
	private int id;
	private String fname;	
	private String lname;
	private String email;
	private int dpt;
	private int spvr;
	private BigDecimal totalReim;
	
	public Employee() {
		
	};
	
	public Employee(int id, String fname, String lname, int dpt, int spvr, BigDecimal totalReim) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.dpt = dpt;
		this.spvr = spvr;
		this.totalReim = totalReim;
	}
	
	public Employee(String fname, String lname, String email, int dpt, int spvr) {
		this.fname = fname;
		this.lname = lname;
		this.dpt = dpt;
		this.spvr = spvr;
		this.email=email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDpt() {
		return dpt;
	}

	public void setDpt(int dpt) {
		this.dpt = dpt;
	}

	public int getSpvr() {
		return spvr;
	}

	public void setSpvr(int spvr) {
		this.spvr = spvr;
	}

	public BigDecimal getTotalReim() {
		return totalReim;
	}

	public void setTotalReim(BigDecimal totalReim) {
		this.totalReim = totalReim;
	}
	
	public void insertMe(){
		
	}
	
	
	
	
}
