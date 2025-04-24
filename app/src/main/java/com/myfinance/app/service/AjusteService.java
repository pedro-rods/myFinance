package com.myfinance.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfinance.app.mapper.GastoMapper;
import com.myfinance.app.repository.AjusteRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AjusteService {

	@Autowired
	private AjusteRepository repository;

	@Autowired
	private GastoMapper mapper;

	@Autowired
	private UsuarioService usuarioService;

}