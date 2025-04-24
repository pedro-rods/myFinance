package com.myfinance.app.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myfinance.app.enums.EnumTipoCategoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GastoResponse {

	private Long id;

	private EnumTipoCategoria categoria;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy ", timezone = "America/Sao_Paulo")
	private Date dataHora;

	private String subcategoria;

	private Double valor;

}
