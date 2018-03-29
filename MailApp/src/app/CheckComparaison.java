package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import connection.Connect;
import mail.MailServer;
import mail.Notification;
import mail.Site;

public class CheckComparaison extends Thread{

	private final String file_path = "files/test.xml";
	private ArrayList<Site> list_sites = new ArrayList<>();
	private MailServer ms;
	private Connect conn;
	private int id_user;

	public CheckComparaison(ArrayList<Site> list_sites, int id_user) {
		this.list_sites = list_sites;
		this.ms = new MailServer("smtp.iut.univ-paris8.fr", "jprochard@iut.univ-paris8.fr", "F4ZKa6R6e", "no-reply@iut.univ-paris8.fr");
		this.id_user = id_user;
		this.conn = new Connect();
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(60*1000);
				this.check();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public synchronized void maj(ArrayList<Site> list_sites) {
		this.list_sites = list_sites;
	}
	//stocker les sections sous la forme: politique;sport;actualite
	//pour les recuperer: split(;) et toArray pour set list
	public void check() {
		for(Site s : list_sites) {
			if(System.currentTimeMillis()-s.getLast_update()>= s.getFrequence()) {
				updateXML(s.getUrl(), "divTest", "#articleBasDroite", "");
				ArrayList<String> updates = checkUpdateSite(s);
				System.out.println("!!UPDATES :"+updates+" !!");
				if(!updates.isEmpty()) {
					String destinataire;
					ResultSet getDest;
					try {
						getDest = conn.doQuery("select email from app_users where id_user="+this.id_user);


						if(getDest.next())
							destinataire = getDest.getString(1);
						else
							destinataire = "jprochard@iut.univ-paris8.fr";

						Notification notif = new Notification(destinataire, s.getUrl(), updates.get(0));
						notif.setSite(s.getUrl());
						this.ms.sendNotif(notif);

						int lastIdNotif;
						ResultSet getLastId = conn.doQuery("select max(idNotif) from app_notification");
						if(getLastId.next())
							lastIdNotif = getLastId.getInt(1)+1;
						else
							lastIdNotif = 1;
						this.conn.doQuery("insert into app_notification values("+lastIdNotif+", "+ this.id_user +", "+ 1 +", '"+" new "+"', CURRENT_TIMESTAMP )");

					} catch (SQLException e) {
						e.printStackTrace();
					}
					s.setLast_update(System.currentTimeMillis());
				}
			}
		}
	}
	public ArrayList<String> checkUpdateSite(Site site) {
		ArrayList<String> updates = new ArrayList<>();
		ArrayList<String[]> contenus;

		Document page = getPage(site.getUrl(), true);
		Element body = cleanBody(page.body());
		contenus = getSectionsXML(site);

		for(String[] section : contenus) {
			String new_section = body.select(section[1]).html();
			String new_section_hash = hash(body.select(section[1]).html());
			String old_section = section[2];

			if(!new_section_hash.equals(old_section)) {
				this.updateXML(site.getUrl(), section[0], section[1], new_section_hash);
				updates.add(new_section);
			}
		}

		return updates;
	}
	//optimiser la maniere dont les sites sont update
	//ajoute des getText pour ne comparer/stocker/envoyer que les textes entre balises
	public ArrayList<String[]> getSectionsXML(Site site) {
		ArrayList<String[]> contenus = new ArrayList<>();
		org.jdom2.Document doc_xml = this.loadXML(this.file_path);
		boolean found_url = false;
		/*
		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());
		System.out.println(xmlOutput.outputString(doc_xml));
		 */
		List<org.jdom2.Element> sites = doc_xml.getRootElement().getChildren();

		Iterator<org.jdom2.Element> it_sites = sites.iterator();
		while(it_sites.hasNext() && !found_url) {
			org.jdom2.Element site_element = it_sites.next();
			//System.out.println(site_element.getChildText("url"));
			//org.jdom2.Element url_element = site_element.getChild("url");

			if(site_element.getChildText("url").equals(site.getUrl())) {
				found_url = true;

				Iterator<org.jdom2.Element> it_sections = site_element.getDescendants(new org.jdom2.filter.ElementFilter("section"));
				//System.out.println(it_sections.next());
				while(it_sections.hasNext()) {
					org.jdom2.Element section = it_sections.next();

					for(String section_name : site.getSections()) {
						//System.out.println(section_name+" "+section.get);
						if(section_name.equals(section.getAttributeValue("nom"))) {

							contenus.add(new String[] {section_name, section.getAttributeValue("chemin"), section.getText()});
						}
					}
				}
			}
		}

		return contenus;
	}

	public String updateXML(String url, String nom_section, String path_section, String contenu) {


		boolean found_url = false;

		org.jdom2.Document doc_xml = this.loadXML(this.file_path);
		List<org.jdom2.Element> sites = doc_xml.getRootElement().getChildren();

		Iterator<org.jdom2.Element> it_sites = sites.iterator();
		while(it_sites.hasNext() && !found_url) {
			org.jdom2.Element site = it_sites.next();
			//org.jdom2.Element url_element = site.getChild("url");

			if(site.getChildText("url").equals(url)) {
				found_url = true;

				boolean found_section = false;
				org.jdom2.Element sections = site.getChild("sections");
				List<org.jdom2.Element> sections_list = sections.getChildren();

				Iterator<org.jdom2.Element> it_sections = sections_list.iterator();
				while(it_sections.hasNext() && !found_section) {
					org.jdom2.Element section = it_sections.next();

					if(section.getAttributeValue("nom").equals(nom_section)) {
						found_section = true;
						section.setAttribute("chemin", path_section);
						section.setText(contenu);	
					}
				}

				if(!found_section) {
					org.jdom2.Element new_section = new org.jdom2.Element("section");

					new_section.setAttribute("nom", nom_section);
					new_section.setAttribute("chemin", path_section);
					new_section.setText(contenu);

					sections.addContent(new_section);			
				}
			}
		}

		if(!found_url) {
			org.jdom2.Element new_site_element = new org.jdom2.Element("site");
			org.jdom2.Element new_url_element = new org.jdom2.Element("url");
			org.jdom2.Element new_sections_element = new org.jdom2.Element("sections");
			org.jdom2.Element new_section_element = new org.jdom2.Element("section");

			new_url_element.setText(url);

			new_section_element.setAttribute("nom", nom_section);
			new_section_element.setAttribute("chemin", path_section);
			new_section_element.setText(contenu);

			new_sections_element.addContent(new_section_element);
			new_site_element.addContent(new_url_element);
			new_site_element.addContent(new_sections_element);

			doc_xml.getRootElement().addContent(new_site_element);
		}

		this.writeXML(file_path, doc_xml);
		return contenu;
	}

	public synchronized org.jdom2.Document loadXML(String file_path) {
		SAXBuilder builder = new SAXBuilder();
		File file_xml = new File(file_path);

		try {
			return (org.jdom2.Document) builder.build(file_xml);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized void writeXML(String file_path, org.jdom2.Document doc) {

		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());

		try {
			xmlOutput.output(doc, new FileWriter(file_path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String hash(String s) {
		MessageDigest sha256 = null;
		try {
			sha256 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}        
		byte[] passBytes = s.getBytes();
		byte[] passHash = sha256.digest(passBytes);
		return javax.xml.bind.DatatypeConverter.printHexBinary(passHash);
	}

	public static Document getPage(String url, boolean proxy) {
		try {
			if (proxy) {
				return Jsoup.connect(url).proxy("proxy", 3128).header("Proxy-Authorization", "Basic bHh1OkEyMjkzMzgxWg==").maxBodySize(0).get();
			}
			else {
				return Jsoup.connect(url).maxBodySize(0).get();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Element cleanBody(Element body) {
		body = removeTagWithSelect(body);
		body = removeTagWithRegex(body);

		return body;
	}

	public Element removeTagWithSelect(Element body) {
		String pattern;
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream("files/deleteTagsWithSelect")));
			try {
				while ((pattern = br.readLine()) != null) {
					body.select(pattern).remove();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return body;
	}

	public Element removeTagWithRegex(Element body) {
		String code = body.html();
		code = code.replaceAll("(?=<!--)([\\s\\S]*?-->)", ""); // remove comments
		code = code.replaceAll("(?=<script)([\\s\\S]*?/script>)", ""); // remove <script>...</script> tags
		code = code.replaceAll("(?=<noscript)([\\s\\S]*?/noscript>)", ""); // remove <noscript>...</noscript> tags
		return Jsoup.parseBodyFragment(code);
	}
}