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

import br.edu.ifms.ordem.dto.TecnicoDTO;
import br.edu.ifms.ordem.entities.Tecnico;
import br.edu.ifms.ordem.repositories.TecnicoRepository;
import br.edu.ifms.ordem.services.exceptions.DataBaseExcepetion;
import br.edu.ifms.ordem.services.exceptions.ResourceNotFoundExcepetion;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

	@Transactional(readOnly = true)
	public List<TecnicoDTO> findAll() {
		List<Tecnico> list = repository.findAll();
		return list.stream().map(t -> new TecnicoDTO(t)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Page<TecnicoDTO> findAllPaged(PageRequest pageRequest) {
		Page<Tecnico> list = repository.findAll(pageRequest);
		return list.map(x -> new TecnicoDTO(x));
	}

	@Transactional(readOnly = true)
	public TecnicoDTO findById(Long id) {
		Optional<Tecnico> obj = repository.findById(id);
		Tecnico entity = obj.orElseThrow(() -> new ResourceNotFoundExcepetion("Tecnico não encontrado"));
		return new TecnicoDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<TecnicoDTO> findAllListId(List<Long> id) {
		List<Tecnico> list = repository.findAllById(id);
		return list.stream().map(t -> new TecnicoDTO(t)).collect(Collectors.toList());
	}

	@Transactional
	public TecnicoDTO insert(TecnicoDTO dto) {
		try {
			Tecnico entity = new Tecnico();
			entity.setData(dto);
			entity = repository.save(entity);
			return new TecnicoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundExcepetion("Não foi possivel cadastrar!");
		}

	}

	@Transactional
	public TecnicoDTO update(Long id, TecnicoDTO dto) {
		try {
			Tecnico entity = repository.getById(id);
			entity.setData(dto);
			entity = repository.save(entity);
			return new TecnicoDTO(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundExcepetion("O recurso com o ID = " + id + " não foi localizado");
		}
	}

	/*
	 * @Transactional public TecnicoDTO delete(Long id) { try { Tecnico entity =
	 * repository.getById(id); repository.delete(entity); return new
	 * TecnicoDTO(entity);
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
