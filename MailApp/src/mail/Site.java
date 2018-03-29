package mail;

import java.util.ArrayList;
import java.util.Iterator;

public class Site {

	private String url;
	private int frequence;
	private long last_update;
	private ArrayList<String> sections;
	
	public Site(String url, ArrayList<String> sections, int frequence, long last_update) {
		this.url = url;
		this.sections = sections;
		this.frequence = frequence;
		this.last_update = last_update;
	}

	public String getUrl() {
		return url;
	}
	
	public String getNom() {
		return (url.split("."))[0];
		
	}
	/*
	public String getSection() {
		return this.sections;
	}
*/
	public ArrayList<String> getSections() {
		return sections;
	}
	public void setSections(ArrayList<String> sections) {
		this.sections = sections;
	}
	
	public void addSection(String section) {
		this.sections.add(section);
	}
	
	public void removeSection(String section) {
		Iterator<String> it_sections = this.sections.iterator();
		
		while(it_sections.hasNext()) {
			
			String section_temp = it_sections.next();
			if(section_temp.equals(section)) {
				it_sections.remove();
			}
		}
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