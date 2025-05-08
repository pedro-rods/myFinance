package com.myfinance.app.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.myfinance.app.entitiy.Usuario;
import com.myfinance.app.exception.RunTimeExceptionHandler;
import com.myfinance.app.mapper.UsuarioMapper;
import com.myfinance.app.repository.UsuarioRepository;
import com.myfinance.app.request.AlterarSenhaRequest;
import com.myfinance.app.request.LoginRequest;
import com.myfinance.app.request.UsuarioCadastroRequest;
import com.myfinance.app.request.UsuarioRequest;
import com.myfinance.app.response.UsuarioResponse;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private UsuarioMapper mapper;

	public UsuarioResponse buscarPorid(Long id) {
		return mapper.toUsuarioResponse(repository.findById(id).orElse(null));
	}

	public Usuario buscarPorIdOuErro(Long id) {
		return repository.findById(id).orElseThrow(() -> new RunTimeExceptionHandler("Usuario inexistente"));
	}

	public Page<UsuarioResponse> buscarTodos(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return mapper.toPageUsuarioResponse(repository.findAll(pageRequest));
	}

	public List<Usuario> buscarTodos() {
		return repository.findAll();
	}

	public void cadastrar(@Valid UsuarioCadastroRequest request) {
		Usuario usuario = mapper.toUsuarioEntity(request);
		Usuario obj = buscarPorEmail(usuario.getEmail());
		if (obj == null) {
			repository.save(usuario);
		} else {
			throw new RunTimeExceptionHandler("Email já cadastrado");
		}
	}

	public Usuario buscarPorEmail(String email) {
		return repository.buscarPorEmail(email);
	}

	public void atualizarUsuario(Long id, UsuarioRequest request) {
		Usuario usuarioNovo = mapper.toUsuarioEntity(request);
		Usuario usuario = buscarPorIdOuErro(id);
		BeanUtils.copyProperties(usuarioNovo, usuario, "id", "senha", "email");
		repository.save(usuario);
	}

	public void alterarEmail(Long id, LoginRequest request) {
		String email = request.getEmail().toLowerCase();
		Usuario usuario = buscarPorIdOuErro(id);

		Usuario user2 = repository.login(usuario.getEmail(), request.getSenha());

		if (user2 == null) {
			throw new RunTimeExceptionHandler("Senha incorreta");
		}

		if (usuario.getEmail().toLowerCase() == email) {

			throw new RunTimeExceptionHandler("este email já é o seu!");
		}
		Usuario flag = buscarPorEmail(email.toLowerCase());
		if (flag != null) {
			throw new RunTimeExceptionHandler("email já cadastrado no banco de dados");
		}
		usuario.setEmail(email);
		repository.save(usuario);
	}

	public void alterarSenha(Long id, AlterarSenhaRequest request) {

		String email = request.getEmail();
		String senhaAntiga = request.getSenhaAntiga();
		String senhaNova = request.getSenhaNova();
		Usuario usuario = repository.login(email, senhaAntiga);
		if (usuario == null) {
			throw new RunTimeExceptionHandler("email ou senha incorretos");
		}
		if (usuario.getId() != id) {

			throw new RunTimeExceptionHandler("usuario não correspondente");
		}
		usuario.setSenha(senhaNova);
		repository.save(usuario);

	}

	public void deletar(Long id) {
		repository.deleteById(id);
	}

	public Page<UsuarioResponse> buscarTodosPorNome(String nome, int page, int size) {

		return null;
	}

}