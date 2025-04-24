package com.myfinance.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myfinance.app.enums.EnumTipoCategoria;
import com.myfinance.app.request.GastoRequest;
import com.myfinance.app.response.GastoResponse;
import com.myfinance.app.response.GastosListaResponse;
import com.myfinance.app.response.JsonResponse;
import com.myfinance.app.service.GastoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("gasto")
@Tag(name = "Gasto Controller")
public class GastoController {

	@Autowired
	private GastoService service;

	@GetMapping(value = "/usuario")
	@Operation(summary = "Buscar gastos por usuarios")
	public ResponseEntity<GastosListaResponse> buscarPorUsuario(@RequestParam Long idUsuario) {

		return new ResponseEntity<>(service.buscarGastosPorUsuario(idUsuario), null, HttpStatus.OK);
	}

	@GetMapping(value = "/buscar/{id}")
	@Operation(summary = "Buscar Gasto por ID")
	public ResponseEntity<GastoResponse> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<>(service.buscar(id), null, HttpStatus.OK);
	}

	@GetMapping(value = "/usuario/all")
	@Operation(summary = "Buscar todos gastos por usuarios")
	public ResponseEntity<List<GastoResponse>> buscarTodosPorUsuario() {

		return new ResponseEntity<>(service.buscarTodos(), null, HttpStatus.OK);
	}

	@PostMapping(value = "")
	@Operation(summary = "Cadastrar gasto")
	public ResponseEntity<JsonResponse> cadastrar(@RequestBody @Valid GastoRequest request) {
		service.cadastrar(request);
		JsonResponse response = new JsonResponse();
		return new ResponseEntity<>(response, null, HttpStatus.OK);
	}

	@PutMapping(value = "")
	@Operation(summary = "Alterar gasto")
	public ResponseEntity<JsonResponse> alterar(@RequestParam Long id, @RequestBody @Valid GastoRequest request) {
		service.editar(id, request);
		JsonResponse response = new JsonResponse();
		return new ResponseEntity<>(response, null, HttpStatus.OK);
	}

	@DeleteMapping(value = "")
	@Operation(summary = "deletar gasto")
	public ResponseEntity<JsonResponse> deletar(@RequestParam Long id) {
		service.deletar(id);
		JsonResponse response = new JsonResponse();
		return new ResponseEntity<>(response, null, HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/buscar/categoria")
	public List<GastoResponse> buscarPorFiltros(@RequestParam Long id, @RequestParam String valor,
			@RequestParam(required = false) EnumTipoCategoria categoria) {
		return service.buscarPorFiltro(id, valor, categoria);
	}
}