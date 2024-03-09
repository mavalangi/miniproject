package com.admindao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.adminmodel.Admin;
import com.adminmodel.Student;
import com.adminservlet.AdminS;

public class AdminDao {
    String url = "jdbc:mysql://localhost:3306/akshata";
    String username = "root";
    String password = "Akshu@123";
    Connection con = null;
    Statement st = null;
    PreparedStatement ps = null;
    ArrayList<Student> al = null;
    

    public String checkLogin(Admin a) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from admin");
            while (rs.next()) {
                String demail = rs.getString("email");
                String dpassword = rs.getString("pw");
                if (demail.equals(a.getEmail()) && dpassword.equals(a.getPassword())) {
                    return "success";
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return "Failure";
    }
    
    //inserting data into db
    
    public void insertStudentDetails(Student s) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            st = con.createStatement();
            ps = con.prepareStatement("INSERT INTO student(id, sname, semail, sub, gender,  education) VALUES (?, ?, ?, ?, ?,?)");
            ps.setInt(1, s.getId());
            ps.setString(2, s.getSname());
            ps.setString(3, s.getSemail()); // Corrected column name
            ps.setString(4, s.getSub());
            ps.setString(5, s.getGender()); // Corrected column name
            ps.setString(6, s.getEducation());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
    }
        finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
  }
    
//extract data present in db // when data is coming frm the front end then use parameterand from backend set return type
     public ArrayList<Student> display() {
    	
    	 ArrayList<Student> al = new ArrayList<Student>();
    	
    	 try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection(url, username, password);
             st = con.createStatement();
             ResultSet rs = st.executeQuery("select * from student");
             while (rs.next()) 
             {
                 int id = rs.getInt("id");
                 String name = rs.getString("sname");
                 String email = rs.getString("semail");
                 String sub = rs.getString("sub");
                 String gender = rs.getString("gender");
                 String education = rs.getString("education");
                 Student s= new Student(id, name, email, sub, gender, education);
                 al.add(s);
             }
         } catch (ClassNotFoundException | SQLException e) {
             e.printStackTrace();
         }
    	 finally {
             try {
                 if (st != null) {
                     st.close();
                 }
                 if (con != null) {
                     con.close();
                 }
             } catch (SQLException e) {
                 e.printStackTrace();
             }
       }
		return al;
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
