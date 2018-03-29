package connection;

public class Parametres {

	private String url;
	private String user;
	private String pwd;
	
	public Parametres(){
		this.url = "jdbc:mysql://database-etudiants.iut.univ-paris8.fr/dutinfopw201647";
		this.user = "dutinfopw201647";
		this.pwd = "maveradu";
	}
	
	public String getURL(){
		return this.url;
	}
	
	public String getUser(){
		return this.user;
	}
	
	public String getPwd(){
		return this.pwd;
	}
	
}
