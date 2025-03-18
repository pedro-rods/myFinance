package com.myfinance.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myfinance.app.entitiy.PlanoFinanceiro;

@Repository
public interface PlanoRepository extends JpaRepository<PlanoFinanceiro, Long> {

	@Query("SELECT p FROM PlanoFinanceiro p JOIN FETCH p.ajustes a WHERE p.usuario.id = :id ORDER BY a.categoria DESC")
	PlanoFinanceiro buscarporUsuario(Long id);
}
