<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Account Registration</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f0f0f0;
      text-align: center;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }
    .form-container {
      background-color: #fff;
      border: 1px solid #ccc;
      border-radius: 5px;
      padding: 20px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    }
    label {
      display: block;
      font-weight: bold;
      margin-bottom: 5px;
    }
    input[type="text"],
    input[type="number"],
    input[type="password"],
    input[type="tel"] {
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
      margin-top: 10px
    }
    input[type="submit"]:hover {
      background-color: #0074D9;
    }
  </style>
</head>
<body>
  <div class="form-container">
    <h1>Account Registration</h1>
    <form action="regForm" method="post">
      <label for="name">Name:</label>
      <input type="text" id="name" name="name" placeholder="Enter Name" required>
      
      <label for="accountNo">Account Number:</label>
      <input type="number" id="accountNo" name="accountNo" placeholder="Enter Account Number" required>
      
      <label for="cardNo">Card Number:</label>
      <input type="number" id="cardNo" name="cardNo" placeholder="Enter Card Number" required>
      
      <label for="pin">PIN:</label>
      <input type="password" id="pin" name="pin" placeholder="Enter PIN" required>
      
      <label for="confirmPin">Confirm PIN:</label>
      <input type="password" id="confirmPin" name="confirmPin" placeholder="Confirm PIN" required>
      
      <label for="phone">Phone Number:</label>
      <input type="tel" id="phone" name="phone" placeholder="Enter Phone Number" required>
      
      <label for="branchName">Branch Name:</label>
      <input type="text" id="branchName" name="branchName" placeholder="Enter Branch Name" required>
      
      <label for="branchCode">Branch Code:</label>
      <input type="text" id="branchCode" name="branchCode" placeholder="Enter Branch Code" required>
      
      <input type="submit" value="Register">
      <div class="success-message">
                <strong>${m}</strong>
            </div>
    </form>
    <div class="back-button">
            <form action="index.jsp">
                <input type="submit" class="button" value="Back to Log In Page">
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
