package com.myfinance.app.controller;

import org.springframework.data.domain.Page;
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

import com.myfinance.app.request.AlterarSenhaRequest;
import com.myfinance.app.request.LoginRequest;
import com.myfinance.app.request.UsuarioCadastroRequest;
import com.myfinance.app.request.UsuarioRequest;
import com.myfinance.app.response.JsonResponse;
import com.myfinance.app.response.UsuarioResponse;
import com.myfinance.app.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("usuario")
@Tag(name = "Usuario Controller")
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioService service;

	@GetMapping(value = "/p")
	@Operation(summary = "Buscar todos os usuarios")
	public ResponseEntity<Page<UsuarioResponse>> buscarTodos(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return new ResponseEntity<Page<UsuarioResponse>>(service.buscarTodos(page, size), null, HttpStatus.OK);
	}

	@GetMapping(value = "/p/pesquisar")
	@Operation(summary = "Buscar todos usuarios pelo nome")
	public ResponseEntity<Page<UsuarioResponse>> buscarTodosPorNome(@RequestParam(value = "nome") String nome,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return new ResponseEntity<Page<UsuarioResponse>>(service.buscarTodosPorNome(nome, page, size), null,
				HttpStatus.OK);
	}

	@GetMapping(value = "/buscar/{id}")
	@Operation(summary = "Buscar usuario por ID")
	public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {

		return new ResponseEntity<>(service.buscarPorid(id), null, HttpStatus.OK);
	}

	@PostMapping(value = "/cadastrar")
	@Operation(summary = "Cadastrar usuario")
	public ResponseEntity<JsonResponse> cadastrar(@RequestBody @Valid UsuarioCadastroRequest request) {
		service.cadastrar(request);
		JsonResponse response = new JsonResponse();
		return new ResponseEntity<>(response, null, HttpStatus.OK);
	}

	@PutMapping(value = "/alterar")
	@Operation(summary = "Alterar usuario")
	public ResponseEntity<JsonResponse> alterar(@RequestParam Long id, @RequestBody @Valid UsuarioRequest request) {

		JsonResponse response = new JsonResponse();
		service.atualizarUsuario(id, request);
		return new ResponseEntity<>(response, null, HttpStatus.OK);
	}

	@PutMapping(value = "/alterar/email")
	@Operation(summary = "Alterar email")
	public ResponseEntity<JsonResponse> alterarEmail(@RequestParam Long id, @RequestBody LoginRequest request) {

		JsonResponse response = new JsonResponse();
		service.alterarEmail(id, request);
		return new ResponseEntity<>(response, null, HttpStatus.OK);
	}

	@PutMapping(value = "/alterar/senha")
	@Operation(summary = "Alterar senha")
	public ResponseEntity<JsonResponse> alterarSenha(@RequestParam Long id, @RequestBody AlterarSenhaRequest request) {

		JsonResponse response = new JsonResponse();
		service.alterarSenha(id, request);
		return new ResponseEntity<>(response, null, HttpStatus.OK);
	}


}