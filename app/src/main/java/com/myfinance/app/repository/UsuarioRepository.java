package com.myfinance.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myfinance.app.entitiy.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("FROM Usuario u WHERE UPPER(u.email) = UPPER(:email)")
	Usuario buscarPorEmail(String email);

	@Query("FROM Usuario WHERE UPPER(email) = UPPER(:email) AND senha = :senha")
	Usuario login(String email, String senha);

}
