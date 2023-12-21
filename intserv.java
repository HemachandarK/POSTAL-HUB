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
public class intserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public intserv() {
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
		String s=request.getParameter("schemes");
		String a=request.getParameter("amt");
		Connection connection=null;
		Statement st=null;
		Statement st1=null;
		String host="postal.postgres.database.azure.com";
		String port="5432";
		String db_name="postgres";
		String username="post";
		String password="Phub@2023";
		
		try {
			
			Class.forName("org.postgresql.Driver");
			connection=DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+db_name+"",username,password);
			
			if(connection!=null) {
				System.out.println("Connection OK");
			}else {
				System.out.println("Connection Not OK");
			}
			
				st=connection.createStatement();
				String query="select interest_rate from schemes where scheme_id="+s;
				ResultSet rs=st.executeQuery(query);
				System.out.println(query);
				
				if(rs.next()) {
					int p=rs.getInt("interest_rate");
					int a1=Integer.parseInt(a);
					st1=connection.createStatement();
					String query1="select interest_rate("+p+","+a1+")";
					ResultSet rs1=st1.executeQuery(query1);
					if(rs1.next()) {
					out.print("<script>alert('TOTAL AMOUNT:" + rs1.getInt("interest_rate") + "');</script>");}

				}
				else {
					out.print("<script>alert('Request failed. Please enter correct details.');</script>");
				}
				rs.close();
			
			
			st.close();
			connection.close();
			
		}catch(Exception e){
			e.printStackTrace();
			
			out.print("<script>alert('Scheme\\'s Maximum Limit Exceeded!');</script>");

			
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
