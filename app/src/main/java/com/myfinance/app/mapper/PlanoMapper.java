package com.myfinance.app.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.myfinance.app.entitiy.Ajuste;
import com.myfinance.app.entitiy.PlanoFinanceiro;
import com.myfinance.app.response.AjusteResponse;
import com.myfinance.app.response.PlanoResponse;

@Mapper(componentModel = "spring")
public interface PlanoMapper {

	AjusteResponse toResponse(Ajuste ajuste);

	List<AjusteResponse> toListResponse(List<Ajuste> list);

	@Mapping(source = "usuario.id", target = "idUsuario")
	PlanoResponse toResponse(PlanoFinanceiro plano);

}
