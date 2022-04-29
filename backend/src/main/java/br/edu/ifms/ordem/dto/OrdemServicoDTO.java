package br.edu.ifms.ordem.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.edu.ifms.ordem.entities.Equipamento;
import br.edu.ifms.ordem.entities.OrdemServico;
import br.edu.ifms.ordem.entities.Tecnico;
import br.edu.ifms.ordem.entities.enums.Prioridade;
import br.edu.ifms.ordem.entities.enums.Status;


public class OrdemServicoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricaoProblema;
	private String descricaoSolucao;
	private Instant dataCadastro;
	private Status status;
	private Prioridade prioridade;
	private Tecnico tecnico;
	private List<EquipamentoDTO> equipamentos = new ArrayList<>();
	
	public OrdemServicoDTO() {
	
	}
	
	public OrdemServicoDTO(Long id, String descricaoProblema, String descricaoSolucao, Instant dataCadastro,
			Status status, Prioridade prioridade, Tecnico tecnico) {
		super();
		this.id = id;
		this.descricaoProblema = descricaoProblema;
		this.descricaoSolucao = descricaoSolucao;
		this.dataCadastro = dataCadastro;
		this.status = status;
		this.prioridade = prioridade;
		this.tecnico = tecnico;
	}

	public OrdemServicoDTO(OrdemServico entity) {
		this.id = entity.getId();
		this.descricaoProblema = entity.getDescricaoProblema();
		this.descricaoSolucao = entity.getDescricaoSolucao();
		this.dataCadastro = entity.getDataCadastro();
		this.status = entity.getStatus();
		this.prioridade = entity.getPrioridade();
		this.tecnico = entity.getTecnico();
	}
	
	public OrdemServicoDTO(OrdemServico entity, Set<Equipamento> equipamentos) {
		this(entity);
		equipamentos.forEach(equipamento -> new EquipamentoDTO(equipamento));
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

	public List<EquipamentoDTO> getEquipamentos() {
		return equipamentos;
	}

	
	

}
