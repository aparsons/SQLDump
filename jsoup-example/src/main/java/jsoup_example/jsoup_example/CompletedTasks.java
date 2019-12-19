package jsoup_example.jsoup_example;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class CompletedTasks {
	/*
	 * Change the method to scrape livescore web site: please extract the name of
	 * away team that wons the match.
	 */
	public static void verifyAndClear() throws IOException {
		String url = "https://www.livescore.com/";
		Document document = Jsoup.connect(url).get();
		String htmlString = "<html><head><title>My title</title></head>"
				+ "<body><center>Body content</center></body></html>";
		boolean valid = Jsoup.isValid(htmlString, Whitelist.basic());
		if (valid) {
			System.out.println("The document is valid");
		} else {

			System.out.println("The document is not valid.");
			Document dirtyDoc = Jsoup.parse(htmlString);
			Document cleanDoc = new Cleaner(Whitelist.basic()).clean(dirtyDoc);
			System.out.println(cleanDoc.html());
			System.out.println("Cleaned document");

		}
	}

	/*
	 * IMPORTANT: Don't change this method!
	 */
	public static void ansaExample() throws IOException {
		String url = "https://www.ansa.it/";

		Document doc = Jsoup.connect(url).userAgent("Jsoup client").timeout(5000).get();

		Elements elems = doc.getElementsByTag("article");
		// print articles
		for (Element el : elems) {
			Elements news = el.getElementsByClass("pp-abs");
			if (!news.isEmpty()) {
				System.out.println(news.outerHtml());
				System.out.println(news.text());
				System.out.println("=========");
			}
			Elements picture = el.getElementsByClass("pp-abs");
			if (!picture.isEmpty())
				System.out.println(picture.get(0).toString());
		}
		// print element
		System.out.println(doc.getElementById("Home_pg").text());
		Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
		for (Element element : images) {
			System.out.println(element.nodeName());
			System.out.println(element.attr("src"));
		}
	}
	
	
	public static void addElement() {
		String htmlString = "<html><head><title>My title</title></head>"
				+ "<body><div>Body content</div></body></html>";
		Document doc = Jsoup.parse(htmlString);
		
		Element div = doc.select("div").first(); // <div></div>
		div.html("<p>lorem ipsum</p>"); // <div><p>lorem ipsum</p></div>
		div.prepend("<p>First</p>");
		div.append("<p>Last</p>");
		System.out.println(doc.toString());
		
	}
}
