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
@WebServlet("/transactionHistory1")
public class Transaction_history extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession hs=req.getSession();
 	   long accountno= Long.parseLong((String)hs.getAttribute("account_no"));
 	   
            
 		   try {
 			   Class.forName("com.mysql.cj.jdbc.Driver");
 		  		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","Anirban@2001");
 		  		PreparedStatement ps=con.prepareStatement("select * from bankhist WHERE account_no = ?");
 		  		ps.setLong(1,accountno);
 		  		ResultSet rs= ps.executeQuery();
 		  		String  data="<table align='center' border='1' bgcolor='lightblue'>";
                data=data+"<th>ACCOUNT NO</th><th>TRANSACTIONS</th><th>TIME</th><th>UPDATED BALANCE</th>";
                if(rs!=null){
                   while(rs.next()){
                       data=data+"<tr>";
                           data=data+"<td>"+rs.getInt(1)+"</td>";
                           data=data+"<td>"+rs.getString(2)+"</td>";
                           data=data+"<td>"+rs.getString(3)+"</td>";
                           data=data+"<td>"+rs.getInt(4)+"</td>";
                       data=data+"</tr>";
                   }
                   data=data+"</table>";
                }
                hs.setAttribute("transaction_table",data );
                RequestDispatcher rd=req.getRequestDispatcher("/transactionHistory.jsp");
  	   			rd.include(req, resp);
                   
 		   }catch(Exception e){
 			    resp.setContentType("text/html");
				
				String data=e.getMessage();
				hs.setAttribute("transaction_table", data);
				RequestDispatcher rd=req.getRequestDispatcher("/transactionHistory.jsp");
				rd.include(req, resp);
 		   }
    }
}
