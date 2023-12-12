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
public class transerv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public transerv() {
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
		String rdc=request.getParameter("rid");
		String a=request.getParameter("amt");
		
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

				st=connection.createStatement();
				String query = "INSERT INTO transaction (user_id, amount, recipient_id) VALUES ('" + id + "', '" + a + "', '" + rdc + "')";


				int rs=st.executeUpdate(query);
				if(rs>0) {
					
					String query3 = "SELECT transaction_id FROM transaction order by transaction_id desc limit 1";
					st3 = connection.createStatement();
					
					
					ResultSet rs3 = st3.executeQuery(query3);

					if (rs3.next()) {
						out.print("<script>alert('Registration successful, Your TRANSACTION NO: " + rs3.getInt("transaction_id") + "');</script>");

					}

					// Close resources (ResultSet, PreparedStatement, Connection) after use
					rs3.close();
					
		        } 
				else {
					 out.print("<script>alert('Transaction failed. Please enter correct details.');</script>");
				}
			st.close();
			connection.close();
			
		}catch(Exception e){
			e.printStackTrace();
			out.print("<script>alert('Transaction failed.Due to Insufficient Balance');</script>");
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
