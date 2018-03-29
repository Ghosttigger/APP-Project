package user;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.JDBC4PreparedStatement;

import connection.Connect;



/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            Connect conn = new Connect();
            ResultSet getLastId = conn.doQuery("select max(id_user) from app_users");
            
            
            int lastId;
            
            if(getLastId.next())
            	lastId = getLastId.getInt(1)+1;
            else
            	lastId = 1;
            
            conn.doQuery("insert into app_users values("+lastId+", '"+email+"', '"+password+"')");
 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			response.sendRedirect("index.jsp");
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
