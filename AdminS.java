package com.adminservlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admindao.AdminDao;
import com.adminmodel.Admin;
import com.adminmodel.Student;

@WebServlet("/")
public class AdminS extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminS() {
        super();
    }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	String path = request.getServletPath();
    	switch(path) {
    	case "/validate":
    		adminValidate(request,response);
    	break;
    	case "/studentpage":
    		getStudentPage(request,response);
    		break;
    	case "/insert":
    		insertStudentDetails(request,response);
    		break;
    	case "/display":
    		displayStudentDetails(request,response);
    		break;
    	default:
    		getLoginPage(request,response);
    	break;
    	}
    }

    
    private void displayStudentDetails(HttpServletRequest request, HttpServletResponse response) {
		AdminDao ad = new AdminDao();
		ArrayList<Student> al = ad.display();
		request.setAttribute("display", al);
		RequestDispatcher rd = request.getRequestDispatcher("display.jsp");
		try {
			rd.forward(request,response);
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}


	private void insertStudentDetails(HttpServletRequest request, HttpServletResponse response) {
        String sname = request.getParameter("sname");
        String email = request.getParameter("semail");
        String sub = request.getParameter("sub");
        String gender = request.getParameter("gender");
        String education = request.getParameter("education");

        Student s1 = new Student(sname, email, sub, gender,  education); 
        AdminDao ad = new AdminDao();
        ad.insertStudentDetails(s1);
    }

	private void getStudentPage(HttpServletRequest request, HttpServletResponse response) {
    	RequestDispatcher rd = request.getRequestDispatcher("student.jsp");
        try 
        {
				rd.forward(request,response);
		} 
        catch (ServletException e) 
        {
				e.printStackTrace();
		}
	    catch (IOException e) 
        {
			e.printStackTrace();
		}
    }
	


	private void adminValidate(HttpServletRequest request, HttpServletResponse response)
    {
    	String email = request.getParameter("email");
        String password = request.getParameter("password");
        Admin a = new Admin(email, password);
        AdminDao ad = new AdminDao();
        String status = ad.checkLogin(a);

        
        if (status.equals("success")) 
        {
        	RequestDispatcher rd = request.getRequestDispatcher("administration.jsp");
    		try 
    		{
    			request.setAttribute("email", email);
                rd.forward(request, response);
    			
    		} 
    		catch (ServletException e)
    		{
    			e.printStackTrace();
    		} 
    		catch (IOException e) 
    		{
    			e.printStackTrace();
    		}
        }
        else 
        {
        	RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
            try 
            {
					rd.forward(request,response);
			} 
            catch (ServletException e) 
            {
					e.printStackTrace();
			}
		    catch (IOException e) 
            {
				e.printStackTrace();
			}
        }
		
	}

	private void getLoginPage(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
		try {
			rd.forward(request,response);
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
