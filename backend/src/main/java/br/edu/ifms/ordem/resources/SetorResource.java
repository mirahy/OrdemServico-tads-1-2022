package br.edu.ifms.ordem.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifms.ordem.dto.SetorDTO;
import br.edu.ifms.ordem.services.SetorService;

@RestController
@RequestMapping(value = "/setor")
public class SetorResource {
	
	@Autowired
	private SetorService service;

	@GetMapping
	public ResponseEntity<Page<SetorDTO>> findAllPage(
				@RequestParam(value = "page", defaultValue = "0") Integer page,
				@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
				@RequestParam(value = "direction", defaultValue = "ASC") String direction,
				@RequestParam(value = "orderBay", defaultValue = "nome") String orderBay
			){
			
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBay);
			
			Page<SetorDTO> list = service.findAllPaged(pageRequest);
			return ResponseEntity.ok().body(list);
	}
	
/*	@GetMapping
	public ResponseEntity<List<SetorDTO>> findAll() {
		List<SetorDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}*/

	@GetMapping(value = "/{id}")
	public ResponseEntity<SetorDTO> findById(@PathVariable Long id) {
		SetorDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping(value = "/")
	public ResponseEntity<List<SetorDTO>> findAllListId(@PathVariable List<Long> ids) {
		List<SetorDTO> list = service.findAllListId(ids);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<SetorDTO> insert(@RequestBody SetorDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<SetorDTO> update(@PathVariable Long id, @RequestBody SetorDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	/*
	 * @DeleteMapping(value = "/{id}") public ResponseEntity<SetorDTO>
	 * delete(@PathVariable Long id){ SetorDTO dto = service.delete(id); return
	 * ResponseEntity.ok().body(dto); }
	 */

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}


}
