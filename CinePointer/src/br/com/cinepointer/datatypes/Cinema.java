package br.com.cinepointer.datatypes;

public class Cinema {
    
	private String nome;
	private String link;
    private String endereco;
    private String telefone;
    private String quantidade;

    
    public Cinema(){
        this.setNome("");
    	this.setLink("");
    	this.setEndereco("");
    	this.setTelefone("");
    	this.setQuantidade("");
    }


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getNome() {
		return nome;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public String getLink() {
		return link;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}


	public String getQuantidade() {
		return quantidade;
	}
    
}
