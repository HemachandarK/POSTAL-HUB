package com.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class delserv
 */
public class delserv extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public delserv() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String ac = request.getParameter("accountNumber");
        String s = request.getParameter("sid");

        Connection connection = null;
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        String host = "localhost";
        String port = "5432";
        String db_name = "post";
        String username = "postgres";
        String password = "postgres";
        int accNo = Integer.parseInt(ac);
        int s1 = Integer.parseInt(s);

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + db_name + "",
                    username, password);

            if (connection != null) {
                System.out.println("Connection OK");
            } else {
                System.out.println("Connection Not OK");
            }
            if ("123".equals(s)) {
                String query = "SELECT * FROM account WHERE account_no=?";
                pst = connection.prepareStatement(query);
                pst.setInt(1, accNo);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    String query1 = "DELETE FROM account WHERE account_no=?";
                    pst1 = connection.prepareStatement(query1);
                    pst1.setInt(1, accNo);
                    int r = pst1.executeUpdate();

                    if (r> 0) {
                        out.print("<script>alert('Deletion Successful.');</script>");
                    } else {
                        out.print("<script>alert('Deletion failed.');</script>");
                    }
                } else {
                    out.print("<script>alert('No Account Found!');</script>");
                }
            } else {

                String query = "SELECT * FROM schemes_enrolled WHERE acc_no=? AND scheme_id=?";
                pst = connection.prepareStatement(query);
                pst.setInt(1, accNo);
                pst.setInt(2, s1);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    String query1 = "DELETE FROM schemes_enrolled WHERE acc_no=?";
                    pst1 = connection.prepareStatement(query1);
                    pst1.setInt(1, accNo);
                    int r = pst1.executeUpdate();

                    if (r > 0) {
                        out.print("<script>alert('Deletion Successful.');</script>");
                    } else {
                        out.print("<script>alert('Deletion failed.');</script>");
                    }
                } else {
                    out.print("<script>alert('No Account Found!');</script>");
                }

            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.print("<script>alert('Deletion Failed!.');</script>");
        } 
                    
              
                    
                
            
        
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
