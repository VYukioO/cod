package br.edu.fatec.les.dominio.modelo;

import java.util.List;

import br.edu.fatec.les.dominio.EntidadeNome;

public class Estado extends EntidadeNome{
	private String sigla;
	private List<Cidade> cidades;
	
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public List<Cidade> getCidades() {
		return cidades;
	}
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	

}
