package in.sp.backend;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/guess_the_number")
public class MyServlet extends HttpServlet {
    private static int numberToGuess;
    private static int maxAttempts = 5;
    private static int numberOfAttempts = 0;
    
    @Override
    public void init() throws ServletException {
        numberToGuess = new Random().nextInt(100) + 1;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out=response.getWriter();
    	String guessParam = request.getParameter("guess");
    	System.out.println(numberToGuess);
        if (guessParam != null) {
            int userGuess = Integer.parseInt(guessParam);
            numberOfAttempts++;

            String result;
            if (userGuess < numberToGuess) {
                result = "The number is higher. Attempts left: " + (maxAttempts - numberOfAttempts);
                

            } else if (userGuess > numberToGuess) {
                result = "The number is lower. Attempts left: " + (maxAttempts - numberOfAttempts);
                

            } else {
                result = "🎊🎊🎊Congratulations! You've guessed the number (" + numberToGuess + ") in " + numberOfAttempts + " attempts🎆🎆🎆";
                HttpSession hs=request.getSession();
     			hs.setAttribute("res",result);
                RequestDispatcher rd=request.getRequestDispatcher("/success.jsp");
    			rd.include(request, response);
                numberOfAttempts=0;
                numberToGuess = new Random().nextInt(100) + 1;
                return;
            }

            if (numberOfAttempts == maxAttempts) {
                result = "🎃🎃🎃Sorry, you've reached the maximum number of attempts. The correct number was:🎃🎃🎃 " + numberToGuess;
                
                HttpSession hs=request.getSession();
     			hs.setAttribute("res",result);
                RequestDispatcher rd=request.getRequestDispatcher("/failure.jsp");
    			rd.include(request, response);
                numberOfAttempts=0;
                numberToGuess = new Random().nextInt(100) + 1;
                return;
            }
            response.setContentType("text/html");
			out.print("<h1 style='color:blue'>"+result+"</h1>");
			RequestDispatcher rd=request.getRequestDispatcher("/index.html");
			rd.include(request, response);

        }
    }
}
