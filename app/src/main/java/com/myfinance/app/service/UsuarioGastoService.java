package com.myfinance.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class UsuarioGastoService {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private GastoService gastoService;

	public void deletar(Long id) { 
		gastoService.deletarGastosPorUsuario(id);
		usuarioService.deletar(id);

	}

}