<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Deposit Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #3498DB, #0074D9);
            text-align: center;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .container {
            background: #fff;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
            padding: 20px;
            text-align: center;
            width: 400px;
            margin-top: 20px;
        }

        h1 {
            font-size: 24px;
            color: #3498DB;
        }

        h2 {
            color: #0074D9;
            font-size: 18px;
        }

        .input-container {
            margin-top: 20px;
        }

        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        .button-container {
            margin-top: 20px;
        }

        .button {
            background-color: #3498DB;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
            transition: background 0.3s;
        }

        .button:hover {
            background-color: #0074D9;
        }

        .back-button {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Deposit Money</h1>
        <h2>Welcome to ABI</h2>
        <form action="pin_transfer.jsp" method="post"> <!-- Replace "process_deposit.jsp" with your actual form handling page -->
            <div class="input-container">
                <label for="accountNumber">Account Number:</label>
                <input type="text" id="accountNumber" name="accountNumber" required>
            </div>
            <div class="input-container">
                <label for="accountHolderName">Account Holder Name:</label>
                <input type="text" id="accountHolderName" name="accountHolderName" required>
            </div>
            <div class="input-container">
                <label for="tamount">Amount:</label>
                <input type="number" id="tamount" name="tamount" required>
            </div>
            <div class="button-container">
                <button class="button" type="submit">Transfer</button>
            </div>
             <div class="message">
            <div class="success-message">
                <strong>${success11}</strong>
            </div>
        </div>
        </form>
        <div class="back-button">
            <form action="bank.jsp">
                <input type="submit" class="button" value="Back to Home">
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
