package com.myfinance.app.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.myfinance.app.request.DataHoraRequest;
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
	public ResponseEntity<GastosListaResponse> buscarPorUsuario(@RequestParam Long idUsuario,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicial,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFinal) {

		return new ResponseEntity<>(service.buscarGastosPorUsuario(idUsuario, dataInicial, dataFinal), null,
				HttpStatus.OK);
	}

	@GetMapping(value = "/buscar/{id}")
	@Operation(summary = "Buscar Gasto por ID")
	public ResponseEntity<GastoResponse> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<>(service.buscar(id), null, HttpStatus.OK);
	}

	@PostMapping(value = "/cadastrar")
	@Operation(summary = "Cadastrar gasto")
	public ResponseEntity<JsonResponse> cadastrar(@RequestParam Long idUsuario,
			@RequestBody @Valid GastoRequest request) {
		service.cadastrar(idUsuario, request);
		JsonResponse response = new JsonResponse();
		return new ResponseEntity<>(response, null, HttpStatus.OK);
	}

	@PutMapping(value = "/alterar")
	@Operation(summary = "Alterar gasto")
	public ResponseEntity<JsonResponse> alterar(@RequestParam Long id, @RequestBody @Valid GastoRequest request) {
		service.editar(id, request);
		JsonResponse response = new JsonResponse();
		return new ResponseEntity<>(response, null, HttpStatus.OK);
	}

	@DeleteMapping(value = "/deletar")
	@Operation(summary = "deletar gasto")
	public ResponseEntity<JsonResponse> deletar(@RequestParam Long id) {
		service.deletar(id);
		JsonResponse response = new JsonResponse();
		return new ResponseEntity<>(response, null, HttpStatus.OK);
	}

	@PostMapping(value = "/buscar/categoria")
	@Operation(summary = "buscar por valor. O parâmetro Valor (opcional) é a string da subcategoria. Categoria (também opcional) é autoexplicativo")
	public List<GastoResponse> buscarPorFiltros(@RequestParam Long idUsuario,
			@RequestParam(required = false) String valor, @RequestParam(required = false) EnumTipoCategoria categoria,
			@RequestBody DataHoraRequest request) {
		return service.buscarPorFiltro(idUsuario, valor, categoria, request);
	}
}