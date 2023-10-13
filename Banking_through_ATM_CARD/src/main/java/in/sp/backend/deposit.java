package in.sp.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime; // Import for LocalDateTime
import java.time.format.DateTimeFormatter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/depositServlet")
public class deposit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        HttpSession hs = req.getSession();
        long accountno = Long.parseLong((String) hs.getAttribute("account_no"));

        int depositAmount = Integer.parseInt(req.getParameter("amount"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Anirban@2001");
            PreparedStatement ps = con.prepareStatement("UPDATE bankauth SET balance = balance + ? WHERE account_no = ?");
            ps.setInt(1, depositAmount);
            ps.setLong(2, accountno);
            int c = ps.executeUpdate();

            if (c != 0) {
                PreparedStatement ps1 = con.prepareStatement("select balance from bankauth WHERE account_no = ?");
                ps1.setLong(1, accountno);
                ResultSet rs = ps1.executeQuery();

                if (rs.next()) {
                    // Get the current date and time
                    LocalDateTime currentDateTime = LocalDateTime.now();

                    // Define a date and time format
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                    // Format the current date and time as a string
                    String formattedDateTime = currentDateTime.format(formatter);

                    // Insert the deposit history with date and time
                    PreparedStatement ps2 = con.prepareStatement("INSERT INTO bankhist VALUES(?,?,?,?)");
                    ps2.setLong(1, accountno);
                    String hist = depositAmount + " INR is deposited to your account";
                    ps2.setString(2, hist);
                    ps2.setString(3, formattedDateTime); // Add date and time
                    ps2.setInt(4,rs.getInt("balance"));
                    ps2.executeUpdate();
                    resp.setContentType("text/html");
                    hs.setAttribute("balance", rs.getInt("balance"));
                    String success1="Your transaction is successful , Your Current Balance  is : "+rs.getInt("balance");
                    hs.setAttribute("success1", success1);
                    RequestDispatcher rd = req.getRequestDispatcher("/deposit.jsp");
                    rd.include(req, resp);
                }
            }
        } catch (Exception e) {
        	 String success1=e.getMessage();
             hs.setAttribute("success1", success1);
             RequestDispatcher rd = req.getRequestDispatcher("/deposit.jsp");
             rd.include(req, resp);
        }
    }
}
