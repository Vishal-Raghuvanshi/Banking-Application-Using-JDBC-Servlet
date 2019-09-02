package com.net.netbanking.first;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;




public class CreateProfileServlet extends HttpServlet {
	static String username;
	static String password;
	static int accno;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		
		
		
		 accno=Integer.parseInt(req.getParameter("accno"));
		String fName=req.getParameter("firstname");
		String mName=req.getParameter("middlename");
		String lName=req.getParameter("lastname");
		String[] str1=req.getParameterValues("gender");
		String email=req.getParameter("email");
		String address1=req.getParameter("address1");
		String address2=req.getParameter("address2");
		String dob=req.getParameter("DOB");
		String city=req.getParameter("city");
		String state=req.getParameter("state");
		String pincode=req.getParameter("pincode");
		String contactno=req.getParameter("contactno");
		 username=req.getParameter("username");
		 password=req.getParameter("password");
		String repassword=req.getParameter("repassword");
		
		
		/*
		out.print("<html>" + 
				"<body>" + 
				"</br></br><h2 align=\"center\"> The Data of the Following Registration table is -----</h2>" +
				"</br><Table border =\"4px\" bordercolor=\"black\" bgcolor=\"#CCCCCC\"" + 
				"align=\"center\" cellspacing=\"1px\" cellpadding=\"10px\" rules=\"all\">" + 
			
				"	  <tr><th>Account no</th>" + "	  <td>"+accno+"</td></tr>" + 
				"	 <tr> <th>firstname</th>" + " <td>"+fName+"</td></tr>" + 
				"	  <tr><th>middlename</th>" + "	  <td>"+mName+"</td></tr>" + 
				"	  <tr><th>lastname</th>" +"	  <td>"+lName+"</td></tr>" + 
				"	  <tr><th>gender</th>" +"	  <td>"+str1[0]+"</td></tr>" +
				"	  <tr><th>email</th>" +"	  <td>"+email+"</td></tr>" +
				"	  <tr><th>address1</th>" +"	  <td>"+address1+"</td></tr>" +
				"	  <tr><th>address2</th>" +"	  <td>"+address2+"</td></tr>" +
				"	  <tr><th>date of birth</th>" +"	  <td>"+dob+"</td></tr>" +
				"	  <tr><th>city</th>" +	"	  <td>"+city+"</td></tr>" +
				"	  <tr><th>state</th>" +"	  <td>"+state+"</td></tr>" +
				"	  <tr><th>pincode</th>" +"	  <td>"+pincode+"</td></tr>" +
				"	  <tr><th>contactno</th>" +"   <td>"+contactno+"</td></tr>" +
				"	  <tr><th>username</th>" +"	  <td>"+username+"</td></tr>" +
				"	  <tr><th>password</th>" +"	  <td>"+password+"</td></tr>" +
				"	  <tr><th>re-enter password</th>" +"  <td>"+repassword+"</td></tr></tr>");
			
		*/
			
		
		/* out.println("Account no =" = accno);
		 * out.println(" Firstname =" + fName);
		out.println(" Middlename =" + mName);
		out.println("Lastname =" + lName);
		out.println("Gender =" + str1[0]);
		out.println("Email id = "+ email);
		out.println("Address 1 ="+ address1);
		out.println("Address 2 ="+ address2);
		out.println("City ="+ city);
		out.println("State ="+ state);
		out.println("Pincode ="+ pincode);
		out.println("Contact No ="+ contactno);
		out.println("UserName ="+ username);
		out.println("Password ="+ password);
		out.println("Re-Enter Password ="+ repassword);
		*/
		
			try {
				Driver ref=new Driver();
				DriverManager.registerDriver(ref);
				
				
				String dburl="jdbc:mysql://localhost:3306/vishal?user=root&password=root";
				Connection con=DriverManager.getConnection(dburl);
				
				
				
				PreparedStatement pstmt1=con.prepareStatement(" insert into netbanking values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
				
				pstmt1.setInt(1,accno);
				pstmt1.setString(2,fName);
				pstmt1.setString(3,mName);
				pstmt1.setString(4,lName);
				pstmt1.setString(5,str1[0]);
				pstmt1.setString(6,address1);
				pstmt1.setString(7,address2);
				pstmt1.setString(8,dob);
				pstmt1.setString(9,city);
				pstmt1.setString(10,state);
				pstmt1.setString(11,pincode);
				pstmt1.setString(12,contactno);
				pstmt1.setString(13,username);
				pstmt1.setString(14,password);
				pstmt1.setString(15,repassword);
				
				
				
				int res1= pstmt1.executeUpdate();
				
				if (res1>0 && password.equals(repassword))
				{
					out.println("WELCOME! YOUR ACCOUNT HAS OPENED");
					RequestDispatcher rd=req.getRequestDispatcher("Signin.html");
					rd.forward(req, resp);
				}
				else
				{
					out.print("Sorry, Registration Failed. Please try again ");
					RequestDispatcher rd=req.getRequestDispatcher("RegistrationForm1.html");
					rd.forward(req, resp);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			out.close();
	}
	
}
	


