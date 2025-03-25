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

	private Double renda;

	private List<GastosAgrupadosResponse> necessidades;

	private List<GastosAgrupadosResponse> desejos;

	private List<GastosAgrupadosResponse> investimento_e_poupanca;

}
