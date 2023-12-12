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
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class loginServlet
 */
public class trackserv extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public trackserv() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String tno = request.getParameter("trackingNumber");

        Connection connection = null;
        Statement st = null;
        String host = "localhost";
        String port = "5432";
        String db_name = "post";
        String username = "postgres";
        String password = "postgres";

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + db_name + "", username, password);

            if (connection != null) {
                System.out.println("Connection OK");
            } else {
                System.out.println("Connection Not OK");
            }

            st = connection.createStatement();
            String query = "SELECT p.post_name, ps.post_send_id, ps.sender, ps.receiver, ps.cost, pi.office_name,h.pincode, ps.status FROM pincode pi,post p, post_send ps,hub h WHERE ps.post_id=p.post_id and ps.hub_id=h.hub_id and pi.pincode=h.pincode and post_send_id = " + tno;
            System.out.println("Query: " + query); // Print the query for debugging purposes

            ResultSet rs = st.executeQuery(query);

            
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
				out.println("    width: 400px;");
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
            	out.print("<h3><u>POST:</u></h3><p>" + rs.getString("post_name") + "</p>");
                out.print("<h3><u>ID:</u></h3><p> " + rs.getString("post_send_id") + "</p>");
                out.print("<h3><u>SENDER:</u></h3> <p>" + rs.getString("sender") + "</p>");
                out.print("<h3><u>RECEIVER:</u></h3> <p>" + rs.getString("receiver") + "</p>");
                out.print("<h3><u>COST:</u></h3><p> " + rs.getString("cost") + "</p>");
                out.print("<h3><u>LOCATION:</u></h3><p> " + rs.getString("office_name") + "</p>");
                out.print("<h3><u>PINCODE:</u></h3><p> " + rs.getString("pincode") + "</p>");
                out.print("<h3><u>STATUS:</u></h3> <p>" + rs.getString("status") + "</p>");
                out.print("</div>");
				out.println("</body>");
                out.println("</html>");
            }
            else {
            	out.print("<script>alert('Not Found!');</script>");
            }

            rs.close();
            st.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"error\": \"Server exception\"}");
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
