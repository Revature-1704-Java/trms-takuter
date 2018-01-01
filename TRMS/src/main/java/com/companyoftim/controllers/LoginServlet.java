package com.companyoftim.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.companyoftim.dao.EmployeeDAO;

public class LoginServlet extends HttpServlet{
	
	EmployeeDAO empD = EmployeeDAO.getInstance();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		     throws ServletException, IOException{
		if(request.getAttribute("username")!=null && request.getAttribute("password")!=null) {
			int user=(Integer)request.getAttribute("username");
			String pass=(String)request.getAttribute("password");
			if(empD.checkLogIn(user, pass))
			{
				HttpSession sess = request.getSession();
				sess.setAttribute("user", empD.getEmployeeInfo(user));
				response.sendRedirect("index.html");
			}
			else response.sendRedirect("login.html");
				
		}
		else response.sendRedirect("login.html");
		
	}

}
