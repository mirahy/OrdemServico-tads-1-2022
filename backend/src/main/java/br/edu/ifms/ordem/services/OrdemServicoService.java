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

import br.edu.ifms.ordem.dto.OrdemServicoDTO;
import br.edu.ifms.ordem.entities.OrdemServico;
import br.edu.ifms.ordem.repositories.OrdemServicoRepository;
import br.edu.ifms.ordem.services.exceptions.DataBaseExcepetion;
import br.edu.ifms.ordem.services.exceptions.ResourceNotFoundExcepetion;

@Service
public class OrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository repository;

	@Transactional(readOnly = true)
	public List<OrdemServicoDTO> findAll() {
		List<OrdemServico> list = repository.findAll();
		return list.stream().map(t -> new OrdemServicoDTO(t)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Page<OrdemServicoDTO> findAllPaged(PageRequest pageRequest) {
		Page<OrdemServico> list = repository.findAll(pageRequest);
		return list.map(x -> new OrdemServicoDTO(x));
	}

	@Transactional(readOnly = true)
	public OrdemServicoDTO findById(Long id) {
		Optional<OrdemServico> obj = repository.findById(id);
		OrdemServico entity = obj.orElseThrow(() -> new ResourceNotFoundExcepetion("Ordem de serviço não encontrado"));
		return new OrdemServicoDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<OrdemServicoDTO> findAllListId(List<Long> id) {
		List<OrdemServico> list = repository.findAllById(id);
		return list.stream().map(t -> new OrdemServicoDTO(t)).collect(Collectors.toList());
	}

	@Transactional
	public OrdemServicoDTO insert(OrdemServicoDTO dto) {
		try {
			OrdemServico entity = new OrdemServico();
			entity.setData(dto);
			entity = repository.save(entity);
			return new OrdemServicoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundExcepetion("Não foi possivel cadastrar!");
		}

	}

	@Transactional
	public OrdemServicoDTO update(Long id, OrdemServicoDTO dto) {
		try {
			OrdemServico entity = repository.getById(id);
			entity.setData(dto);
			entity = repository.save(entity);
			return new OrdemServicoDTO(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundExcepetion("O recurso com o ID = " + id + " não foi localizado");
		}
	}

	/*
	 * @Transactional public OrdemServicoDTO delete(Long id) { try { OrdemServico entity =
	 * repository.getById(id); repository.delete(entity); return new
	 * OrdemServicoDTO(entity);
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
