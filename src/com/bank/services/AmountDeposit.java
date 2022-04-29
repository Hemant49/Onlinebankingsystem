package com.bank.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AmountDeposit
 */
public class AmountDeposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmountDeposit() {
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
		String umobile = request.getParameter("mobile");
		int depoamt = Integer.parseInt(request.getParameter("deposit"));
		int perbal=0,ubalance=0;
		try
		{
			Connection obj = Connect.connect();
			PreparedStatement ps2 = obj.prepareStatement("select * from accountsdetails where mobile =?");
			ps2.setString(1,umobile);
			ResultSet res = ps2.executeQuery();
			while(res.next())
			{
				perbal = res.getInt(4);
			
			if(depoamt>0){
			ubalance = perbal+depoamt;
			PreparedStatement ps1 = obj.prepareStatement("update accountsdetails set balance=? where mobile=?");
			ps1.setInt(1, ubalance);
			ps1.setString(2, umobile);
			int rs = ps1.executeUpdate();
			if(rs>0)
			{
				System.out.println("Balance UPdated : ");
				System.out.println("Updated balance is : " + ubalance);
				response.sendRedirect("AmountDeposit.html");
			}
			else
			{
				response.sendRedirect("404.html");
				
			}
			}
			else
			{
				System.out.println("Enter a valid amount");
				response.sendRedirect("404.html");
			}
			
		}}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}

}
