package com.myfinance.app.response;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	List<RiscoResponse> riscos;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy ", timezone = "America/Sao_Paulo")
	private Date dataAlteracao;

}
