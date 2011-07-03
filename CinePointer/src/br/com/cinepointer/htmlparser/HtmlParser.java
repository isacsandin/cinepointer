package br.com.cinepointer.htmlparser;

import br.com.cinepointer.datatypes.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;


public class HtmlParser {
	
	public static void parse() throws IOException{

		//String url = "http://www.cinemaki.com.br/shows";
//		String url ="http://www.cinemaki.com.br/cinemas/tag/95";
		//String url ="http://www.cinemaki.com.br/Cinemark-Diamond-Mall/t/2806";
//		String url = "http://www.cinemaki.com.br/Anima%C3%A7%C3%A3o-/shows/tag/54";
		//String url = "http://www.cinemaki.com.br/Carros-2/p/2339";

//		while(url != null ){
			ArrayList<Cinema> ListaCinema = new ArrayList<Cinema>();
			ArrayList<Filme> ListaFilme = new ArrayList<Filme>();
//			url = processCinemPageDetails(url);
//			ListaCinema = processCinemPage("http://www.cinemaki.com.br/cinemas/tag/95");
//          ListaFilme = processFilmPage("http://www.cinemaki.com.br/Anima%C3%A7%C3%A3o-/shows/tag/54");
			ListaFilme  = processFilmDetailsPage("http://www.cinemaki.com.br/Carros-2/p/2339");
//			url =null;
//		}

	}
	
	//Detalhes Filme
	private static ArrayList<Filme> processFilmDetailsPage(String url) {
        
		Filme filme = new Filme();
		ArrayList<Filme> listFilme = new ArrayList<Filme>();
		HashMap<String, String> hash = new HashMap<String, String>();
		url +="?version=desktop";

		Document doc;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			return null;
		}
		Elements imgs = doc.select("img[class~=new_4col]");
		filme.setBanner(imgs.first().attr("src"));

		Elements links = doc.select("[class~=new_7col fl m10l]");
		for (Element link : links) {
			filme.setNome(link.select("table.fl").attr("summary"));
			Log.d("HTMLPARSER::",filme.getNome());
			for (Element element : link.select("table.fl").select("tr")) {
				 String aux ="";			

			      aux = element.select("td.first").text() + ": "+ element.select("td:not([class~=first])").text();
				  String[] str = aux.split(": ");
				  hash.put(str[0].trim(), str[1].trim());
			}
			
			filme.setGenero(hash.get("Gênero"));
			Log.d("HTMLPARSER::",filme.getGenero());
			filme.setDuracao(hash.get("Duração"));
			Log.d("HTMLPARSER::",filme.getDuracao());
			filme.setDiretor(hash.get("Diretor"));
			Log.d("HTMLPARSER::",filme.getDiretor());
			filme.setAtoresPrincipais(hash.get("Atores principais"));
			Log.d("HTMLPARSER::",filme.getAtoresPrincipais());
			
			Element sinopse = doc.select("[class~=synopsis dn fcl]").first();
			filme.setSinopse(sinopse.text());
			Log.d("HTMLPARSER::",filme.getSinopse());
			Log.d("HTMLPARSER::","==========");
			listFilme.add(filme);
		}

		return listFilme;
	}
	
	
	//
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
	

	//Informações de Cinemas de uma cidade
	//String url ="http://www.cinemaki.com.br/cinemas/tag/95";
	private static ArrayList<Cinema> processCinemPage(String urlEntrada){
		
		ArrayList<Cinema> listCinema = new ArrayList<Cinema>();
		Cinema cinema;
		HashMap<String, String> hash = new HashMap<String, String>();
		
		String url = urlEntrada;
		url +="?version=desktop";

		Document doc;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			return null;
		}
		
		Elements links = doc.select("[class~=p10 theater_row]");
		for (Element link : links) {
			cinema = new Cinema();
			cinema.setNome(link.select("h4").first().text());
			Log.d("HTMLPARSER::",cinema.getNome());
			cinema.setLink(link.select("h4 > a").first().attr("abs:href"));
			Log.d("HTMLPARSER::",cinema.getLink());
			Log.d("HTMLPARSER::","DETALHES:");
			for (Element element : link.select("[class~=m5b]")) {
				String[] str = element.text().split(": ");
				hash.put(str[0].trim(), str[1].trim());
			}
			cinema.setEndereco(hash.get("Endereço"));
			Log.d("HTMLPARSER::",cinema.getEndereco());
			cinema.setTelefone(hash.get("Telefone"));
			Log.d("HTMLPARSER::",cinema.getTelefone());
			cinema.setQuantidade(hash.get("Quantidade de salas"));
			Log.d("HTMLPARSER::",cinema.getQuantidade());
			Log.d("HTMLPARSER::","==========");
			listCinema.add(cinema);
		}

		return listCinema;
	}




	private static ArrayList<Filme> processFilmPage(String url) {

		ArrayList<Filme> listFilme = new ArrayList<Filme>();
		Filme filme;
		while(url != null){

			url +="?version=desktop";
			
			Document doc;
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				return null;
			}
	
			Elements links = doc.select("[class~=fl oh] > a > img");
			for (Element link : links) {
				filme = new Filme();
				filme.setNome(link.parent().attr("title"));
				Log.d("HTMLPARSER::",filme.getNome());
				filme.setSite(link.parent().attr("abs:href"));
				Log.d("HTMLPARSER::",filme.getSite());
				filme.setBanner(link.attr("src"));
				Log.d("HTMLPARSER::",filme.getBanner());
				Log.d("HTMLPARSER::","==========");
				
				listFilme.add(filme);
			}

		    Element link = doc.select("div.newpager > div.fr > a").first();
			if(link !=null){
				url = link.attr("abs:href");
			}
			else{
				url = null;
			}
		}		
		return listFilme;
	}

	public static void parser(){

		try {
			parse();
		} catch (IOException e) {
			Log.d("HTMLPARSER::","ferrou");
		}

	}
}

