package br.com.cinepointer.htmlparser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;


public class HtmlParser {
	
	public static void parse() throws IOException{

		//String url = "http://www.cinemaki.com.br/shows";
		//String url ="http://www.cinemaki.com.br/cinemas/tag/95";
		//String url ="http://www.cinemaki.com.br/Cinemark-Diamond-Mall/t/2806";
		//String url = "http://www.cinemaki.com.br/Anima%C3%A7%C3%A3o-/shows/tag/54";
		String url = "http://www.cinemaki.com.br/Carros-2/p/2339";

		while(url != null ){

			//url = processCinemPageDetails(url);
			//url = processCinemPage(url);
			//url = processFilmPage(url);
			url = processFilmDetailsPage(url);
		}

	}
	private static String processFilmDetailsPage(String url) throws IOException {

		url +="?version=desktop";

		Document doc = Jsoup.connect(url).get();
		
		Elements links = doc.select("[class~=new_7col fl m10l]");
		for (Element link : links) {
			Log.d("HTMLPARSER::",link.select("table.fl").attr("summary"));
			for (Element element : link.select("table.fl").select("tr")) {
				Log.d("HTMLPARSER::",element.select("td.first").text()+": ");
				Log.d("HTMLPARSER::",element.select("td").text());
			}
			Element sinopse = doc.select("[class~=synopsis dn fcl]").first();
			Log.d("HTMLPARSER::","sinopse:\n"+sinopse.text());
			Log.d("HTMLPARSER::","==========");
		}

		return null;
	}
	
	
	private static String processCinemPageDetails(String url) throws IOException {

		url +="?version=desktop";

		Document doc = Jsoup.connect(url).get();
		
		Elements links = doc.select("[class~=p10 oh nbb boxed]");
		for (Element link : links) {
			Log.d("HTMLPARSER::","FILME: "+link.select("a > img").attr("title"));
			Log.d("HTMLPARSER::","IMAGEM: "+link.select("a > img").attr("src"));
			Log.d("HTMLPARSER::","DETALHES: "+link.select("a > img").parents().first().attr("abs:href"));
			Log.d("HTMLPARSER::","PONTUACAO: "+link.select("[class~=t oh]").select("[class~=f10 m10t]").select("img").attr("title"));
			Log.d("HTMLPARSER::","HORARIOS:");
			for (Element element : link.select("[class~=oh] > h4 > a")) {
				Log.d("HTMLPARSER::",element.text());
			}
			Log.d("HTMLPARSER::","==========");
		}

		return null;
	}
	

	private static String processCinemPage(String url) throws IOException {

		url +="?version=desktop";

		Document doc = Jsoup.connect(url).get();
		
		Elements links = doc.select("[class~=p10 theater_row]");
		for (Element link : links) {
			Log.d("HTMLPARSER::","NOME: "+link.select("h4").first().text());
			Log.d("HTMLPARSER::","LINK: "+link.select("h4 > a").first().attr("abs:href"));
			Log.d("HTMLPARSER::","DETALHES:");
			for (Element element : link.select("[class~=m5b]")) {
				Log.d("HTMLPARSER::",element.text());
			}
			Log.d("HTMLPARSER::","==========");
		}

		return null;
	}



	private static String processFilmPage(String url) throws IOException {

		url +="?version=desktop";
		
		Document doc = Jsoup.connect(url).get();

		Elements links = doc.select("[class~=fl oh] > a > img");
		for (Element link : links) {
			Log.d("HTMLPARSER::","NOME: "+link.parent().attr("title"));
			Log.d("HTMLPARSER::","LINK: "+link.parent().attr("abs:href"));
			Log.d("HTMLPARSER::","IMAGEM: "+link.attr("src"));
			Log.d("HTMLPARSER::","==========");
		}

		Element link = doc.select("div.newpager > div.fr > a").first();
		if(link !=null){
			url = link.attr("abs:href");
		}
		else{
			url = null;
		}
		return url;
	}

	public static void parser(){

		try {
			parse();
		} catch (IOException e) {
			Log.d("HTMLPARSER::","ferrou");
		}

	}
}

