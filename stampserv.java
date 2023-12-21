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
public class stampserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public stampserv() {
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
		String yr=request.getParameter("year");
		
		Connection connection=null;
		Statement st=null;
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
				String query="select * from stamp where (stamp_id,"+yr+") in (select stamp_id,extract(year from issue_date) from stamp);";
				ResultSet rs=st.executeQuery(query);
				System.out.println(query);
				
				if(rs.next()) {
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
					out.println("<h3><u>ID:</u></h3><p>"+rs.getString("stamp_id")+ "</p>");
					out.println("<img src='" + rs.getString("cover") + "'>");
					out.println("<h3><u>DESCRIPTION:</u></h3><p>"+rs.getString("description")+ "</p>");
					out.println("<h3><u>DENOMINATION:</u></h3><p>"+rs.getString("denomination")+ "</p>");
					out.println("<h3><u>DATE:</u></h3><p>"+rs.getString("issue_date")+ "</p>");
					out.print("</div>");
					out.println("</body>");
	                out.println("</html>");
				}
				else {
					out.print("<script>alert('Request failed. Please enter correct details.');</script>");
				}
				rs.close();
			
			
			st.close();
			connection.close();
			
		}catch(Exception e){
			e.printStackTrace();
			out.print("<h1>Login failed because of server exception</h1>");
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
