package com.myfinance.app.response;

import com.myfinance.app.enums.EnumTipoCategoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiscoResponse {

	private EnumTipoCategoria categoria;

	private String subcategoria;

	private Double valor;

}
