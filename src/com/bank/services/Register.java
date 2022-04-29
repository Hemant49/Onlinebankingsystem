package com.bank.services;
import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		String uemail = request.getParameter("email");
		String uname = request.getParameter("name");
		String upassword =request.getParameter("password");
		
		Connection obj = Connect.connect();
		try 
		{
			PreparedStatement ps = obj.prepareStatement("insert into userdetails values(?,?,?)");
			ps.setString(1,uname);
			ps.setString(2,uemail);
			ps.setString(3,upassword);
			int rs = ps.executeUpdate();
			if(rs>0)
			{
				response.sendRedirect("adminlogin.html");
				System.out.println("Registeration successfully");
			}
			else
			{
				response.sendRedirect("UserRegister.html");
				System.out.println("Registeration Failed");
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
