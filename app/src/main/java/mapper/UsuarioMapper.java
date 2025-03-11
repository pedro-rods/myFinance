package mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import entity.Usuario;
import request.UsuarioRequest;
import response.UsuarioResponse;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

	UsuarioResponse toUsuarioResponse(Usuario caminhada);

	default Page<UsuarioResponse> toPageUsuarioResponse(Page<Usuario> page) {
		return page.map(this::toUsuarioResponse);
	}

	@Mapping(target = "id", ignore = true)
	Usuario toUsuarioEntity(UsuarioRequest caminhadaRequest);

	List<UsuarioResponse> toListUsuarioResponse(List<Usuario> list);

}