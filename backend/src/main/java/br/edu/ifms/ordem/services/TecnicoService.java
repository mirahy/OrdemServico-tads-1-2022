package br.edu.ifms.ordem.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifms.ordem.dto.TecnicoDTO;
import br.edu.ifms.ordem.entities.Tecnico;
import br.edu.ifms.ordem.repositories.TecnicoRepository;
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
	public TecnicoDTO findById(Long id) {
		Optional<Tecnico> obj = repository.findById(id);
		Tecnico entity = obj.orElseThrow(() -> new ResourceNotFoundExcepetion("Tecnico não encontrado"));
		return new TecnicoDTO(entity);
	}
	
	@Transactional
	public TecnicoDTO insert(TecnicoDTO dto){
		try {
			Tecnico entity = new Tecnico();
			entity.setNome(dto.getNome());
			entity.setTelefone(dto.getTelefone());
			entity.setEmail(dto.getEmail());
			entity.setSenha(dto.getSenha());
			entity = repository.save(entity);
			return new TecnicoDTO(entity);	
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundExcepetion("Não foin possivel cadastrar!");
		}
		
		
	}
	
	@Transactional
	public TecnicoDTO update(Long id, TecnicoDTO dto) {
		try {
			Tecnico entity = repository.getById(id);
			entity.setNome(dto.getNome());
			entity.setTelefone(dto.getTelefone());
			entity.setEmail(dto.getEmail());
			entity.setSenha(dto.getSenha());
			entity = repository.save(entity);
			return new TecnicoDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundExcepetion("O recurso com o ID = "+id+" não foi localizado");
		}
		
	}
	
	@Transactional
	public TecnicoDTO delete(Long id) {
		try {
			Tecnico entity = repository.getById(id);
			repository.delete(entity);
			return new TecnicoDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundExcepetion("O recurso com o ID = "+id+" não foi localizado");
		}
		
	}

	
}
