package br.edu.ifms.ordem.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.edu.ifms.ordem.dto.TecnicoDTO;

@Entity
@Table(name = "tb_tecnico")
public class Tecnico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento
	private Long id;
	private String nome;
	private String telefone;
	private String email;
	private String senha;
	
	@OneToMany(mappedBy = "tecnico")
	private List<OrdemServico> ordensServico;
	
	/**
	 * audit information
	 */
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant updatedAt;
	

	public Tecnico() {

	}

	public Tecnico(Long id, String nome, String telefone, String email, String senha,
			List<OrdemServico> ordensServico) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
		this.ordensServico = ordensServico;
	}



	public void setData(TecnicoDTO dto) {
		this.nome = dto.getNome();
		this.telefone = dto.getTelefone();
		this.email = dto.getEmail();
		this.senha = dto.getSenha();
		this.ordensServico = dto.getOrdensServico();
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
	
	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}
	
	@PrePersist
	public void prePersist() {
		createdAt = Instant.now();
	}
	
	@PreUpdate
	public void preUpdate() {
		updatedAt = Instant.now();
	}

	public List<OrdemServico> getOrdensServico() {
		return ordensServico;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tecnico other = (Tecnico) obj;
		return Objects.equals(id, other.id);
	}

}
