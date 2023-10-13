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
@WebServlet("/loginForm")
public class Login extends HttpServlet{
     @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 HttpSession hs=req.getSession();
    	 PrintWriter out=resp.getWriter(); 
    	 long cardno = Long.parseLong(req.getParameter("cardNumber"));

         int pin=Integer.parseInt(req.getParameter("pin"));
         
         try {
     		Class.forName("com.mysql.cj.jdbc.Driver");
     		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","Anirban@2001");
     		PreparedStatement ps=con.prepareStatement("select * from BankMain where card_no=? and pin=?");
     		
     		ps.setLong(1, cardno);
     		ps.setInt(2, pin);
     		
     		ResultSet rs=ps.executeQuery();
     		
     		if(rs.next()) {
     			
     			hs.setAttribute("name", rs.getString("name"));
     			hs.setAttribute("account_no", rs.getString("account_no"));
     			hs.setAttribute("pin_no", pin);
     			hs.setAttribute("holder_name", rs.getString("name"));
     			RequestDispatcher rd=req.getRequestDispatcher("/bank.jsp");
     			rd.include(req, resp);
     			
     		}else {
     			resp.setContentType("text/html");
     			String msg="<h3 style='color:red'>Card No and PIN is not valid </h3>";
     			hs.setAttribute("messa", msg);
     			RequestDispatcher rd=req.getRequestDispatcher("/index.jsp");
     			rd.include(req, resp);
     		}
     		
            }
         catch(Exception e) {
        	resp.setContentType("text/html");
        	String msg=e.getMessage();
        	hs.setAttribute("messa", msg);
  			RequestDispatcher rd=req.getRequestDispatcher("/index.jsp");
  			rd.include(req, resp);
         }
}
}
