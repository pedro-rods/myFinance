package com.myfinance.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfinance.app.entitiy.Gasto;
import com.myfinance.app.entitiy.Usuario;
import com.myfinance.app.exception.RunTimeExceptionHandler;
import com.myfinance.app.mapper.GastoMapper;
import com.myfinance.app.repository.GastoRepository;
import com.myfinance.app.request.GastosRequest;
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
		lista.setIdUsuario(idUsuario);
		lista.setNECESSARIO(repository.findTotalBySubcategoria(idUsuario, "NECESSARIO"));
		lista.setFUTIL(repository.findTotalBySubcategoria(idUsuario, "FUTIL"));
		return lista;

	}

	public void cadastrar(GastosRequest request) {
		Usuario usuario = usuarioService.buscarPorIdOuErro(request.getIdUsuario());
		request.setSubcategoria(request.getSubcategoria().toUpperCase());
		Gasto gasto = mapper.toGastosEntity(request);
		gasto.setUsuario(usuario);
		repository.save(gasto);
	}

	public Gasto buscarPorIdOuErro(Long id) {
		return repository.findById(id).orElseThrow(() -> new RunTimeExceptionHandler("gasto inexistente"));
	}
}