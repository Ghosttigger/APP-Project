package connection;

import java.sql.*;

import mail.Notification;

public class Connect{
	
	private Parametres param;
	private Connection connexion;

	public Connect(){
		this.param = new Parametres();
		this.connexion = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection(param.getURL(), param.getUser(), param.getPwd());
		} catch (SQLException e) {
			System.out.println("Connexion a échoué...");
		} catch (ClassNotFoundException e) {
			System.out.println("Problème avec la classe Driver");
		} finally {
			System.out.println("Connexion établie !");
		}
		
	}

	public ResultSet doQuery(String request) throws SQLException {
		
		System.out.println("request : " + request);
		
		PreparedStatement prep = connexion.prepareStatement(request);
        
        if(request.startsWith("select"))
        	return prep.executeQuery();
        else
        	prep.execute();
        return null;
	}
	
	public void afficheQuery(ResultSet result){
		try {
			ResultSetMetaData rsmd = result.getMetaData();
			int nbColonnes = rsmd.getColumnCount();
			while(result.next()){
				for(int i=1; i<nbColonnes+1; i++)
					System.out.print(result.getString(i) + "\t\t");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return this.connexion;
	}
	
}
