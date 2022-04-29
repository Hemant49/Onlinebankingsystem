package com.bank.services;
import java.sql.*;
public class Connect {
	
		static Connection con = null;
		public static Connection connect()
		{
			try{
				if(con == null)
				{
					/*Loading the driver*/
					Class.forName("com.mysql.cj.jdbc.Driver");
					/* Getting the connection*/
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/web_bank","root","");
					System.out.println("Connection Establish: ");
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
				
			}
			return con;
		}	
	public static void main(String agrs[])
	{
		Connect obj1 = new Connect();
		obj1.connect();
		
	}
}
