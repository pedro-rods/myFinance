package com.myfinance.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myfinance.app.entitiy.Ajuste;
import com.myfinance.app.entitiy.PlanoFinanceiro;
import com.myfinance.app.entitiy.Risco;
import com.myfinance.app.entitiy.Usuario;
import com.myfinance.app.enums.EnumTipoCategoria;
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
	private UsuarioService usuarioService;

	@Autowired
	private AjusteService ajusteService;

	@Autowired
	private RiscoService riscoService;

	@Autowired
	private RestTemplate restTemplate; // Injeção do RestTemplate

	public List<PlanoResponse> buscarPorUsuario(Long id) {
		return mapper.toResponse(repository.buscarporUsuario(id));
	}

	public PlanoResponse gerarPlano(Long id) {
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
			return this.processarRespostaFlaskESalvar(response.getBody(), usuarioService.buscarPorIdOuErro(id));
		} catch (Exception e) {
			log.error("Erro ao gerar plano: ", e);
//			throw new RuntimeException("Erro ao gerar plano", e);
			return gerarPlanoEstatico(id);

		}
	}

	private PlanoResponse gerarPlanoEstatico(Long id) {
		Usuario usuario = usuarioService.buscarPorIdOuErro(id);
		PlanoFinanceiro plano = new PlanoFinanceiro();
		plano.setUsuario(usuario);
		plano.setDataAlteracao(new Date());

		List<Ajuste> ajustes = new ArrayList<>();
		List<Risco> riscos = new ArrayList<>();

		// Ajustes estáticos genéricos
		ajustes.add(novoAjuste(plano, EnumTipoCategoria.NECESSIDADES, "aluguel", 500.00));
		ajustes.add(novoAjuste(plano, EnumTipoCategoria.DESEJOS, "streaming", 100.00));
		ajustes.add(novoAjuste(plano, EnumTipoCategoria.INVESTIMENTO_E_POUPANCA, "poupança", 300.00));

		// Riscos estáticos genéricos
		riscos.add(novoRisco(plano, EnumTipoCategoria.NECESSIDADES, "aluguel", 3000.00));
		riscos.add(novoRisco(plano, EnumTipoCategoria.DESEJOS, "streaming", 800.00));

		plano.setAjustes(ajustes);
		plano.setRiscos(riscos);

		plano = repository.save(plano);

		return mapper.toResponse(plano);
	}

	// Métodos auxiliares para clareza
	private Ajuste novoAjuste(PlanoFinanceiro plano, EnumTipoCategoria categoria, String subcategoria, double valor) {
		Ajuste ajuste = new Ajuste();
		ajuste.setPlanoFinanceiro(plano);
		ajuste.setCategoria(categoria);
		ajuste.setSubcategoria(subcategoria);
		ajuste.setValor(valor);
		return ajuste;
	}

	private Risco novoRisco(PlanoFinanceiro plano, EnumTipoCategoria categoria, String subcategoria, double valor) {
		Risco risco = new Risco();
		risco.setPlanoFinanceiro(plano);
		risco.setCategoria(categoria);
		risco.setSubcategoria(subcategoria);
		risco.setValor(valor);
		return risco;
	}

	@Transactional
	public PlanoResponse processarRespostaFlaskESalvar(String respostaJson, Usuario usuario) {
		ObjectMapper mapper = new ObjectMapper();
		PlanoFinanceiro plano = new PlanoFinanceiro();
		plano.setUsuario(usuario);
		plano.setDataAlteracao(new Date());

		List<Ajuste> ajustes = new ArrayList<>();
		List<Risco> riscos = new ArrayList<>();

		try {
			JsonNode root = mapper.readTree(respostaJson);

			// Processa os ajustes de cada categoria
			for (EnumTipoCategoria categoriaEnum : EnumTipoCategoria.values()) {
				String categoria = categoriaEnum.name().toLowerCase();
				System.out.println(categoria);// Categoria em lowercase, conforme esperado na
												// resposta JSON
				JsonNode categoriaArray = root.get(categoria);
				if (categoriaArray != null && categoriaArray.isArray()) {
					for (JsonNode item : categoriaArray) {
						Ajuste ajuste = new Ajuste();
						ajuste.setPlanoFinanceiro(plano);
						ajuste.setCategoria(categoriaEnum);
						System.out.println(categoriaEnum);
						ajuste.setSubcategoria(item.get("subcategoria").asText());
						ajuste.setValor(item.get("valor").asDouble());
						ajustes.add(ajuste);
					}
				}
			}

			// Processa os riscos detectados
			JsonNode riscosArray = root.get("riscos_detectados");
			if (riscosArray != null && riscosArray.isArray()) {
				for (JsonNode riscoNode : riscosArray) {
					Risco risco = new Risco();
					risco.setPlanoFinanceiro(plano);
					// 🔥 Aqui foi ajustado para usar fromString:
					String categoriaStr = riscoNode.get("categoria").asText();
					risco.setCategoria(EnumTipoCategoria.fromString(categoriaStr));
					risco.setSubcategoria(riscoNode.get("subcategoria").asText());
					risco.setValor(riscoNode.get("valor").asDouble());
					riscos.add(risco);
				}
			}

			plano.setAjustes(ajustes);
			plano.setRiscos(riscos);

			// Persiste o plano no banco de dados
			plano = repository.save(plano);
			return this.mapper.toResponse(plano);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao processar ou salvar o plano financeiro", e);
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

	public void deletarPlanosporUsuario(Long id) {
		List<PlanoFinanceiro> planos = repository.buscarporUsuario(id);
		for (PlanoFinanceiro plano : planos) {
			for (Ajuste ajuste : plano.getAjustes()) {
				ajusteService.deletar(ajuste.getId());
			}
			for (Risco risco : plano.getRiscos()) {
				riscoService.deletar(risco.getId());
			}
			repository.deleteById(plano.getId());
		}

	}

}