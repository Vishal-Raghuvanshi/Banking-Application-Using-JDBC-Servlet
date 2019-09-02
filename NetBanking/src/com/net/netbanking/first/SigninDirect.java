package com.net.netbanking.first;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class SigninDirect extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		
		try {
			Driver ref=new Driver();
			DriverManager.registerDriver(ref);
			
			String dburl="jdbc:mysql://localhost:3306/vishal?user=root&password=root";
			Connection con=DriverManager.getConnection(dburl);
			
			String query="SELECT  * FROM netbanking";
			System.out.println("out side if lloop");
			Statement stmt= con.createStatement();
			System.out.println("after stat");
			ResultSet res=stmt.executeQuery(query);
			System.out.println("after res");
			
			
			if(res.next())
			{
				System.out.println("inside if ");
				String uname=res.getString("username");
				System.out.println(uname);
				if (username.equals(uname))
				{
				RequestDispatcher rd=req.getRequestDispatcher("Welcome.html");
				rd.forward(req, resp);
			}
			else
			{
				System.out.println("inside else");
				RequestDispatcher rd=req.getRequestDispatcher("Signin.html");
			rd.forward(req, resp);
			}}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
}
