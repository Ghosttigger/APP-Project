package user;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.Connect;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/ConnexionServlet")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Connect conn = new Connect();
		ResultSet getId;
		int id = 0;
		
		try {
			getId = conn.doQuery("select id_user from app_users where email='" + email + "' and password='" + password + "'");

			if(getId.next())
				id = getId.getInt(1);
			
			HttpSession session = request.getSession();
			session.setAttribute("id_user", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			response.sendRedirect("site.jsp");
        }
				
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
