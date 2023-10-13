<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <%! 
    	int a=10;
        String name="Anirban Jash";
    	int area(){
    		return a*a;
    	}
    %>
    <%
    	out.println("a"+a);
        out.println("Name"+name);
        out.println(area());
        int b=20;
        if(b>15){
        	out.println("b is smaller than 20");
        }
        else{
        	out.println("b is 20");
        }
        for(int i=0;i<5;i++){
        	out.println(i);
        }
          	
    %>
    <%= a %>
    <%= area() %>
    <%= LocalDate.now() %>
    
        <form action="output.jsp" method ="get">
        	<input type="text" name="name1" placeholder="Enter Name"/>
        	<input type="submit"  value="Click Me">
        </form>
    
</body>
</html>