package in.sp.backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/pinVerificationServlet")
public class Pin_Withdrawl extends HttpServlet {
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       HttpSession hs = req.getSession();
       int pin_actual = (Integer) hs.getAttribute("pin_no");
       int pin_input = Integer.parseInt(req.getParameter("wpin"));
       int wamount = (Integer) hs.getAttribute("withdrawamount");
       long accountno = Long.parseLong((String) hs.getAttribute("account_no"));
       if (pin_actual == pin_input) {
           try {
               Class.forName("com.mysql.cj.jdbc.Driver");
               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Anirban@2001");
               PreparedStatement ps3 = con.prepareStatement("select balance from bankauth WHERE account_no = ?");
               ps3.setLong(1, accountno);
               ResultSet rs4 = ps3.executeQuery();
               if (rs4.next()) {
                   if (rs4.getInt(1) > wamount) {
                       PreparedStatement ps = con.prepareStatement("UPDATE bankauth SET balance = balance - ? WHERE account_no = ?");
                       ps.setInt(1, wamount);
                       ps.setLong(2, accountno);
                       int c = ps.executeUpdate();
                       if (c != 0) {
                           PreparedStatement ps1 = con.prepareStatement("select balance from bankauth WHERE account_no = ?");
                           ps1.setLong(1, accountno);
                           ResultSet rs = ps1.executeQuery();
                           if (rs.next()) {
                               LocalDateTime currentDateTime = LocalDateTime.now();
                               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                               String formattedDateTime = currentDateTime.format(formatter);
                               PreparedStatement ps2 = con.prepareStatement("INSERT INTO bankhist VALUES(?,?,?,?)");
                               ps2.setLong(1, accountno);
                               String hist = wamount + " INR has withdrawn from your account";
                               ps2.setString(2, hist);
                               ps2.setString(3, formattedDateTime);
                               ps2.setInt(4, rs.getInt("balance"));
                               ps2.executeUpdate();
                               resp.setContentType("text/html");
                               hs.setAttribute("balance", rs.getInt("balance"));
                               String name = "Your transaction is successful. Your current balance is: " + rs.getInt("balance");
                               hs.setAttribute("success", name);
                               RequestDispatcher rd = req.getRequestDispatcher("/withdrawl.jsp");
                               rd.include(req, resp);
                           } else {
                               resp.setContentType("text/html");
                               String name = "<h3 style='color:red'>Your transaction is not successful</h3>";
                               hs.setAttribute("success", name);
                               RequestDispatcher rd = req.getRequestDispatcher("/withdrawl.jsp");
                               rd.include(req, resp);
                           }
                       } else {
                           resp.setContentType("text/html");
                           String name = "<h3 style='color:red'>Your transaction is not successful</h3>";
                           hs.setAttribute("success", name);
                           RequestDispatcher rd = req.getRequestDispatcher("/withdrawl.jsp");
                           rd.include(req, resp);
                       }
                   } else {
                       resp.setContentType("text/html");
                       String name = "<h3 style='color:red'>Insufficient Balance</h3>";
                       hs.setAttribute("success", name);
                       RequestDispatcher rd = req.getRequestDispatcher("/withdrawl.jsp");
                       rd.include(req, resp);
                   }
               } else {
                   resp.setContentType("text/html");
                   String name = "<h3 style='color:red'>No data found</h3>";
                   hs.setAttribute("success", name);
                   RequestDispatcher rd = req.getRequestDispatcher("/withdrawl.jsp");
                   rd.include(req, resp);
               }
           } catch (Exception e) {
               resp.setContentType("text/html");
               String name = e.getMessage();
               hs.setAttribute("success", name);
               RequestDispatcher rd = req.getRequestDispatcher("/withdrawl.jsp");
               rd.include(req, resp);
           }
       } else {
           resp.setContentType("text/html");
           String name = "Your transaction is not successful. You have entered the wrong PIN";
           hs.setAttribute("success", name);
           RequestDispatcher rd = req.getRequestDispatcher("/withdrawl.jsp");
           rd.include(req, resp);
       }
   }
}
