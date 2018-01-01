package com.companyoftim.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.companyoftim.beans.Reimbursement;

import jdbc.ConnectionUtil;


public class ReimbursementDAO {
	
	private static final ReimbursementDAO reimD = new ReimbursementDAO();
	
	private ReimbursementDAO() {
		
	}
	
	public static ReimbursementDAO getInstance(){
		CallableStatement cs = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "{CALL CK_REIM_DATES()}";
			cs = conn.prepareCall(sql);
			cs.execute();

			cs.close();
		} catch (Exception ex) {
			ex.getMessage();
		}
		return reimD;
	}
	
	public List<Reimbursement> getAllReimbursements() {
		PreparedStatement ps = null;
		List<Reimbursement> reims = new ArrayList<Reimbursement>();
		Reimbursement r = null;

		try(Connection conn = ConnectionUtil.getConnection()) {
			r = new Reimbursement();
			String sql = "SELECT * FROM REIMBURSEMENT";
			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				reims.add(r);

			}
			rs.close();
			ps.close();

		} catch (Exception ex) {
			ex.getMessage();
		}

		return reims;
	}
	
	public List<Reimbursement> getSubReimbursements() {
		PreparedStatement ps = null;
		List<Reimbursement> reims = new ArrayList<Reimbursement>();
		Reimbursement r = null;

		try(Connection conn = ConnectionUtil.getConnection()) {
			r = new Reimbursement();
			String sql = "SELECT * FROM REIMBURSEMENT";
			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				reims.add(r);

			}
			rs.close();
			ps.close();

		} catch (Exception ex) {
			ex.getMessage();
		}

		return reims;
	}
	
	public List<Reimbursement> getDeptReimbursements() {
		PreparedStatement ps = null;
		List<Reimbursement> reims = new ArrayList<Reimbursement>();
		Reimbursement r = null;

		try(Connection conn = ConnectionUtil.getConnection()) {
			r = new Reimbursement();
			String sql = "SELECT * FROM REIMBURSEMENT";
			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				reims.add(r);

			}
			rs.close();
			ps.close();

		} catch (Exception ex) {
			ex.getMessage();
		}

		return reims;
	}

	public void insertReimbursement(double amount, String reason, int eid, int did) {
		CallableStatement cs=null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			cs=conn.prepareCall("{call ReimReq(?,?,?,?)}");
			cs.setInt(1, eid);
			cs.setInt(2, did);
			cs.setBigDecimal(3, BigDecimal.valueOf(amount));
			cs.setString(4, reason);

			cs.execute();
		}  catch (Exception ex) {
			ex.getMessage();
		}
	}
}

