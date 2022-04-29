package br.edu.ifms.ordem.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.edu.ifms.ordem.dto.OrdemServicoDTO;
import br.edu.ifms.ordem.entities.enums.Prioridade;
import br.edu.ifms.ordem.entities.enums.Status;


@Entity
@Table(name = "tb_ordem_servico")
public class OrdemServico implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricaoProblema;
	private String descricaoSolucao;
	private Instant dataCadastro;
	@Enumerated(EnumType.STRING)
	private Status status;
	@Enumerated(EnumType.STRING)
	private Prioridade prioridade;
	
	@ManyToOne
	@JoinColumn(name = "id_tecnico_fk")
	private Tecnico tecnico;
	
	@ManyToMany
	@JoinTable(name = "tb_ordem_has_equipamento",
						joinColumns = @JoinColumn(name = "id_ordem"),
						inverseJoinColumns = @JoinColumn(name = "id_equipamento"))
	Set<Equipamento> equipamento = new HashSet<>();
	
	
	/**
	 * audit information
	 */
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant updatedAt;
	
	
	public OrdemServico() {
		
	}
	
	public OrdemServico(Long id, String descricaoProblema, String descricaoSolucao, Instant dataCadastro, Status status,
			Prioridade prioridade, Tecnico tecnico, Set<Equipamento> equipamento) {
		this.id = id;
		this.descricaoProblema = descricaoProblema;
		this.descricaoSolucao = descricaoSolucao;
		this.dataCadastro = dataCadastro;
		this.status = status;
		this.prioridade = prioridade;
		this.tecnico = tecnico;
		this.equipamento = equipamento;
	}
	
	public void setData(OrdemServicoDTO dto) {
		this.id = dto.getId();
		this.descricaoProblema = dto.getDescricaoProblema();
		this.descricaoSolucao = dto.getDescricaoSolucao();
		this.dataCadastro = dto.getDataCadastro();
		this.status = dto.getStatus();
		this.prioridade = dto.getPrioridade();
		this.tecnico = dto.getTecnico();
		this.equipamento = (Set) dto.getEquipamentos();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoProblema() {
		return descricaoProblema;
	}

	public void setDescricaoProblema(String descricaoProblema) {
		this.descricaoProblema = descricaoProblema;
	}

	public String getDescricaoSolucao() {
		return descricaoSolucao;
	}

	public void setDescricaoSolucao(String descricaoSolucao) {
		this.descricaoSolucao = descricaoSolucao;
	}

	public Instant getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Instant dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public Set<Equipamento> getEquipamento() {
		return equipamento;
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
		OrdemServico other = (OrdemServico) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
