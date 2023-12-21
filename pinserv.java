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
public class pinserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pinserv() {
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
		String pin=request.getParameter("pinCode");
		
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
				String query="select * from pincode where pincode='"+pin+"'";
				ResultSet rs=st.executeQuery(query);
				
				if(rs.next()) {
					out.println("<header>\n"
							+ "    <a href=\"home.html\"><img id=\"logo\" src=\"logo.png\" alt=\"Indian Postal Service\" style=\"text-align: left;\"></a>\n"
							+ "    <div class=\"navbar\">\n"
							+ "        <a href=\"home.html\">Home</a>\n"
							+ "        <div class=\"dropdown\">\n"
							+ "        <button class=\"dropbtn\" onclick=\"drop()\">Services</button>\n"
							+ "        <div class=\"dropdown-content\" id=\"myDropdown\">\n"
							+ "          <a href=\"locpost.html\">Locate Post Office</a>\n"
							+ "          <a href=\"findpin.html\">Find Pincode</a>\n"
							+ "          <a href=\"schemes.html\">Savings Schemes</a>\n"
							+ "          <a href=\"complaint.html\">Complaints</a>\n"
							+ "        </div>\n"
							+ "        </div> \n"
							+ "        <a href=\"track.html\">Track Shipment</a>\n"
							+ "        <a href=\"stamps.html\">Stamps</a>\n"
							+ "        <span id=\"userNamePlaceholder\"></span>\n"
							+ "        <button class=\"login-button\" onclick=\"document.getElementById('id01').style.display='block'\" style=\"width:auto;\">Login</button>\n"
							+ "      </div>\n"
							+ "    </header>");
					out.println("available");
					out.println("<footer>\n"
							+ "        <div class=\"footer-content\">\n"
							+ "            <div class=\"contact-info\">\n"
							+ "                <div class=\"contact-item\">\n"
							+ "                    <h4>Register No</h4>\n"
							+ "                    <p>2022115063<br>2022115086<br>2022115092</p>\n"
							+ "                </div>\n"
							+ "                <div class=\"vertical-line\"></div>\n"
							+ "                <div class=\"contact-item\">\n"
							+ "                    <h4>Email</h4>\n"
							+ "                    <p>r.ajaykumars@gmail.com</p>\n"
							+ "                </div>\n"
							+ "                <div class=\"vertical-line\"></div>\n"
							+ "                <div class=\"contact-item\">\n"
							+ "                    <h4>Submitted by:</h4>\n"
							+ "                    <p>Vijay K G<br>Ajay Kumar R<br>Hemachandar K</p>\n"
							+ "                </div>\n"
							+ "            </div>\n"
							+ "        </div>\n"
							+ "        <!--<p style=\"font-family: Aldrich; text-align: center; background-color: #cb0d0d; color: white;\">&copy; 2023 Postal Service. All rights reserved.</p>-->\n"
							+ "    </footer>");
				}
				else {
					out.print("<h1>Not available!!</h1>");
				}
				rs.close();
			
			
			st.close();
			connection.close();
			
			/*st=connection.createStatement();
			String query="select * from admin where user_name='"+user+"'and password='"+pass+"'";
			ResultSet rs=st.executeQuery(query);
			
			if(rs.next()) {
				out.print("<h1> Hello"+user+"welcome to our Website </h1><br>");
				out.print("<h1>Login successfull!!</h1>");
			}
			else {
				out.print("<h1> Hello"+user+"Please Enter correct Details</h1><br>");
				out.print("<h1>Login Failed!!</h1>");
			}
			rs.close();
			st.close();
			connection.close();*/
			
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
