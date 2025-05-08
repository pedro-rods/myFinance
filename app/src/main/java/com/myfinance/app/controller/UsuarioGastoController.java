package com.myfinance.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myfinance.app.response.JsonResponse;
import com.myfinance.app.service.UsuarioGastoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("usuario_gasto")
@Tag(name = "Usuario_Gasto Controller")
@RequiredArgsConstructor
public class UsuarioGastoController {

	private final UsuarioGastoService service;

	@DeleteMapping(value = "/delete")
	@Operation(summary = "deletar usuario e seus gastos")
	public ResponseEntity<JsonResponse> deletar(@RequestParam Long id) {
		service.deletar(id);
		JsonResponse response = new JsonResponse();
		return new ResponseEntity<>(response, null, HttpStatus.OK);

	}
}