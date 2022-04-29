package br.edu.ifms.ordem.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifms.ordem.dto.EquipamentoDTO;
import br.edu.ifms.ordem.entities.Equipamento;
import br.edu.ifms.ordem.repositories.EquipamentoRepository;
import br.edu.ifms.ordem.services.exceptions.DataBaseExcepetion;
import br.edu.ifms.ordem.services.exceptions.ResourceNotFoundExcepetion;

@Service
public class EquipamentoService {
	
	@Autowired
	private EquipamentoRepository repository;
	
	@Transactional(readOnly = true)
	public List<EquipamentoDTO> findAll() {
		List<Equipamento> list = repository.findAll();
		return list.stream().map(t -> new EquipamentoDTO(t)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Page<EquipamentoDTO> findAllPaged(PageRequest pageRequest) {
		Page<Equipamento> list = repository.findAll(pageRequest);
		return list.map(x -> new EquipamentoDTO(x));
	}

	@Transactional(readOnly = true)
	public EquipamentoDTO findById(Long id) {
		Optional<Equipamento> obj = repository.findById(id);
		Equipamento entity = obj.orElseThrow(() -> new ResourceNotFoundExcepetion("Equipamento não encontrado"));
		return new EquipamentoDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<EquipamentoDTO> findAllListId(List<Long> id) {
		List<Equipamento> list = repository.findAllById(id);
		return list.stream().map(t -> new EquipamentoDTO(t)).collect(Collectors.toList());
	}

	@Transactional
	public EquipamentoDTO insert(EquipamentoDTO dto) {
		try {
			Equipamento entity = new Equipamento();
			entity.setData(dto);
			entity = repository.save(entity);
			return new EquipamentoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundExcepetion("Não foi possivel cadastrar!");
		}

	}

	@Transactional
	public EquipamentoDTO update(Long id, EquipamentoDTO dto) {
		try {
			Equipamento entity = repository.getById(id);
			entity.setData(dto);
			entity = repository.save(entity);
			return new EquipamentoDTO(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundExcepetion("O recurso com o ID = " + id + " não foi localizado");
		}
	}

	/*
	 * @Transactional public EquipamentoDTO delete(Long id) { try { Equipamento entity =
	 * repository.getById(id); repository.delete(entity); return new
	 * EquipamentoDTO(entity);
	 * 
	 * } catch (EntityNotFoundException e) { throw new
	 * ResourceNotFoundExcepetion("O recurso com o ID = "+id+" não foi localizado");
	 * }
	 * 
	 * }
	 */

	public void delete(Long id) {
		try {
			repository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundExcepetion("O recurso com o ID = " + id + " não foi localizado");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseExcepetion("Não é posivel excluir o registro, pois o mesmo esta em uso");
		}

	}


}
