package com.myfinance.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myfinance.app.response.JsonResponse;
import com.myfinance.app.response.PlanoResponse;
import com.myfinance.app.service.PlanoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("plano")
@Tag(name = "Plano Financeiro Controller")
public class PlanoController {

	@Autowired
	private PlanoService service;

	@GetMapping(value = "/usuario")
	@Operation(summary = "Buscar gastos por usuarios")
	public ResponseEntity<PlanoResponse> buscarPorUsuario(@RequestParam Long idUsuario) {

		return new ResponseEntity<>(service.buscarPorUsuario(idUsuario), null, HttpStatus.OK);
	}

	@PostMapping(value = "")
	@Operation(summary = "gerar plano")
	public ResponseEntity<JsonResponse> gerarPlano(@RequestParam Long idUsuario) {
		service.gerarPlano(idUsuario);
		JsonResponse response = new JsonResponse();
		return new ResponseEntity<>(response, null, HttpStatus.OK);
	}

}