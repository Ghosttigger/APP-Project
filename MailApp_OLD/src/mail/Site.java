package mail;
public class Site {

	private String url;
	private String contenu;
	private int frequence;
	private long last_update;
	
	public Site(String url, String contenu, int frequence) {
		this.url = url;
		this.contenu = contenu;
		this.frequence = frequence;
	}
	
	public Site(String url, String contenu, int frequence, int last_update) {
		this.url = url;
		this.contenu = contenu;
		this.frequence = frequence;
		this.last_update = last_update;
	}

	public String getUrl() {
		return url;
	}
	
	public String getNom() {
		return (url.split("."))[0];
		
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public int getFrequence() {
		return frequence;
	}

	public void setFrequence(int frequence) {
		this.frequence = frequence;
	}

	public long getLast_update() {
		return last_update;
	}

	public void setLast_update(long last_update) {
		this.last_update = last_update;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}