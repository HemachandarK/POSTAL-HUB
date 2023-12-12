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
public class newsserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newsserv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        ArrayList<String> userList = new ArrayList<>();

        // JDBC connection
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String host="localhost";
		String port="5432";
		String db_name="post";
		String username="postgres";
		String password="postgres";

        try {
        	Class.forName("org.postgresql.Driver");
			conn=DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+db_name+"",username,password);

            if (conn != null) {
                System.out.println("Connection OK");
            } else {
                System.out.println("Connection Not OK");
            }

            st = conn.createStatement();
            String query = "SELECT news_file FROM news"; // Adjust the query as per your database schema
            rs = st.executeQuery(query);

            // Populate the list with data from the database
            while (rs.next()) {
                userList.add(rs.getString("news_file"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Convert the list to JSON and send it as the response
        out.print("[\"" + String.join("\",\"", userList) + "\"]");
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
