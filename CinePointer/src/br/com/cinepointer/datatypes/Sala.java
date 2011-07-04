package br.com.cinepointer.datatypes;
import java.util.ArrayList;


public class Sala {
	private String cidade;
	private String cinema;
	private String urlCinema;
	private ArrayList<String> horario;
	
	public Sala(){
		this.setCidade("");
		this.setCinema("");
		this.setUrlCinema("");
		this.setHorario(new ArrayList<String>());
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCinema(String cinema) {
		this.cinema = cinema;
	}

	public String getCinema() {
		return cinema;
	}

	public void setUrlCinema(String urlCinema) {
		this.urlCinema = urlCinema;
	}

	public String getUrlCinema() {
		return urlCinema;
	}

	public void setHorario(ArrayList<String> horario) {
		this.horario = horario;
	}

	public ArrayList<String> getHorario() {
		return horario;
	}
}
