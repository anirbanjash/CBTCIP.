<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>WELCOME TO ABI</title>
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
    .container-heading {
      text-shadow: 2px 2px 4px #000000;
      margin-bottom: 20px;
    }
    label {
      display: block;
      font-weight: bold;
      margin-bottom: 5px;
    }
    input[type="text"],
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
    .register-button {
      margin-top: 10px;
    }
  </style>
</head>
<body>
  <div class="form-container">
       <div class="success-message">
                <strong>${messa}</strong>
        </div>
    <h1 class="container-heading">WELCOME TO ABI ONLINE</h1>
    <form action="loginForm" method="post">
      <label for="cardNumber">Card Number:</label>
      <input type="text" id="cardNumber" name="cardNumber" placeholder="Enter Card Number" required>
      <label for="pin">PIN:</label>
      <input type="password" id="pin" name="pin" placeholder="Enter PIN" required>
      <input type="submit" value="Log In">
      <div class="success-message">
                <strong>${m}</strong>
        </div>
    </form>
    <a href="register.jsp" class="register-button">Register</a>
  </div>

  <script>
    // Remove the message after 4 seconds
    setTimeout(() => {
      const successMessages = document.querySelectorAll('.success-message');
      successMessages.forEach(message => {
        message.style.display = 'none';
      });
    }, 4000);
  </script>
</body>
</html>
