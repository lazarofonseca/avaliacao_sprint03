package br.com.lazaro.api.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.lazaro.api.dto.StateDTO;
import br.com.lazaro.api.models.State;
import br.com.lazaro.api.repositories.StateRepository;
import br.com.lazaro.api.services.exception.ErrorException;
import br.com.lazaro.api.services.exception.RegiaoException;

@Service
public class StateService {

	@Autowired
	StateRepository stateRepository;

	@Transactional
	public Page<StateDTO> findAllPaged(PageRequest pageRequest) {
		Page<State> list = stateRepository.findAll(pageRequest);
		return list.map(x -> new StateDTO(x));
	}

	@Transactional
	public StateDTO findById(Long id) {
		Optional<State> objModel = stateRepository.findById(id);
		State model = objModel.orElseThrow(() -> new ErrorException("State not found"));
		return new StateDTO(model);
	}

	@Transactional
	public StateDTO insert(StateDTO stateDto) {
		State state = new State();
		state.setNome(stateDto.getNome());
		String regiao = stateDto.getRegiao();
		if (regiao.equalsIgnoreCase("Nordeste") || regiao.equalsIgnoreCase("Nordeste") || regiao.equalsIgnoreCase("Sul")
				|| regiao.equalsIgnoreCase("Sudeste") || regiao.equalsIgnoreCase("Centro-Oeste")) {
			state.setRegiao(regiao);
		} else {
			throw new RegiaoException("Região inválida");
		}

		state.setPopulacao(stateDto.getPopulacao());
		state.setCapital(stateDto.getCapital());
		state.setArea(stateDto.getArea());
		state.setDataFundacao(stateDto.getDataFundacao());
		
		Date dataInicial = state.getDataFundacao(); //Coleta a data do banco
		Date dataFinal = new Date(); //Pega a data atual
		int anosFundação = stateDto.getNumeroAnos();//Pega o número de anos do cliente via DTO
		
		int diferenca = comparaAno(dataInicial, dataFinal); //Compara a diferença
		
		if(anosFundação != diferenca) {
			throw new ErrorException("Years fundation not compatibily");
		}else {
		state.setNumeroAnos(stateDto.getNumeroAnos());
		}

		state = stateRepository.save(state);

		return new StateDTO(state);
	}

	@Transactional
	public StateDTO update(Long id, StateDTO stateDto) {
		try {
			State state = stateRepository.getById(id);
			state.setNome(stateDto.getNome());
			state.setRegiao(stateDto.getRegiao());
			state.setPopulacao(stateDto.getPopulacao());
			state.setCapital(stateDto.getCapital());
			state.setArea(stateDto.getArea());
			state.setDataFundacao(stateDto.getDataFundacao());
			
			Date dataInicial = state.getDataFundacao(); //Coleta a data do banco
			Date dataFinal = new Date(); //Pega a data atual
			int anosFundação = stateDto.getNumeroAnos();//Pega o número de anos do cliente via DTO
			
			int diferenca = comparaAno(dataInicial, dataFinal); //Compara a diferença
			
			if(anosFundação != diferenca) {
				throw new ErrorException("Years fundation not compatibily");
			}else {
			state.setNumeroAnos(stateDto.getNumeroAnos());
			}

			state = stateRepository.save(state);

			return new StateDTO(state);
		} catch (ErrorException e) {
			throw new ErrorException(e.getMessage());
		}
	}

	public void delete(Long id) {
		try {
			stateRepository.deleteById(id);
		} catch (ErrorException e) {
			throw new ErrorException("Id not found " + id);
		}
	}
	
	public static int comparaAno(Date dataInicial, Date dataFinal) {
		
		long diffMilleSconds = Math.abs(dataFinal.getTime() - dataInicial.getTime());
		long diff = TimeUnit.DAYS.convert(diffMilleSconds, TimeUnit.MILLISECONDS);
		int diferencaAnosCalculados = (int) diff / 365;
		return (int) diferencaAnosCalculados;
	}

}
