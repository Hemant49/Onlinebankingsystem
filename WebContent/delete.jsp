<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*" %>
     <%@page import="com.bank.services.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 
 <%
int id = Integer.parseInt(request.getParameter("id"));
 
	Connection con = Connect.connect();
	try{
		PreparedStatement ps = con.prepareStatement("Delete from accountsdetails where id=? ");
		ps.setInt(1,id);
		int res = ps.executeUpdate();
		if(res>0)
		{
			response.sendRedirect("viewAccount.jsp");
		}
		else
		{
			response.sendRedirect("404.html");
		}
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	
 %>
 
</body>
</html>