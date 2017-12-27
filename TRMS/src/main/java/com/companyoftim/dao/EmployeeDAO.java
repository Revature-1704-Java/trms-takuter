package com.companyoftim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.companyoftim.users.Employee;

import jdbc.ConnectionUtil;

public class EmployeeDAO {
	
	public Employee getEmployeeInfo(int eid) {
		PreparedStatement ps = null;
		Employee emp = new Employee();

		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EMP_ID= ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, eid);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				
				emp.setId(rs.getInt("EMP_ID"));
				emp.setDpt(rs.getInt("EMP_DPT"));
				emp.setFname(rs.getString("EMP_FNAME"));
				emp.setLname(rs.getString("EMP_LNAME"));
				emp.setSpvr(rs.getInt("EMP_SPVR"));
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
			String sql = "SELECT * FROM EMPLOYEE WHERE EXISTS EMP_ID = ? AND EMP_PASSWORD = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, eid);
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
	}
	
	public boolean checkLogIn(String email, String pass) {
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
	}

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
	
	public void InsertEmployee() {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String emp = "Insert into Employee(EMP_FIRSTNAME, EMP_LASTNAME, EMP_EMAIL, EMP_DPT, EMP_SPVR)"
					+ " VALUES (?,?,?,?,?,?)";
			PreparedStatement empCreate = conn.prepareStatement(emp);
			//ps.close();
		} catch(Exception ex) {
			ex.getMessage();
		}
	}
}
