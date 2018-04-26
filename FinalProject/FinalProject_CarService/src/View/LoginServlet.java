package View;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.AdminDao;
import Dao.UserDao;
import Model.Admin;
import Model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String email,password;
		UserDao ud = new UserDao();
        //AdminDao ad = new AdminDao();
		try {
		 email=request.getParameter("email");
		 password=request.getParameter("password"); 
		 System.out.println(2);
		 //Admin a = ad.selectAdmin(email);
		 User u = ud.selectUser(email);
		 System.out.println(1);
//		 if(email.equals("Tongky_t@outlook.com")&&password.equals("admin")) {
//			 request.getRequestDispatcher("AdminService.jsp").forward(request, response);
//			 return;
//		 }
//		 System.out.println(4);
//		 if(a.getEmail().equals(u.getEmail())) {
//			 System.out.println(3);
//			 request.getRequestDispatcher("AdminService.jsp").forward(request, response);
//			 return;
//		 }else {
//             System.out.println(5);
			 if(u.getEmail().equals(email)&&u.getPassword().equals(password)&&u.getRole().equals("admin")) {
		    request.getRequestDispatcher("AdminService.jsp").forward(request, response);
		     return;
			}else if(u.getEmail().equals(email)&&u.getPassword().equals(password)&&u.getRole().equals("customer")){			 
			request.getRequestDispatcher("CustomerService.jsp").forward(request, response);
			return;
			}else {
				request.getRequestDispatcher("LoginFail.jsp").forward(request, response);
				return;
			}
		 
		}catch(Exception e) {
		e.printStackTrace();
	    }
	}
}


