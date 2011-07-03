package br.com.cinepointer.datatypes;

import java.util.ArrayList;

public class Filme {

    private String nome;
	private String genero;
    private String duracao;
    private String diretor;
    private String atoresPrincipais;
    private String sinopse;
    private String site;
    private String banner;
    private String pontuacao;
    private ArrayList<String> horario;
    
    		
    public Filme(){
        this.nome="";
    	this.genero="";
        this.duracao="";
        this.diretor="";
        this.atoresPrincipais="";
        this.sinopse="";
        this.site="";
        this.banner="";	
        this.pontuacao="";
        setHorario(new ArrayList<String>());
    }
    
    /**
	 * @return the duracao
	 */
	public String getDuracao() {
		return duracao;
	}

	/**
	 * @param duracao the duracao to set
	 */
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	/**
	 * @return the diretor
	 */
	public String getDiretor() {
		return diretor;
	}

	/**
	 * @param diretor the diretor to set
	 */
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	/**
	 * @return the atoresPrincipais
	 */
	public String getAtoresPrincipais() {
		return atoresPrincipais;
	}

	/**
	 * @param atoresPrincipais the atoresPrincipais to set
	 */
	public void setAtoresPrincipais(String atoresPrincipais) {
		this.atoresPrincipais = atoresPrincipais;
	}

	/**
	 * @return the sinopse
	 */
	public String getSinopse() {
		return sinopse;
	}

	/**
	 * @param sinopse the sinopse to set
	 */
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	/**
	 * @return the site
	 */
	public String getSite() {
		return site;
	}

	/**
	 * @param site the site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

	public void setPontuacao(String pontuacao) {
		this.pontuacao = pontuacao;
	}

	public String getPontuacao() {
		return pontuacao;
	}

	public void setHorario(ArrayList<String> horario) {
		this.horario = horario;
	}

	public ArrayList<String> getHorario() {
		return horario;
	}

}


