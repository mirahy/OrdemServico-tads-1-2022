package br.edu.ifms.ordem.resources;

import java.net.URI;
import java.util.List;

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

import br.edu.ifms.ordem.dto.OrdemServicoDTO;
import br.edu.ifms.ordem.services.OrdemServicoService;

@RestController
@RequestMapping(value = "/ordem")
public class OrdemServicoResource {
	
	private OrdemServicoService service;

	
	@GetMapping
	public ResponseEntity<Page<OrdemServicoDTO>> findAllPage(
				@RequestParam(value = "page", defaultValue = "0") Integer page,
				@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
				@RequestParam(value = "direction", defaultValue = "ASC") String direction,
				@RequestParam(value = "orderBay", defaultValue = "nome") String orderBay
			){
			
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBay);
			
			Page<OrdemServicoDTO> list = service.findAllPaged(pageRequest);
			return ResponseEntity.ok().body(list);
	}
	
/*	@GetMapping
	public ResponseEntity<List<OrdemServicoDTO>> findAll() {
		List<OrdemServicoDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}*/

	@GetMapping(value = "/{id}")
	public ResponseEntity<OrdemServicoDTO> findById(@PathVariable Long id) {
		OrdemServicoDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping(value = "/")
	public ResponseEntity<List<OrdemServicoDTO>> findAllListId(@PathVariable List<Long> ids) {
		List<OrdemServicoDTO> list = service.findAllListId(ids);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<OrdemServicoDTO> insert(@RequestBody OrdemServicoDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<OrdemServicoDTO> update(@PathVariable Long id, @RequestBody OrdemServicoDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	/*
	 * @DeleteMapping(value = "/{id}") public ResponseEntity<OrdemServicoDTO>
	 * delete(@PathVariable Long id){ OrdemServicoDTO dto = service.delete(id); return
	 * ResponseEntity.ok().body(dto); }
	 */

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
