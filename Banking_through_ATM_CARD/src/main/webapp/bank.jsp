<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>ABI Online Banking</title>
  <style>
    body {
      font-family: Roboto, sans-serif;
      background: linear-gradient(to right, #3498DB, #0074D9);
      text-align: center;
      margin: 0;
      padding: 0;
      display: flex;
      flex-direction: column;
      align-items: center;
      height: 100vh;
      justify-content: center; /* Vertically center the content */
    }

    .heading-container {
      background: linear-gradient(to right, #3498DB, #0074D9);
      width: 100%;
      padding: 20px 0;
      text-align: center;
      box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
    }

    h1 {
      font-size: 36px;
      color: #fff;
      font-family: 'Helvetica Neue', sans-serif;
      text-shadow: 2px 2px 4px #000000;
      margin: 0; /* Remove margin to align text at the top */
    }

    .bank-container {
      background: #ffffff;
      border: 1px solid #ccc;
      border-radius: 10px;
      box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
      padding: 20px;
      text-align: center;
      width: 400px;
      transform: translateZ(10px);
      transition: all 0.3s;
      margin-top: 20px; /* Move the container down */
    }

    .bank-container:hover {
      transform: translateZ(0);
      box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
    }

    ul {
      list-style-type: none;
      padding: 0;
    }

    li {
      margin: 15px 0;
    }

    .button-form {
      display: inline-block;
    }

    .button-link {
      text-decoration: none;
      background: linear-gradient(to right, #3498DB, #0074D9);
      color: white;
      padding: 12px 24px;
      border: 2px solid #005DA0;
      border-radius: 5px;
      cursor: pointer;
      display: inline-block;
      font-size: 18px;
      transition: transform 0.2s, box-shadow 0.2s;
      font-weight: bold;
      text-transform: uppercase;
      letter-spacing: 1px;
    }

    .button-link:hover {
      background: linear-gradient(to right, #0074D9, #005DA0);
      transform: scale(1.05);
      box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2);
    }
  </style>
</head>
<body>
  <div class="heading-container">
    <h1>Hi ${holder_name}</h1>
  </div>
  <div class="bank-container">
    <h1>ABI Online Banking</h1>
    <ul>
      <li>
        <div class="button-form">
          <form action="transactionHistory1" method="post">
            <button type="submit" class="button-link">Transaction History</button>
          </form>
        </div>
      </li>
      <li>
        <div class="button-form">
          <form action="withdrawl.jsp" method="post">
            <button type="submit" class="button-link">Withdraw</button>
          </form>
        </div>
      </li>
      <li>
        <div class="button-form">
          <form action="deposit.jsp" method="post">
            <button type="submit" class="button-link">Deposit</button>
          </form>
        </div>
      </li>
      <li>
        <div class="button-form">
          <form action="balanceCheck" method="post">
            <button type="submit" class="button-link">Balance Check</button>
          </form>
        </div>
      </li>
      <li>
        <div class="button-form">
          <form action="transfer.jsp" method="post">
            <button type="submit" class="button-link">Transfer</button>
          </form>
        </div>
      </li>
      <li>
        <div class="button-form">
          <form action="LogOut" method="post">
            <button type="submit" class="button-link">Log out</button>
          </form>
        </div>
      </li>
    </ul>
  </div>
</body>
</html>
