package com.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Servlet implementation class stfacserv
 */
public class stfacserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public stfacserv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter();
		response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		String acc=request.getParameter("accountNumber");
		
		Connection connection=null;
		java.sql.Statement st=null;
		java.sql.Statement st1=null;
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
				String query="select u.f_name,a.* from account a,users u where a.account_no='"+acc+"'and u.user_id=a.user_id";
				ResultSet rs=st.executeQuery(query);
				
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
					out.print("<h3><u>SAVINGS ACCOUNT:</u></h3>");
					out.print("<h3><u>NAME:</u></h3><p>" + rs.getString("f_name") + "</p>");
					out.print("<h3><u>USER ID:</u></h3> <p>" + rs.getString("user_id") + "</p>");
					out.print("<h3><u>ACCOUNT NO:</u></h3> <p>" + rs.getString("account_no") + "</p>");
					out.print("<h3><u>BALANCE:</u></h3><p> " + rs.getString("balance") + "</p>");
					out.print("</div>");
					out.println("</body>");
	                out.println("</html>");
				}	
				else {
					st1=connection.createStatement();
					String query1="select u.f_name,a.*,s.scheme_name from schemes_enrolled a,users u,schemes s where a.acc_no='"+acc+"'and u.user_id=a.user_id and a.scheme_id=s.scheme_id";
					ResultSet rs1=st1.executeQuery(query1);
					if(rs1.next()) {
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
						out.println("<h3><u>SPECIAL SCHEMES ACCOUNT:</u></h3>");
						out.print("<h3><u>NAME:</u></h3><p>" + rs1.getString("f_name") + "</p>");
						out.print("<h3><u>USER ID:</u></h3> <p>" + rs1.getString("user_id") + "</p>");
						out.print("<h3><u>SCHEME NAME:</u></h3> <p>" + rs1.getString("scheme_name") + "</p>");
						out.print("<h3><u>ACCOUNT NO:</u></h3> <p>" + rs1.getString("acc_no") + "</p>");
						out.print("<h3><u>BALANCE:</u></h3><p> " + rs1.getString("balance") + "</p>");
						out.print("</div>");
						out.println("</body>");
		                out.println("</html>");
					}
					else {
						out.print("<script>alert('No Account Found!.');</script>");
					}
					st1.close();
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
