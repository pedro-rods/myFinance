package com.myfinance.app.service;

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

	public GastosListaResponse buscarGastosPorUsuario(Long idUsuario) {
		GastosListaResponse lista = new GastosListaResponse();
		Usuario usuario = usuarioService.buscarPorIdOuErro(idUsuario);
		lista.setRenda(usuario.getRenda());
		lista.setIdUsuario(idUsuario);
		lista.setNecessidades(repository.findTotalBySubcategoria(idUsuario, EnumTipoCategoria.NECESSIDADE));
		lista.setDesejos(repository.findTotalBySubcategoria(idUsuario, EnumTipoCategoria.DESEJOS));
		lista.setInvestimento_e_poupanca(
				repository.findTotalBySubcategoria(idUsuario, EnumTipoCategoria.INVESTIMENTOS_POUPANCA));
		return lista;

	}

	public List<GastoResponse> buscarTodos() {
		return mapper.toListGastosResponse(repository.findAll(Sort.by("id").descending()));
	}

	public GastoResponse buscar(Long id) {
		return mapper.toGastosResponse(repository.findById(id).orElse(null));
	}

	public void cadastrar(GastoRequest request) {
		Usuario usuario = usuarioService.buscarPorIdOuErro(request.getIdUsuario());
		request.setSubcategoria(request.getSubcategoria().toLowerCase());
		Gasto gasto = mapper.toGastosEntity(request);
		gasto.setUsuario(usuario);
		repository.save(gasto);
	}

	public void editar(Long id, GastoRequest request) {
		Gasto gasto = buscarPorIdOuErro(id);
		request.setSubcategoria(request.getSubcategoria().toUpperCase());
		Gasto novoGasto = mapper.toGastosEntity(request);
		BeanUtils.copyProperties(novoGasto, gasto, "id", "usuario");
		repository.save(gasto);
	}

	public Gasto buscarPorIdOuErro(Long id) {
		return repository.findById(id).orElseThrow(() -> new RunTimeExceptionHandler("Gasto inexistente"));
	}

	public void deletar(Long id) {
		repository.deleteById(id);

	}
}