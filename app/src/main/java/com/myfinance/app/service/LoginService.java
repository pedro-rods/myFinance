package com.myfinance.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfinance.app.entitiy.Usuario;
import com.myfinance.app.exception.RunTimeExceptionHandler;
import com.myfinance.app.mapper.UsuarioMapper;
import com.myfinance.app.repository.UsuarioRepository;
import com.myfinance.app.request.LoginRequest;
import com.myfinance.app.response.UsuarioResponse;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class LoginService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private UsuarioMapper mapper;

	public UsuarioResponse login(LoginRequest login) {
		Usuario usuario = repository.login(login.getEmail(), login.getSenha());
		if (usuario == null) {
			throw new RunTimeExceptionHandler("Email ou senha incooretos");
		}
		return mapper.toUsuarioResponse(usuario);
	}

}