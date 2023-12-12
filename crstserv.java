package com.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class loginServlet
 */
public class crstserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public crstserv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String ac=request.getParameter("sid");
		String p=request.getParameter("pid");
		String u=request.getParameter("uid");
		String b=request.getParameter("bl");
		Connection connection=null;
		Statement st=null;
		Statement st1=null;
		Statement st3=null;
		String host="localhost";
		String port="5432";
		String db_name="post";
		String username="postgres";
		String password="postgres";
		System.out.println(ac);
		System.out.println(p);
		System.out.println(u);
		try {
			
			Class.forName("org.postgresql.Driver");
			connection=DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+db_name+"",username,password);
			
			if(connection!=null) {
				System.out.println("Connection OK");
			}else {
				System.out.println("Connection Not OK");
			}
			if("123".equals(ac)) {
				st=connection.createStatement();
				String query = "INSERT INTO account (user_id, balance, post_office_id) VALUES (" + u+","+b+","+p+")";



				int rs=st.executeUpdate(query);
				if(rs>0) {
					st3 = connection.createStatement();
					String query3 = "SELECT account_no FROM account WHERE user_id ="+u;
					st3 = connection.createStatement();
					
					System.out.println(query3);
					ResultSet rs3 = st3.executeQuery(query3);

					if (rs3.next()) {
						out.print("<script>alert('Registration successful, Your ACCOUNT NO: " + rs3.getInt("account_no") + "');</script>");

					}

					// Close resources (ResultSet, PreparedStatement, Connection) after use
					rs3.close();
					

		        } 
				else {
					 out.print("<script>alert('Registration failed. Please enter correct details.');</script>");
				}
				st.close();
			}
			else {
				st1=connection.createStatement();
				String query1 = "INSERT INTO schemes_enrolled (scheme_id, user_id, post_office_id, balance) VALUES ('" + ac + "', " + u + ", " + p + ","+b+")";



				int rs1=st1.executeUpdate(query1);
				if(rs1>0) {
					st3 = connection.createStatement();
					String query3 = "SELECT acc_no FROM schemes_enrolled WHERE user_id =" + u + " AND scheme_id =" + ac + " ORDER BY acc_no DESC LIMIT 1";


					st3 = connection.createStatement();
					
					System.out.println(query3);
					ResultSet rs3 = st3.executeQuery(query3);

					if (rs3.next()) {
						out.print("<script>alert('Registration successful, Your ACCOUNT NO: " + rs3.getInt("acc_no") + "');</script>");

					}

					// Close resources (ResultSet, PreparedStatement, Connection) after use
					rs3.close();
					
					
					
		        } 
				else {
					 out.print("<script>alert('Registration failed. Please enter correct details.');</script>");
				}
				
			}
			
			connection.close();
			
		}catch(Exception e){
			e.printStackTrace();
			out.print("<script>alert('Registration failed. Already Existing User.');</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
