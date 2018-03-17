package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import mail.Site;

public class Main {

	public static void main(String[] args) {

		Site site = new Site("http://www.lemonde.fr", "AAAAA", 10000, 0);
		ArrayList<Site> list_sites = new ArrayList<>();
		list_sites.add(site);
		String u = "http://www.iut.univ-paris8.fr/";
		
		System.out.println(u.replaceAll("http://|https://", "").replaceAll("/", "_"));
		
		
		while(true) {
			try {
				Thread.sleep(1000);
				for(Site s : list_sites) {
					if(System.currentTimeMillis()-s.getLast_update()>= s.getFrequence()) {
						//System.out.println("notif");
						//s.getUrl();
						String new_page = getPageCleaned("http://www.iut.univ-paris8.fr/");
						String old_page = getOldPage("www.iut.univ-paris8.fr");
						
						if(old_page == null) {
							writeFile(new_page, "www.iut.univ-paris8.fr");
						}
						
						if(!new_page.equals(old_page)) {
							writeFile(new_page, "www.iut.univ-paris8.fr");
							//send notif
							System.out.println("write");
						}
						
						s.setLast_update(System.currentTimeMillis());
					}
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	/*public static boolean pageEquals(String old_site, String new_site) {
		return getContent(old_site).equals(new_site);
	}*/
	
	public static String getPageCleaned(String url) {
		Document page = getPage(url, true);
		Element body = page.body();
		String pageWithoutTags = removeTag(body);
		Document doc1 = Jsoup.parseBodyFragment(pageWithoutTags);
		return doc1.html();
	}
	
	public static String getOldPage(String url) {
		//stocker uniquement le hash de la page
		String path = "src/files/old_"+url;
		File f = new File(path);
		if(f.exists() && !f.isDirectory()) {
		   return getContent(path);
		}
		else {
			return null;
		}
	}
	
	public static String fotmatURL(String url) {
		return url;
	}
	
	public static boolean pageEquals(String old, String news) {
		return old.equals(news);
	}
	
	public static void writeFile(String new_site, String url) {
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter("src/files/old_"+url));
			out.append(new_site);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	public static void comp(String old, String news) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(old)));
			char c;
			int i;
			int j = 0;
			boolean eq = true;
			
			try {
				while((i = br.read()) != -1 && eq) {
					c = (char)i;
					System.out.print(c);
					if(c != news.charAt(j)) {
						eq = false;
						System.out.println("\nDifferent caractere at: "+j);
					}
					j++;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	*/
	private static String getContent(String path) {
		String content = new String();
		try {
			content = new String(Files.readAllBytes(Paths.get(path)));
			//System.out.println(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
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
	
	public static String removeTag(Element doc) {
		String code = doc.html();
		code = code.replaceAll("(?=<!--)([\\s\\S]*?-->)", ""); // remove comments
		code = code.replaceAll("(?=<script)([\\s\\S]*?/script>)", ""); // remove <script>...</script> tags
		code = code.replaceAll("(?=<noscript)([\\s\\S]*?/noscript>)", ""); // remove <noscript>...</noscript> tags
		return code;
	}
	
	/*
	public static byte[] hash(String s) throws NoSuchAlgorithmException {
	    MessageDigest sha256 = MessageDigest.getInstance("SHA-256");        
	    byte[] passBytes = s.getBytes();
	    byte[] passHash = sha256.digest(passBytes);
	    return passHash;

	}
	*/
}
