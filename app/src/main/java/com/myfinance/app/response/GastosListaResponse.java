package com.myfinance.app.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GastosListaResponse {

	private Long idUsuario;

	private List<GastosAgrupadosResponse> NECESSARIO;

	private List<GastosAgrupadosResponse> FUTIL;

}
