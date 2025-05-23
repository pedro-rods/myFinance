package com.myfinance.app.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import com.myfinance.app.entitiy.Usuario;
import com.myfinance.app.request.UsuarioCadastroRequest;
import com.myfinance.app.request.UsuarioRequest;
import com.myfinance.app.response.UsuarioResponse;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

	UsuarioResponse toUsuarioResponse(Usuario caminhada);

	default Page<UsuarioResponse> toPageUsuarioResponse(Page<Usuario> page) {
		return page.map(this::toUsuarioResponse);
	}

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "renda", ignore = true)
	Usuario toUsuarioEntity(UsuarioCadastroRequest caminhadaRequest);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "senha", ignore = true)
	Usuario toUsuarioEntity(UsuarioRequest caminhadaRequest);

	List<UsuarioResponse> toListUsuarioResponse(List<Usuario> list);

}
