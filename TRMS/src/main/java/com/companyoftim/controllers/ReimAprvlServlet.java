package com.companyoftim.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReimAprvlServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		     throws ServletException, IOException{
		         String jsonString = "{\"name\":\"Mehrab Rahman\"}";

		         response.setContentType("application/json");

		         //send back Person object
		    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		     throws ServletException, IOException{
		response.sendRedirect("login	.html");
		
	}
