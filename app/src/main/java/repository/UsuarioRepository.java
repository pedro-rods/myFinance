package repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("FROM Usuario u WHERE u.email = :email")
	Usuario buscarPorEmail(String email);

	@Query("FROM Usuario WHERE UPPER(nome) LIKE CONCAT('%', :nome, '%')")
	Page<Usuario> buscarTodosPorNome(String nome, Pageable pageable);

}
