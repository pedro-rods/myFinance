package controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import request.UsuarioRequest;
import response.JsonResponse;
import response.UsuarioResponse;
import service.UsuarioService;

@RestController
@RequestMapping("usuario")
@Tag(name = "Usuario Controller")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping(value = "/p")
	@Operation(summary = "Buscar todos os usuarioes")
	public ResponseEntity<Page<UsuarioResponse>> buscarTodos(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return new ResponseEntity<Page<UsuarioResponse>>(service.buscarTodos(page, size), null, HttpStatus.OK);
	}

	@GetMapping(value = "/p/pesquisar")
	@Operation(summary = "Buscar todos usuarioes pelo nome")
	public ResponseEntity<Page<UsuarioResponse>> buscarTodosPorNome(@RequestParam(value = "nome") String nome,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return new ResponseEntity<Page<UsuarioResponse>>(service.buscarTodosPorNome(nome, page, size), null,
				HttpStatus.OK);
	}

	@GetMapping(value = "/buscar/{id}")
	@Operation(summary = "Buscar usuario por ID")
	public UsuarioResponse buscarPorId(@PathVariable Long id) {
		return service.buscarPorId(id);
	}

	@PostMapping(value = "")
	@Operation(summary = "Cadastrar usuario")
	public UsuarioResponse cadastrar(@RequestBody @Valid UsuarioRequest usuarioRequest) {
		return service.cadastrar(usuarioRequest);
	}

	@PutMapping(value = "")
	@Operation(summary = "Alterar usuario")
	public UsuarioResponse alterar(@RequestParam Long id, @RequestBody @Valid UsuarioRequest usuarioRequest) {
		return service.alterar(id, usuarioRequest);
	}

	@DeleteMapping(value = "")
	@Operation(summary = "deletar usuario")
	public ResponseEntity<JsonResponse> deletar(@RequestParam Long id) {
		service.deletar(id);
		JsonResponse response = new JsonResponse();
		return new ResponseEntity<>(response, null, HttpStatus.NO_CONTENT);

	}
}
