package com.myfinance.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UsuarioResponse login(LoginRequest login) {
		Usuario usuario = repository.buscarPorEmail(login.getEmail());
		if (usuario == null || !passwordEncoder.matches(login.getSenha(), usuario.getSenha())) {
			throw new RunTimeExceptionHandler("Email ou senha incorretos");
		}
		return mapper.toUsuarioResponse(usuario);
	}

}