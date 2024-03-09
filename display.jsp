
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.adminmodel.Student" %>  

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <title>Student Page</title>
</head>
<body>
    <h1>STUDENT DETAILS</h1>
    <TABLE class="table table-striped">
    <thead class="thead-dark">
        <TR>
            <TH>ID</TH>  
            <TH>NAME</TH>
            <TH>EMAIL</TH>
            <TH>SUBJECT</TH>
            <TH>GENDER</TH>
            <TH>EDUCATION</TH>
        </TR>
        </thead>
        <% 
        ArrayList<Student> al = (ArrayList<Student>) request.getAttribute("display");
        for (Student s : al) { 
        %>
        <tbody>
        <tr>
            <td><%= s.getId() %></td>
            <td><%= s.getSname() %></td>
            <td><%= s.getSemail() %></td>
            <td><%= s.getSub() %></td>
            <td><%= s.getGender() %></td>
            <td><%= s.getEducation() %></td>
        </tr>
        </tbody>
        <%
           }
        %>
    </TABLE>
</body>
</html>
