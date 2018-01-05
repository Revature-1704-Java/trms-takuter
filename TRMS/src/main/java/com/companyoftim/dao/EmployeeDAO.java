package com.companyoftim.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.companyoftim.beans.Employee;

import jdbc.ConnectionUtil;

public class EmployeeDAO {
	
	private static final EmployeeDAO empD = new EmployeeDAO();
	
	private EmployeeDAO() {
		
	}
	
	public static EmployeeDAO getInstance(){
		return empD;
	}
	
	public Employee getEmployeeInfo(int eid) {
		PreparedStatement ps = null;
		Employee emp = new Employee();

		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EMP_ID= ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, eid);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				
				emp.setId(eid);
				emp.setDpt(rs.getInt("EMP_DPT"));
				emp.setFname(rs.getString("EMP_FNAME"));
				emp.setLname(rs.getString("EMP_LNAME"));
				emp.setSpvr(rs.getInt("EMP_SPVR"));
				emp.setEmail(rs.getString("EMP_EMAIL"));
				emp.setTotalReim(BigDecimal.valueOf(0.00));
			}

			else {
				System.out.println("Invalid Employee");
			}
			rs.close();
			ps.close();

		} catch (Exception ex) {
			ex.getMessage();
		}

		return emp;
	}
	
	public ArrayList<Employee> getSubEmployee(int id) {
		PreparedStatement ps = null;
		ArrayList<Employee> emps = new ArrayList<Employee>();

		try(Connection conn = ConnectionUtil.getConnection()) {

			String emp_retrieve = "SELECT EMP_ID, EMP_FNAME, EMP_LNAME, EMP_EMAIL FROM EMPLOYEE WHERE EMP_SPVR = ?";
			Employee emp;
			ps = conn.prepareStatement(emp_retrieve);
			ResultSet rs=ps.executeQuery();

			while (rs.next()) {
				emp = new Employee();
				emp.setId(rs.getInt("EMP_ID"));
				emp.setFname(rs.getString("EMP_FNAME"));
				emp.setLname(rs.getString("EMP_LNAME"));
				emp.setEmail(rs.getString("EMP_EMAIL"));
				
				emps.add(emp);
			}

			rs.close();
			ps.close();

		} catch (Exception ex) {
			ex.getMessage();
		}

		return emps;
	}


	public ArrayList<Employee> getDptEmployee(int id) {
		PreparedStatement ps = null;
		ArrayList<Employee> emps = new ArrayList<Employee>();

		try(Connection conn = ConnectionUtil.getConnection()) {

			String emp_retrieve = "SELECT EMP_ID, EMP_FNAME, EMP_LNAME, EMP_EMAIL, EMP_SPVR FROM EMPLOYEE WHERE EMP_DPT = ?";
			Employee emp;
			ps = conn.prepareStatement(emp_retrieve);
			ResultSet rs=ps.executeQuery();

			while (rs.next()) {
				emp = new Employee();
				emp.setId(rs.getInt("EMP_ID"));
				emp.setFname(rs.getString("EMP_FNAME"));
				emp.setLname(rs.getString("EMP_LNAME"));
				emp.setEmail(rs.getString("EMP_EMAIL"));
				
				emps.add(emp);

			}

			rs.close();
			ps.close();

		} catch (Exception ex) {
			ex.getMessage();
		}

		return emps;
	}

	public boolean checkLogIn(int eid, String pass) {
		boolean exists=false;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "{call checkLoginId(?,?,?)}";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setInt(1, eid);
			cs.setString(2, pass);
			cs.registerOutParameter(3, java.sql.Types.CHAR);	
			cs.execute();
			exists = cs.getBoolean(3);
			cs.close();

		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return exists;
	}
	
	/*public boolean checkLogIn(String email, String pass) {
		boolean exists=false;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EXISTS EMP_EMAIL = ? AND EMP_PASSWORD = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1,email);
			statement.setString(2, pass);
			ResultSet rs = statement.executeQuery();
			rs.next();
			System.out.println(rs.getInt("EMP_ID"));
			System.out.println(rs.toString());
			if (rs.next()){
				exists=true;
			}
			else {
				System.out.println("Invalid ID");
			}
			
			rs.close();
			statement.close();

		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return exists;
	}*/

	public boolean isManager(int eid) {
		boolean mngr=false;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT 1 FROM DEPARTMENT WHERE EXISTS MNG_ID= ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, eid);
			
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
				mngr=true;
			}
			
			rs.close();
			statement.close();

		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return mngr;
	}
	
	public void InsertEmployee(String fnames,String lnames,String pass,String email,int role,int dept ) {
		CallableStatement empCreate = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String emp = "{call insertEmployee(?,?,?,?,?,?,?)} ";
			empCreate = conn.prepareCall(emp);
			empCreate.setString(1, fnames);
			empCreate.setString(2, lnames);
			empCreate.setString(3, pass);
			empCreate.setString(4, email);
			empCreate.setInt(5, dept);
			empCreate.setInt(6, role);
			empCreate.executeUpdate();
			empCreate.close();
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	public void InsertEmployee(String fnames,String lnames,String pass,String email,int supervisor,int role,int dept ) {
		CallableStatement empCreate = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String emp = "{call insertEmployeewithSPVR(?,?,?,?,?,?,?)}";
			empCreate = conn.prepareCall(emp);
			empCreate.setString(1, fnames);
			empCreate.setString(2, lnames);
			empCreate.setString(3, pass);
			empCreate.setString(4, email);
			empCreate.setInt(5, dept);
			empCreate.setInt(6, role);
			empCreate.setInt(7, supervisor);
			empCreate.executeUpdate();
			empCreate.close();
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
}
