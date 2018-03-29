package app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.Connect;
import mail.Site;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 try {
	            String[] urls = request.getParameterValues("url");
	            String[] frequences = request.getParameterValues("frequence");
	            String[] sections = request.getParameterValues("section");
	            
	            ArrayList<Site> list = new ArrayList<>();
	            
	            Connect conn = new Connect();
	            HttpSession session = request.getSession();
	    		int id_user = (int)session.getAttribute("id_user");
	    		
	    		
	            for(int i=0;i<urls.length;i++){
	            	if(!urls[i].contains("http://"))
	        			urls[i] = "http://"+urls[i];
	            	ArrayList<String> listSections = new ArrayList<>();
	            	listSections.addAll(Arrays.asList(sections[i].split(";")));
	            	list.add(new Site(urls[i], listSections, Integer.parseInt(frequences[i]), System.currentTimeMillis()));
	            	conn.doQuery("insert into app_site values(default, '"+ urls[i] + "', '"+ "" +"', " + Integer.parseInt(frequences[i]) + ")");
	            	conn.doQuery("insert into app_sites_suivies values(default, " + id_user + ")");
	            }
	            System.out.println(list + "?!");
	            System.out.println(request.getSession().getAttribute("id_user"));
	            CheckComparaison comparaison = new CheckComparaison(list, (int)request.getSession().getAttribute("id_user"));
	            comparaison.start();
	            
	        } catch (SQLException e) {
				e.printStackTrace();
			} finally {
	        	response.sendRedirect("site.jsp");
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
