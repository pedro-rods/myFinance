package com.myfinance.app.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myfinance.app.entitiy.Gasto;
import com.myfinance.app.entitiy.Usuario;
import com.myfinance.app.enums.EnumTipoCategoria;
import com.myfinance.app.exception.RunTimeExceptionHandler;
import com.myfinance.app.mapper.GastoMapper;
import com.myfinance.app.repository.GastoRepository;
import com.myfinance.app.request.DataHoraRequest;
import com.myfinance.app.request.GastoRequest;
import com.myfinance.app.response.GastoResponse;
import com.myfinance.app.response.GastosListaResponse;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class GastoService {

	@Autowired
	private GastoRepository repository;

	@Autowired
	private GastoMapper mapper;

	@Autowired
	private UsuarioService usuarioService;

	public GastosListaResponse buscarGastosPorUsuario(Long idUsuario, Date dataInicio, Date dataFim) {

		if (dataFim == null) {
			dataFim = new Date();
		}
		// Se dataLimite for nulo, define como 30 dias atrás
		if (dataInicio == null) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -30);
			dataInicio = cal.getTime();
		}

		GastosListaResponse lista = new GastosListaResponse();
		Usuario usuario = usuarioService.buscarPorIdOuErro(idUsuario);
		lista.setRenda(usuario.getRenda());
		lista.setIdUsuario(idUsuario);
		lista.setNecessidades(
				repository.findTotalBySubcategoria(idUsuario, EnumTipoCategoria.NECESSIDADES, dataInicio, dataFim));
		lista.setDesejos(repository.findTotalBySubcategoria(idUsuario, EnumTipoCategoria.DESEJOS, dataInicio, dataFim));
		lista.setInvestimento_e_poupanca(repository.findTotalBySubcategoria(idUsuario,
				EnumTipoCategoria.INVESTIMENTO_E_POUPANCA, dataInicio, dataFim));
		return lista;

	}

	public void deletarGastosPorUsuario(Long idUsuario) {
		List<Long> IDs = repository.buscarIDdeGastosPorUsuario(idUsuario);
		repository.deleteAllByIdInBatch(IDs);

	}

	public List<GastoResponse> buscarTodos() {
		return mapper.toListGastosResponse(repository.findAll(Sort.by("id").descending()));
	}

	public GastoResponse buscar(Long id) {
		return mapper.toGastosResponse(repository.findById(id).orElse(null));
	}

	public void cadastrar(Long idUsuario, GastoRequest request) {
		Usuario usuario = usuarioService.buscarPorIdOuErro(idUsuario);
		if (request.getCategoria() == EnumTipoCategoria.ECONOMIA_PLANEJADA) {
			throw new RunTimeExceptionHandler("Categoria destinada apenas a IA");
		}
		request.setSubcategoria(request.getSubcategoria().toLowerCase());
		Gasto gasto = mapper.toGastosEntity(request);
		gasto.setUsuario(usuario);
		repository.save(gasto);
	}

	public void editar(Long id, GastoRequest request) {
		Gasto gasto = buscarPorIdOuErro(id);
		request.setSubcategoria(request.getSubcategoria().toLowerCase());
		Gasto novoGasto = mapper.toGastosEntity(request);
		BeanUtils.copyProperties(novoGasto, gasto, "id", "usuario");
		repository.save(gasto);
	}

	public List<GastoResponse> buscarPorFiltro(Long idUsuario, String valor, EnumTipoCategoria categoria,
			DataHoraRequest request) {
		Date dataFim = null;
		Date dataInicio = null;

		if (request != null) {
			dataInicio = request.getDataInicio();
			dataFim = request.getDataFinal();
		}
		if (dataFim == null) {
			dataFim = new Date();
		}

		// Se dataLimite for nulo, define como 30 dias atrás
		if (dataInicio == null) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -30);
			dataInicio = cal.getTime();
		}
		if (dataInicio.after(dataFim)) {
			throw new RunTimeExceptionHandler("Data final deve ser depois da data inicial");
		}
		if (categoria == null && valor != null && !valor.isBlank()) {
			log.info("entrou no sem cat");
			return mapper.toListGastosResponse(repository.buscarPorValor(idUsuario, valor, dataInicio, dataFim));
		}

		if (categoria != null && (valor == null || valor.isBlank())) {
			return mapper
					.toListGastosResponse(repository.buscarPorCategoria(idUsuario, categoria, dataInicio, dataFim));
		}

		if (categoria != null && valor != null) {

			log.info("entrou no com cat");
			return mapper.toListGastosResponse(
					repository.buscarPorValorECategoria(idUsuario, valor, categoria, dataInicio, dataFim));

		}
		return mapper.toListGastosResponse(repository.buscarPorUsuario(idUsuario, dataInicio, dataFim));

	}

	public Gasto buscarPorIdOuErro(Long id) {
		return repository.findById(id).orElseThrow(() -> new RunTimeExceptionHandler("Gasto inexistente"));
	}

	public void deletar(Long id) {
		repository.deleteById(id);

	}
}