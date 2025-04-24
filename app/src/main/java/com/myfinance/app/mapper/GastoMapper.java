package com.myfinance.app.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import com.myfinance.app.entitiy.Gasto;
import com.myfinance.app.request.GastoRequest;
import com.myfinance.app.response.GastoResponse;

@Mapper(componentModel = "spring")
public interface GastoMapper {

	GastoResponse toGastosResponse(Gasto gastos);

	default Page<GastoResponse> toPageGastosResponse(Page<Gasto> page) {
		return page.map(this::toGastosResponse);
	}

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "usuario", ignore = true)
	Gasto toGastosEntity(GastoRequest gastosRequest);

	List<GastoResponse> toListGastosResponse(List<Gasto> list);

}
