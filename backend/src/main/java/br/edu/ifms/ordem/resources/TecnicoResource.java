package br.edu.ifms.ordem.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifms.ordem.entities.Tecnico;
import br.edu.ifms.ordem.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {
	
	@Autowired
	private TecnicoService service;
	
	@GetMapping
	public ResponseEntity<List<Tecnico>> findAll(){
		List<Tecnico> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
