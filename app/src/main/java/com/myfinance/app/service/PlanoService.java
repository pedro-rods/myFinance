package com.myfinance.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myfinance.app.mapper.PlanoMapper;
import com.myfinance.app.repository.PlanoRepository;
import com.myfinance.app.response.GastosListaResponse;
import com.myfinance.app.response.PlanoResponse;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class PlanoService {

	@Autowired
	private PlanoRepository repository;

	@Autowired
	private PlanoMapper mapper;

	@Autowired
	private GastoService gastoService;

	@Autowired
	private RestTemplate restTemplate; // Injeção do RestTemplate

	public PlanoResponse buscarPorUsuario(Long id) {
		return mapper.toResponse(repository.buscarporUsuario(id));
	}

	public String gerarPlano(Long id) {
		// Primeiro, buscar os gastos por usuário
		GastosListaResponse listaGastos = gastoService.buscarGastosPorUsuario(id);

		// URL do endpoint do Flask (ajuste conforme sua URL)
		String urlFlask = "http://localhost:5000/api/gerar_plano";

		// Enviar os dados para o Flask
		try {
			// Criando a requisição com o objeto GastosListaResponse
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON); // Configurando o cabeçalho para JSON
			HttpEntity<GastosListaResponse> entity = new HttpEntity<>(listaGastos, headers);

			// Enviando a requisição POST para o Flask
			ResponseEntity<String> response = restTemplate.exchange(urlFlask, HttpMethod.POST, entity, String.class);

			// Logando a resposta da requisição
			log.info("Resposta do Flask: " + response.getBody());
			return response.getBody();
		} catch (Exception e) {
			log.error("Erro ao gerar plano: ", e);
			throw new RuntimeException("Erro ao gerar plano", e);
		}
	}

	public String treinarModelo() {

		// URL do endpoint do Flask (ajuste conforme sua URL)
		String urlFlask = "http://localhost:5000/api/treinar_modelo";

		// Enviar os dados para o Flask
		try {
			// Criando a requisição com o objeto GastosListaResponse
			HttpHeaders headers = new HttpHeaders();
			HttpEntity<String> entity = new HttpEntity<>(headers);

			// Enviando a requisição POST para o Flask
			ResponseEntity<String> response = restTemplate.exchange(urlFlask, HttpMethod.POST, entity, String.class);

			// Logando a resposta da requisição
			log.info("Resposta do Flask: " + response.getBody());
			return response.getBody();
		} catch (Exception e) {
			log.error("Erro ao gerar plano: ", e);
			throw new RuntimeException("Erro ao gerar plano", e);
		}
	}

}