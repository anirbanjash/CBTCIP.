<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PIN Verification</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background: #f0f0f0;
        text-align: center;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .pin-container {
        background-color: #fff;
        border: 1px solid #ccc;
        border-radius: 5px;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        text-align: center;
        width: 400px;
    }

    h1 {
        font-size: 24px;
        color: #3498DB;
    }

    form {
        display: flex;
        flex-direction: column;
    }

    label {
        margin-bottom: 5px;
        font-weight: bold;
    }

    input[type="password"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 3px;
    }

    input[type="submit"] {
        background-color: #3498DB;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 3px;
        cursor: pointer;
        transition: background 0.3s;
    }

    input[type="submit"]:hover {
        background-color: #0074D9;
    }
</style>
</head>
<body>
           
   
  <%
    int tramount=Integer.parseInt(request.getParameter("tamount"));
    session.setAttribute("transferamount",tramount);
    long acoount_no_tr=Long.parseLong(request.getParameter("accountNumber"));
    session.setAttribute("t_account",acoount_no_tr); 
    String holdername=request.getParameter("accountHolderName");
    session.setAttribute("t_holder",holdername); 
  %>
  
<div class="pin-container">
    <h1>PIN Verification</h1>
    <form action="pinVerificationTransfer" method="post">
        <label for="tpin">Enter your 4-digit PIN:</label>
        <input type="password" name="tpin" id="tpin" pattern="[0-9]{4}" required>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
