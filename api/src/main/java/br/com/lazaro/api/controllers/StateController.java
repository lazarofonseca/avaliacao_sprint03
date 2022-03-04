package br.com.lazaro.api.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lazaro.api.dto.StateDTO;
import br.com.lazaro.api.services.StateService;

@RestController("/")
public class StateController {
	
	@Autowired
	private StateService stateService;
	
	@GetMapping
	public ResponseEntity<Page<StateDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "1") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction
			){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, org.springframework.data.domain.Sort.Direction.valueOf(direction), orderBy);
		
		Page<StateDTO> list = stateService.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(list);
		
	}

}
