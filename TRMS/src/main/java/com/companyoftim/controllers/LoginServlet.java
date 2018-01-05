package com.companyoftim.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.companyoftim.dao.EmployeeDAO;
import com.google.gson.Gson;

@WebServlet ("/LoginServlet")
public class LoginServlet extends HttpServlet{

	EmployeeDAO empD = EmployeeDAO.getInstance();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		StringBuilder sb = new StringBuilder();
		BufferedReader br = request.getReader();
		String str = null;
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}

		class User{
			int username;
			String password;
		}
		Gson gson = new Gson();
		User user = gson.fromJson(sb.toString(), User.class);
		if(empD.checkLogIn(user.username,user.password))
		{
			//Convert Employee object into json and send it back;
			String json = gson.toJson(EmployeeDAO.getInstance().getEmployeeInfo(user.username));
			System.out.println();
			response.setContentType("application/json");
			// Get the printwriter object from response to write the required json object to the output stream
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
		}

		else response.sendError(200, "Invalid Username/Password");

	}

}
