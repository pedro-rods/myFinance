package com.myfinance.app.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import com.myfinance.app.entitiy.Gasto;
import com.myfinance.app.request.GastosRequest;
import com.myfinance.app.response.GastosResponse;

@Mapper(componentModel = "spring")
public interface GastoMapper {

	@Mapping(source = "usuario.id", target = "idUsuario")
	GastosResponse toGastosResponse(Gasto gastos);

	default Page<GastosResponse> toPageGastosResponse(Page<Gasto> page) {
		return page.map(this::toGastosResponse);
	}

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "usuario", ignore = true)
	Gasto toGastosEntity(GastosRequest gastosRequest);

	List<GastosResponse> toListGastosResponse(List<Gasto> list);

}
