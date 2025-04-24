package com.myfinance.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum EnumTipoCategoria {

	NECESSIDADES("NECESSIDADES"), INVESTIMENTO_E_POUPANCA("INVESTIMENTOS_E_POUPANCA"), DESEJOS("DESEJOS");

	@Getter
	@Setter
	private String id;

	public static EnumTipoCategoria fromString(String categoria) {
		switch (categoria.toLowerCase()) {
		case "necessidade":
			return EnumTipoCategoria.NECESSIDADES;
		case "necessidades":
			return EnumTipoCategoria.NECESSIDADES;
		case "desejos":
			return EnumTipoCategoria.DESEJOS;
		case "investimento_poupanca":
			return EnumTipoCategoria.INVESTIMENTO_E_POUPANCA;
		case "investimento_e_poupanca":
			return EnumTipoCategoria.INVESTIMENTO_E_POUPANCA;
		default:
			throw new IllegalArgumentException("Categoria desconhecida: " + categoria);
		}
	}

}
