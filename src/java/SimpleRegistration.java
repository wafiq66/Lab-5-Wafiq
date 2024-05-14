/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 *
 * @author user
 */
public class SimpleRegistration extends HttpServlet {
    
    private PreparedStatement pstmt;
            
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String lastname = request.getParameter("lastName");
        String firstname = request.getParameter("firstName");
        String mi = request.getParameter("mi");
        String phone = request.getParameter("telephone");
        String email = request.getParameter("email");
        String address = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String postcode = request.getParameter("postcode");
        
        try{
            if(lastname.length() ==0 || firstname.length()==0){
                out.println("Last Name and First Name are required");
                return;
            }
            storeStudent(lastname,firstname,mi,phone,email,address,city,state,postcode);
            out.println(firstname + " "+lastname +" is now reqistered in the database");
        } catch(Exception ex){
            out.println("Error: "+ex.getMessage());
        }finally{
            out.close();
        }
}
    private void initializeJdbc(){
        try{
            String driver = "org.apache.derby.jdbc.ClientDriver";
            String connectionString = "jdbc:derby://localhost1527/AddressDB;create=true;user=app;password=123";
            
            Class.forName(driver);
            
            Connection conn = DriverManager.getConnection(connectionString);
            pstmt = conn.prepareStatement("insert into Address "
                    + "(lastname, firstname,mi, telephone, email, street, city, state, postcode)"
                    + "values (?,?,?,?,?,?,?,?,?)"    
            );
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void storeStudent(String a,String b,String c,String d,String e,String f, String g, 
            String h, String i)throws SQLException{
        
        pstmt.setString(1, a);
        pstmt.setString(2, b);
        pstmt.setString(3, c);
        pstmt.setString(4, d);
        pstmt.setString(5, e);
        pstmt.setString(6, f);
        pstmt.setString(7, g);
        pstmt.setString(8, h);
        pstmt.setString(9, i);
        pstmt.executeUpdate();
        
    }
}
