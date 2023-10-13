package in.sp.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/regForm")
public class Register extends HttpServlet {
   @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession hs = req.getSession();
        int mypin = 0;
        int myconfirmPin = 0;
        String mybranch = null;
        String mybranchcode = null;
        long mycardno = 0;
        String message = null;
        PrintWriter out = resp.getWriter();
        String myname = req.getParameter("name");
        long myaccount = Long.parseLong(req.getParameter("accountNo"));
        if (myaccount < 10000000 || myaccount > 99999999) {
            resp.setContentType("text/html");
            message = "<h3 style='color:red'>Account is not present </h3>";
            hs.setAttribute("m", message);
            RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
            rd.include(req, resp);
            return;
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Anirban@2001");
                PreparedStatement ps = con.prepareStatement("select * from bankauth where account_no=?");
                ps.setLong(1, myaccount);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    resp.setContentType("text/html");
                    message = "<h3 style='color:red'>Account does not exist. Please enter a valid account number.</h3>";
                    hs.setAttribute("m", message);
                    RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
                    rd.include(req, resp);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                resp.setContentType("text/html");
                message = e.getMessage();
                hs.setAttribute("m", message);
                RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
                rd.include(req, resp);
            }
            mycardno = Long.parseLong(req.getParameter("cardNo"));
            if (req.getParameter("cardNo").length() != 16) {
                resp.setContentType("text/html");
                message = "<h3 style='color:red'>CARD NO SHOULD BE OF 16 DIGITS</h3>";
                hs.setAttribute("m", message);
                RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
                rd.include(req, resp);
                return;
            } else {
                mypin = Integer.parseInt(req.getParameter("pin"));
                if (req.getParameter("pin").length() != 4) {
                    resp.setContentType("text/html");
                    message = "<h3 style='color:red'>PIN SHOULD BE OF 4 DIGITS</h3>";
                    hs.setAttribute("m", message);
                    RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
                    rd.include(req, resp);
                    return;
                } else {
                    myconfirmPin = Integer.parseInt(req.getParameter("confirmPin"));
                    mybranch = req.getParameter("branchName");
                    mybranchcode = req.getParameter("branchCode");
                }
            }
        }

        if (mypin == myconfirmPin) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Anirban@2001");
                PreparedStatement ps = con.prepareStatement("insert into BankMain values (?,?,?,?,?,?)");
                ps.setString(1, myname);
                ps.setLong(2, myaccount);
                ps.setLong(3, mycardno);
                ps.setInt(4, mypin);
                ps.setString(5, mybranch);
                ps.setString(6, mybranchcode);

                int count = ps.executeUpdate();
                if (count > 0) {
                    resp.setContentType("text/html");
                    message = "<h3 style='color:green'>User Registered Successfully </h3>";
                    hs.setAttribute("m", message);
                    RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
                    rd.include(req, resp);
                } else {
                    resp.setContentType("text/html");
                    message = "<h3 style='color:red'>User not Registered Successfully due to some error </h3>";
                    hs.setAttribute("m", message);
                    RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
                    rd.include(req, resp);
                }
            } catch (Exception e) {
                e.printStackTrace();
                resp.setContentType("text/html");
                message = e.getMessage();
                hs.setAttribute("m", message);
                RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
                rd.include(req, resp);
            }
        } else {
            resp.setContentType("text/html");
            message = "<h3 style='color:red'> Confirm Your PIN correctly </h3>";
            hs.setAttribute("m", message);
            RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
            rd.include(req, resp);
        }
    }
}
