package View;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.User;
import Model.UserDao;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//User u = (User) request.getSession(false).getAttribute("regUser");
		String email,password;
		UserDao ud = new UserDao();
		try {
		 email=request.getParameter("email");
		 password=request.getParameter("password");
		 ArrayList<User> list = ud.selectUser();
		 for (User u : list) {
			 if(u.getEmail().equals(email)&&u.getPassword().equals(password)) {
					request.getRequestDispatcher("BuyCar.jsp").forward(request, response);
					 }else{
						 request.getRequestDispatcher("LoginFail.jsp").forward(request, response);
					 }	
	    }
		}catch(Exception e) {
		e.printStackTrace();
	    }
	}

}

