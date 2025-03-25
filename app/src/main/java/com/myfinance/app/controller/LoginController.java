package com.myfinance.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myfinance.app.request.LoginRequest;
import com.myfinance.app.response.UsuarioResponse;
import com.myfinance.app.service.LoginService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("login")
@Tag(name = "Login Controller")
public class LoginController {

	@Autowired
	private LoginService service;

	@PostMapping(value = "")
	@Operation(summary = "login")
	public ResponseEntity<UsuarioResponse> login(@RequestBody @Valid LoginRequest login) {
		return new ResponseEntity<>(service.login(login), null, HttpStatus.OK);
	}

}