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
public class crparserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public crparserv() {
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
		String snd=request.getParameter("sender");
		String rec=request.getParameter("receiver");
		String rad=request.getParameter("radd");
		String rp=request.getParameter("rpin");
		String pd=request.getParameter("pid");
		String hd=request.getParameter("hid");
		
		String cs=request.getParameter("cstat");
		String pst=request.getParameter("pstype");
		Connection connection=null;
		Statement st=null;
		Statement st3=null;
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
			Statement st1=connection.createStatement();
			String query1="select * from post where post_id="+pst;
			ResultSet rs1=st1.executeQuery(query1);
			
			if(rs1.next()) {
			
			
				st=connection.createStatement();
				String query = "INSERT INTO post_send (post_id, sender, receiver, recipient_address, recipient_pincode, post_office_id,cost,hub_id,status) VALUES ('" + rs1.getString("post_id")
				        + "', '" + snd + "', '" + rec + "', '" + rad + "', '" + rp + "', '" + pd + "','"+rs1.getString("cost")+"','" + hd + "','"+cs+"')";


				int rs=st.executeUpdate(query);
				if(rs>0) {
					st3 = connection.createStatement();
					String query3 = "SELECT post_send_id FROM post_send order by post_send_id desc limit 1";
					st3 = connection.createStatement();
					
					System.out.println(query3);
					ResultSet rs3 = st3.executeQuery(query3);

					if (rs3.next()) {
						out.print("<script>alert('Registration successful, Your TRACKING NO: " + rs3.getInt("post_send_id") + "');</script>");

					}

					// Close resources (ResultSet, PreparedStatement, Connection) after use
					rs3.close();
					
		        } 
				else {
					 out.print("<script>alert('Registration failed. Please enter correct details.');</script>");
				}
			st.close();
			connection.close();
			}
		}catch(Exception e){
			e.printStackTrace();
			out.print("<script>alert('Registration failed.');</script>");
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
