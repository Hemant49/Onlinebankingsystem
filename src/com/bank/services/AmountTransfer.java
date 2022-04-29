package com.bank.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AmountTransfer
 */
public class AmountTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmountTransfer() {
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
		String tmobile= request.getParameter("tmobile");
		String umobile = request.getParameter("mobile");
		int transamount = Integer.parseInt(request.getParameter("transfer"));
		int perbal=0,ubalance=0,perbal1 = 0;
		try
		{
			/*Transfered from sender account*/
			Connection obj = Connect.connect();
			PreparedStatement ps1 = obj.prepareStatement("select * from accountsdetails where mobile = ?");
			ps1.setString(1,umobile);
			ResultSet rs = ps1.executeQuery();
			while(rs.next())
			{
				perbal = rs.getInt(4);
			}
			if(transamount<=perbal && transamount>0)
			{
			ubalance = perbal-transamount;
			PreparedStatement ps2 = obj.prepareStatement("update accountsdetails set balance=? where mobile=?");
			ps2.setInt(1, ubalance);
			ps2.setString(2, umobile);
			
			int res = ps2.executeUpdate();
			
			if(res>0)
			{
				System.out.println("Transaction Successfully ");
				System.out.println("Sender's Balance after transaction " + ubalance);
				response.sendRedirect("AmountTransfer.html");
			}
			else
			{
				System.out.println("Failed to deposit");
				response.sendRedirect("404.html");
				
			}
			
		  }
			else
			{
				System.out.println("Insufficient Balance : ");
				response.sendRedirect("404.html");
			}
			//Receives Amount and updates
			PreparedStatement ps3 = obj.prepareStatement("select * from accountsdetails where mobile = ?");
			ps3.setString(1,tmobile);
			ResultSet rs1 = ps3.executeQuery();
			while(rs1.next())
			{
				perbal1 = rs1.getInt(4);
			}
			if(transamount>0 &&transamount<=perbal)
			{
			ubalance = perbal1+transamount;
			PreparedStatement ps4 = obj.prepareStatement("update accountsdetails set balance=? where mobile=?");
			ps4.setInt(1, ubalance);
			ps4.setString(2, tmobile);
			
			int res = ps4.executeUpdate();
			
			if(res>0)
			{
				System.out.println("Amount Received ");
				System.out.println("Receiver's Balance after transaction " + ubalance);
				response.sendRedirect("AmountTransfer.html");
			}
			else
			{
				System.out.println("Failed to Receive");
				response.sendRedirect("404.html");
				
			}
			
		  }
			else
			{
				System.out.println("Insufficient Balance : ");
				response.sendRedirect("404.html");
			}
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
			
		
		
	}

}
