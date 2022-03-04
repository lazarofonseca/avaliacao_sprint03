package br.com.lazaro.api.dto;

import java.time.LocalDateTime;

import br.com.lazaro.api.models.State;

public class StateDTO {

	private Long id;
	private String nome;
	private String regiao;
	private Integer populacao;
	private String capital;
	private Double area;

	private LocalDateTime dataFundacao;
	private Integer numeroAnos;
	
	public StateDTO() {
	}

	public StateDTO(Long id, String nome, String regiao, Integer populacao, String capital, Double area,
			LocalDateTime dataFundacao, Integer numeroAnos) {
		this.id = id;
		this.nome = nome;
		this.regiao = regiao;
		this.populacao = populacao;
		this.capital = capital;
		this.area = area;
		this.dataFundacao = dataFundacao;
		this.numeroAnos = numeroAnos;
	}

	public StateDTO(State model) {
		this.id = model.getId();
		this.nome = model.getNome();
		this.regiao = model.getRegiao();
		this.populacao = model.getPopulacao();
		this.capital = model.getCapital();
		this.area = model.getArea();
		this.dataFundacao = model.getDataFundacao();
		this.numeroAnos = model.getNumeroAnos();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public Integer getPopulacao() {
		return populacao;
	}

	public void setPopulacao(Integer populacao) {
		this.populacao = populacao;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public LocalDateTime getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(LocalDateTime dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public Integer getNumeroAnos() {
		return numeroAnos;
	}

	public void setNumeroAnos(Integer numeroAnos) {
		this.numeroAnos = numeroAnos;
	}

}
