<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Deposit Money</title>
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

        .deposit-container {
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

        input[type="number"] {
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

        .message {
            margin-top: 20px;
        }

        .success-message {
            color: green;
        }
    </style>
</head>
<body>
        

    <div class="deposit-container">
        <h1>Deposit Money</h1>
        <form action="depositServlet" method="post">
            <label for="amount">Enter the amount to deposit:</label>
            <input type="number" name="amount" id="amount" required>
            <input type="submit" value="Deposit">
        </form>
        <div class="message">
            <div class="success-message">
                    <strong>${success1}</strong>
            </div>
             <form action="bank.jsp">
            <input type="submit" value="Back to Home">
        </form>
        </div>
    </div>
    <script>
    // Remove the message after 2 seconds
    setTimeout(() => {
      const successMessages = document.querySelectorAll('.success-message');
      successMessages.forEach(message => {
        message.style.display = 'none';
      });
    }, 4000);
  </script>
</body>
</html>
