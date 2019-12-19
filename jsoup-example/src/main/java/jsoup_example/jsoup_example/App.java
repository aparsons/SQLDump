package jsoup_example.jsoup_example;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class App {

	/*
	 * Parsing a Ansa news
	 */
	public static void main(String[] args) throws IOException {
		
	}
	

	/*
	 * Complete the method - scrape tuttojuve mobile website and select all
	 * thumbnails from "CALIOMERCATO" news. Print the results on the console
	 * Use a timeout when Jsoup connects.
	 */
	public static void tuttojuveImages() throws IOException {
		String url = "https://m.tuttojuve.com/";
		Document document = Jsoup.connect(url).get();
		Elements images = document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
		images.parents();
		for (Element element : images) {
			System.out.println(element);
		}
	}
	/*
	 * Complete the method - scrape and modify vocegiallorossa mobile website: add a 2
	 *  fake news: one on top of the list and one to the bottom and store the new document as a 
	 *  file.
	 */
//	
	public static void voceGiallorossa() throws IOException {
		String url = "https://m.vocegiallorossa.it/";
		Document document = Jsoup.connect(url).get();
		Elements news = document.getElementsByClass("list");
		news.append("<a>news</a>");
		news.prepend("");
		
	}
	/*
	 * Complete the method - scrape livescore web site: please extract the name of
	 * away teams that wons the match and print them to the console.
	 */
	public static void livescoreTask() throws IOException {
		String url = "https://www.livescore.com/";
		Document document = Jsoup.connect(url).get();
		Elements scores = document.getElementsByClass("sco");
		scores.parents();
	}
	
	/*
	 * Complete the method - scrape meteo.it web site: please collect the 
	 * different weather icons in the map of Abruzzo and print them to the console.
	 * (suggestions search for <use xlink:href="#meteo_18"></use> where
	 * xlink:href is the type of the weather).
	 */
	public static void meteo() throws IOException {
		String url = "https://www.meteo.it/meteo/abruzzo//";
		Document document = Jsoup.connect(url).get();
		Elements meteo = document.select("use");
	}
	

}
