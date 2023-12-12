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
public class upserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upserv() {
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
		String id=request.getParameter("uid");
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String email=request.getParameter("email");
		String opass=request.getParameter("oldPsw");
		String npass=request.getParameter("newPsw");
		String add=request.getParameter("add");
		String pin=request.getParameter("pincode");
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
				st=connection.createStatement();
				String query = "select * from users where password='"+opass+"' and user_id='"+id+"'";

				ResultSet rs=st.executeQuery(query);
				if(rs.next()) {
					st1=connection.createStatement();
					String query1 = "update users set f_name='" + fname + "', l_name='" + lname + "', address='" + add + "', pincode='" + pin + "', email='" + email + "', password='" + npass + "' where user_id='" + id + "'";

					

					int rs1=st1.executeUpdate(query1);
				if(rs1>0) {
					out.print("<script>alert('Updation Successful.');</script>");
				}
				else {
		            out.print("<script>alert('Updation failed.');</script>");
		        }
					
		        } 
				else {
					 out.print("<script>alert('Updation failed. Please enter correct details.');</script>");
				}
			st.close();
			connection.close();
			
		}catch(Exception e){
			e.printStackTrace();
			out.print("<script>alert('Updation failed.');</script>");
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
