package com.myfinance.app.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanoResponse {

	private Long id;

	private Long idUsuario;

	List<AjusteResponse> ajustes;

}
