<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
    /* Stylish button styles */
    input[type="submit"] {
        background-color: #3498DB; /* Blue background color */
        color: #fff;
        border: none;
        padding: 10px 20px;
        cursor: pointer;
    }

    input[type="submit"]:hover {
        background-color: #0074D9; /* Darker blue background color on hover */
    }
    
    body {
        font-family: Arial, sans-serif;
        text-align: center;
        background-color: #1aa4b8; /* Light blue background color */
        margin: 0;
        padding: 0;
        
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100vh;
    }
</style>
</head>
<body>
<h1></h1>
${transaction_table}
<form action="bank.jsp">
    <input type="submit" value="Back to Home">
</form>
</body>
</html>
