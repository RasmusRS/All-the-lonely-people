/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class MySQLConnect
 */
@WebServlet("/MySQLConnect")
public class MySQLConnect extends HttpServlet {

    private static final long serialVersionUID = 1L;
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/IS_202";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String user = request.getParameter("mail");
        String pass = request.getParameter("pass");
        System.out.println("**Passord =" + pass);
        
        
        Connection conn = null;
        Statement stmt = null;
        String password = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT mail, u_password FROM user";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            password = null;
            while (rs.next()) {
                password = rs.getString("u_password");
            }
            
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end 
        
        // check if username and password is correct
        if (pass.equals(password)){
            System.out.println("hey passordet er riktig");
        }else {
            System.out.println("passordet er feil");
        }
    }

}
