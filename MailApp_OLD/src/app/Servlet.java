package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.javafx.collections.ArrayListenerHelper;

import mail.MailServer;
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
	            
	            for(int i=0;i<urls.length;i++){
	            	list.add(new Site(urls[i], sections[i], Integer.parseInt(frequences[i])));
	            }
	            
	            MailServer ms = new MailServer("smtp.iut.univ-paris8.fr", "jprochard@iut.univ-paris8.fr", "F4ZKa6R6e", "no-reply@iut.univ-paris8.fr");
	            
	 
	        } finally {
	           
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
