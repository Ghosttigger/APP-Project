package user;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.Connect;

/**
 * Servlet implementation class ProfilServlet
 */
@WebServlet("/ProfilServlet")
public class ProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id_user = (int)session.getAttribute("id_user");

		if(id_user != 0) {

			Connect conn = new Connect();
			ResultSet result;
			try {
				result = conn.doQuery("select dateNotif, url, new_content from app_notification natural join app_site where idDestinataire=" + id_user + "");
				ResultSetMetaData rsmd = result.getMetaData();
				int nbColonnes = rsmd.getColumnCount();
				
				ArrayList<Timestamp> dates = new ArrayList<>();
				ArrayList<String> urls = new ArrayList<>();
				ArrayList<String> contents = new ArrayList<>();
				
				while(result.next()){
					dates.add(result.getTimestamp(1));
					urls.add(result.getString(2));
					contents.add(result.getString(3));
					for(int i=1; i<nbColonnes+1; i++)
						System.out.print(result.getString(i) + "\t\t");
					System.out.println();
				}
				
				request.setAttribute("dates", dates);
				request.setAttribute("urls", urls);
				request.setAttribute("contents", contents);
	            request.getRequestDispatcher("/historique.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
