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

	@Query("FROM Gasto WHERE usuario.id = :id AND categoria = :categoria ORDER BY dataHora DESC")
	List<Gasto> buscarPorUsuarioETipo(Long id, String categoria);

	@Query("SELECT new com.myfinance.app.response.GastosAgrupadosResponse(g.subcategoria, SUM(g.valor)) FROM Gasto g "
			+ "WHERE usuario.id = :id AND categoria = :categoria GROUP BY g.subcategoria")
	List<GastosAgrupadosResponse> findTotalBySubcategoria(Long id, EnumTipoCategoria categoria);

	@Query("SELECT id FROM Gasto WHERE usuario.id = :id")
	List<Long> buscarIDdeGastosPorUsuario(Long id);

	@Query("FROM Gasto WHERE usuario.id = :id AND LOWER(subcategoria) LIKE LOWER(CONCAT('%', :valor, '%')) ORDER BY dataHora DESC, valor DESC")
	List<Gasto> buscarPorValor(Long id, String valor);

	@Query("FROM Gasto WHERE usuario.id = :id AND LOWER(subcategoria) LIKE LOWER(CONCAT('%', :valor, '%')) AND categoria = :categoria ORDER BY dataHora DESC, valor DESC")
	List<Gasto> buscarPorValorECategoria(Long id, String valor, EnumTipoCategoria categoria);

}
