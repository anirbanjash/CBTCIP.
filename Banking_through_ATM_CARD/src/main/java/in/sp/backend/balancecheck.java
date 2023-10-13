package in.sp.backend;

import java.io.IOException;
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
@WebServlet("/balanceCheck")
public class balancecheck extends HttpServlet{
   @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   HttpSession hs=req.getSession();
	   long accountno= Long.parseLong((String)hs.getAttribute("account_no"));
	   try {
	    Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","Anirban@2001");
		PreparedStatement ps=con.prepareStatement("select balance from bankauth where account_no=?");
		ps.setLong(1, accountno);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			resp.setContentType("text/html");
 			hs.setAttribute("balance", rs.getInt("balance"));
 			RequestDispatcher rd=req.getRequestDispatcher("/balancecheck.jsp");
 			rd.include(req, resp);
		}
	    
	    }
	    catch(Exception e){
	    	
	    }
}
}
