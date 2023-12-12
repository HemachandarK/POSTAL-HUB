package com.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class loginServlet
 */
public class compserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public compserv() {
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
		String ac=request.getParameter("accountNo");
	
		String cde=request.getParameter("complaintDescription");
		Connection connection=null;
		Statement st=null;
		Statement st1=null;
		String host="localhost";
		String port="5432";
		String db_name="post";
		String username="postgres";
		String password="postgres";
		
		try {
			
			Class.forName("org.postgresql.Driver");
			connection=DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+db_name+"",username,password);
			
			if(connection!=null) {
				System.out.println("Connection OK");
			}else {
				System.out.println("Connection Not OK");
			}
			st1=connection.createStatement();
			String query1 = "select user_id from users where email='"+ac+ "'";

			ResultSet rs1=st1.executeQuery(query1);
			if(rs1.next()) {
			String em=rs1.getString("user_id");
				st=connection.createStatement();
				String query = "INSERT INTO feedback (user_id,description) VALUES ('" + em+ "','" + cde + "')";
			
				
				int rs=st.executeUpdate(query);
				if(rs>0) {
					out.print("<script>alert('Sorry for Your Inconvinience We Will Look Into Your Problem Soon!');</script>");
					
		        } 
				else {
					 out.print("<script>alert('Registration failed. Please enter correct details.');</script>");
				}}
			st.close();
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
