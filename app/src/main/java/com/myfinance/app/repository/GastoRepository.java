package com.myfinance.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myfinance.app.entitiy.Gasto;
import com.myfinance.app.enums.EnumTipoCategoria;
import com.myfinance.app.response.GastosAgrupadosResponse;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {

	@Query("FROM Gasto WHERE usuario.id = :id AND categoria = :categoria ORDER BY dataHora")
	List<Gasto> buscarPorUsuarioETipo(Long id, String categoria);

	@Query("SELECT new com.myfinance.app.response.GastosAgrupadosResponse(g.subcategoria, SUM(g.valor)) FROM Gasto g "
			+ "WHERE usuario.id = :id AND categoria = :categoria GROUP BY g.subcategoria")
	List<GastosAgrupadosResponse> findTotalBySubcategoria(Long id, EnumTipoCategoria categoria);

}
