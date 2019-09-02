package com.net.netbanking.first;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class SigninForm extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
		
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		
		if(CreateProfileServlet.username.equals(username) && CreateProfileServlet.password.equals(password))
		{
		
			try {
				Driver ref=new Driver();
				DriverManager.registerDriver(ref);
				
				String dburl="jdbc:mysql://localhost:3306/vishal?user=root&password=root";
				Connection con=DriverManager.getConnection(dburl);
				
				PreparedStatement pstmt=con.prepareStatement("insert into signin values(?,?)");
				
				pstmt.setString(1,username);
				pstmt.setString(2, password);
				
				int res=pstmt.executeUpdate();
				if (res>0)
				{
					
					RequestDispatcher rd=req.getRequestDispatcher("Welcome.html");
					rd.forward(req, resp);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			
			RequestDispatcher rd=req.getRequestDispatcher("Signin.html");
			rd.include(req, resp);
		}
		}
		
	}
	
	


