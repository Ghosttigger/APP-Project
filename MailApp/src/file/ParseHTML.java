package file;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ParseHTML {
	
	private Document doc;

	public ParseHTML(String url) {

		if(!url.contains("http://"))
			url = "http://"+url;
		Document page = getPage(url, true);
		Element body = page.body();
		String pageWithoutTags = removeTag(body);
	
		this.doc = Jsoup.parseBodyFragment(pageWithoutTags);
		
		//System.out.println(pageEquals(getContent("src/files/old"), doc1.html()));
		//comp("src/files/old", doc1.html());
		//test();
	}
	
	public boolean pageEquals(String old, String news) {
		return old.equals(news);
	}
	
	public void comp(String old, String news) {
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
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Document getPage(String url, boolean proxy) {
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
	
	public String removeTag(Element doc) {
		String code = doc.html();
		String pattern;
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream("WEB-INF/files/deleteTags")));
			try {
				while ((pattern = br.readLine()) != null) {
					code = code.replaceAll(pattern, "");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//code = code.replaceAll("(?=<!--)([\\s\\S]*?-->)", ""); // remove comments
		//code = code.replaceAll("(?=<script)([\\s\\S]*?/script>)", ""); // remove <script>...</script> tags
		//code = code.replaceAll("(?=<noscript)([\\s\\S]*?/noscript>)", ""); // remove <noscript>...</noscript> tags*/
		return code;
	}
	
	public Document getDoc() {
		return this.doc;
	}
	
}