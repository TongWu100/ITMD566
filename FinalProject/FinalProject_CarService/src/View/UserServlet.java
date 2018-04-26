package View;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
		User u = new User();
		String username,password,email,streetaddress,city,state;
		try {
		 username=request.getParameter("username");
		 password=request.getParameter("password");
		 email=request.getParameter("email");
		 streetaddress=request.getParameter("streetaddress");
		 city=request.getParameter("city");
		 state=request.getParameter("state");
		 u.setUsername(username);
		 u.setPassword(password);
		 u.setEmail(email);
		 u.setStreetaddress(streetaddress);
		 u.setCity(city);
		 u.setState(state);
		 request.getSession().setAttribute("regUser",u);
		 request.getRequestDispatcher("Validate.jsp").forward(request, response);
	    }catch(Exception e) {
		e.printStackTrace();
	    }

 }
	}


