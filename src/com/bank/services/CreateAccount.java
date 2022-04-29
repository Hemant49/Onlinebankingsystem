package com.bank.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateAccount
 */
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		int id=0;
		String uname=request.getParameter("name");
		String umobile=request.getParameter("mobile");
		int ubalance= Integer.parseInt(request.getParameter("balance"));
		
		
		if(ubalance>=0){
		try
		{
			Connection obj = Connect.connect();
			PreparedStatement ps1 = obj.prepareStatement("insert into accountsdetails values(?,?,?,?)");
			ps1.setInt(1,id);
			ps1.setString(2,uname);
			ps1.setString(3,umobile);
			ps1.setInt(4,ubalance);
			int res = ps1.executeUpdate();
			
					if(res>0)
					{
						response.sendRedirect("welcome.html");
						System.out.println("Failed to create account");
						
					} 
					else
					{
						response.sendRedirect("welcome.html");
						System.out.println("Account create Successfully");
					}
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
		}
		else{
			response.sendRedirect("404.html");
		}

	}

}
