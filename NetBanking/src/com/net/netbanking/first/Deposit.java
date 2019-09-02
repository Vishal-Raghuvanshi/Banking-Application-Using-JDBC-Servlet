package com.net.netbanking.first;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;


public class Deposit extends HttpServlet {
	int balance;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		PrintWriter out=resp.getWriter();
		
		String username=req.getParameter("username");
		int accno=Integer.parseInt(req.getParameter("accno"));
		int  Amount= Integer.parseInt(req.getParameter("amount"));
		
		
			try {
				Driver ref=new Driver();
				DriverManager.registerDriver(ref);
				
				String dburl="jdbc:mysql://localhost:3306/vishal?user=root&password=root";
				Connection con=DriverManager.getConnection(dburl);
				
				PreparedStatement pstmt=con.prepareStatement("insert into AccountDetails values(?,?)");
				pstmt.setString(1,username);
				pstmt.setInt(2,accno);
				//pstmt.setInt(3,Amount);
				
				
				int res=pstmt.executeUpdate();
				
				if (res>1)
				{
					
					String query="SELECT * FROM AccountDetails";
					Statement stmt= con.createStatement();
					ResultSet res1=stmt.executeQuery(query);
					
					if(res1.next())
						{
							String uname=res1.getString("username");
							 balance = res1.getInt("balance");
						
					if (uname.equals(username))
					{
				
					balance=balance+Amount;
					String query1="UPDATE AccountDetails SET WHERE  username="+username+" AND accno="+accno+"";
					PreparedStatement pstmt1=con.prepareStatement(query1);
					pstmt1.setInt(3,balance);
				
					int res2=pstmt1.executeUpdate();
					if (res2>0)
					{
						RequestDispatcher rd=req.getRequestDispatcher("CheckBal.html");
						rd.forward(req,resp);
					}
			    }
				}}
				else
				{
					out.println("Please Enter Proper Credentials");
					RequestDispatcher rd=req.getRequestDispatcher("Deposit.html");
					rd.forward(req,resp);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	}

}
