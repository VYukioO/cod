package br.edu.fatec.les.dominio.modelo;

import java.time.LocalDateTime;
import java.util.List;

import br.edu.fatec.les.dominio.EntidadeNome;
import br.edu.fatec.les.dominio.enums.Generos;

public class Cliente extends EntidadeNome {
	private Usuario usuario;
	private Generos genero;
	private LocalDateTime dtNascimento;
	private String cpf;
	private String telefone;
	private List<Endereco> enderecos;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Generos getGenero() {
		return genero;
	}
	public void setGenero(Generos genero) {
		this.genero = genero;
	}
	public LocalDateTime getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(LocalDateTime dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
}
