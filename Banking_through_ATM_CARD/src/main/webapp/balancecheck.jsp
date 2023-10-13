<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Account Balance</title>
  <style>
    body {
      font-family: 'Arial', sans-serif;
      background: linear-gradient(to right, #3498DB, #0074D9);
      text-align: center;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .balance-container {
      background-color: #ffffff;
      border: 2px solid #0074D9;
      border-radius: 10px;
      padding: 20px;
      box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
      text-align: center;
      width: 400px;
    }

    h1 {
      font-size: 28px;
      color: #0074D9;
      margin-bottom: 20px;
    }

    .balance-text {
      color: aqua;
      font-size: 24px;
      margin-bottom: 30px;
    }

    .back-button {
      background-color: #0074D9;
      color: white;
      padding: 10px 20px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      font-size: 18px;
      transition: background 0.3s;
    }
    

    .back-button:hover {
      background-color: #005DA0;
    }
  </style>
</head>
<body>

  <div class="balance-container">
    <h1>Your Current Bank Balance</h1>
    <p class="balance-text"> <strong>${balance}</strong>INR </p>
    <form action="bank.jsp">
      <input type="submit" class="back-button" value="Back to Bank">
    </form>
  </div>
</body>
</html>
