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
public class loginserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginserv() {
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
		String user=request.getParameter("uname");
		String pass=request.getParameter("psw");
		
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
			if(request.getParameter("user-login")!=null) {
				st=connection.createStatement();
				String query="select * from users where email='"+user+"'and password='"+pass+"'";
				ResultSet rs=st.executeQuery(query);
				
				if(rs.next()) {
					String us=rs.getString("user_id");
					Statement st0=connection.createStatement();
					System.out.println(us);
					String query0="select * from account where user_id="+us;
					ResultSet rs0=st0.executeQuery(query0);
					Statement st1=connection.createStatement();
					String query1 = "SELECT upper(u.f_name) f_name, s.scheme_name, se.user_id, pof.post_office_id, po.office_name, se.balance " +
			                "FROM users u, schemes s, schemes_enrolled se, pincode po, post_office pof " +
			                "WHERE u.email='" + user + "' AND se.user_id=u.user_id AND se.scheme_id=s.scheme_id " +
			                "AND pof.pincode=po.pincode AND se.post_office_id=pof.post_office_id";
;
					ResultSet rs1=st1.executeQuery(query1);
					Statement st2=connection.createStatement();
					String query2="select * from transaction where user_id="+us+" or recipient_id="+us;
					ResultSet rs2=st2.executeQuery(query2);
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
						out.println("    height: 125vh;");
						out.println("    margin: 0;");
						out.println("    background: linear-gradient(to right, rgb(182, 80, 80), rgb(183, 26, 3));");
						out.println("}");

						out.println("div {");
						out.println("    width: 500px;");
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
						out.print("<h3><u>NAME:</u></h3><h1>" + rs1.getString("f_name") + "</h1>");
						out.print("<h3><u>SAVINGS ACCOUNT:</u></h3>");
						if(rs0.next()) {
						out.print("<h3><u>ACCOUNT NO:</u></h3> <p>" + rs0.getString("account_no") + "</p>");
						out.print("<h3><u>BALANCE:</u></h3><p> " + rs0.getString("balance") + "</p>");}
						out.print("<h3><u>ADDITIONAL SCHEMES:</u></h3>");
		                out.print("<h3><u>SCHEME NAME:</u></h3><p> " + rs1.getString("scheme_name") + "</p>");
		                out.print("<h3><u>USER ID:</u></h3> <p>" + rs1.getString("user_id") + "</p>");
		                out.print("<h3><u>POST OFFICE ID:</u></h3> <p>" + rs1.getString("post_office_id") + "</p>");
		                out.print("<h3><u>POST OFFICE:</u></h3> <p>" + rs1.getString("office_name") + "</p>");
		                out.print("<h3><u>BALANCE:</u></h3><p> " + rs1.getString("balance") + "</p>");
		        
		                
		                out.print("<h3><u>TRANSACTIONS:</u></h3>");
		                out.println("<table border='1' style='border-collapse: collapse;'>");
		                out.println("<tr>");
		                out.println("<th>User ID</th>");
		                out.println("<th>Transaction ID</th>");
		                out.println("<th>Amount</th>");
		                out.println("<th>Recipient ID</th>");
		                
		                out.println("</tr>");
		                
		                
		                while (rs2.next()) {
		                    out.println("<tr>");
		                    out.println("<td>" + rs2.getString("user_id") + "</td>");
		                    out.println("<td>" + rs2.getString("transaction_id") + "</td>");
		                    out.println("<td>" + rs2.getString("amount") + "</td>");
		                    out.println("<td>" + rs2.getString("recipient_id") + "</td>");
		                    
		                    out.println("</tr>");
		                }

		                out.println("</table>");
		            

		                out.print("</div>");
		                out.println("</body>");
		                out.println("</html>");
					}
					else {
						out.print("<script>alert('No Account has Been Created For This user[User should have Both basic savings account as well as any special scheme Account');</script>");
					}
				}
				else {
					out.print("<script>alert('Login failed. Please enter correct details.');</script>");
				}
				rs.close();
			}
			else if(request.getParameter("staff-login")!=null) {
				st=connection.createStatement();
				String query="select * from staff where email='"+user+"'and password='"+pass+"'";
				ResultSet rs=st.executeQuery(query);
				
				if(rs.next()) {
					response.sendRedirect("staff_login.html");
				}
				else {
					out.print("<script>alert('Registration failed. Please enter correct details.');</script>");
				}
				rs.close();
			}
			
			
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
