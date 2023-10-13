package in.sp.backend;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/pinVerificationTransfer")
public class Pin_transfer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession hs = req.getSession();
        PrintWriter out = resp.getWriter();
        int pin_actual = (Integer) hs.getAttribute("pin_no");
        int pin_input = Integer.parseInt(req.getParameter("tpin"));
        int tamount = (Integer) hs.getAttribute("transferamount");
        long my_accountno = Long.parseLong((String) hs.getAttribute("account_no"));
        long tr_accoountno = (Long) hs.getAttribute("t_account");
        String tr_holder = (String) hs.getAttribute("t_holder");

        if (pin_actual == pin_input) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Anirban@2001");
                PreparedStatement ps8 = con.prepareStatement("select * from bankauth WHERE account_no = ?");
                ps8.setLong(1, tr_accoountno);
                ResultSet rs7 = ps8.executeQuery();
              if(rs7.next()) {
                PreparedStatement ps3 = con.prepareStatement("select balance from bankauth WHERE account_no = ?");
                ps3.setLong(1, my_accountno);
                ResultSet rs4 = ps3.executeQuery();

                if (rs4.next()) {
                    if (rs4.getInt(1) > tamount) {
                        PreparedStatement ps5 = con.prepareStatement("UPDATE bankauth SET balance = balance + ? WHERE account_no = ?");
                        ps5.setInt(1, tamount);
                        ps5.setLong(2, tr_accoountno);
                        int c1 = ps5.executeUpdate();

                        if (c1 != 0) {
                            PreparedStatement ps = con.prepareStatement("UPDATE bankauth SET balance = balance - ? WHERE account_no = ?");
                            ps.setInt(1, tamount);
                            ps.setLong(2, my_accountno);
                            int c = ps.executeUpdate();

                            if (c != 0) {
                                PreparedStatement ps1 = con.prepareStatement("select balance from bankauth WHERE account_no = ?");
                                ps1.setLong(1, my_accountno);
                                ResultSet rs = ps1.executeQuery();

                                if (rs.next()) {
                                    LocalDateTime currentDateTime = LocalDateTime.now();
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                    String formattedDateTime = currentDateTime.format(formatter);

                                    PreparedStatement ps2 = con.prepareStatement("INSERT INTO bankhist VALUES(?,?,?,?)");
                                    ps2.setLong(1, my_accountno);
                                    String hist = tamount + " INR has transferred from your account";
                                    ps2.setString(2, hist);
                                    ps2.setString(3, formattedDateTime);
                                    ps2.setInt(4, rs.getInt("balance"));
                                    ps2.executeUpdate();

                                    PreparedStatement ps7 = con.prepareStatement("select balance from bankauth WHERE account_no = ?");
                                    ps7.setLong(1, tr_accoountno);
                                    ResultSet rs3 = ps7.executeQuery();

                                    if (rs3.next()) {
                                        PreparedStatement ps6 = con.prepareStatement("INSERT INTO bankhist VALUES(?,?,?,?)");
                                        ps6.setLong(1, tr_accoountno);
                                        String hist1 = tamount + " INR is credited to your account";
                                        ps6.setString(2, hist1);
                                        ps6.setString(3, formattedDateTime);
                                        ps6.setInt(4, rs3.getInt("balance"));
                                        ps6.executeUpdate();

                                        resp.setContentType("text/html");
                                        hs.setAttribute("balance", rs.getInt("balance"));
                                        String name = tamount + " INR transferred to " + tr_holder + ", Your Current Balance is: " + rs.getInt("balance");
                                        hs.setAttribute("success11", name);
                                        RequestDispatcher rd = req.getRequestDispatcher("/transfer.jsp");
                                        rd.include(req, resp);
                                    } else {
                                        resp.setContentType("text/html");
                                        String name = "<h3 style='color:red'>Your transaction is not successful</h3>";
                                        hs.setAttribute("success11", name);
                                        RequestDispatcher rd = req.getRequestDispatcher("/transfer.jsp");
                                        rd.include(req, resp);
                                    }
                                } else {
                                    resp.setContentType("text/html");
                                    String name = "<h3 style='color:red'>Your transaction is not successful</h3>";
                                    hs.setAttribute("success11", name);
                                    RequestDispatcher rd = req.getRequestDispatcher("/transfer.jsp");
                                    rd.include(req, resp);
                                }
                            } else {
                                resp.setContentType("text/html");
                                String name = "<h3 style='color:red'>Your transaction is not successful</h3>";
                                hs.setAttribute("success11", name);
                                RequestDispatcher rd = req.getRequestDispatcher("/transfer.jsp");
                                rd.include(req, resp);
                            }
                        } else {
                            resp.setContentType("text/html");
                            String name = "<h3 style='color:red'>Wrong account number provided</h3>";
                            hs.setAttribute("success11", name);
                            RequestDispatcher rd = req.getRequestDispatcher("/transfer.jsp");
                            rd.include(req, resp);
                        }
                    } else {
                        resp.setContentType("text/html");
                        String name = "<h3 style='color:red'>Insufficient Balance</h3>";
                        hs.setAttribute("success11", name);
                        RequestDispatcher rd = req.getRequestDispatcher("/transfer.jsp");
                        rd.include(req, resp);
                    }
                } else {
                    // Handle the case where no data was found
                    resp.setContentType("text/html");
                    String name = "<h3 style='color:red'>No data found</h3>";
                    hs.setAttribute("success11", name);
                    RequestDispatcher rd = req.getRequestDispatcher("/transfer.jsp");
                    rd.include(req, resp);
                }
              }
            } catch (Exception e) {
                resp.setContentType("text/html");
                String name = "Exception occurred: " + e.getMessage();
                hs.setAttribute("success11", name);
                RequestDispatcher rd = req.getRequestDispatcher("/transfer.jsp");
                rd.include(req, resp);
            }
        } else {
            resp.setContentType("text/html");
            String name = "Your transaction is not successful, you have entered the wrong PIN";
            hs.setAttribute("success11", name);
            RequestDispatcher rd = req.getRequestDispatcher("/transfer.jsp");
            rd.include(req, resp);
        }
    }
}
