package service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import entity.Usuario;
import exception.RunTimeExceptionHandler;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import repository.UsuarioRepository;
import response.UsuarioResponse;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Usuario buscarPorid(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Usuario buscarPorIdOuErro(Long id) {
		return repository.findById(id).orElseThrow(() -> new RunTimeExceptionHandler("Usuario inexistente"));
	}

	public Usuario cadastrarUsuario(@Valid Usuario usuario) {
		Usuario obj = buscarPorEmail(usuario.getEmail());
		if (obj == null) {
			return repository.save(usuario);
		} else {
			throw new RunTimeExceptionHandler("Email já cadastrado");
		}
	}

	public Usuario buscarPorEmail(String email) {
		return repository.buscarPorEmail(email);
	}

	public Usuario atualizarUsuario(Long id, Usuario usuarioNovo) {
		Usuario usuario = buscarPorIdOuErro(id);
		BeanUtils.copyProperties(usuarioNovo, usuario, "id", "senha", "primeiroAcesso", "permissao");
		return repository.save(usuario);
	}

	public void deletarUsuario(Long id) {
		repository.deleteById(id);

	}

	public Page<UsuarioResponse> buscarTodosPorNome(String nome, int page, int size) {
		
		return null;
	}
}
