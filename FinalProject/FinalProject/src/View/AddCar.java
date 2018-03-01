package View;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Car;
import Model.User;

/**
 * Servlet implementation class AddCar
 */
@WebServlet("/AddCar")
public class AddCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Car c = new Car();
		String id,name,type;
		double rentprice,buyprice;
		try {
		 id=request.getParameter("carid");
		 name=request.getParameter("carname");
		 type=request.getParameter("cartype");
		 rentprice=Double.parseDouble(request.getParameter("rentprice"));
		 buyprice=Double.parseDouble(request.getParameter("buyprice"));
		 c.setBuyPrice((double)buyprice);
		 c.setId(id);
		 c.setName(name);
		 c.setRentPrice(rentprice);
		 c.setType(type);
		 request.getSession().setAttribute("Car",c);
		 request.getRequestDispatcher("BuyCar.jsp").forward(request, response);
	    }catch(Exception e) {
		e.printStackTrace();
	    }
	}

}
