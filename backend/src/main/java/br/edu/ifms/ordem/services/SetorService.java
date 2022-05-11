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

import br.edu.ifms.ordem.dto.SetorDTO;
import br.edu.ifms.ordem.entities.Setor;
import br.edu.ifms.ordem.repositories.SetorRepository;
import br.edu.ifms.ordem.services.exceptions.DataBaseExcepetion;
import br.edu.ifms.ordem.services.exceptions.ResourceNotFoundExcepetion;

@Service
public class SetorService {
	
	@Autowired
	private SetorRepository repository;

	@Transactional(readOnly = true)
	public List<SetorDTO> findAll() {
		List<Setor> list = repository.findAll();
		return list.stream().map(t -> new SetorDTO(t)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Page<SetorDTO> findAllPaged(PageRequest pageRequest) {
		Page<Setor> list = repository.findAll(pageRequest);
		return list.map(x -> new SetorDTO(x));
	}

	@Transactional(readOnly = true)
	public SetorDTO findById(Long id) {
		Optional<Setor> obj = repository.findById(id);
		Setor entity = obj.orElseThrow(() -> new ResourceNotFoundExcepetion("Setor não encontrado"));
		return new SetorDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<SetorDTO> findAllListId(List<Long> id) {
		List<Setor> list = repository.findAllById(id);
		return list.stream().map(t -> new SetorDTO(t)).collect(Collectors.toList());
	}

	@Transactional
	public SetorDTO insert(SetorDTO dto) {
		try {
			Setor entity = new Setor();
			entity.setData(dto);
			entity = repository.save(entity);
			return new SetorDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundExcepetion("Não foi possivel cadastrar!");
		}

	}

	@Transactional
	public SetorDTO update(Long id, SetorDTO dto) {
		try {
			Setor entity = repository.getById(id);
			entity.setData(dto);
			entity = repository.save(entity);
			return new SetorDTO(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundExcepetion("O recurso com o ID = " + id + " não foi localizado");
		}
	}

	/*
	 * @Transactional public SetorDTO delete(Long id) { try { Setor entity =
	 * repository.getById(id); repository.delete(entity); return new
	 * SetorDTO(entity);
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
