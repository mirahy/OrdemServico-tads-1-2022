package br.edu.ifms.ordem.dto;

import java.io.Serializable;
import java.util.List;

import br.edu.ifms.ordem.entities.OrdemServico;
import br.edu.ifms.ordem.entities.Tecnico;

public class TecnicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String telefone;
	private String email;
	private String senha;
	private List<OrdemServico> ordensServico;

	public TecnicoDTO() {

	}


	public TecnicoDTO(Long id, String nome, String telefone, String email, String senha,
			List<OrdemServico> ordensServico) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
		this.ordensServico = ordensServico;
	}



	public TecnicoDTO(Tecnico entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.telefone = entity.getTelefone();
		this.email = entity.getEmail();
		this.senha = entity.getSenha();
		this.ordensServico = entity.getOrdensServico();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<OrdemServico> getOrdensServico() {
		return ordensServico;
	}
	

}
