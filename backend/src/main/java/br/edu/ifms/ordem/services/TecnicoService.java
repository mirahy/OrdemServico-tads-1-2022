package br.edu.ifms.ordem.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifms.ordem.dto.TecnicoDTO;
import br.edu.ifms.ordem.entities.Tecnico;
import br.edu.ifms.ordem.repositories.TecnicoRepository;
import br.edu.ifms.ordem.services.exceptions.EntityNotFoundExcepetion;

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
		Tecnico entity = obj.orElseThrow(() -> new EntityNotFoundExcepetion("Tecnico não encontrado"));
		return new TecnicoDTO(entity);
	}
	
	@Transactional
	public TecnicoDTO insert(TecnicoDTO dto){
		Tecnico entity = new Tecnico();
		entity.setNome(dto.getNome());
		entity.setTelefone(dto.getTelefone());
		entity.setEmail(dto.getEmail());
		entity.setSenha(dto.getSenha());
		entity = repository.save(entity);
		return new TecnicoDTO(entity);
		
	}
}
