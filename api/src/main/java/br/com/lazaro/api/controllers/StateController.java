package br.com.lazaro.api.controllers;



import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lazaro.api.dto.StateDTO;
import br.com.lazaro.api.services.StateService;

@RestController("/")
public class StateController {
	
	@Autowired
	private StateService stateService;
	
	@GetMapping(value = "/states")
	public ResponseEntity<Page<StateDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction
			){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, org.springframework.data.domain.Sort.Direction.valueOf(direction), orderBy);
		
		Page<StateDTO> list = stateService.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/states/{id}")
	public ResponseEntity <StateDTO> findById(@PathVariable Long id){
		StateDTO dto = stateService.findById(id);
		return ResponseEntity.ok().body(dto);
		
	}
	@PostMapping(value = "/states")
	public ResponseEntity<StateDTO> insert(@RequestBody StateDTO stateDto){
		stateDto = stateService.insert(stateDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(stateDto.getId()).toUri();
		return ResponseEntity.created(uri).body(stateDto);
	}
	
	@PutMapping(value = "/states/{id}")
	public ResponseEntity<StateDTO> update(@PathVariable Long id, @RequestBody StateDTO stateDto){
		stateDto = stateService.update(id, stateDto);
		return ResponseEntity.ok().body(stateDto);
	}
	
	@DeleteMapping(value = "states/{id}")
	public ResponseEntity<StateDTO> delete(@PathVariable Long id){
		stateService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
