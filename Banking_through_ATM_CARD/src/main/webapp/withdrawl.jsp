<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Withdraw Money</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #3498DB;
            text-align: center;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center; /* Center vertically */
            height: 100vh;
            color: #fff;
        }

        .withdraw-container {
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            text-align: center;
            width: 400px;
            color: #3498DB;
        }

        h1 {
            font-size: 24px;
            color: #fff;
            text-transform: uppercase;
        }

        h2 {
            color: #fff;
            font-size: 18px;
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

        .back-button {
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="withdraw-container">
       
        <form action="pin_withdrawl.jsp" method="post">
            <label for="withamount">Enter the amount to withdraw:</label>
            <input type="number" name="withamount" id="withamount" required>
            <input type="submit" value="Withdraw">
        </form>
        <div class="message">
            <div class="success-message">
                <strong>${success}</strong>
            </div>
        </div>
        <div class="back-button">
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
