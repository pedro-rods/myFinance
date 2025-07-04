package com.myfinance.app.repository;

import java.util.Date;
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
			+ "WHERE usuario.id = :id AND categoria = :categoria AND dataHora BETWEEN :dataInicio AND :dataFim GROUP BY g.subcategoria")
	List<GastosAgrupadosResponse> findTotalBySubcategoria(Long id, EnumTipoCategoria categoria, Date dataInicio,
			Date dataFim);

	@Query("SELECT SUM(g.valor) FROM Gasto g WHERE g.usuario.id = :usuarioId")
	Double somarGastosPorUsuario(Long usuarioId);

	@Query("SELECT g.id FROM Gasto g WHERE usuario.id = :id")
	List<Long> buscarIDdeGastosPorUsuario(Long id);

	@Query(" FROM Gasto	WHERE usuario.id=:idUsuario " + "AND (:valor IS NULL "
			+ "OR LOWER(subcategoria) LIKE LOWER(CONCAT('%', :valor, '%'))) "
			+ "AND dataHora BETWEEN :dataInicio AND :dataFim  " + "ORDER BY dataHora DESC, valor DESC")

	List<Gasto> buscarPorValor(Long idUsuario, String valor, Date dataInicio, Date dataFim);

	@Query("FROM Gasto WHERE usuario.id = :idUsuario AND LOWER(subcategoria) LIKE LOWER(CONCAT('%', :valor, '%')) AND categoria = :categoria "
			+ "AND dataHora BETWEEN :dataInicio AND :dataFim ORDER BY dataHora DESC, valor DESC")
	List<Gasto> buscarPorValorECategoria(Long idUsuario, String valor, EnumTipoCategoria categoria, Date dataInicio,
			Date dataFim);

	@Query("FROM Gasto WHERE usuario.id = :idUsuario AND categoria = :categoria "
			+ "AND dataHora BETWEEN :dataInicio AND :dataFim ORDER BY dataHora DESC, valor DESC")
	List<Gasto> buscarPorCategoria(Long idUsuario, EnumTipoCategoria categoria, Date dataInicio, Date dataFim);

	@Query("FROM Gasto WHERE usuario.id = :idUsuario "
			+ "AND dataHora BETWEEN :dataInicio AND :dataFim ORDER BY dataHora DESC, valor DESC")
	List<Gasto> buscarPorUsuario(Long idUsuario, Date dataInicio, Date dataFim);

}
