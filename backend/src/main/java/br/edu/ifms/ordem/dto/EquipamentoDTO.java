package br.edu.ifms.ordem.dto;

import java.io.Serializable;

import br.edu.ifms.ordem.entities.Equipamento;
import br.edu.ifms.ordem.entities.Setor;

public class EquipamentoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String equipamento;
	private String patrimonio;
	
	public EquipamentoDTO() {
		
	}

	public EquipamentoDTO(Long id, String equipamento, String patrimonio, Setor setor) {
		super();
		this.id = id;
		this.equipamento = equipamento;
		this.patrimonio = patrimonio;
		
	}
	
	public EquipamentoDTO(Equipamento entity) {
		this.id = entity.getId();
		this.equipamento = entity.getEquipamento();
		this.patrimonio = entity.getPatrimonio();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}

	public String getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}



}
