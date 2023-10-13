package in.sp.backend;

import java.io.IOException;
import java.util.Random;

import com.mysql.cj.protocol.Message;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.random.*;

//@WebServlet("/regform")
public class OTP extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Construct data
			String apiKey = "apikey=" + "MzY0OTYxNTQ2YjUwMzM3OTZiNDE0Nzc3NTgzMTQ3NjU=";
			Random rand=new Random();
			int otp=rand.nextInt(999999);
			HttpSession session=req.getSession();
			String name=(String)session.getAttribute("name");
			String message = "&message=" + "Hey "+name+"your OTP is "+otp;
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + req.getParameter("phone");
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
			
//			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
//			return "Error "+e;
		}

}
