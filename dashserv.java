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
public class dashserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dashserv() {
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
		
		Connection connection=null;
		Statement st=null;
		Statement st1=null;
		String query=null;
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
			
			out.println("<!DOCTYPE html>");
			out.println("<html lang=\"en\">");
			out.println("<head>");
			out.println("<meta charset=\"UTF-8\">");
			out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
			out.println("<title>Your Page Title</title>");
			out.println("<style>");

			
			out.println("body {");
			out.println("    font-family: 'Aldrich';");
			out.println("    display: flex;");
			out.println("    align-items: center;");
			out.println("    justify-content: center;");
			out.println("    height: 100vh;");
			out.println("    margin: 0;");
			out.println("    background: linear-gradient(to right, rgb(182, 80, 80), rgb(183, 26, 3));");
			out.println("}");

			out.println("div {");
			out.println("    max-width: 400px;");
			out.println("    padding: 5%;");
			out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
			out.println("    background-color: #f5f5f5;");
			out.println("    border-radius: 10px;");
			out.println("    position: relative;");
			out.println("}");

			out.println("h1, p {");
			out.println("    text-align: center;");
			out.println("    color: #333;");
			out.println("}");

			out.println("</style>");
			out.println("</head>");
			out.println("<body>");
			out.print("<div>");
				st=connection.createStatement();
				query="select count(*) from account";
				ResultSet rs=st.executeQuery(query);
				
				if(rs.next()) {
					int p=rs.getInt("count");
					out.println("<h3>TOTAL NO. OF SAVINGS ACCOUNT HOLDERS:</h3><h4>"+p+"</h4>");	
					out.println("<br>");
				}
				else {
					out.println("<script>alert('Request failed.');</script>");
				}
				rs.close();
				
				st=connection.createStatement();
				query="select count(*) from schemes_enrolled";
				ResultSet rs1=st.executeQuery(query);
				if(rs1.next()) {
					int p=rs1.getInt("count");
					out.println("<h3>TOTAL NO. OF SPECIAL SCHEMES SAVINGS ACCOUNTS:</h3><h4>"+p+"</h4>");	
					out.println("<br>");
				}
				else {
					out.println("<script>alert('Request failed.');</script>");
				}
				rs1.close();
				
				st=connection.createStatement();
				query="select count(*) from transaction";
				ResultSet rs2=st.executeQuery(query);
				if(rs2.next()) {
					int p=rs2.getInt("count");
					out.println("<h3>TOTAL NO. OF TRANSACTIONS:</h3><h4>"+p+"</h4>");
					out.println("<br>");
				}
				else {
					out.print("<script>alert('Request failed.');</script>");
				}
				rs2.close();
			
			
				
			st.close();
			connection.close();
			
		}catch(Exception e){
			e.printStackTrace();
			out.println("<h1>Request failed because of server exception</h1>");
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
