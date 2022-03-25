package br.edu.ifms.ordem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifms.ordem.dto.TecnicoDTO;
import br.edu.ifms.ordem.entities.Tecnico;
import br.edu.ifms.ordem.repositories.TecnicoRepository;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	
	@Transactional(readOnly = true)
	public List<TecnicoDTO> findAll(){
		List<Tecnico> list = repository.findAll();
		//List<TecnicoDTO> listDto = new ArrayList<>();
		
		//for(Tecnico t : list) {
		//	listDto.add(new TecnicoDTO(t));
		//}
		
		//return listDto;
		return list.stream().map(t -> new TecnicoDTO(t)).collect(Collectors.toList());
	}
}
