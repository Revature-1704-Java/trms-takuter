package com.companyoftim.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
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
		/*try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "{CALL CK_REIM_DATES()}";
			cs = conn.prepareCall(sql);
			cs.execute();

			cs.close();
		} catch (Exception ex) {
			ex.getMessage();
		}*/
		return reimD;
	}
	
	public List<Reimbursement> getAllReimbursements() {
		PreparedStatement ps = null;
		List<Reimbursement> reims = new ArrayList<Reimbursement>();
		Reimbursement r = null;

		try(Connection conn = ConnectionUtil.getConnection()) {
			r = new Reimbursement();
			String sql = "SELECT  FROM REIMBURSEMENT r "
					+ "inner join EVENTYPE et on r.EVNTYPE_ID = et.EVNTYPE_ID"
					+ "inner join GRADEFORMAT gf on r.REIM_GRADEFORMAT=gf.GRDFORM_ID "
					+ "inner join EVENT e on r.EVNT_ID = e.EVNT_ID "
					+ "inner join EMPLOYEE em on em.EMP_ID=r.EMP_ID";
			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				r.getId();
				r.getEmpid();
				
				r.getEvtype();
				r.getEnterdate();
				r.getReason();
				r.getWorkmissed();
				r.getGrdForm();
				r.getPassingGrade();
				
				r.getAmount();
				r.getPredictedReim();
				r.getBenCoReim();
				
				r.getSprvapproval();
				r.getHeadapproval();
				r.isComplete();
				r.isUrgent();
				
				r.getDesc();
				r.getLoc();
				r.getEvttime();

			}
			rs.close();
			ps.close();

		} catch (Exception ex) {
			ex.getMessage();
		}

		return reims;
	}
	
	public List<Reimbursement> getSubReimbursements(int spvrid) {
		PreparedStatement ps = null;
		List<Reimbursement> reims = new ArrayList<Reimbursement>();
		Reimbursement r = null;

		try(Connection conn = ConnectionUtil.getConnection()) {
			r = new Reimbursement();
			String sql = "SELECT  FROM REIMBURSEMENT r "
					+ "inner join EVENTYPE et on r.EVNTYPE_ID = et.EVNTYPE_ID "
					+ "inner join GRADEFORMAT gf on r.REIM_GRADEFORMAT=gf.GRDFORM_ID "
					+ "inner join EVENT e on r.EVNT_ID = e.EVNT_ID "
					+ "inner join EMPLOYEE em on em.EMP_ID=r.EMP_ID "
					+ "where EMP_SPVR = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,spvrid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				r.getId();
				r.getEmpid();
				
				r.getEvtype();
				r.getEnterdate();
				r.getReason();
				r.getWorkmissed();
				r.getGrdForm();
				r.getPassingGrade();
				
				r.getAmount();
				r.getPredictedReim();
				r.getBenCoReim();
				
				r.getSprvapproval();
				r.getHeadapproval();
				r.isComplete();
				r.isUrgent();
				
				r.getDesc();
				r.getLoc();
				r.getEvttime();

			}
			rs.close();
			ps.close();

		} catch (Exception ex) {
			ex.getMessage();
		}

		return reims;
	}
	
	public List<Reimbursement> getDeptReimbursements(int dept) {
		PreparedStatement ps = null;
		List<Reimbursement> reims = new ArrayList<Reimbursement>();
		Reimbursement r = null;

		try(Connection conn = ConnectionUtil.getConnection()) {
			r = new Reimbursement();
			String sql = "SELECT  FROM REIMBURSEMENT r "
					+ "inner join EVENTYPE et on r.EVNTYPE_ID = et.EVNTYPE_ID"
					+ "inner join GRADEFORMAT gf on r.REIM_GRADEFORMAT=gf.GRDFORM_ID "
					+ "inner join EVENT e on r.EVNT_ID = e.EVNT_ID "
					+ "inner join EMPLOYEE em on em.EMP_ID=r.EMP_ID"
					+ "where em.EMP_DPT = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,dept);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				r.getId();
				r.getEmpid();
				
				r.getEvtype();
				r.getEnterdate();
				r.getReason();
				r.getWorkmissed();
				r.getGrdForm();
				r.getPassingGrade();
				
				r.getAmount();
				r.getPredictedReim();
				r.getBenCoReim();
				
				r.getSprvapproval();
				r.getHeadapproval();
				r.isComplete();
				r.isUrgent();
				
				r.getDesc();
				r.getLoc();
				r.getEvttime();

			}
			rs.close();
			ps.close();

		} catch (Exception ex) {
			ex.getMessage();
		}

		return reims;
	}
	
	public void insertReimbursement(int eid, int evtid, String reason, int worktime, int grdform, BigDecimal cost, String desc, String loc, Date rtime) {
		CallableStatement cs=null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			cs=conn.prepareCall("{call ReimReq(?,?,?,?,?,?,?,?,?)}");
			cs.setInt(1, eid);
			cs.setInt(2, evtid);
			cs.setString(3, reason);
			cs.setInt(4, worktime);
			cs.setInt(5, grdform);
			cs.setBigDecimal(6, cost);
			cs.setString(7, desc);
			cs.setString(8, loc);
			cs.setDate(9, rtime);
			cs.execute();
		}  catch (Exception ex) {
			ex.getMessage();
		}
	}
	
	/*public void stateChanged(int emptype) {
		CallableStatement cs=null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			cs=conn.prepareCall("{call ReimReq(?,?,?,?,?,?,?,?,?)}");
			cs.setInt(1, eid);
			cs.setInt(2, evtid);
			cs.setString(3, reason);
			cs.setInt(4, worktime);
			cs.setInt(5, grdform);
			cs.setBigDecimal(6, cost);
			cs.setString(7, desc);
			cs.setString(8, loc);
			cs.setDate(9, rtime);
			cs.execute();
		}  catch (Exception ex) {
			ex.getMessage();
		}
	}*/
}

