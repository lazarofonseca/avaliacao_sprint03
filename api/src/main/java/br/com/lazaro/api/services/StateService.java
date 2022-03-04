package br.com.lazaro.api.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.lazaro.api.dto.StateDTO;
import br.com.lazaro.api.models.State;
import br.com.lazaro.api.repositories.StateRepository;

@Service
public class StateService {
	
	
	@Autowired
	StateRepository stateRepository;

	@Transactional
	public Page<StateDTO> findAllPaged(PageRequest pageRequest) {
		Page<State> list = stateRepository.findAll(pageRequest);
		return list.map(x -> new StateDTO(x));
	}

}
