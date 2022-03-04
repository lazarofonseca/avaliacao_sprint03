package br.com.lazaro.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class StateController {
	
	@GetMapping
	public String nome() {
		return "Lázaro Nóbrega Fonseca";
	}

}
